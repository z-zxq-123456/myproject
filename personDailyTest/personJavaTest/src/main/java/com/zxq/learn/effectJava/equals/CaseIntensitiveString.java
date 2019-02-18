package com.zxq.learn.effectJava.equals;

/**
 * equals
 * Created{ by zhouxqh} on 2017/10/21.
 */
public class CaseIntensitiveString {
    private String s;

    public CaseIntensitiveString(String s) {
        if (s == null)
            throw new NullPointerException();
        this.s = s;
    }

    @Override
    public boolean equals(Object o) {

       /* if (o instanceof CaseIntensitiveString){
            return s.equalsIgnoreCase(((CaseIntensitiveString) o).s);
        }
        if (o instanceof String){
            return s.equalsIgnoreCase((String)o);
        }
        return false;*/
        //问题解决办法
        return o instanceof CaseIntensitiveString && ((CaseIntensitiveString) o).s.equalsIgnoreCase(s);
    }
}
