
$(document).ready(function() {

	$("#mbLinkConditionAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbLinkConditionAdd();
			return false;
		}
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
	$(".select2").select2();
});

function mbLinkConditionAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_LINK_CONDITION";
		keyFieldsJson.CONDITION_ID=$("#conditionId").val();
		generalFieldsJson.CONDITION_DESC=$("#conditionDesc").val();
		generalFieldsJson.STATUS=$("#status").val();
		generalFieldsJson.CONDITION_RULE=$("#conditionRule").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbLinkConditionAdd,"json");
}

function callback_mbLinkConditionAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbLinkConditionAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


