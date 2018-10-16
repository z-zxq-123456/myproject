
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CATEGORY_TYPE&tableCol=CATEGORY_TYPE,CATEGORY_DESC",
		id: "categoryType",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_STATE&tableCol=STATE,STATE_DESC",
		id: "stateLoc",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COUNTRY&tableCol=COUNTRY,COUNTRY_DESC",
		id: "countryRisk",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COUNTRY&tableCol=COUNTRY,COUNTRY_DESC",
		id: "countryLoc",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COUNTRY&tableCol=COUNTRY,COUNTRY_DESC",
		id: "countryCitizen",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CLIENT_TYPE&tableCol=CLIENT_TYPE,CLIENT_TYPE_DESC",
		id: "clientType",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CR_RATING&tableCol=CR_RATING,CR_RATING_DESC",
		id: "crRating",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=CIF_DOCUMENT_TYPE&tableCol=DOCUMENT_TYPE,DOCUMENT_TYPE",
		id: "globalIdType",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CLIENT_STATUS&tableCol=CLIENT_STATUS,CLIENT_STATUS_DESC",
		id: "clientStatus",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#cifClientAdd").Validform({
		tiptype:2,
		callback:function(form){
			cifClientAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cifClientAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CIF_CLIENT";
		keyFieldsJson.CLIENT_NO=$("#clientNo").val();
		generalFieldsJson.TRAN_STATUS=$("#tranStatus").val();
		generalFieldsJson.ACCT_EXEC=$("#acctExec").val();
		generalFieldsJson.CATEGORY_TYPE=$("#categoryType").val();
		generalFieldsJson.STATE_LOC=$("#stateLoc").val();
		generalFieldsJson.COUNTRY_RISK=$("#countryRisk").val();
		generalFieldsJson.COUNTRY_LOC=$("#countryLoc").val();
		generalFieldsJson.COUNTRY_CITIZEN=$("#countryCitizen").val();
		generalFieldsJson.CLIENT_TYPE=$("#clientType").val();
		generalFieldsJson.CLIENT_SHORT=$("#clientShort").val();
		generalFieldsJson.INLAND_OFFSHORE=$("#inlandOffshore").val();
		generalFieldsJson.LOCATION=$("#location").val();
		generalFieldsJson.TEMP_CLIENT=$("#tempClient").val();
		generalFieldsJson.CR_RATING=$("#crRating").val();
		generalFieldsJson.CTRL_BRANCH=$("#ctrlBranch").val();
		generalFieldsJson.CLIENT_CITY=$("#clientCity").val();
		generalFieldsJson.GLOBAL_ID_TYPE=$("#globalIdType").val();
		generalFieldsJson.CLIENT_STATUS=$("#clientStatus").val();
		generalFieldsJson.GLOBAL_ID=$("#globalId").val();
		generalFieldsJson.CH_CLIENT_NAME=$("#chClientName").val();
		generalFieldsJson.CLIENT_NAME=$("#clientName").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cifClientAdd,"json");
}

function callback_cifClientAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cifClientAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


