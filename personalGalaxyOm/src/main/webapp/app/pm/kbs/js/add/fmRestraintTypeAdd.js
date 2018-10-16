
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#fmRestraintTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmRestraintTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmRestraintTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_RESTRAINT_TYPE";
		keyFieldsJson.RESTRAINT_TYPE=$("#restraintType").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.EOD_IMPOUND_FALG=$("#eodImpoundFalg").val();
		generalFieldsJson.MANUAL_RES_FLAG=$("#manualResFlag").val();
		generalFieldsJson.STOP_WTD_FALG=$("#stopwtdfalg").val();
		generalFieldsJson.RESTRAINT_CLASS=$("#restraintClass").val();
		generalFieldsJson.RESTRAINT_DESC=$("#restraintDesc").val();
		generalFieldsJson.AH_BU=$("#ahBu").val();
		generalFieldsJson.SYSTEM_USE_FLAG=$("systemUserFlag").val();
		generalFieldsJson.MANUAL_UNRES_FLAG=$("#manualUnresFlag").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmRestraintTypeAdd,"json");
}

function callback_fmRestraintTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmRestraintTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


