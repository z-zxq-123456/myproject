
$(document).ready(function() {
		  var paraJson, keyFieldsJson;
		  paraJson = {};
		  keyFieldsJson = {};
		  paraJson.tableName = "FM_REF_CODE";
		  paraJson.tableCol="FIELD_VALUE,MEANING";

		  keyFieldsJson.DOMAIN = "INT_CLASS";
		  paraJson.key = keyFieldsJson;
		   getPkList({
							url:contextPath+"/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
									   id: "intClass",
									   async: false

					  });

		  keyFieldsJson.DOMAIN = "EVENT_TYPE";
							  paraJson.key = keyFieldsJson;
						getPkList({
								url:contextPath+"/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
										   id: "eventType",
										   async: false
						});
		  paraJson = {};
		  keyFieldsJson = {};
		  paraJson.tableName = "IRL_INT_TYPE";
		  paraJson.tableCol="INT_TAX_TYPE,INT_TAX_TYPE_DESC";
		  keyFieldsJson.INT_TAX_FLAG = "INT";
		  paraJson.key = keyFieldsJson;
		   getPkList({
							url:contextPath+"/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
									   id: "intType",
									   async: false

					  });
		  keyFieldsJson.INT_TAX_FLAG = "TAX";
		  paraJson.key = keyFieldsJson;
		   getPkList({
							url:contextPath+"/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
									   id: "taxType",
									   async: false

					  });


			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_PROD_TYPE&tableCol=PROD_TYPE,PROD_TYPE_DESC",
				id: "prodType",
				async: false
			});

			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_AMT_EXPRESSION&tableCol=EXPRESS_ID,EXPRESSION_DESC",
				id: "intAmtId",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_AMT_EXPRESSION&tableCol=EXPRESS_ID,EXPRESSION_DESC",
				id: "rateAmtId",
				async: false
			});

			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_PERIOD_FREQ&tableCol=PERIOD_FREQ,PERIOD_FREQ_DESC",
				id: "rollFreq",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
				id: "company",
				async: false
			});


	$("#irlProdIntAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlProdIntAdd();
			return false;
		}
	});
    $('#minRate').blur(function(){
        var maxRate=$("#maxRate").val();
        var minRate=$("#minRate").val();
        if(maxRate<minRate){
            alert("最小利率不能大于最大利率！");
            $('#minRate').val("");
        }
    });
    $('#maxRate').blur(function(){
        var maxRate=$("#maxRate").val();
        var minRate=$("#minRate").val();
        if(maxRate<minRate){
            alert("最小利率不能大于最大利率！");
            $('#maxRate').val("");
        }
    });

	$(".select2").select2();
});

function irlProdIntAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_PROD_INT";
	paraJson.systemId="UP";
		keyFieldsJson.INT_CLASS=$("#intClass").val();
		keyFieldsJson.PROD_TYPE=$("#prodType").val();
		keyFieldsJson.EVENT_TYPE=$("#eventType").val();
        keyFieldsJson.SPLIT_ID=$("#splitId").val();
        keyFieldsJson.RULEID=$("#ruleid").val();
        generalFieldsJson.SPLIT_TYPE=$("#splitType").val();
		generalFieldsJson.INT_AMT_ID=$("#intAmtId").val();
		generalFieldsJson.RECAL_METHOD=$("#recalMethod").val();
		generalFieldsJson.RATE_AMT_ID=$("#rateAmtId").val();
		generalFieldsJson.TAX_TYPE=$("#taxType").val();
		generalFieldsJson.MAX_RATE=$("#maxRate").val();
		generalFieldsJson.MIN_RATE=$("#minRate").val();
		generalFieldsJson.MONTH_BASIS=$("#monthBasis").val();
		generalFieldsJson.ROLL_DAY=$("#rollDay").val();
		generalFieldsJson.ROLL_FREQ=$("#rollFreq").val();
		generalFieldsJson.INT_START=$("#intStart").val();
		generalFieldsJson.INT_RATE_IND=$("#intRateInd").val();
		generalFieldsJson.INT_DAYS_TYPE=$("#intDaysType").val();
		generalFieldsJson.INT_CALC_BAL=$("#intCalcBal").val();
		generalFieldsJson.INT_APPL_TYPE=$("#intApplType").val();
		generalFieldsJson.INT_TYPE=$("#intType").val();
		generalFieldsJson.GROUP_RULE_TYPE=$("#groupRuleType").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlProdIntAdd,"json");
}

function callback_irlProdIntAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlProdIntAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

