
$(document).ready(function() {

	$("#cdTranControlAdd").Validform({
		tiptype:2,
		callback:function(form){
			cdTranControlAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cdTranControlAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CD_TRAN_CONTROL";
		keyFieldsJson.CD_AREA_CODE=$("#cdAreaCode").val();
		keyFieldsJson.PROD_TYPE=$("#prodType").val();
		keyFieldsJson.PROD_CHANNEL=$("#prodChannel").val();
		keyFieldsJson.CD_CUST_GRADE=$("#cdCustGrade").val();
		generalFieldsJson.MERCHANT_CODE=$("#merchantCode").val();
		generalFieldsJson.PASSWORD_CTR=$("#passwordCtr").val();
		generalFieldsJson.DAY_TRAN_LIM=$("#dayTranLim").val();
		generalFieldsJson.SINGLE_TRAN_LIM=$("#singleTranLim").val();
		generalFieldsJson.TERMINAL_ID=$("#terminalId").val();
		generalFieldsJson.TRAN_COUNT=$("#tranCount").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cdTranControlAdd,"json");
}

function callback_cdTranControlAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cdTranControlAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


