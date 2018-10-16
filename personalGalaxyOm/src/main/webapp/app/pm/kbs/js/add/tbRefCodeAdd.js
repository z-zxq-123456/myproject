
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#tbRefCodeAdd").Validform({
		tiptype:2,
		callback:function(form){
			tbRefCodeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function tbRefCodeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="TB_REF_CODE";
		keyFieldsJson.DOMAIN=$("#domain").val();
		keyFieldsJson.REF_GROUP=$("#refGroup").val();
		keyFieldsJson.REF_LANG=$("#refLang").val();
		generalFieldsJson.FIELD_VALUE=$("#fieldValue").val();
		generalFieldsJson.MEANING=$("#meaning").val();
		generalFieldsJson.ABBREVIATION=$("#abbreviation").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_tbRefCodeAdd,"json");
}

function callback_tbRefCodeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function tbRefCodeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


