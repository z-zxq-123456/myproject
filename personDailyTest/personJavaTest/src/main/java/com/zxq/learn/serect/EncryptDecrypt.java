package com.zxq.learn.serect;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * Created{ by zhouxqh} on 2018/3/1.
 */
public class EncryptDecrypt {

    public static void main(String []args){
        try {

            Cipher cipher = Cipher.getInstance("DES");
            /*产生随机秘钥*/
            SecretKey key = KeyGenerator.getInstance("DES").generateKey();

            /*加密初始化*/
            cipher.init(Cipher.ENCRYPT_MODE,key);
            String pass = "000000";
            /*加密*/
            byte[] bytes = cipher.doFinal(pass.getBytes());
            /*解密*/
            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] origins = cipher.doFinal(bytes);
            String pin = new String(origins);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
