
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#cifDistCodeAdd").Validform({
		tiptype:2,
		callback:function(form){
			cifDistCodeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cifDistCodeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CIF_DIST_CODE";
		keyFieldsJson.CITY=$("#city").val();
		keyFieldsJson.PROVINCE=$("#province").val();
		keyFieldsJson.DIST_CODE=$("#distCode").val();
		keyFieldsJson.DIST_NAME=$("#distName").val();
		generalFieldsJson.DIST_GRADE=$("#distGrade").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.STATE=$("#state").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cifDistCodeAdd,"json");
}

function callback_cifDistCodeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cifDistCodeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


