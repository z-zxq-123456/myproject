
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#fmProfitCentreAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmProfitCentreAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmProfitCentreAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_PROFIT_CENTRE";
		keyFieldsJson.PROFIT_CENTRE=$("#profitCentre").val();
		generalFieldsJson.PROFIT_CENTRE_DESC=$("#profitCentreDesc").val();
		generalFieldsJson.PROFIT_CENTRE_TYPE=$("#profitCentreType").val();
		generalFieldsJson.PROFIT_CENTRE_DESC_EN=$("#profitCentreDescEn").val();
		generalFieldsJson.PROFIT_CENTRE_LEVEL=$("#profitCentreLevel").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmProfitCentreAdd,"json");
}

function callback_fmProfitCentreAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmProfitCentreAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


