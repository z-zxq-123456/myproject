
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_PROD_CLASS&tableCol=PROD_CLASS",
		id: "prodClass",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
	$("#mbProdTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbProdTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbProdTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_PROD_TYPE";
		keyFieldsJson.PROD_TYPE=$("#prodType").val();
		generalFieldsJson.PROD_CLASS=$("#prodClass").val();
		generalFieldsJson.PROD_DESC=$("#prodDesc").val();
		generalFieldsJson.STATUS=$("#status").val();
		generalFieldsJson.BASE_PROD_TYPE=$("#baseProdType").val();
		generalFieldsJson.PROD_GROUP=$("#prodGroup").val();
		generalFieldsJson.PROD_RANGE=$("#prodRange").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbProdTypeAdd,"json");
}

function callback_mbProdTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbProdTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


