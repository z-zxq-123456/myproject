$(document).ready(function() {

	$("#rcListTypeAdd").Validform({
		tiptype:2,
		callback:function(){
			rcListTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function rcListTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="RC_LIST_TYPE";
    keyFieldsJson.LIST_TYPE=$("#listType").val();
    generalFieldsJson.LIST_CATEGORY=$("#listCategory").val();
    generalFieldsJson.VERIFY_TERM=$("#verifyTerm").val();
    generalFieldsJson.VERIFY_IND=$("#verifyInd").val();
    generalFieldsJson.VERIFY_ACCT_IND=$("#verifyAcctInd").val();
    generalFieldsJson.STATUS=$("#status").val();
    generalFieldsJson.RELATE_ACCT_IND=$("#relateAcctInd").val();
    generalFieldsJson.LIST_TYPE_DESC=$("#listTypeDesc").val();
    generalFieldsJson.LIST_ORG=$("#listOrg").val();
    generalFieldsJson.VERIFY_TERM_TYPE=$("#verifyTermType").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_rcListTypeAdd,"json");
}

function callback_rcListTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function rcListTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}