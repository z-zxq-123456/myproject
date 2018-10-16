
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_PERIOD_FREQ&tableCol=PERIOD_FREQ,PERIOD_FREQ_DESC",
		id: "periodFreq",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#fmPeriodFreqAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmPeriodFreqAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmPeriodFreqAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_PERIOD_FREQ";
		keyFieldsJson.PERIOD_FREQ=$("#periodFreq").val();
		generalFieldsJson.ADD_NO=$("#addNo").val();
		generalFieldsJson.FIRST_LAST=$("#firstLast").val();
		generalFieldsJson.PERIOD_FREQ_DESC=$("#periodFreqDesc").val();
		generalFieldsJson.HALF_MONTH=$("#halfMonth").val();
		generalFieldsJson.DAY_MTH=$("#dayMth").val();
		generalFieldsJson.DAY_NUM=$("#dayNum").val();
		generalFieldsJson.FORCE_WORK_DAY=$("#forceWorkDay").val();
		generalFieldsJson.CLIENT_SPREAD=$("#clientSpread").val();
		generalFieldsJson.PRIOR_DAYS=$("#priorDays").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmPeriodFreqAdd,"json");
}

function callback_fmPeriodFreqAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmPeriodFreqAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


