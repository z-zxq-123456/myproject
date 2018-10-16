
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=KBS_FM_MODULE&tableCol=MODULE_ID,MODULE_NAME",
		id: "prodGrp",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
		id: "ccy",
		async: false
	});

	$("#fmGapTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmGapTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmGapTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_GAP_TYPE";
		keyFieldsJson.GAP_TYPE=$("#gapType").val();
		generalFieldsJson.GAP_START=$("#gapStart").val();
		generalFieldsJson.WORKING_DAYS=$("#workingDays").val();
		generalFieldsJson.GAP_TYPE_DESC=$("#gapTypeDesc").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.PROD_GRP=$("#prodGrp").val();
		generalFieldsJson.CCY=$("#ccy").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmGapTypeAdd,"json");
}

function callback_fmGapTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmGapTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


