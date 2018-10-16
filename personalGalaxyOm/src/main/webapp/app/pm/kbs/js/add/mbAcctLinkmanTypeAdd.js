
$(document).ready(function() {

	$("#mbAcctLinkmanTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbAcctLinkmanTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbAcctLinkmanTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_ACCT_LINKMAN_TYPE";
		keyFieldsJson.LINKMAN_TYPE=$("#linkmanType").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.LINKMAN_DESC=$("#linkmanDesc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbAcctLinkmanTypeAdd,"json");
}

function callback_mbAcctLinkmanTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbAcctLinkmanTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


