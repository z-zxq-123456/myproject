package com.dcits.ensemble.om.pm.module;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Map;

public class OutParamsBean implements Serializable {

	private static final long serialVersionUID = 6610721257650389970L;

	private Map<String, Object> headParams;

	private Map<String, Object> outParams;

	public Map<String, Object> getHeadParams() {
		return headParams;
	}

	public void setHeadParams(Map<String, Object> headParams) {
		this.headParams = headParams;
	}

	public Map<String, Object> getOutParams() {
		return outParams;
	}

	public void setOutParams(Map<String, Object> outParams) {
		this.outParams = outParams;
	}

	/**
	 * 对象转JSON字符串
	 * @param bean
	 * @return String
	 */
	public static String toJson(OutParamsBean bean) {
		String jsonStr = null;
		if (bean != null) {
			JSONObject json = new JSONObject();
			json.put("SYS_HEAD", bean.getHeadParams());
			json.put("BODY", bean.getOutParams());
			jsonStr = json.toJSONString();
		}
		return jsonStr;
	}

	/**
	 * JSON字符串转对象
	 * @param jsonStr
	 * @return OutParamsBean
	 */
	@SuppressWarnings("unchecked")
	public static OutParamsBean parse(String jsonStr) {
		OutParamsBean bean = null;
		if (jsonStr!=null && !"".equals(jsonStr)) {
			JSONObject json = JSON.parseObject(jsonStr);
			Map<String, Object> headParams = json.getObject("SYS_HEAD", Map.class);
			Map<String, Object> outParams = json.getObject("BODY", Map.class);

			bean = new OutParamsBean();
			bean.setHeadParams(headParams);
			bean.setOutParams(outParams);
		}
		return bean;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OutParamsBean [headParams=");
		builder.append(headParams);
		builder.append(", outParams=");
		builder.append(outParams);
		builder.append("]");
		return builder.toString();
	}
}
