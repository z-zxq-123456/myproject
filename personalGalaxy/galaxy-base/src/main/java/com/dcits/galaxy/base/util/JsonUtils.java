package com.dcits.galaxy.base.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.exception.BusinessException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将JSON字符串的key进行大写+下划线格式与驼峰模式互转<br>
 * JSONObject取值二次封装<br>
 * JSONObject取值支持驼峰和大写+下划线格Key取值
 *
 * @author Tim
 */
public class JsonUtils {
    private static final Logger logger = LoggerFactory
            .getLogger(JsonUtils.class);

    private static final String S1 = ":";
    private static final String S2 = ",";
    private static final String S3 = "_";
    private static final String S4 = "{";
    private static final String S5 = "[";
    private static final String fillStringUnit = "    ";
    private static Map<String, String> uConv = new ConcurrentHashMap<>();
    private static Map<String, String> hConv = new ConcurrentHashMap<>();

    /**
     * @fields UPPER_TYPE
     */
    public static final String UPPER_TYPE = "U";
    /**
     * @fields HUMP_TYPE
     */
    public static final String HUMP_TYPE = "H";
    /**
     * @fields CONVERT 驼峰和大写+下划线格Key转换开关
     */
    public static final boolean CONVERT = true;

    /**
     * 驼峰格式转为大写+下划线格式
     *
     * @param key
     * @return
     */
    public final static String convertUpperCase(String key) {
        if (!CONVERT)
            return key;
        if (uConv.containsKey(key)) {
            return uConv.get(key);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(S3);
                sb.append(c);
            } else {
                sb.append(Character.toUpperCase(c));
            }
        }
        String tmp = sb.toString();
        uConv.put(key, tmp);
        return tmp;
    }

    /**
     * 大写+下划线格式转化为驼峰格式
     *
     * @param key
     * @return
     */
    public final static String convertHumpCase(String key) {
        if (!CONVERT)
            return key;
        if (hConv.containsKey(key)) {
            return hConv.get(key);
        }
        StringBuilder sb = new StringBuilder();
        String[] fs = key.split(S3);
        int i = 0;
        for (String s : fs) {
            if (i == 0)
                sb.append(s.toLowerCase());
            else {
                sb.append(s.substring(0, 1).toUpperCase());
                sb.append(s.substring(1).toLowerCase());
            }
            i++;
        }
        String tmp = sb.toString();
        hConv.put(key, tmp);
        return tmp;
    }

    /**
     * JSON字符串转换
     *
     * @param msg
     * @param type
     * @return
     */
    public final static String convertMsg(String msg, String type) {
        if (!CONVERT)
            return msg;
        long starttime = 0;
        if (logger.isInfoEnabled()) {
            starttime = System.currentTimeMillis();
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Before convert:[" + msg + "]");
        }

        String[] jsonStrs = msg.split(S1);
        String[] temp = null;
        StringBuilder sb = new StringBuilder(jsonStrs.length);
        int i = 1;
        for (String s1 : jsonStrs) {
            temp = s1.split(S2);
            for (int j = 0; j < temp.length; j++) {
                if (j == temp.length - 1) {
                    if (isHumpKey(temp[j]) && UPPER_TYPE.equals(type))
                        sb.append(convertUpperCase(temp[j]));
                    else if (isUpperKey(temp[j]) && HUMP_TYPE.equals(type))
                        sb.append(convertHumpCase(temp[j]));
                    else
                        sb.append(temp[j]);
                } else {
                    sb.append(temp[j]);
                    sb.append(S2);
                }
            }

            if (i != jsonStrs.length)
                sb.append(S1);
            i++;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("After convert:[" + sb.toString() + "]");
        }
        if (logger.isInfoEnabled()) {
            long elapsedTime = System.currentTimeMillis() - starttime;
            logger.info("Convert Msg elapsed time:[" + elapsedTime + "]");
        }
        return sb.toString();
    }

    /**
     * 检查是否是Json的Key
     *
     * @param keyStr
     * @return
     */
    protected static boolean isUpperKey(String keyStr) {
        // modify for sonar
        if (null == keyStr) {
            return false;
        }
        Pattern p = Pattern.compile("\\s*|\r|\n");
        Matcher m = p.matcher(keyStr);
        keyStr = m.replaceAll("");
        if (keyStr.startsWith(S4) || keyStr.startsWith(S5))
            return true;
        Pattern pattern = Pattern.compile("^\"[A-Z0-9_]+\"$");
        Matcher matcher = pattern.matcher(keyStr);
        while (matcher.find()) {
            return true;
        }
        return false;
    }

    /**
     * 检查是否是Json的Key
     *
     * @param keyStr
     * @return
     */
    protected static boolean isHumpKey(String keyStr) {
        // modify for sonar
        if (null == keyStr) {
            return false;
        }
        Pattern p = Pattern.compile("\\s*|\r|\n");
        Matcher m = p.matcher(keyStr);
        keyStr = m.replaceAll("");
        if (keyStr.startsWith(S4) || keyStr.startsWith(S5))
            return true;
        Pattern pattern = Pattern.compile("^\"[a-zA-Z0-9]+\"$");
        Matcher matcher = pattern.matcher(keyStr);
        while (matcher.find()) {
            return true;
        }
        return false;
    }

    public final static String formatJson(String json) {
        return formatJson(json, fillStringUnit);
    }

    /**
     * json字符串的格式化
     *
     * @param json
     * @param fillStringUnit
     * @return
     */
    public final static String formatJson(String json, String fillStringUnit) {
        if (json == null || json.trim().length() == 0) {
            return null;
        }

        int fixedLenth = 0;
        ArrayList<String> tokenList = new ArrayList<String>();
        {
            String jsonTemp = json;
            // 预读取
            while (jsonTemp.length() > 0) {
                String token = getToken(jsonTemp);
                jsonTemp = jsonTemp.substring(token.length());
                token = token.trim();
                tokenList.add(token);
            }
        }

        for (int i = 0; i < tokenList.size(); i++) {
            String token = tokenList.get(i);
            int length = token.getBytes().length;
            if (length > fixedLenth && i < tokenList.size() - 1
                    && tokenList.get(i + 1).equals(":")) {
                fixedLenth = length;
            }
        }

        StringBuilder buf = new StringBuilder();
        int count = 0;
        for (int i = 0; i < tokenList.size(); i++) {

            String token = tokenList.get(i);

            if (token.equals(",")) {
                buf.append(token);
                doFill(buf, count, fillStringUnit);
                continue;
            }
            if (token.equals(":")) {
                buf.append(" ").append(token).append(" ");
                continue;
            }
            if (token.equals("{")) {
                String nextToken = tokenList.get(i + 1);
                if (nextToken.equals("}")) {
                    i++;
                    buf.append("{ }");
                } else {
                    count++;
                    buf.append(token);
                    doFill(buf, count, fillStringUnit);
                }
                continue;
            }
            if (token.equals("}")) {
                count--;
                doFill(buf, count, fillStringUnit);
                buf.append(token);
                continue;
            }
            if (token.equals("[")) {
                String nextToken = tokenList.get(i + 1);
                if (nextToken.equals("]")) {
                    i++;
                    buf.append("[ ]");
                } else {
                    count++;
                    buf.append(token);
                    doFill(buf, count, fillStringUnit);
                }
                continue;
            }
            if (token.equals("]")) {
                count--;
                doFill(buf, count, fillStringUnit);
                buf.append(token);
                continue;
            }

            buf.append(token);
            // 左对齐
            /**
             * if (i < tokenList.size() - 1 && tokenList.get(i + 1).equals(":"))
             * { int fillLength = fixedLenth - token.getBytes().length; if
             * (fillLength > 0) { for (int j = 0; j < fillLength; j++) {
             * buf.append(" "); } } }
             */
        }
        return buf.toString();
    }

    private static String getToken(String json) {
        StringBuilder buf = new StringBuilder();
        boolean isInYinHao = false;
        while (json.length() > 0) {
            String token = json.substring(0, 1);
            json = json.substring(1);

            if (!isInYinHao
                    && (token.equals(":") || token.equals("{")
                    || token.equals("}") || token.equals("[")
                    || token.equals("]") || token.equals(","))) {
                if (buf.toString().trim().length() == 0) {
                    buf.append(token);
                }

                break;
            }

            if (token.equals("\\")) {
                buf.append(token);
                buf.append(json.substring(0, 1));
                json = json.substring(1);
                continue;
            }
            if (token.equals("\"")) {
                buf.append(token);
                if (isInYinHao) {
                    break;
                } else {
                    isInYinHao = true;
                    continue;
                }
            }
            buf.append(token);
        }
        return buf.toString();
    }

    private static void doFill(StringBuilder buf, int count,
                               String fillStringUnit) {
        buf.append("\n");
        for (int i = 0; i < count; i++) {
            buf.append(fillStringUnit);
        }
    }

    /**
     * 合并JSONObject
     *
     * @param source
     * @param dest
     */
    public static void mergeJSONObject(JSONObject source, JSONObject dest) {
        for (String key : source.keySet()) {
            if (dest.get(key) instanceof JSONObject) {
                if (source.get(key) instanceof JSONObject) {
                    mergeJSONObject((JSONObject) source.get(key),
                            (JSONObject) dest.get(key));
                } else
                    dest.put(key, source.get(key));
            } else if (dest.get(key) instanceof JSONArray) {
                Iterator<Object> it = ((JSONArray) source.get(key)).iterator();
                for (; it.hasNext(); ) {
                    ((JSONArray) dest.get(key)).add(it.next());
                }

            } else
                dest.put(key, source.get(key));
        }
    }

    /**
     * 检查字符串数据有效性<br>
     *
     * @param key
     * @param in
     * @return
     * @throws BusinessException 输入为空或者不上送
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年1月4日 下午3:20:45
     */
    public static boolean checkValue(String key, JSONObject in) {
        getString(key, in);
        return true;
    }

    /**
     * 检查字符串数据有效性<br>
     *
     * @param key
     * @param in
     * @param i   0-允许不上送或者为空<br>
     *            1-允许为空，但必须上送<br>
     * @return
     * @throws BusinessException
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年1月4日 下午3:20:08
     */
    public static boolean checkValue(String key, JSONObject in, int i) {
        switch (i) {
            case 0:
                break;
            case 1:
                i = 2;
                break;
            default:
                i = -1;
                break;
        }
        getString(key, in, i);
        return true;
    }

    /**
     * 检查字符串数据有效性<br>
     *
     * @param key
     * @param in
     * @param verifys 合法数据
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月22日 下午2:23:26
     */
    public static boolean verifyValue(String key, JSONObject in,
                                      String... verifys) {
        String value = getString(key, in);
        boolean v = false;
        for (String verify : verifys) {
            if (verify.equals(value)) {
                v = true;
                break;
            }
        }
        if (!v) {
            if (CONVERT && isHumpKey("\"" + key + "\"")) {
                key = convertUpperCase(key);
            }
            throw new BusinessException("000305", "[" + key + ":" + value + "]"
                    + ErrorUtils.getErrorDesc("000305"));
        }
        return v;
    }

    /**
     * @param key
     * @param in
     * @return
     */
    public static boolean containsKey(String key, JSONObject in) {
        // 不再进行转换
        // if (CONVERT && isUpperKey("\"" + key + "\"")) {
        // key = convertHumpCase(key);
        // }

        return in.containsKey(key);
    }

    /**
     * 获取字符串数据<br>
     *
     * @param key
     * @param in
     * @return
     * @throws BusinessException 输入为空或者不上送
     */
    public static String getString(String key, JSONObject in) {
        Object o = getObject(key, in);
        String str = o == null ? null : String.valueOf(o);
        if (null == str) {
            if (CONVERT && isHumpKey("\"" + key + "\"")) {
                key = convertUpperCase(key);
            }
            String[] args = {key};
            throw new BusinessException("000301", ErrorUtils.getParseErrorDesc(
                    "000301", args));
        }

        if ("".equals(str)) {
            if (CONVERT && isHumpKey("\"" + key + "\"")) {
                key = convertUpperCase(key);
            }
            String[] args = {key};
            throw new BusinessException("000302", ErrorUtils.getParseErrorDesc(
                    "000302", args));
        }
        return str;
    }

    /**
     * 获取字符串数据<br>
     *
     * @param key
     * @param in
     * @param i   0-不上送或者值为空，返回null<br>
     *            1-不上送或者值为空，返回""<br>
     *            2-值为空，返回null,不上送域则throw异常<br>
     *            3-值为空，返回"",不上送域则throw异常
     * @return
     * @throws BusinessException
     */
    public static String getString(String key, JSONObject in, int i) {
        Object o = getObject(key, in);
        String str = o == null ? null : String.valueOf(o);
        switch (i) {
            case 0:
                if ("".equals(str)) {
                    str = null;
                }
                break;
            case 1:
                if (null == str) {
                    str = "";
                }
                break;
            case 2:
                if (null == str) {
                    if (CONVERT && isHumpKey("\"" + key + "\"")) {
                        key = convertUpperCase(key);
                    }
                    String[] args = {key};
                    throw new BusinessException("000301",
                            ErrorUtils.getParseErrorDesc("000301", args));
                }
                if ("".equals(str))
                    str = null;
                break;
            case 3:
                if (null == str) {
                    if (CONVERT && isHumpKey("\"" + key + "\"")) {
                        key = convertUpperCase(key);
                    }
                    String[] args = {key};
                    throw new BusinessException("000301",
                            ErrorUtils.getParseErrorDesc("000301", args));
                }
                break;
            default:
                String[] args1 = {"i[" + i + "]"};
                throw new BusinessException("000304", ErrorUtils.getParseErrorDesc(
                        "000304", args1));
        }
        return str;
    }

    /**
     * 获取date数据<br>
     *
     * @param key
     * @param in
     * @return
     * @throws BusinessException 输入为空或者不上送
     */
    public static Date getDate(String key, JSONObject in) {
        return getDate(key, "yyMMdd", in);
    }

    /**
     * 获取date数据<br>
     *
     * @param key
     * @param pattern
     * @param in
     * @return
     * @throws BusinessException 输入为空或者不上送
     */
    public static Date getDate(String key, String pattern, JSONObject in) {
        Object o = getObject(key, in);
        if (o instanceof Long) {
            String s = DateUtils.getDateTime(new Date(((Long) o).longValue()),
                    pattern);
            return DateUtils.parse(s, pattern);
        }
        String str = String.valueOf(o);
        if (null == str) {
            if (CONVERT && isHumpKey("\"" + key + "\"")) {
                key = convertUpperCase(key);
            }
            String[] args = {key};
            throw new BusinessException("000301", ErrorUtils.getParseErrorDesc(
                    "000301", args));
        }

        if ("".equals(str)) {
            if (CONVERT && isHumpKey("\"" + key + "\"")) {
                key = convertUpperCase(key);
            }
            String[] args = {key};
            throw new BusinessException("000302", ErrorUtils.getParseErrorDesc(
                    "000302", args));
        }
        return DateUtils.parse(str, pattern);
    }

    /**
     * 获取date数据<br>
     *
     * @param key
     * @param in
     * @return
     * @throws BusinessException 输入为空或者不上送
     */
    public static String getDateStr(String key, JSONObject in) {
        return DateUtils.getDateTime(getDate(key, in), "yyMMdd");
    }

    /**
     * 获取date数据<br>
     *
     * @param key
     * @param in
     * @return
     * @throws BusinessException 输入为空或者不上送
     */
    public static String getDateStr(String key, String pattern, JSONObject in) {
        return DateUtils.getDateTime(getDate(key, pattern, in), pattern);
    }

    /**
     * 获取double数据<br>
     *
     * @param key
     * @param in
     * @return
     * @throws BusinessException 输入为空或者不上送
     */
    public static double getDouble(String key, JSONObject in) {
        Object o = getObject(key, in);
        String str = o == null ? null : String.valueOf(o);
        if (null == str) {
            if (CONVERT && isHumpKey("\"" + key + "\"")) {
                key = convertUpperCase(key);
            }
            String[] args = {key};
            throw new BusinessException("000301", ErrorUtils.getParseErrorDesc(
                    "000301", args));
        }

        if ("".equals(str)) {
            if (CONVERT && isHumpKey("\"" + key + "\"")) {
                key = convertUpperCase(key);
            }
            String[] args = {key};
            throw new BusinessException("000302", ErrorUtils.getParseErrorDesc(
                    "000302", args));
        }

        double value;
        try {
            value = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            if (CONVERT && isHumpKey("\"" + key + "\"")) {
                key = convertUpperCase(key);
            }
            String[] args = {key};
            throw new BusinessException("000303", ErrorUtils.getParseErrorDesc(
                    "000303", args));
        }
        return value;
    }

    /**
     * 获取double数据<br>
     *
     * @param key
     * @param in
     * @param i   0-不上送或者值为空,返回0.0<br>
     *            1-值为空返回0.0,不上送域throw异常
     * @return
     * @throws BusinessException
     */
    public static double getDouble(String key, JSONObject in, int i) {
        Object o = getObject(key, in);
        String str = o == null ? null : String.valueOf(o);

        switch (i) {
            case 0:
                if (null == str || "".equals(str))
                    str = "0.0";
                break;
            case 1:
                if (null == str) {
                    if (CONVERT && isHumpKey("\"" + key + "\"")) {
                        key = convertUpperCase(key);
                    }
                    String[] args = {key};
                    throw new BusinessException("000301",
                            ErrorUtils.getParseErrorDesc("000301", args));
                }
                if ("".equals(str))
                    str = "0.0";
                break;
            default:
                String[] args1 = {"i[" + i + "]"};
                throw new BusinessException("000304", ErrorUtils.getParseErrorDesc(
                        "000304", args1));
        }

        double value;
        try {
            value = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            if (CONVERT && isHumpKey("\"" + key + "\"")) {
                key = convertUpperCase(key);
            }
            String[] args = {key};
            throw new BusinessException("000303", ErrorUtils.getParseErrorDesc(
                    "000303", args));
        }
        return value;
    }

    /**
     * 获取double数据<br>
     *
     * @param key
     * @param scale
     * @param in
     * @return
     * @throws BusinessException 输入为空或者不上送
     */
    public static double getDouble(String key, int scale, JSONObject in) {
        return getDouble(key, scale, BigDecimal.ROUND_HALF_UP, in);
    }

    /**
     * 获取double数据<br>
     *
     * @param key
     * @param scale
     * @param roundingMode
     * @param in
     * @return
     * @throws BusinessException 输入为空或者不上送
     */
    public static double getDouble(String key, int scale, int roundingMode,
                                   JSONObject in) {
        return DoubleUtils
                .round(getDouble(key, scale, in), scale, roundingMode);
    }

    /**
     * 获取double数据<br>
     *
     * @param key
     * @param in
     * @return
     * @throws BusinessException 输入为空或者不上送
     */
    public static BigDecimal getBigDecimal(String key, JSONObject in) {
        return BigDecimal.valueOf(getDouble(key, in));
    }

    /**
     * 获取double数据<br>
     *
     * @param key
     * @param in
     * @param i   0-不上送或者值为空,返回0.0<br>
     *            1-值为空返回0.0,不上送域throw异常
     * @return
     * @throws BusinessException
     */
    public static BigDecimal getBigDecimal(String key, JSONObject in, int i) {
        return BigDecimal.valueOf(getDouble(key, in, i));
    }

    /**
     * 获取double数据<br>
     *
     * @param key
     * @param scale
     * @param in
     * @return
     * @throws BusinessException 输入为空或者不上送
     */
    public static BigDecimal getBigDecimal(String key, int scale, JSONObject in) {
        return getBigDecimal(key, scale, BigDecimal.ROUND_HALF_UP, in);
    }

    /**
     * 获取double数据<br>
     *
     * @param key
     * @param scale
     * @param roundingMode
     * @param in
     * @return
     * @throws BusinessException 输入为空或者不上送
     */
    public static BigDecimal getBigDecimal(String key, int scale,
                                           int roundingMode, JSONObject in) {
        BigDecimal bd = getBigDecimal(key, in);
        bd = bd.setScale(scale, roundingMode);
        return bd;
    }

    /**
     * 获取int数据<br>
     *
     * @param key
     * @param in
     * @return
     * @throws BusinessException 输入为空或者不上送
     */
    public static int getInt(String key, JSONObject in) {
        Object o = getObject(key, in);
        String str = o == null ? null : String.valueOf(o);
        if (null == str) {
            if (CONVERT && isHumpKey("\"" + key + "\"")) {
                key = convertUpperCase(key);
            }
            String[] args = {key};
            throw new BusinessException("000301", ErrorUtils.getParseErrorDesc(
                    "000301", args));
        }

        if ("".equals(str)) {
            if (CONVERT && isHumpKey("\"" + key + "\"")) {
                key = convertUpperCase(key);
            }
            String[] args = {key};
            throw new BusinessException("000302", ErrorUtils.getParseErrorDesc(
                    "000302", args));
        }

        int value;
        try {
            value = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            if (CONVERT && isHumpKey("\"" + key + "\"")) {
                key = convertUpperCase(key);
            }
            String[] args = {key};
            throw new BusinessException("000303", ErrorUtils.getParseErrorDesc(
                    "000303", args));
        }
        return value;
    }

    /**
     * 获取double数据<br>
     *
     * @param key
     * @param in
     * @param i   0-不上送或者值为空,返回0<br>
     *            1-值为空返回0,不上送域throw异常
     * @return
     * @throws BusinessException
     */
    public static int getInt(String key, JSONObject in, int i) {
        Object o = getObject(key, in);
        String str = o == null ? null : String.valueOf(o);

        switch (i) {
            case 0:
                if (null == str || "".equals(str))
                    str = "0";
                break;
            case 1:
                if (null == str) {
                    if (CONVERT && isHumpKey("\"" + key + "\"")) {
                        key = convertUpperCase(key);
                    }
                    String[] args = {key};
                    throw new BusinessException("000301",
                            ErrorUtils.getParseErrorDesc("000301", args));
                }
                if ("".equals(str))
                    str = "0";
                break;
            default:
                String[] args1 = {"i[" + i + "]"};
                throw new BusinessException("000304", ErrorUtils.getParseErrorDesc(
                        "000304", args1));
        }

        int value;
        try {
            value = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            if (CONVERT && isHumpKey("\"" + key + "\"")) {
                key = convertUpperCase(key);
            }
            String[] args = {key};
            throw new BusinessException("000303", ErrorUtils.getParseErrorDesc(
                    "000303", args));
        }
        return value;
    }

    /**
     * 支持驼峰和大写字母下划线模式转换
     *
     * @param key
     * @param in
     * @return
     */
    public static Object getObject(String key, JSONObject in) {
        // 不再进行转换
        // if (CONVERT && isUpperKey("\"" + key + "\"")) {
        // key = convertHumpCase(key);
        // }
        return in.get(key);
    }

    /**
     * 支持驼峰和大写字母下划线Key
     *
     * @param key
     * @param in
     * @return
     */
    public static JSONObject getJSONObject(String key, JSONObject in) {
        // 不再进行转换
        // if (CONVERT && isUpperKey("\"" + key + "\"")) {
        // key = convertHumpCase(key);
        // }
        return in.getJSONObject(key);
    }

    /**
     * 支持驼峰和大写字母下划线Key
     *
     * @param key
     * @param in
     * @return
     */
    public static JSONArray getJSONArray(String key, JSONObject in) {
        // 不再进行转换
        // if (CONVERT && isUpperKey("\"" + key + "\"")) {
        // key = convertHumpCase(key);
        // }
        return in.getJSONArray(key);
    }

    /**
     * 支持驼峰和大写字母下划线Key
     *
     * @param key
     * @param in
     * @return
     */
    public static Object putObject(String key, Object value, JSONObject in) {
        // 不再进行转换
        // if (CONVERT && isUpperKey("\"" + key + "\"")) {
        // key = convertHumpCase(key);
        // }
        return in.put(key, value);
    }
}
