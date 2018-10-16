
$(document).ready(function() {
     getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
	$("#cifClientNatureDefAdd").Validform({
		tiptype:2,
		callback:function(form){
			cifClientNatureDefAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cifClientNatureDefAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CIF_CLIENT_NATURE_DEF";
		keyFieldsJson.CLIENT_NATURE=$("#clientNature").val();
		generalFieldsJson.LOSS_IND=$("#lossInd").val();
		generalFieldsJson.CLIENT_NATURE_DESC=$("#clientNatureDesc").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cifClientNatureDefAdd,"json");
}

function callback_cifClientNatureDefAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cifClientNatureDefAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


