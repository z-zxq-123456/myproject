/**
 * <p>Title: VHandler.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2015年1月15日 下午1:36:59
 * @version V1.0
 */
package com.dcits.galaxy.base.validate;

import com.dcits.galaxy.base.exception.BusinessException;
import com.dcits.galaxy.base.util.ErrorUtils;
import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.galaxy.base.util.ObjectUtils;
import com.dcits.galaxy.base.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Tim
 * @version V1.0
 * @description 检查组件
 * @update 2015年1月15日 下午1:40:17
 */

public class VHandler implements Handler {
    private static final Logger logger = LoggerFactory
            .getLogger(VHandler.class);

    @Override
    public void validate(V annotation, Object filter, Field field, Object fieldValue,
                         String prefix) {
        // 检查是否必须上送
        if (annotation.notNull())
            checkNotNull(annotation, filter, field, prefix, fieldValue);

        // 检查List和String是否为空
        if (((fieldValue instanceof String) || (fieldValue instanceof List))
                && annotation.notEmpty())
            checkNotEmpty(annotation, filter, field, prefix, fieldValue);

        // 检查是否全整数类型
        if ((fieldValue instanceof String) && annotation.digit())
            checkDigit(annotation, field, prefix, (String) fieldValue);

        // 检查整形是否超过最大最小
        if ((fieldValue instanceof Integer))
            checkInt(annotation, field, prefix, (Integer) fieldValue);

        // 正则表达式检查
        if ((fieldValue instanceof String)
                && !"".equals(annotation.pattern()))
            checkStringPattern(annotation, field, prefix,
                    (String) fieldValue);

        // 检查字符串是否超过最大最小长度值
        if ((fieldValue instanceof String)
                && (annotation.minSize() > 0 || annotation.maxSize() > 0))
            checkSize(annotation, field, prefix, (String) fieldValue);

        // 检查是否是指定范围值
        if ((fieldValue instanceof String) && !"".equals(annotation.in()))
            checkStringIn(annotation, field, prefix, (String) fieldValue);

        // 检查起始日期是否超出了截止日期
        if ((fieldValue instanceof Date)
                && !"".equals(annotation.laterTime()))
            checkTime(annotation, filter, field, prefix, (Date) fieldValue);

        // 检查长度是否正确
        if ((fieldValue instanceof String)
                && !"".equals(annotation.length()))
            checkLength(annotation, field, prefix, (String) fieldValue);
        
        // 检查数字精度是否正确
        if((fieldValue instanceof String || fieldValue instanceof Number) &&
        		(annotation.integerLength() != -1 || annotation.decimalLength() != -1)){
        	checkNumberPrecision(annotation, field, prefix, fieldValue);
        }
        
        // 检查日期格式是否正确
        if(fieldValue instanceof String && !annotation.dateFormat().isEmpty()){
        	checkDateFormat(annotation, field, prefix, (String) fieldValue);
        }
    }

    private String getDesc(V annotation) {
        String desc = annotation.desc();
        // modify for sonar
        if (StringUtils.isNotEmpty(desc)) {
            desc = StringUtils.append(desc, ".");
        }
        return desc;
    }

    /**
     * @param annotation
     * @param filter
     * @param field
     * @param prefix
     * @param dest
     * @throws ValidateException
     * @description 检查是否必须上送
     * @version 1.0
     * @author Tim
     * @update 2015年1月15日 下午2:14:49
     */
    private void checkNotNull(V annotation, Object filter, Field field,
                              String prefix, Object dest) throws ValidateException {
        String restraint = annotation.restraint();
        if (!"".equals(restraint)) {
            try {
                String[] restraints = restraint.split("=");
                String key = restraints[0];
                String res = restraints[1];
                String value = (String) ObjectUtils.getField(filter, key);
                if (StringUtils.isNotEmpty(value)) {
                    String[] validateValues = res.split(",");
                    List<String> list = Arrays.asList(validateValues);
                    if (!list.contains(value)) {
                        return;
                    }
                } else {
                    // 约束条件的值为null，跳过此检查
                    return;
                }
            } catch (Throwable t) {
                // 检查条件出异常，忽略后续检查
                if (logger.isWarnEnabled())
                    logger.warn("获取检查条件出异常，忽略此项检查");
                return;
            }
        }

        if (dest == null) {
            if (logger.isDebugEnabled())
                logger.debug("Validate fail. Error message: validate value is:null");
            throw new BusinessException("000301", ErrorUtils.getParseErrorDesc(
                    "000301", new String[]{JsonUtils.convertUpperCase(prefix + field.getName())
                            + (annotation.desc() == null ? "" : "(" + annotation.desc() + ")")}));
        }
    }

