
$(document).ready(function() {

	$("#glAccountingAdd").Validform({
		tiptype:2,
		callback:function(form){
			glAccountingAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function glAccountingAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="GL_ACCOUNTING";
		keyFieldsJson.ACCOUNTING_NO=$("#accountingNo").val();
		keyFieldsJson.AMOUNT_TYPE=$("#amountType").val();
		keyFieldsJson.COUNTER=$("#counter").val();
		generalFieldsJson.PROFIT_CENTRE_EXP=$("#profitCentreExp").val();
		generalFieldsJson.PROD_TYPE_EXP=$("#prodTypeExp").val();
		generalFieldsJson.GL_CODE_EXP=$("#glCodeExp").val();
		generalFieldsJson.CR_DR=$("#crDr").val();
		generalFieldsJson.COMPANY_EXP=$("#companyExp").val();
		generalFieldsJson.CLIENT_NO_EXP=$("#clientNoExp").val();
		generalFieldsJson.CCY_EXP=$("#ccyExp").val();
		generalFieldsJson.BUSINESS_UNIT_EXP=$("#businessUnitExp").val();
		generalFieldsJson.BRANCH_EXP=$("#branchExp").val();
		generalFieldsJson.AMOUNT_EXP=$("#amountExp").val();
		generalFieldsJson.SEQ_NO=$("#seqNo").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_glAccountingAdd,"json");
}

function callback_glAccountingAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function glAccountingAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


