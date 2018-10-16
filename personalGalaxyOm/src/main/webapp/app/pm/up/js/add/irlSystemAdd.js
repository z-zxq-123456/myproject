
$(document).ready(function() {
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
				id: "baseCcy",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
				id: "company",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
				id: "defaultBranch",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_EXCHANGE_TYPE&tableCol=RATE_TYPE,RATE_TYPE_DESC",
				id: "defaultRateType",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_EXCHANGE_TYPE&tableCol=RATE_TYPE,RATE_TYPE_DESC",
				id: "defaultChargeRateType",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
				id: "localCcy",
				async: false
			});

	$("#irlSystemAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlSystemAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function irlSystemAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_SYSTEM";
		keyFieldsJson.COY_NAME=$("#coyName").val();
		generalFieldsJson.BASE_CCY=$("#baseCcy").val();
		generalFieldsJson.COY_SHORT=$("#coyShort").val();
		generalFieldsJson.DEFAULT_BRANCH=$("#defaultBranch").val();
		generalFieldsJson.DEFAULT_RATE_TYPE=$("#defaultRateType").val();
		generalFieldsJson.GL_MERGE_TYPE=$("#glMergeType").val();
		generalFieldsJson.HALF_END_DATE=$("#halfEndDate").val();
		generalFieldsJson.INT_EVENT_VALUE=$("#intEventValue").val();
		generalFieldsJson.LAST_RUN_DATE=$("#lastRunDate").val();
		generalFieldsJson.LOCAL_CCY=$("#localCcy").val();
		generalFieldsJson.MTH_END_DATE=$("#mthEndDate").val();
		generalFieldsJson.NEXT_RUN_DATE=$("#nextRunDate").val();
		generalFieldsJson.QUOTE_BALANCE_TYPE=$("#quoteBalanceType").val();
		generalFieldsJson.QUR_END_DATE=$("#qurEndDate").val();
		generalFieldsJson.RUN_DATE=$("#runDate").val();
		generalFieldsJson.SYSTEM_PHASE=$("#systemPhase").val();
		generalFieldsJson.YR_END_DATE=$("#yrEndDate").val();
		generalFieldsJson.DEFAULT_CHARGE_RATE_TYPE=$("#defaultChargeRateType").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlSystemAdd,"json");
}

function callback_irlSystemAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlSystemAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

