
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CATEGORY_TYPE&tableCol=CATEGORY_TYPE,CATEGORY_DESC",
		id: "categoryType",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CLIENT_TYPE&tableCol=CLIENT_TYPE,CLIENT_TYPE_DESC",
		id: "clientType",
		async: false
	});

	$("#cifCategoryTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			cifCategoryTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cifCategoryTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CIF_CATEGORY_TYPE";
		keyFieldsJson.CATEGORY_TYPE=$("#categoryType").val();
		generalFieldsJson.REP_OFFICE=$("#repOffice").val();
		generalFieldsJson.CATEGORY_DESC=$("#categoryDesc").val();
		generalFieldsJson.CENTRAL_BANK=$("#centralBank").val();
		generalFieldsJson.BANK=$("#bank").val();
		generalFieldsJson.BROKER=$("#broker").val();
		generalFieldsJson.OTHER=$("#other").val();
		generalFieldsJson.CORPORATION=$("#corporation").val();
		generalFieldsJson.FIN_INSTITUTION=$("#finInstitution").val();
		generalFieldsJson.GOVERNMENT=$("#government").val();
		generalFieldsJson.INDIVIDUAL=$("#individual").val();
		generalFieldsJson.INTL_INSTITUTION=$("#intlInstitution").val();
		generalFieldsJson.JOINT=$("#joint").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.CLIENT_TYPE=$("#clientType").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cifCategoryTypeAdd,"json");
}

function callback_cifCategoryTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cifCategoryTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