    /**
     * @param annotation
     * @param filter
     * @param field
     * @param prefix
     * @param dest
     * @throws ValidateException
     * @description 检查List和String是否为空
     * @version 1.0
     * @author Tim
     * @update 2015年1月15日 下午3:06:07
     */
    private void checkNotEmpty(V annotation, Object filter, Field field, String prefix,
                               Object dest) throws ValidateException {
        // 必须上送的字段，再检查不允许为空
        if (annotation.notNull()) {
            String restraint = annotation.restraint();
            if (!"".equals(restraint)) {
                try {
                    String[] restraints = restraint.split("=");
                    String key = restraints[0];
                    String res = restraints[1];
                    String value = (String) ObjectUtils.getField(filter, key);
                    if (StringUtils.isNotEmpty(value)) {
                        String[] validateValues = res.split(",");
                        List<String> list = Arrays.asList(validateValues);
                        if (!list.contains(value)) {
                            return;
                        }
                    } else {
                        // 约束条件的值为null，跳过此检查
                        return;
                    }
                } catch (Throwable t) {
                    // 检查条件出异常，忽略后续检查
                    if (logger.isWarnEnabled())
                        logger.warn("获取检查条件出异常，忽略此项检查");
                    return;
                }
            }
            if (dest instanceof String) {
                if (((String) dest).length() == 0) {
                    if (logger.isDebugEnabled())
                        logger.debug("The String " + field.getName()
                                + " length is 0.");
                    throw new BusinessException("000302", ErrorUtils.getParseErrorDesc(
                            "000302", new String[]{JsonUtils.convertUpperCase(prefix + field.getName())
                                    + (annotation.desc() == null ? "" : "(" + annotation.desc() + ")")}));
                }
            }
            if (dest instanceof List) {
                if (((List<?>) dest).size() == 0) {
                    if (logger.isDebugEnabled())
                        logger.debug("The List " + field.getName() + " is empty.");
                    throw new BusinessException("000302", "["
                            + JsonUtils.convertUpperCase(prefix + field.getName())
                            + (annotation.desc() == null ? "" : "(" + annotation.desc() + ")")
                            + "]" + getDesc(annotation) + "数组不能为空");
                }
            }
        }
    }

    /**
     * @param annotation
     * @param field
     * @param prefix
     * @throws ValidateException
     * @description 检查是否全数字
     * @version 1.0
     * @author Tim
     * @update 2015年1月15日 下午1:40:19
     */
    private void checkDigit(V annotation, Field field, String prefix,
                            String value) throws ValidateException {
        String patternStr = "\\d*";
        Pattern pattern = Pattern.compile(patternStr);
        if (value != null && !"".equals(value)) {
            if (!pattern.matcher(value).matches()) {
                if (logger.isDebugEnabled())
                    logger.debug("Validate fail. Error message: validate value is:"
                            + value);
                throw new BusinessException("000303", "["
                        + JsonUtils.convertUpperCase(prefix + field.getName())
                        + (annotation.desc() == null ? "" : "(" + annotation.desc() + ")")
                        + "]" + getDesc(annotation) + "必须全数字[" + value + "]");
            }
        }
    }

