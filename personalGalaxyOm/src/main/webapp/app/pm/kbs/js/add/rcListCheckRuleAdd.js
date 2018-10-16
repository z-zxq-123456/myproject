$(document).ready(function() {

	$("#rcListCheckRuleAdd").Validform({
		tiptype:2,
		callback:function(){
			rcListCheckRuleAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function rcListCheckRuleAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="RC_LIST_CHECK_RULE";
    keyFieldsJson.LIST_TYPE=$("#listType").val();
    generalFieldsJson.EVENT_FORBID_IND=$("#eventForbidInd").val();
    generalFieldsJson.FORBID_CHANNELS=$("#forbidChannels").val();
    generalFieldsJson.VERIFY_FLAG=$("#verifyFlag").val();
    generalFieldsJson.CHANNEL_FORBID_IND=$("#channelForbidInd").val();
    generalFieldsJson.FORBID_EVENTS=$("#forbidEvents").val();
    generalFieldsJson.FORBID_TERM=$("#forbidTerm").val();
    generalFieldsJson.FORBID_TERM_TYPE=$("#forbidTermType").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_rcListCheckRuleAdd,"json");
}

function callback_rcListCheckRuleAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function rcListCheckRuleAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}