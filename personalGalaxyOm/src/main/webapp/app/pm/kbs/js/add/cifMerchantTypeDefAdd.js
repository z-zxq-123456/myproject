
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#cifMerchantTypeDefAdd").Validform({
		tiptype:2,
		callback:function(form){
			cifMerchantTypeDefAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cifMerchantTypeDefAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CIF_MERCHANT_TYPE_DEF";
		keyFieldsJson.CC_SUB_TYPE=$("#ccSubType").val();
		generalFieldsJson.CC_TYPE=$("#ccType").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.CC_TYPE_DESC=$("#ccTypeDesc").val();
		generalFieldsJson.CC_SUB_TYPE_DESC=$("#ccSubTypeDesc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cifMerchantTypeDefAdd,"json");
}

function callback_cifMerchantTypeDefAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cifMerchantTypeDefAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


