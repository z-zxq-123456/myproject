
$(document).ready(function() {
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_FEE_RATE&tableCol=IRL_SEQ_NO,IRL_SEQ_NO",
				id: "irlSeqNo",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_TYPE&tableCol=INT_TAX_TYPE,INT_TAX_TYPE_DESC",
				id: "intType",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
				id: "company",
				async: false
			});

	$("#irlFeeMatrixAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlFeeMatrixAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function irlFeeMatrixAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_FEE_MATRIX";
		keyFieldsJson.MATRIX_NO=$("#matrixNo").val();
		generalFieldsJson.IRL_SEQ_NO=$("#irlSeqNo").val();
		generalFieldsJson.FEE_AMT=$("#feeAmt").val();
		generalFieldsJson.BOUNDARY=$("#boundary").val();
		generalFieldsJson.FEE_RATE=$("#feeRate").val();
		generalFieldsJson.FLOAT_RATE=$("#floatRate").val();
		generalFieldsJson.INT_TYPE=$("#intType").val();

	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlFeeMatrixAdd,"json");
}

function callback_irlFeeMatrixAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlFeeMatrixAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

