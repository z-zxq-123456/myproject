package com.dcits.ensemble.om.pm.module;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;
import java.util.Map;

public class InParamsBean implements Serializable {

	private static final long serialVersionUID = 5081805014577957352L;

	private Map<String, Object> headParams;

	private Map<String, Object> inParams;

	public Map<String, Object> getHeadParams() {
		return headParams;
	}

	public void setHeadParams(Map<String, Object> headParams) {
		this.headParams = headParams;
	}

	public Map<String, Object> getInParams() {
		return inParams;
	}

	public void setInParams(Map<String, Object> inParams) {
		this.inParams = inParams;
	}

	/**
	 * 对象转JSON字符串
	 * @param bean
	 * @return String
	 */
	public static String toJson(InParamsBean bean) {
		String jsonStr = null;
		if (bean != null) {
			JSONObject json = new JSONObject();
			json.put("SYS_HEAD", bean.getHeadParams());
			json.put("BODY", bean.getInParams());
			jsonStr = JSON.toJSONStringWithDateFormat(json, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
		}
		return jsonStr;
	}

	/**
	 * JSON字符串转对象
	 * @param jsonStr
	 * @return InParamsBean
	 */
	@SuppressWarnings("unchecked")
	public static InParamsBean parse(String jsonStr) {
		InParamsBean bean = null;
		if (jsonStr!=null && !"".equals(jsonStr)) {
			JSONObject json = JSON.parseObject(jsonStr);
			Map<String, Object> headParams = json.getObject("SYS_HEAD", Map.class);
			Map<String, Object> inParams = json.getObject("BODY", Map.class);

			bean = new InParamsBean();
			bean.setHeadParams(headParams);
			bean.setInParams(inParams);
		}
		return bean;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InParamsBean [headParams=");
		builder.append(headParams);
		builder.append(", inParams=");
		builder.append(inParams);
		builder.append("]");
		return builder.toString();
	}
}
