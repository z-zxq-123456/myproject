
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#cifDocumentTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			cifDocumentTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cifDocumentTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CIF_DOCUMENT_TYPE";
		keyFieldsJson.DOCUMENT_TYPE=$("#documentType").val();
		generalFieldsJson.DOCUMENT_TYPE_DESC=$("#documentTypeDesc").val();
		generalFieldsJson.APP_IND=$("#appInd").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.DOCUMENT_TYPE_SHORT=$("#documentTypeShort").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cifDocumentTypeAdd,"json");
}

function callback_cifDocumentTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cifDocumentTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


