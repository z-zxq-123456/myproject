
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_EVENT_DEFAULT_TYPE&tableCol=EVENT_DEFAULT_TYPE",
		id: "eventType",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_PROD_TYPE&tableCol=PROD_TYPE",
		id: "subProdType",
		async: false
	});

	$("#mbProdRelationDefineAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbProdRelationDefineAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbProdRelationDefineAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_PROD_RELATION_DEFINE";
		keyFieldsJson.ASSEMBLE_ID=$("#assembleId").val();
		keyFieldsJson.EVENT_TYPE=$("#eventType").val();
		keyFieldsJson.PROD_TYPE=$("#prodType").val();
		keyFieldsJson.SUB_PROD_TYPE=$("#subProdType").val();
		generalFieldsJson.ASSEMBLE_TYPE=$("#assembleType").val();
		generalFieldsJson.STATUS=$("#status").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbProdRelationDefineAdd,"json");
}

function callback_mbProdRelationDefineAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbProdRelationDefineAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


