
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#fmSeqTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmSeqTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmSeqTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_SEQ_TYPE";
		keyFieldsJson.SEQ_TYPE=$("#seqType").val();
		generalFieldsJson.CCY_RESET=$("#ccyReset").val();
		generalFieldsJson.START_NO=$("#startNo").val();
		generalFieldsJson.BRANCH_RESET=$("#branchReset").val();
		generalFieldsJson.END_NO=$("#endNo").val();
		generalFieldsJson.PROD_TYPE_RESET=$("#prodTypeReset").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.IS_INDVL_RESET=$("#isIndvlReset").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmSeqTypeAdd,"json");
}

function callback_fmSeqTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmSeqTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


