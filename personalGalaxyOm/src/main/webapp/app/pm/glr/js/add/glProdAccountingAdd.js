
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCodeOdpRec",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCodeOdpI",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCodeOdpAcr",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCodeOdiRec",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCodeOdiI",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCodeOdiAcr",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCodeL",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCodeIntRec",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCodeIntPay",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCodeIntI",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCodeIntE",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCodeIntAcr",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCodeALoss",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCodeAdjust",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCodeA",
		async: false
	});
    getPkList({
    		url: contextPath + "/baseCommon/pklistBase?tableName=GL_PROD_MAPPING&tableCol=PROD_TYPE,PROD_DESC",
    		id: "prodType",
    		async: false
    	});

	$("#glProdAccountingAdd").Validform({
		tiptype:2,
		callback:function(form){
			glProdAccountingAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function glProdAccountingAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="GL_PROD_ACCOUNTING";
	paraJson.systemId="GLR";
		keyFieldsJson.ACCOUNTING_STATUS=$("#accountingStatus").val();
		keyFieldsJson.PROD_TYPE=$("#prodType").val();
		generalFieldsJson.GL_CODE_ODP_REC=$("#glCodeOdpRec").val();
		generalFieldsJson.GL_CODE_ODP_I=$("#glCodeOdpI").val();
		generalFieldsJson.GL_CODE_ODP_ACR=$("#glCodeOdpAcr").val();
		generalFieldsJson.GL_CODE_ODI_REC=$("#glCodeOdiRec").val();
		generalFieldsJson.GL_CODE_ODI_I=$("#glCodeOdiI").val();
		generalFieldsJson.GL_CODE_ODI_ACR=$("#glCodeOdiAcr").val();
		generalFieldsJson.GL_CODE_L=$("#glCodeL").val();
		generalFieldsJson.GL_CODE_INT_REC=$("#glCodeIntRec").val();
		generalFieldsJson.GL_CODE_INT_PAY=$("#glCodeIntPay").val();
		generalFieldsJson.GL_CODE_INT_I=$("#glCodeIntI").val();
		generalFieldsJson.GL_CODE_INT_E=$("#glCodeIntE").val();
		generalFieldsJson.GL_CODE_INT_ACR=$("#glCodeIntAcr").val();
		generalFieldsJson.GL_CODE_A_LOSS=$("#glCodeALoss").val();
		generalFieldsJson.GL_CODE_ADJUST=$("#glCodeAdjust").val();
		generalFieldsJson.GL_CODE_A=$("#glCodeA").val();
		generalFieldsJson.BUSINESS_UNIT=$("#businessUnit").val();
		generalFieldsJson.PROFIT_CENTRE=$("#profitCentre").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_glProdAccountingAdd,"json");
}

function callback_glProdAccountingAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function glProdAccountingAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


