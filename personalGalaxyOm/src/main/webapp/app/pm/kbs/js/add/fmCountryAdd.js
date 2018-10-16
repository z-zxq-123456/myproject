
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
		id: "ccy",
		async: false
	});

	$("#fmCountryAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmCountryAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmCountryAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_COUNTRY";
		keyFieldsJson.COUNTRY=$("#country").val();
		generalFieldsJson.COUNTRY_DESC=$("#countryDesc").val();
		generalFieldsJson.SAFE_CODE=$("#safeCode").val();
		generalFieldsJson.ISO_CODE=$("#isoCode").val();
		generalFieldsJson.NCCT=$("#ncct").val();
		generalFieldsJson.PSC=$("#psc").val();
		generalFieldsJson.REGION=$("#region").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.CCY=$("#ccy").val();
		generalFieldsJson.COUNTRY_TEL=$("#countryTel").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmCountryAdd,"json");
}

function callback_fmCountryAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmCountryAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


