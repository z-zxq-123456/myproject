
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=GL_PROD_MAPPING&tableCol=MAPPING_TYPE,MAPPING_DESC",
		id: "mappingType",
		async: false
	});
	getPkList({
		url: contextPath + "/pklist/getGlProdMappingType",
		id: "prodType",
		async: false
	});

	$("#glProdMappingAdd").Validform({
		tiptype:2,
		callback:function(form){
			glProdMappingAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function glProdMappingAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="GL_PROD_MAPPING";
	paraJson.systemId="GLR";
		keyFieldsJson.MAPPING_TYPE=$("#mappingType").val();
		generalFieldsJson.PROD_TYPE=$("#prodType").val();
		generalFieldsJson.PROD_DESC=$("#prodDesc").val();
		generalFieldsJson.MAPPING_DESC=$("#mappingDesc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_glProdMappingAdd,"json");
}

function callback_glProdMappingAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function glProdMappingAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


