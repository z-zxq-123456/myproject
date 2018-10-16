$(document).ready(function() {

	$("#glReservePaySubjectAdd").Validform({
		tiptype:2,
		callback:function(){
			glReservePaySubjectAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function glReservePaySubjectAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="GL_RESERVE_PAY_SUBJECT";
    keyFieldsJson.PAY_GL_CODE=$("#payGlCode").val();
    keyFieldsJson.PAY_TYPE=$("#payType").val();
    generalFieldsJson.COUNT_RATE=$("#countRate").val();
    generalFieldsJson.SUM_BAL_FLAG=$("#sumBalFlag").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_glReservePaySubjectAdd,"json");
}

function callback_glReservePaySubjectAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function glReservePaySubjectAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}