
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#cifSalutationAdd").Validform({
		tiptype:2,
		callback:function(form){
			cifSalutationAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cifSalutationAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CIF_SALUTATION";
		keyFieldsJson.SALUTATION=$("#salutation").val();
		generalFieldsJson.SALUTATION_DESC=$("#salutationDesc").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.GENDER_FLAG=$("#genderFlag").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cifSalutationAdd,"json");
}

function callback_cifSalutationAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cifSalutationAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


