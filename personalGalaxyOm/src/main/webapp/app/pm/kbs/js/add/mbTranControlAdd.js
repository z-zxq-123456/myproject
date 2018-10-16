$(document).ready(function() {

	$("#mbTranControlAdd").Validform({
		tiptype:2,
		callback:function(){
			mbTranControlAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbTranControlAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_TRAN_CONTROL";
    keyFieldsJson.CD_CUST_GRADE=$("#cdCustGrade").val();
    keyFieldsJson.PROD_TYPE=$("#prodType").val();
    keyFieldsJson.PROD_CHANNEL=$("#prodChannel").val();
    keyFieldsJson.CD_AREA_CODE=$("#cdAreaCode").val();
    generalFieldsJson.SINGLE_TRAN_LIM=$("#singleTranLim").val();
    generalFieldsJson.TRAN_COUNT=$("#tranCount").val();
    generalFieldsJson.DAY_TRAN_LIM=$("#dayTranLim").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.PASSWORD_CTR=$("#passwordCtr").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbTranControlAdd,"json");
}

function callback_mbTranControlAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbTranControlAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}