
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_PROD_TYPE&tableCol=PROD_TYPE",
		id: "prodSubType",
		async: false
	});

	$("#mbProdGroupAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbProdGroupAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbProdGroupAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_PROD_GROUP";
		keyFieldsJson.PROD_SUB_TYPE=$("#prodSubType").val();
		keyFieldsJson.PROD_TYPE=$("#prodType").val();
		generalFieldsJson.SEQ_NO=$("#seqNo").val();
		generalFieldsJson.DEFAULT_PROD=$("#defaultProd").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbProdGroupAdd,"json");
}

function callback_mbProdGroupAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbProdGroupAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


