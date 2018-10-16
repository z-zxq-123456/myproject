
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_ACCT_NATURE_DEF&tableCol=ACCT_NATURE,DESCRIPTION",
		id: "acctNature",
		async: false
	});

	$("#mbAcctNatureDefAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbAcctNatureDefAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbAcctNatureDefAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_ACCT_NATURE_DEF";
		keyFieldsJson.ACCT_NATURE=$("#acctNature").val();
		generalFieldsJson.DESCRIPTION=$("#description").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbAcctNatureDefAdd,"json");
}

function callback_mbAcctNatureDefAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbAcctNatureDefAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


