package com.dcits.orion.api;

/**
 * Created by lixbb on 2016/1/6.
 */
public interface IField2EncryptDecrypt<T> {
    T encryptDecrypt(T fieldValue,Object[] args);
}
