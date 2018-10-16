
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#fmCityAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmCityAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmCityAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_CITY";
		keyFieldsJson.CITY=$("#city").val();
		keyFieldsJson.STATE=$("#state").val();
        keyFieldsJson.COUNTRY=$("#country").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.CITY_DESC=$("#cityDesc").val();
		generalFieldsJson.CITY_TEL=$("#cityTel").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmCityAdd,"json");
}

function callback_fmCityAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmCityAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


