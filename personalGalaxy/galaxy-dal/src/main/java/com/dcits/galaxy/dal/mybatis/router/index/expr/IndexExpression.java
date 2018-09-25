package com.dcits.galaxy.dal.mybatis.router.index.expr;

import java.util.List;

/**
 * @author huang.zhe
 *
 */
public interface IndexExpression {
    String expr();

    List<Integer> apply(Object context);
}



