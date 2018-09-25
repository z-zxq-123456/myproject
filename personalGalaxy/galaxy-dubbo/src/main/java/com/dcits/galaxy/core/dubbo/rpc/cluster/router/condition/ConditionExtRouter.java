package com.dcits.galaxy.core.dubbo.rpc.cluster.router.condition;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.common.utils.UrlUtils;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.Router;
import com.alibaba.dubbo.rpc.cluster.router.condition.ConditionRouter;

/**
 * 对dubbo的ConditionRule的扩展，添加基于Spring EL的方法调用参行路由功能
 * @author yin.weicai
 */
public class ConditionExtRouter implements Router,Comparable<Router> {
	
	public static final String EXT_RULE_KEY = "extrule";

	private static final Logger logger = LoggerFactory.getLogger(ConditionExtRouter.class);

    private final URL url;
    
    private final int priority;

    private final boolean force;
    
    private final String extRule;

    private final Map<String, MatchPair> whenCondition;
    
    private final Map<String, MatchPair> thenCondition;

    public ConditionExtRouter(URL url) {
        this.url = url;
        this.priority = url.getParameter(Constants.PRIORITY_KEY, 0);
        this.force = url.getParameter(Constants.FORCE_KEY, false);
        try {
        	
        	//扩展的路由
        	extRule = url.getParameterAndDecoded( EXT_RULE_KEY );
        	
            String rule = url.getParameterAndDecoded(Constants.RULE_KEY);
            if (rule == null || rule.trim().length() == 0) {
                throw new IllegalArgumentException("Illegal route rule!");
            }
            rule = rule.replace("consumer.", "").replace("provider.", "");
            int i = rule.indexOf("=>");
            String whenRule = i < 0 ? null : rule.substring(0, i).trim();
            String thenRule = i < 0 ? rule.trim() : rule.substring(i + 2).trim();
            Map<String, MatchPair> when = StringUtils.isBlank(whenRule) || "true".equals(whenRule) ? new HashMap<String, MatchPair>() : parseRule(whenRule);
            Map<String, MatchPair> then = StringUtils.isBlank(thenRule) || "false".equals(thenRule) ? null : parseRule(thenRule);
            // NOTE: When条件是允许为空的，外部业务来保证类似的约束条件
            this.whenCondition = when;
            this.thenCondition = then;
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
            if (! matchWhen(url)) {
                return invokers;
            }
            
            //基于方法调用参数的路由
            Object[] arguments = invocation.getArguments();
            if( extRule != null && arguments.length > 0){
            	EvaluationContext context = new StandardEvaluationContext();
            	context.setVariable("arguments", arguments );
            	ExpressionParser parser = new SpelExpressionParser();
        		Expression exp = parser.parseExpression( extRule );
        		boolean isMatch = exp.getValue(context, boolean.class);
        		if( !isMatch ){
        			return invokers;
        		}
            }
            
            List<Invoker<T>> result = new ArrayList<Invoker<T>>();
            if (thenCondition == null) {
            	logger.warn("The current consumer in the service blacklist. consumer: " + NetUtils.getLocalHost() + ", service: " + url.getServiceKey());
                return result;
            }
            for (Invoker<T> invoker : invokers) {
                if (matchThen(invoker.getUrl(), url)) {
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
        ConditionExtRouter c = (ConditionExtRouter) o;
        return this.priority == c.priority ? url.toFullString().compareTo(c.url.toFullString()) : (this.priority > c.priority ? 1 : -1);
    }

    public boolean matchWhen(URL url) {
        return matchCondition(whenCondition, url, null);
    }

    public boolean matchThen(URL url, URL param) {
        return thenCondition != null && matchCondition(thenCondition, url, param);
    }
    
    private boolean matchCondition(Map<String, MatchPair> condition, URL url, URL param) {
        Map<String, String> sample = url.toMap();
        for (Map.Entry<String, String> entry : sample.entrySet()) {
            String key = entry.getKey();
            MatchPair pair = condition.get(key);
            if (pair != null && ! pair.isMatch(entry.getValue(), param)) {
                return false;
            }
        }
        return true;
    }
    
    private static Pattern ROUTE_PATTERN = Pattern.compile("([&!=,]*)\\s*([^&!=,\\s]+)");
    
    public static Map<String, MatchPair> parseRule(String rule)
            throws ParseException {
        Map<String, MatchPair> condition = new HashMap<String, MatchPair>();
        if(StringUtils.isBlank(rule)) {
            return condition;
        }        
        // 匹配或不匹配Key-Value对
        MatchPair pair = null;
        // 多个Value值
        Set<String> values = null;
        final Matcher matcher = ROUTE_PATTERN.matcher(rule);
        while (matcher.find()) { // 逐个匹配
            String separator = matcher.group(1);
            String content = matcher.group(2);
            // 表达式开始
            if (separator == null || separator.length() == 0) {
                pair = new MatchPair();
                condition.put(content, pair);
            }
            // KV开始
            else if ("&".equals(separator)) {
                if (condition.get(content) == null) {
                    pair = new MatchPair();
                    condition.put(content, pair);
                } else {
                    condition.put(content, pair);
                }
            }
            // KV的Value部分开始
            else if ("=".equals(separator)) {
                if (pair == null)
                    throw new ParseException("Illegal route rule \""
                            + rule + "\", The error char '" + separator
                            + "' at index " + matcher.start() + " before \""
                            + content + "\".", matcher.start());

                values = pair.matches;
                values.add(content);
            }
            // KV的Value部分开始
            else if ("!=".equals(separator)) {
                if (pair == null)
                    throw new ParseException("Illegal route rule \""
                            + rule + "\", The error char '" + separator
                            + "' at index " + matcher.start() + " before \""
                            + content + "\".", matcher.start());

                values = pair.mismatches;
                values.add(content);
            }
            // KV的Value部分的多个条目
            else if (",".equals(separator)) { // 如果为逗号表示
                if (values == null || values.size() == 0)
                    throw new ParseException("Illegal route rule \""
                            + rule + "\", The error char '" + separator
                            + "' at index " + matcher.start() + " before \""
                            + content + "\".", matcher.start());
                values.add(content);
            } else {
                throw new ParseException("Illegal route rule \"" + rule
                        + "\", The error char '" + separator + "' at index "
                        + matcher.start() + " before \"" + content + "\".", matcher.start());
            }
        }
        return condition;
    }
    
    private static final class MatchPair {
        final Set<String> matches = new HashSet<String>();
        final Set<String> mismatches = new HashSet<String>();
        public boolean isMatch(String value, URL param) {
            for (String match : matches) {
                if (! UrlUtils.isMatchGlobPattern(match, value, param)) {
                    return false;
                }
            }
            for (String mismatch : mismatches) {
                if (UrlUtils.isMatchGlobPattern(mismatch, value, param)) {
                    return false;
                }
            }
            return true;
        }
    }
}