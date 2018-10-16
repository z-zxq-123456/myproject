package com.dcits.ensemble.om.cmc.util;

import com.baixin.infra.head.utils.CommonUtils;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/5/21
 */
public class TransIDModel {

    private  Pattern TRANSID_PATTERN1 =
            Pattern.compile("^(\\d\\d)(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$");
    private  Pattern TRANSID_PATTERN2 = Pattern.compile("^[A-Za-z]{2}$");
    private  Pattern TRANSID_PATTERN3 = Pattern.compile("^[A-Za-z]{3}$");
    private  Pattern TRANSID_PATTERN4 = Pattern.compile("^[0-9A-Za-z]{3}$");
    private  Pattern TRANSID_PATTERN5 = Pattern.compile("^[0-9A-Za-z]{19}$");

    private String prefix;
    private String dcn;
    private String sysflag;
    private String uniqueKey;

    private static AtomicInteger count = new AtomicInteger(1);

    private TransIDModel(Builder builder){
        this.dcn = CommonUtils.getDCN();
        this.prefix = builder.prefix;
        this.sysflag = builder.sysflag;
        this.uniqueKey = builder.uniqueKey;
    }

    @Override
    public String toString() {
        int seqNo = count.getAndIncrement();
        StringBuilder sb = new StringBuilder();
        sb .append(prefix)
                .append(dcn)
                .append(sysflag)
                .append(uniqueKey)
                .append(seqNo > 9 ? String.valueOf(seqNo).substring(1):seqNo);
        return sb.toString();
    }

    public static class Builder{
        private String prefix;
        private String sysflag;
        private String uniqueKey;
        public Builder prefix(String val){this.prefix=val;return this;};
        public Builder sysflag(String val){this.sysflag=val;return this;};
        public Builder uniqueKey(String val){this.uniqueKey=val;return this;};

        public TransIDModel build(){
            return new TransIDModel(this);
        }
    }

    public String validate(String transId){

        if (TRANSID_PATTERN1.matcher(transId.substring(0, 6)).find()
                   && TRANSID_PATTERN2.matcher(transId.substring(6, 8)).find()
                   && TRANSID_PATTERN3.matcher(transId.substring(8, 11)).find()
                  && TRANSID_PATTERN4.matcher(transId.substring(11, 14)).find()
                   && TRANSID_PATTERN5.matcher(transId.substring(14)).find()) {
               return transId;
         }else {
            throw new RuntimeException("transId is invalid!");
        }
    }
}
