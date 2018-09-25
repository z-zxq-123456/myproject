package com.dcits.galaxy.orion.encrypt;

import com.dcits.orion.api.IField2EncryptDecrypt;

import org.springframework.stereotype.Repository;

/**
 * Created by lixbb on 2016/1/7.
 */
@Repository
public class FieldEncrypt implements IField2EncryptDecrypt<String> {
    @Override
    public String encryptDecrypt(String fieldValue, Object[] args) {
        String ret = "<" + fieldValue + ">";
        if (args != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(ret);
            for (Object arg : args) {
                sb.append(",").append(arg);
            }
            ret = sb.toString();
        }
        return ret;
    }
}
