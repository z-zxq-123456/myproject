
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CR_RATING&tableCol=CR_RATING,CR_RATING_DESC",
		id: "crRating",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#cifCrRatingAdd").Validform({
		tiptype:2,
		callback:function(form){
			cifCrRatingAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cifCrRatingAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CIF_CR_RATING";
		keyFieldsJson.CR_RATING=$("#crRating").val();
		generalFieldsJson.CR_RATING_DESC=$("#crRatingDesc").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cifCrRatingAdd,"json");
}

function callback_cifCrRatingAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cifCrRatingAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


