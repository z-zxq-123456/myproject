
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#fmRefCodeAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmRefCodeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmRefCodeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_REF_CODE";
		keyFieldsJson.FIELD_VALUE=$("#fieldValue").val();
		keyFieldsJson.REF_LANG=$("#refLang").val();
		keyFieldsJson.DOMAIN=$("#domain").val();
		generalFieldsJson.MEANING=$("#meaning").val();
		generalFieldsJson.PARA_ROW_NUM=$("#paraRowNum").val();
		generalFieldsJson.PARA_FLAG=$("#paraFlag").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.ABBREVIATION=$("#abbreviation").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmRefCodeAdd,"json");
}

function callback_fmRefCodeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmRefCodeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


