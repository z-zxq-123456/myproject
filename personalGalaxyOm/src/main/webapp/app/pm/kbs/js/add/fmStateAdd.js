
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#fmStateAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmStateAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmStateAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_STATE";
		keyFieldsJson.STATE=$("#state").val();
		keyFieldsJson.COUNTRY=$("#country").val();
		generalFieldsJson.STATE_DESC=$("#stateDesc").val();
		generalFieldsJson.WEEKEND_2=$("#weekend2").val();
		generalFieldsJson.WEEKEND_1=$("#weekend1").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmStateAdd,"json");
}

function callback_fmStateAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmStateAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