    /**
     * @param annotation
     * @param field
     * @param prefix
     * @param dest
     * @throws ValidateException
     * @description 检查是否超过最小最大值
     * @version 1.0
     * @author Tim
     * @update 2015年1月15日 下午2:35:22
     */
    private void checkInt(V annotation, Field field, String prefix, Integer dest)
            throws ValidateException {
        if (dest == null) {
            return; // NULL value is allowed.
        }

        int min = annotation.min();
        int max = annotation.max();

        int value = dest.intValue();
        if (value < min) {
            if (logger.isDebugEnabled())
                logger.debug(
                        "Validate fail. Error message: validate value is:{0},min value is:{1}",
                        new Integer[]{value, min});
            throw new BusinessException("000303", "["
                    + JsonUtils.convertUpperCase(prefix + field.getName())
                    + (annotation.desc() == null ? "" : "(" + annotation.desc() + ")")
                    + "]" + getDesc(annotation) + "值[" + value + "]不能小于最小值["
                    + min + "]");
        }

        if (value > max) {
            if (logger.isDebugEnabled())
                logger.debug(
                        "Validate fail. Error message: validate value is:{0},max value is:{1}",
                        new Integer[]{value, max});
            throw new BusinessException("000303", "["
                    + JsonUtils.convertUpperCase(prefix + field.getName())
                    + (annotation.desc() == null ? "" : "(" + annotation.desc() + ")")
                    + "]" + getDesc(annotation) + "值[" + value + "]不能大于最大值["
                    + max + "]");
        }
    }

    /**
     * @param annotation
     * @param field
     * @param prefix
     * @param dest
     * @throws ValidateException
     * @description 正则表达式检查
     * @version 1.0
     * @author Tim
     * @update 2015年1月15日 下午2:54:20
     */
    private void checkStringPattern(V annotation, Field field, String prefix,
                                    String dest) throws ValidateException {
        if (dest != null) {
            String patternStr = annotation.pattern();
            Pattern pattern = Pattern.compile(patternStr);
            String[] str = dest.split(",");
            for (int i = 0; i < str.length; i++) {
                if (!pattern.matcher(str[i]).matches()) {
                    if (logger.isDebugEnabled())
                        logger.debug("Validate fail. Error message: validate value is:"
                                + str[i]);
                    throw new BusinessException("000303", "["
                            + JsonUtils.convertUpperCase(prefix + field.getName())
                            + (annotation.desc() == null ? "" : "(" + annotation.desc() + ")")
                            + "]" + getDesc(annotation) + "值[" + dest + "]正则表达式检查出错["
                            + patternStr + "]");
                }
            }
        }
    }

    /**
     * @param annotation
     * @param field
     * @param prefix
     * @param dest
     * @throws ValidateException
     * @description 检查字符串长度是否超出范围
     * @version 1.0
     * @author Tim
     * @update 2015年1月15日 下午2:59:27
     */
    private void checkSize(V annotation, Field field, String prefix, String dest)
            throws ValidateException {
        int min = annotation.minSize();
        int max = annotation.maxSize();

        int size = 0;
        if (!"".equals(dest) && dest != null) {
            size = dest.length();
        }
        if (min > 0 && max > 0) {
            if (size < min || size > max) {
                if (logger.isDebugEnabled())
                    logger.debug("Validate fail. Error message: validate size is:"
                            + size + ",minSize value is:" + min
                            + ",maxSize value is:" + max);
                throw new BusinessException("000303", "["
                        + JsonUtils.convertUpperCase(prefix + field.getName())
                        + (annotation.desc() == null ? "" : "(" + annotation.desc() + ")")
                        + "]" + getDesc(annotation) + "实际长度[" + size
                        + "],不应该小于[" + min + "]或大于[" + max + "]");
            }
        }
        if (min > 0 && max < 0) {
            if (size < min) {
                logger.debug("Validate fail. Error message: validate size is:"
                        + size + ",minSize value is:" + min);
                throw new BusinessException("000303", "["
                        + JsonUtils.convertUpperCase(prefix + field.getName())
                        + (annotation.desc() == null ? "" : "(" + annotation.desc() + ")")
                        + "]" + getDesc(annotation) + "实际长度[" + size
                        + "],不应该小于[" + min + "]");
            }
        }
        if (min < 0 && max > 0) {
            if (size > max) {
                if (logger.isDebugEnabled())
                    logger.debug("Validate fail. Error message: validate size is:"
                            + size + ",maxSize value is:" + max);
                throw new BusinessException("000303", "["
                        + JsonUtils.convertUpperCase(prefix + field.getName())
                        + (annotation.desc() == null ? "" : "(" + annotation.desc() + ")")
                        + "]" + getDesc(annotation) + "实际长度[" + size
                        + "],不应该大于[" + max + "]");
            }
        }
    }

