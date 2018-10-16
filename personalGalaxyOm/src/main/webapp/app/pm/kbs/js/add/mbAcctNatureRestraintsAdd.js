
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_ACCT_NATURE_DEF&tableCol=ACCT_NATURE,DESCRIPTION",
		id: "acctNature",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_RESTRAINT_TYPE&tableCol=RESTRAINT_TYPE,RESTRAINT_DESC",
		id: "restraintType",
		async: false
	});

	$("#mbAcctNatureRestraintsAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbAcctNatureRestraintsAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbAcctNatureRestraintsAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_ACCT_NATURE_RESTRAINTS";
		keyFieldsJson.ACCT_NATURE=$("#acctNature").val();
		keyFieldsJson.RESTRAINT_TYPE=$("#restraintType").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbAcctNatureRestraintsAdd,"json");
}

function callback_mbAcctNatureRestraintsAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbAcctNatureRestraintsAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


