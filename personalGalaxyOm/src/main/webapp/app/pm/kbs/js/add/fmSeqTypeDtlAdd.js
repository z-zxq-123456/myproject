
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#fmSeqTypeDtlAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmSeqTypeDtlAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmSeqTypeDtlAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_SEQ_TYPE_DTL";
		keyFieldsJson.SEQ_TYPE=$("#seqType").val();
		keyFieldsJson.SEQ_RESET_PARAM=$("#seqResetParam").val();
		generalFieldsJson.END_NO=$("#endNo").val();
		generalFieldsJson.START_NO=$("#startNo").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.LAST_NO=$("#lastNo").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmSeqTypeDtlAdd,"json");
}

function callback_fmSeqTypeDtlAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmSeqTypeDtlAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


