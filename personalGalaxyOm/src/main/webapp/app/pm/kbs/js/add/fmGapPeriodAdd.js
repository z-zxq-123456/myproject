
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#fmGapPeriodAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmGapPeriodAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmGapPeriodAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_GAP_PERIOD";
		keyFieldsJson.PERIOD_NO=$("#periodNo").val();
		keyFieldsJson.GAP_TYPE=$("#gapType").val();
		keyFieldsJson.PERIOD_TYPE=$("#periodType").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.PERIOD_DESC=$("#periodDesc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmGapPeriodAdd,"json");
}

function callback_fmGapPeriodAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmGapPeriodAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


