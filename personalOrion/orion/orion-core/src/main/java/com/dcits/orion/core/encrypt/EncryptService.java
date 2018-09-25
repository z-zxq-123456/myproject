package com.dcits.orion.core.encrypt;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lixbb on 2016/1/6.
 */
public class EncryptService {
    private String serviceId;

    private Map<String ,EncryptField> requestFields = new HashMap<String ,EncryptField>();
    private Map<String ,EncryptField> responseFields = new HashMap<String ,EncryptField>();

    public Map<String, EncryptField> getRequestFields() {
        return requestFields;
    }

    public void setRequestFields(Map<String, EncryptField> requestFields) {
        this.requestFields = requestFields;
    }

    public Map<String, EncryptField> getResponseFields() {
        return responseFields;
    }

    public void setResponseFields(Map<String, EncryptField> responseFields) {
        this.responseFields = responseFields;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

}
