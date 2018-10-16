
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCode",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_PROFIT_CENTRE&tableCol=PROFIT_CENTRE,PROFIT_CENTRE_DESC",
		id: "profitCentre",
		async: false
	});
	$("#glCustomRuleAdd").Validform({
		tiptype:2,
		callback:function(form){
			glCustomRuleAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function glCustomRuleAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="GL_CUSTOM_RULE";
		keyFieldsJson.RULE_NO=$("#ruleNo").val();
		generalFieldsJson.GL_CODE=$("#glCode").val();
		generalFieldsJson.RULE_DESC=$("#ruleDesc").val();
		generalFieldsJson.RULE_EXPRESSION=$("#ruleExpression").val();
		generalFieldsJson.BUSSINESS_UNIT=$("#bussinessUnit").val();
		generalFieldsJson.PROFIT_CENTRE=$("#profitCentre").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_glCustomRuleAdd,"json");
}

function callback_glCustomRuleAdd(json){
	if (json.success) {
        parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function glCustomRuleAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


