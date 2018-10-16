
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCode",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCodeProfit",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCodeLoss",
		async: false
	});

	$("#glCcyCtrlAcctTblAdd").Validform({
		tiptype:2,
		callback:function(form){
			glCcyCtrlAcctTblAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function glCcyCtrlAcctTblAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="GL_CCY_CTRL_ACCT_TBL";
		keyFieldsJson.GL_CODE=$("#glCode").val();
		generalFieldsJson.GL_CODE_PROFIT=$("#glCodeProfit").val();
		generalFieldsJson.GL_CODE_LOSS=$("#glCodeLoss").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_glCcyCtrlAcctTblAdd,"json");
}

function callback_glCcyCtrlAcctTblAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function glCcyCtrlAcctTblAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


