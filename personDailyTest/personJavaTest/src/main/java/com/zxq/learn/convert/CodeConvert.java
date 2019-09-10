package com.zxq.learn.convert;

/**
 *
 * 全角半角 转换工具类
 *
 * @cons 只转换ASCII可显示字符，十进制33-126（图形!-~），其余字符不转换
 * @author zhouxqh
 * @version 1.0
 */
public class CodeConvert {

    //相对偏移
    private static final int DISTANCE = 65248;

    /**
     * 半角字符->全角字符转换
     * 只处理空格，!到˜之间的字符，忽略其他
     */
    public static String halfToFull(String src) {

        return change(33,126,src,"Y");
    }

    /**
     * 全角字符->半角字符转换
     * 只处理全角的空格，全角！到全角～之间的字符，忽略其他
     */
    public static String fullToHalf(String src) {

        return change(65281,65374,src,"N");
    }

    /**
     * 转换code
     */
    private static String change(int start,int end,String src,String positive){

        if (src == null) {
            return src;
        }
        StringBuilder buf = new StringBuilder(src.length());
        char[] ca = src.toCharArray();
        for (char aCa : ca) {

          if (aCa>= start && aCa <= end) {
              if (positive.equals("N")) {
                  buf.append((char) (aCa - DISTANCE));/*全角转半角*/
              }else {
                  buf.append((char) (aCa + DISTANCE));/*半角转全角*/
              }
          }else {
                buf.append(aCa);
          }

        }
        return buf.toString();
    }


}
