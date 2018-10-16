$(document).ready(function() {
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_PROD_TYPE&tableCol=PROD_TYPE,PROD_TYPE_DESC",
				id: "prodType",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
				id: "ccy",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_PERIOD_FREQ&tableCol=PERIOD_FREQ,PERIOD_FREQ_DESC",
				id: "periodFreq",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
				id: "company",
				async: false
			});




	$("#irlRiskSetingAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlRiskSetingAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function irlRiskSetingAdd(){


				var prodRuleId="";
				var url2 = contextPath + "/baseCommon/getMaxAutoAddID";
				var params;
				var prodType=$("#prodType").val();
				var balance=$("#balance").val();
				var ccy=$("#ccy").val();
				var periodFreq=$("#periodFreq").val();
				var flag=0;
				var maxIntRate=$("#maxIntRate").val();
				var minIntRate=$("#minIntRate").val();
				var maxSpreadPercent=$("#maxSpreadPercent").val();
				var minSpreadPercent=$("#minSpreadPercent").val();
				var maxSpreadRate=$("#maxSpreadRate").val();
				var minSpreadRate=$("#minSpreadRate").val();
				var minBranchRate=$("#minBranchRate").val();
				var maxBranchRate=$("#maxBranchRate").val();
				var inquery;
				if(periodFreq!==null&&periodFreq!==""){
				inquery="PROD_TYPE='"+prodType+"' "+"AND CCY='"+ccy+"'"+"AND BALANCE='"+balance+"'"+"AND PERIOD_FREQ='"+periodFreq+"'";
				}else{
				inquery="PROD_TYPE='"+prodType+"' "+"AND CCY='"+ccy+"'"+"AND BALANCE='"+balance+"'"+"AND PERIOD_FREQ is null";
				}
				 params={
							tableName: "IRL_RISK_SETING",
                            fieldName: "IRL_SEQ_NO",
                            inqueryCondition: inquery};

				sendPostRequest(url2,params, function (json) {
							var maxId = json;
							proTdRuleId = maxId;

				},"json",false);



if(proTdRuleId!==1){
	showMsg("产品类型、币种、金额、期限类型必须唯一确定一条数据", 'error');
	flag=flag+1;
}else if(maxIntRate!==null&&maxIntRate!==""&&minIntRate!==null&&minIntRate!==""){
			if(maxIntRate<minIntRate){
			showMsg("执行利率上限不得小于执行利率下限", 'error');
			flag=flag+1;
			}else if(maxSpreadPercent!==null&&maxSpreadPercent!==""&&minSpreadPercent!==null&&minSpreadPercent!==""){
             			if(maxSpreadPercent<minSpreadPercent){
                 		showMsg("浮动百分比上限不得小于浮动百分比下限", 'error');
                 		flag=flag+1;

                 	}else if(maxSpreadRate!==null&&maxSpreadRate!==""&&minSpreadRate!==null&&minSpreadRate!==""){
                     				if(maxSpreadRate<minSpreadRate){
                     				showMsg("浮动点数上限不得小于浮动点数下限", 'error');
                     				flag=flag+1;

                             	}else if(maxBranchRate!==null&&maxBranchRate!==""&&minBranchRate!==null&&minBranchRate!==""){
                                                     				if(maxBranchRate<minBranchRate){
                                                     				showMsg("行内利率上限不得小于行内利率下限", 'error');
                                                     				flag=flag+1;
                                                     				}
                                                             	}
                                                             	}
                                                             	}

	}



    if(flag===0){

	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_RISK_SETING";
		keyFieldsJson.IRL_SEQ_NO=$("#irlSeqNo").val();
		generalFieldsJson.PROD_TYPE=$("#prodType").val();
		generalFieldsJson.BALANCE=$("#balance").val();
		generalFieldsJson.CCY=$("#ccy").val();
		generalFieldsJson.MAX_INT_RATE=$("#maxIntRate").val();
		generalFieldsJson.MIN_INT_RATE=$("#minIntRate").val();
		generalFieldsJson.PERIOD_FREQ=$("#periodFreq").val();
		generalFieldsJson.MAX_SPREAD_PERCENT=$("#maxSpreadPercent").val();
		generalFieldsJson.MIN_SPREAD_PERCENT=$("#minSpreadPercent").val();
		generalFieldsJson.MAX_SPREAD_RATE=$("#maxSpreadRate").val();
		generalFieldsJson.DAY_NUM=$("#dayNum").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.MIN_BRANCH_RATE=$("#minBranchRate").val();
		generalFieldsJson.MIN_SPREAD_RATE=$("#minSpreadRate").val();
		generalFieldsJson.MAX_BRANCH_RATE=$("#maxBranchRate").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlRiskSetingAdd,"json");
	}
}

function callback_irlRiskSetingAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlRiskSetingAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

