$(document).ready(function() {

	$("#rcListCategoryAdd").Validform({
		tiptype:2,
		callback:function(){
			rcListCategoryAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function rcListCategoryAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="RC_LIST_CATEGORY";
    keyFieldsJson.LIST_CATEGORY=$("#listCategory").val();
    generalFieldsJson.APP_IND=$("#appInd").val();
    generalFieldsJson.LIST_CATEGORY_DESC=$("#listCategoryDesc").val();
    generalFieldsJson.PROPERTY=$("#property").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_rcListCategoryAdd,"json");
}

function callback_rcListCategoryAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function rcListCategoryAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}