    /**
     * @param annotation
     * @param field
     * @param prefix
     * @param dest
     * @throws ValidateException
     * @description 检查值是否在指定范围内的值
     * @version 1.0
     * @author Tim
     * @update 2015年1月15日 下午3:04:01
     */
    private void checkStringIn(V annotation, Field field, String prefix,
                               String dest) throws ValidateException {
        String exceptValue = annotation.in();
        if (!"".equals(dest) && dest != null) {
            String[] validateValues = exceptValue.split(",");
            List<String> list = Arrays.asList(validateValues);
            if (!list.contains(dest)) {
                if (logger.isDebugEnabled())
                    logger.debug("Validate fail. Error message: validate value is:"
                            + dest);
                throw new BusinessException("000304", "["
                        + JsonUtils.convertUpperCase(prefix + field.getName())
                        + (annotation.desc() == null ? "" : "(" + annotation.desc() + ")")
                        + "]" + getDesc(annotation) + "实际值[" + dest
                        + "],期望值应该为" + Arrays.asList(validateValues));
            }
        }
    }

    /**
     * @param annotation
     * @param filter
     * @param field
     * @param prefix
     * @param startTime
     * @throws ValidateException
     * @description 检查起始日期是否超出了截止日期
     * @version 1.0
     * @author Tim
     * @update 2015年1月15日 下午3:11:06
     */
    private void checkTime(V annotation, Object filter, Field field,
                           String prefix, Date startTime) throws ValidateException {
        String eTime = annotation.laterTime();
        Date endTime = null;
        try {
            endTime = (Date) ObjectUtils.getField(filter, eTime);
        } catch (Exception ex) {
            if (logger.isDebugEnabled())
                logger.debug("Get field value or cast value error. Error message: "
                        + ex.getMessage(), ex);
            throw new ValidateException(ex.getMessage(), ex);
        }
        if (startTime != null && endTime != null) {
            if (startTime.after(endTime)) {
                if (logger.isDebugEnabled())
                    logger.debug("Validate fail. Error message: startTime is:"
                            + startTime + ", endTime is:" + endTime);
                throw new BusinessException("000303", "["
                        + JsonUtils.convertUpperCase(prefix + field.getName())
                        + (annotation.desc() == null ? "" : "(" + annotation.desc() + ")")
                        + "]" + getDesc(annotation) + "起始日期[" + startTime
                        + "]超出了截止日期[" + endTime + "]");
            }
        } else {
            if (logger.isDebugEnabled())
                logger.debug("Validate fail. Error message: startTime or endTime is null,startTime is:"
                        + startTime + ", endTime is:" + endTime);
            throw new BusinessException("000303", "["
                    + JsonUtils.convertUpperCase(prefix + field.getName())
                    + (annotation.desc() == null ? "" : "(" + annotation.desc() + ")")
                    + "]" + getDesc(annotation) + "起始日期和截止日期都必须输入.起始日期["
                    + startTime + "]截止日期[" + endTime + "]");
        }
    }

    private void checkLength(V annotation, Field field, String prefix,
                             String dest) throws ValidateException {
        String lens = annotation.length();
        if (dest != null) {
            boolean v = false;
            for (String len : lens.split(",")) {
                if (dest.length() == Integer.valueOf(len).intValue()) {
                    v = true;
                    break;
                }

            }
            if (!v) {
                if (logger.isDebugEnabled())
                    logger.debug("The String " + field.getName() + " length is "
                            + dest.length() + ",the excepted length is " + lens);
                throw new BusinessException("000303", "["
                        + JsonUtils.convertUpperCase(prefix + field.getName())
                        + (annotation.desc() == null ? "" : "(" + annotation.desc() + ")")
                        + "]" + getDesc(annotation) + "值实际长度为[" + dest.length()
                        + "],期望应该为[" + lens + "]");
            }
        }
    }
    
