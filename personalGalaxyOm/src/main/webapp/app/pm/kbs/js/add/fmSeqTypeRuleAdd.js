
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#fmSeqTypeRuleAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmSeqTypeRuleAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmSeqTypeRuleAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_SEQ_TYPE_RULE";
		keyFieldsJson.SEQ_TYPE=$("#seqType").val();
		generalFieldsJson.RULE_TYPE=$("#ruleType").val();
		generalFieldsJson.END_NO=$("#endNo").val();
		generalFieldsJson.START_NO=$("#startNo").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmSeqTypeRuleAdd,"json");
}

function callback_fmSeqTypeRuleAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmSeqTypeRuleAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


