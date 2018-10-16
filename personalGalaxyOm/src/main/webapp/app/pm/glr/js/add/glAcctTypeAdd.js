
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCodeAl",
		async: false
	});

	$("#glAcctTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			glAcctTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function glAcctTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="GL_ACCT_TYPE";
		keyFieldsJson.ACCT_TYPE=$("#acctType").val();
		generalFieldsJson.ACCT_TYPE_DESC=$("#acctTypeDesc").val();
		generalFieldsJson.DEPOSIT_TYPE=$("#depositType").val();
		generalFieldsJson.GL_CODE_AL=$("#glCodeAl").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_glAcctTypeAdd,"json");
}

function callback_glAcctTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function glAcctTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


