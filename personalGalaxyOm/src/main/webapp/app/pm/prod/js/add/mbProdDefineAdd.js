
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
	$("#mbProdDefineAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbProdDefineAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbProdDefineAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_PROD_DEFINE";
		keyFieldsJson.PROD_TYPE=$("#prodType").val();
		keyFieldsJson.SEQ_NO=$("#seqNo").val();
		generalFieldsJson.ASSEMBLE_TYPE=$("#assembleType").val();
		generalFieldsJson.STATUS=$("#status").val();
		generalFieldsJson.ASSEMBLE_ID=$("#assembleId").val();
		generalFieldsJson.ATTR_KEY=$("#attrKey").val();
		generalFieldsJson.ATTR_VALUE=$("#attrValue").val();
		generalFieldsJson.EVENT_DEFAULT=$("#eventDefault").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbProdDefineAdd,"json");
}

function callback_mbProdDefineAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbProdDefineAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


