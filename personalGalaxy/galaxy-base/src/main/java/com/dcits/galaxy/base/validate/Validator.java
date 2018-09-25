package com.dcits.galaxy.base.validate;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.ObjectUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Tim
 * @version V1.0
 * @description
 * @update 2015年1月14日 下午2:56:14
 */

public class Validator {
    private static final Logger logger = LoggerFactory
            .getLogger(Validator.class);
    
    @V(desc = "未加@V注解字段缓存占位符")
    private static V emptyPlaceHolder;
    
    static {
    	try {
			Field field = Validator.class.getDeclaredField("emptyPlaceHolder");
			V empty = field.getAnnotation(V.class);
			emptyPlaceHolder = empty;
		} catch (NoSuchFieldException | SecurityException e) {
			throw new GalaxyException("can't load empty place holder for @V. " + e.getMessage(), e);
		}
    }

    private volatile static Validator validator;

    private static Map<String, V> validators = new ConcurrentHashMap<>();

    private Handler handler = new VHandler();

    private Validator() {
    }

    public static Validator getInstance() {
        if (null == validator) {
            synchronized (Validator.class) {
                if (null == validator) {
                    validator = new Validator();
                }
            }
        }
        return validator;
    }

    public void validate(Object validatedObj) throws ValidateException,
            NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        long start = System.currentTimeMillis();
        try {
            validate(validatedObj, null);
        } finally {
            if (logger.isInfoEnabled())
                logger.info("Validate - 执行时间["
                        + (System.currentTimeMillis() - start) + "]["
                        + validatedObj.getClass().getName() + "]");
        }
    }

    public void validate(Object validatedObj, String prefix)
            throws ValidateException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        validate(validatedObj, prefix, new ArrayList<String>());
    }

    private void validate(Object validatedObj, String prefix, List<String> keys)
            throws ValidateException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        if (null == validatedObj) {
            if (logger.isWarnEnabled())
                logger.warn("The input validatedObj is null.");
            return;
        }
        if (null == prefix)
            prefix = "";

        Class<?> currentClass = validatedObj.getClass();
        while (currentClass != null) {
            Field[] fields = currentClass.getDeclaredFields();
            for (Field elem : fields) {
                if ((!Modifier.isPrivate(elem.getModifiers()) && !Modifier
                        .isProtected(elem.getModifiers()))
                        || Modifier.isStatic(elem.getModifiers())
                        || Modifier.isFinal(elem.getModifiers())
                        || elem.getType() == Boolean.TYPE) {
                    continue;
                }
                String key = getHandlerKey(validatedObj, elem);
                // 增加缓存机制
                V annotation = null;
                if (!validators.containsKey(key)) {
                    annotation = elem.getAnnotation(V.class);
                    if(null == annotation){
                    	annotation = emptyPlaceHolder;
                    }
                    
                    validators.put(key, annotation);
                }
                
                annotation = validators.get(key);
                if(annotation == emptyPlaceHolder){
                	continue;
                }

                Object o = ObjectUtils.getField(validatedObj, elem.getName());
                if (elem.getType().isMemberClass() || o instanceof AbstractBean) {
                    validate(o, prefix + elem.getName() + ".", new ArrayList<String>());
                } else if (o instanceof List) {
                    // 防止父类的同属性被重复校验。
                    if (!keys.contains(elem.getName())) {
                        validateField(validatedObj, elem, prefix);
                        keys.add(elem.getName());
                    }
                    int i = 0;
                    for (Object oo : ((List<?>) o).toArray()) {
                        validate(oo, prefix + elem.getName() + "[" + i + "].", new ArrayList<String>());
                        i++;
                    }
                } else {
                    // 防止父类的同属性被重复校验。
                    if (!keys.contains(elem.getName())) {
                        validateField(validatedObj, elem, prefix);
                        keys.add(elem.getName());
                    }
                }
            }
            Class<?> superClass = currentClass.getSuperclass();
            if (Object.class == superClass) {
                currentClass = null;
                keys.clear();
            } else {
                currentClass = superClass;
            }
        }
    }

    private void validateField(Object validatedObj, Field field, String prefix)
            throws ValidateException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Object destValue;
        try {
            destValue = ObjectUtils.getField(validatedObj, field.getName());
        } catch (Exception ex) {
            if (logger.isDebugEnabled())
                logger.debug("Get field value or cast value error. Error message: "
                        + ex.getMessage(), ex);
            throw new ValidateException(ex.getMessage(), ex);
        }

        String key = getHandlerKey(validatedObj, field);
        // 增加缓存机制
        if (validators.containsKey(key)) {
            handler.validate(validators.get(key), validatedObj, field, destValue, prefix);
        }
    }

    private String getHandlerKey(Object validatedObj, Field field) {
        return new StringBuilder().append(validatedObj.getClass().getName()).append(".").append(field.getName()).toString();
    }
    
    public void addValidator(String key, V v){
    	Validator.validators.put(key, v);
    }
    
    public void addValidators(Map<String, V> validators){
    	Validator.validators.putAll(validators);
    }
}
