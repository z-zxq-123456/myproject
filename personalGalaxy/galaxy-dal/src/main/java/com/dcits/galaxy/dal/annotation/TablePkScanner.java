/**   
 * <p>Title: TablePkScanner.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2015年2月10日 下午1:17:40
 * @version V1.0
 */
package com.dcits.galaxy.dal.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dcits.galaxy.base.bean.AbstractBean;

/**
 * TablePk注解扫描器
 *
 * @author Tim
 * @version V1.0
 * @description
 * @update 2015年2月10日 下午1:17:40
 */

public class TablePkScanner {

    public final static String PREFIX = "TablePk";

    private final static Map<String, String[]> pks = new ConcurrentHashMap<>();

    public static <T extends AbstractBean> String[] pkColsScanner(T mapper) {
        String key = mapper.getClass().getName();
        if (pks.containsKey(key)) {
            return pks.get(key);
        }
        List<String> colsName = new ArrayList<String>();
        List<Integer> index = new ArrayList<Integer>();
        Class<? extends AbstractBean> currentClass = mapper.getClass();
        Field[] fields = currentClass.getDeclaredFields();
        for (Field elem : fields) {
            if ((!Modifier.isPrivate(elem.getModifiers()) && !Modifier
                    .isProtected(elem.getModifiers()))
                    || Modifier.isStatic(elem.getModifiers())
                    || Modifier.isFinal(elem.getModifiers())) {
                continue;
            }
            if (elem.isAnnotationPresent(TablePk.class)) {
                TablePk annotation = elem.getAnnotation(TablePk.class);
                colsName.add(elem.getName());
                index.add(annotation.index());
            }
        }
        Integer[] indexs = new Integer[index.size()];
        indexs = index.toArray(indexs);
        String[] cols = new String[index.size()];
        cols = colsName.toArray(cols);
        int temp = 0;
        String col = null;
        // 冒泡排序
        for (int j = 0; j < indexs.length; j++) {
            for (int i = j; i < indexs.length - 1; i++) {
                if (indexs[j] > indexs[i + 1]) {
                    temp = indexs[j];
                    indexs[j] = indexs[i + 1];
                    indexs[i + 1] = temp;
                    col = cols[j];
                    cols[j] = cols[i + 1];
                    cols[i + 1] = col;
                }
            }
        }
        pks.put(key, cols);
        return cols;
    }
}
