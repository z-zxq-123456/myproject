$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
		id: "branch",
		async: false
	});

	$("#glReservePayCcyAdd").Validform({
		tiptype:2,
		callback:function(){
			glReservePayCcyAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function glReservePayCcyAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="GL_RESERVE_PAY_CCY";
    keyFieldsJson.BRANCH=$("#branch").val();
    keyFieldsJson.PAY_CCY=$("#payCcy").val();
    keyFieldsJson.PAY_TYPE=$("#payType").val();
    generalFieldsJson.COLL_IND=$("#collInd").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_glReservePayCcyAdd,"json");
}

function callback_glReservePayCcyAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function glReservePayCcyAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}