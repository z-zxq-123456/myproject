$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_ACCT_NATURE_DEF&tableCol=ACCT_NATURE,DESCRIPTION",
		id: "acctNature",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_PERIOD_FREQ&tableCol=PERIOD_FREQ,PERIOD_FREQ_DESC",
		id: "periodFreq",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_RESTRAINT_TYPE&tableCol=RESTRAINT_TYPE,RESTRAINT_DESC",
		id: "restraintType",
		async: false
	});
	$("#mbAnnualSurveyAdd").Validform({
		tiptype:2,
		callback:function(){
			mbAnnualSurveyAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbAnnualSurveyAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_ANNUAL_SURVEY";
    keyFieldsJson.ACCT_NATURE=$("#acctNature").val();
    generalFieldsJson.PERIOD_FREQ=$("#periodFreq").val();
    generalFieldsJson.RESET_DATE=$("#resetDate").val();
    generalFieldsJson.STOP_DATE=$("#stopDate").val();
    generalFieldsJson.STOP_FLAG=$("#stopFlag").val();
    generalFieldsJson.RESTRAINT_TYPE=$("#restraintType").val();
    generalFieldsJson.DAYS=$("#days").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbAnnualSurveyAdd,"json");
}

function callback_mbAnnualSurveyAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbAnnualSurveyAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}