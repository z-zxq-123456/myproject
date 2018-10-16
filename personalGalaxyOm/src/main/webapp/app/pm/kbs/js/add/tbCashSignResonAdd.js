
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_TRAN_DEF&tableCol=TRAN_TYPE,TRAN_TYPE_DESC",
		id: "overTranType",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_TRAN_DEF&tableCol=TRAN_TYPE,TRAN_TYPE_DESC",
		id: "shortTranType",
		async: false
	});

	$("#tbCashSignResonAdd").Validform({
		tiptype:2,
		callback:function(form){
			tbCashSignResonAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function tbCashSignResonAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="TB_CASH_SIGN_RESON";
		keyFieldsJson.SHTG_ID=$("#shtgId").val();
		keyFieldsJson.SHTG_TYPE=$("#shtgType").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.OVER_TRAN_TYPE=$("#overTranType").val();
		generalFieldsJson.SHORT_TRAN_TYPE=$("#shortTranType").val();
		generalFieldsJson.SHTG_DESC=$("#shtgDesc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_tbCashSignResonAdd,"json");
}

function callback_tbCashSignResonAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function tbCashSignResonAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


