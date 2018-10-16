
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
	$("#mbEventLinkAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbEventLinkAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbEventLinkAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_EVENT_LINK";
		keyFieldsJson.LINK_EVENT_TYPE=$("#linkEventType").val();
		keyFieldsJson.LINK_PROD_TYPE=$("#linkProdType").val();
		keyFieldsJson.ORIG_EVENT_TYPE=$("#origEventType").val();
		keyFieldsJson.ORIG_PROD_TYPE=$("#origProdType").val();
		keyFieldsJson.PROD_TYPE=$("#prodType").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.LINK_CONDITION=$("#linkCondition").val();
		generalFieldsJson.STATUS=$("#status").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbEventLinkAdd,"json");
}

function callback_mbEventLinkAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbEventLinkAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


