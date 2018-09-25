package com.dcits.galaxy.dal.mybatis.router.index.rule;

import java.util.ArrayList;
import java.util.List;

import com.dcits.galaxy.dal.mybatis.router.index.expr.IndexExpression;


/**
 * 封装具体的路由规则
 * 
 * @author huang.zhe
 */
public class IndexRoute {

	private String sqlmap;
    private IndexExpression expression;
    private List<String> shards;

    public IndexRoute(String sqlmap, IndexExpression expression, List<String> shards) {
        this.sqlmap = sqlmap;
        this.expression = expression;
        this.shards = shards;
    }

    /**
     * 根据所传入的sqlmap与argument判断是否符合此条规则
     * @param sqlmap 所执行的sql语句所对应的sqlmap或namespace
     * @param argument 
     * @return 若符合此规则，则返回true
     */
    public List<String> apply(String sqlmap, Object argument) {
        if (this.sqlmap == null) return null;
        if (!this.sqlmap.equals(sqlmap)) return null;
        if (expression == null) return shards;
        if (argument != null){
        	List<Integer> ids=expression.apply(argument);
			if (ids != null) {
				if(ids.size()==1&&ids.get(0)==-1){
					return shards;
				}
				List<String> r=new ArrayList<String>();
				for (int id : ids) {
				  r.add(shards.get(id));
				}
				return r;
			}
        }
        return null;
    }

    public IndexExpression getExpression() {
        return expression;
    }

    public void setExpression(IndexExpression expression) {
        this.expression = expression;
    }

    public List<String> getShards() {
        return shards;
    }

    public void setShards(List<String> shards) {
        this.shards = shards;
    }

    public String getSqlmap() {
        return sqlmap;
    }

    public void setSqlmap(String sqlmap) {
        this.sqlmap = sqlmap;
    }

    @Override
    public String toString() {
        return "Route{" +
                "expression=" + expression +
                ", sqlmap='" + sqlmap + '\'' +
                ", shards=" + shards +
                '}';
    }
}
