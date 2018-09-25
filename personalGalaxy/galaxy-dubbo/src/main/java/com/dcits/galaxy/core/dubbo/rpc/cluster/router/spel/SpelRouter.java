package com.dcits.galaxy.core.dubbo.rpc.cluster.router.spel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.Router;
import com.alibaba.dubbo.rpc.cluster.router.condition.ConditionRouter;

public class SpelRouter implements Router,Comparable<Router> {

	private static final Logger logger = LoggerFactory.getLogger(SpelRouter.class);

    private final URL url;
    
    private final int priority;

    private final boolean force;

    private final Expression whenExp;
    
    private final Expression thenExp;

    public SpelRouter(URL url) {
        this.url = url;
        this.priority = url.getParameter(Constants.PRIORITY_KEY, 0);
        this.force = url.getParameter(Constants.FORCE_KEY, false);
        try {
            String rule = url.getParameterAndDecoded(Constants.RULE_KEY);
            if (rule == null || rule.trim().length() == 0) {
                throw new IllegalArgumentException("Illegal spel route rule!");
            }
            int i = rule.indexOf("=>");
            String whenRule = i < 0 ? null : rule.substring(0, i).trim();
            String thenRule = i < 0 ? rule.trim() : rule.substring(i + 2).trim();
            
            ExpressionParser parser = new SpelExpressionParser();
            this.whenExp = StringUtils.isBlank(whenRule) ? null : parser.parseExpression(whenRule);
            this.thenExp = StringUtils.isBlank(thenRule) ? null : parser.parseExpression(thenRule);
        } catch (ParseException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public <T> List<Invoker<T>> route(List<Invoker<T>> invokers, URL url, Invocation invocation)
            throws RpcException {
        if (invokers == null || invokers.size() == 0) {
            return invokers;
        }
        try {
        	
        	EvaluationContext context = new StandardEvaluationContext();
        	context.setVariable("arguments", invocation.getArguments());
        	context.setVariable("method", invocation.getMethodName());
        	
            if (whenExp != null) {
            	context.setVariable("consumer", url.toMap());
            	if( !whenExp.getValue(context, boolean.class) ){
            		return invokers;
            	}
            }
            
            List<Invoker<T>> result = new ArrayList<Invoker<T>>();
            if (thenExp == null) {
            	logger.warn("The current consumer in the service blacklist. consumer: " + NetUtils.getLocalHost() + ", service: " + url.getServiceKey());
                return result;
            }
            for (Invoker<T> invoker : invokers) {
            	URL invokeUrl = invoker.getUrl();
            	context.setVariable("provider", invokeUrl.toMap());
            	if ( thenExp.getValue(context, boolean.class) ) {
            		result.add(invoker);
            	}
            }
            if (result.size() > 0) {
                return result;
            } else if (force) {
            	logger.warn("The route result is empty and force execute. consumer: " + NetUtils.getLocalHost() + ", service: " + url.getServiceKey() + ", router: " + url.getParameterAndDecoded(Constants.RULE_KEY));
            	return result;
            }
        } catch (Throwable t) {
            logger.error("Failed to execute condition router rule: " + getUrl() + ", invokers: " + invokers + ", cause: " + t.getMessage(), t);
        }
        return invokers;
    }

    public URL getUrl() {
        return url;
    }

    public int compareTo(Router o) {
        if (o == null || o.getClass() != ConditionRouter.class) {
            return 1;
        }
        SpelRouter c = (SpelRouter) o;
        return this.priority == c.priority ? url.toFullString().compareTo(c.url.toFullString()) : (this.priority > c.priority ? 1 : -1);
    }
}