    /**
     * @param annotation
     * @param field
     * @param prefix
     * @param dest
     * @throws ValidateException
     * @description String及Number类型精度校验
     * @version 1.0
     * @author Tim
     * @update 2015年1月15日 下午2:54:20
     */
    private void checkNumberPrecision(V annotation, Field field, String prefix,
            Object dest) throws ValidateException {
    	int maxDecimalLength = annotation.decimalLength();
    	int maxIntegerLength = annotation.integerLength();
    	
    	String value = null;
    	if(dest instanceof String){
    		try {
    			value = new BigDecimal((String) dest).toString();
			} catch (Exception e) {
				if (logger.isDebugEnabled())
                    logger.debug("Validate fail. Error message: validate value is:"
                            + value);
                throw new BusinessException("000303", "["
                        + JsonUtils.convertUpperCase(prefix + field.getName())
                        + (annotation.desc() == null ? "" : "(" + annotation.desc() + ")")
                        + "]" + getDesc(annotation) + "必须为数字[" + value + "]");
			}
    	} else if(dest instanceof BigDecimal){
    		value = ((BigDecimal)dest).toPlainString();
    	} else {
    		value = ((Number)dest).toString();
    	}
    	
    	int point = value.indexOf(".");
    	
    	if(point == -1){
    		point = value.length();
    	}
    	
    	int integerSize = point;
    	if(value.indexOf("-") == 0){
    		integerSize--;
    	}
    	int decimalSize = value.length() - point -1;
    	
    	if(maxIntegerLength != -1 && integerSize > maxIntegerLength){
    		if (logger.isDebugEnabled())
    			logger.debug("Validate fail. Error message: validate integerSize is:"
    					+ integerSize + ",maxIntegerSize value is:" + maxIntegerLength);
    		throw new BusinessException("000303", "["
    				+ JsonUtils.convertUpperCase(prefix + field.getName())
    				+ (annotation.desc() == null ? "" : "(" + annotation.desc() + ")")
    				+ "]" + getDesc(annotation) + "实际整数位长度[" + integerSize
    				+ "],不应该大于[" + maxIntegerLength + "]");
    	}
    	
    	if(maxDecimalLength != -1 && decimalSize > maxDecimalLength){
    		if (logger.isDebugEnabled())
    			logger.debug("Validate fail. Error message: validate decimalSize is:"
    					+ decimalSize + ",maxDecimalSize value is:" + maxDecimalLength);
    		throw new BusinessException("000303", "["
    				+ JsonUtils.convertUpperCase(prefix + field.getName())
    				+ (annotation.desc() == null ? "" : "(" + annotation.desc() + ")")
    				+ "]" + getDesc(annotation) + "实际小数位长度[" + decimalSize
    				+ "],不应该大于[" + maxDecimalLength + "]");
    	}
    }
    
    /**
     * @param annotation
     * @param field
     * @param prefix
     * @param dest
     * @throws ValidateException
     * @description 检查日期类型的格式
     * @version 1.0
     * @author Tim
     * @update 2015年1月15日 下午3:04:01
     */
    private void checkDateFormat(V annotation, Field field, String prefix, String dest) throws ValidateException {
    	String format = annotation.dateFormat();
        if (dest != null && !"".equals(dest)) {
        	SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        	String formatDate = null;
        	try {
				Date date = dateFormat.parse(dest);
				formatDate = dateFormat.format(date);
			} catch (ParseException e) {
				if(logger.isDebugEnabled()){
					logger.debug("Validate fail. Error message: validate date is:" + dest + ", date format is:" + format, e);
				}
			}
        	
        	if(!dest.equals(formatDate)){
        		throw new BusinessException("000303", "[" + JsonUtils.convertUpperCase(prefix + field.getName())
        		+ (annotation.desc() == null ? "" : "(" + annotation.desc() + ")")
        		+ "]" + getDesc(annotation) + "实际值[" + dest + "], 期望日期格式应该为:" + format);
        	}
        }
    }
}
