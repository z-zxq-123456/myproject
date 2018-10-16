
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#tbVoucherChangeAdd").Validform({
		tiptype:2,
		callback:function(form){
			tbVoucherChangeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function tbVoucherChangeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="TB_VOUCHER_CHANGE";
		generalFieldsJson.STATUS_DESC=$("#statusDesc").val();
		generalFieldsJson.UPDATE_AFTER=$("#updateAfter").val();
		generalFieldsJson.UPDATE_BEFORE=$("#updateBefore").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.NEW_STATUS=$("#newStatus").val();
		generalFieldsJson.OLD_STATUS=$("#oldStatus").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_tbVoucherChangeAdd,"json");
}

function callback_tbVoucherChangeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function tbVoucherChangeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


