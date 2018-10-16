
$(document).ready(function() {

	$("#mbAccountingStatusAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbAccountingStatusAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbAccountingStatusAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_ACCOUNTING_STATUS";
		keyFieldsJson.WRITE_OFF=$("#writeOff").val();
		keyFieldsJson.GRACE_DAY=$("#graceDay").val();
		keyFieldsJson.HUNTING_STATUS=$("#huntingStatus").val();
		keyFieldsJson.NON_PERFORMING=$("#nonPerforming").val();
		keyFieldsJson.NON_PERFORMING_PRI=$("#nonPerformingPri").val();
		keyFieldsJson.PERIOD=$("#period").val();
		keyFieldsJson.PERIOD_TYPE=$("#periodType").val();
		keyFieldsJson.SUSPEND_IND=$("#suspendInd").val();
		keyFieldsJson.TERMINATE_IND=$("#terminateInd").val();
		keyFieldsJson.COMPANY=$("#company").val();
		keyFieldsJson.CHANGE_TYPE=$("#changeType").val();
		keyFieldsJson.AVAILABLE=$("#available").val();
		keyFieldsJson.ACCOUNTING_STATUS_DESC=$("#accountingStatusDesc").val();
		keyFieldsJson.ALLOC_SEQ_FEE=$("#allocSeqFee").val();
		keyFieldsJson.ALLOC_SEQ_INT=$("#allocSeqInt").val();
		keyFieldsJson.ALLOC_SEQ_ODI=$("#allocSeqOdi").val();
		keyFieldsJson.ALLOC_SEQ_ODP=$("#allocSeqOdp").val();
		keyFieldsJson.ALLOC_SEQ_PRI=$("#allocSeqPri").val();
		keyFieldsJson.ALLOC_SEQ_TYPE=$("#allocSeqType").val();
		keyFieldsJson.AUTO_CHANGE=$("#autoChange").val();
		generalFieldsJson.ACCOUNTING_STATUS=$("#accountingStatus").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbAccountingStatusAdd,"json");
}

function callback_mbAccountingStatusAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbAccountingStatusAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


