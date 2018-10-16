
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH_TBL&tableCol=BRANCH,BRANCH_NAME",
		id: "branch",
		async: false
	});

	$("#fmPasswordFailureTimesAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmPasswordFailureTimesAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmPasswordFailureTimesAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_PASSWORD_FAILURE_TIMES";
		keyFieldsJson.BRANCH=$("#branch").val();
		keyFieldsJson.COMPANY=$("#company").val();
		keyFieldsJson.LAST_CHANGE_DATE=$("#lastChangeDate").val();
		keyFieldsJson.LAST_CHANGE_OFFICER=$("#lastChangeOfficer").val();
		keyFieldsJson.LAST_CHANGE_TIME=$("#lastChangeTime").val();
		keyFieldsJson.MAX_FAILUER_TIMES=$("#maxFailuerTimes").val();
		keyFieldsJson.RESET_IND=$("#resetInd").val();
		generalFieldsJson.CHANNEL=$("#channel").val();
		generalFieldsJson.PASSWORD_TYPE=$("#passwordType").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmPasswordFailureTimesAdd,"json");
}

function callback_fmPasswordFailureTimesAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmPasswordFailureTimesAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


