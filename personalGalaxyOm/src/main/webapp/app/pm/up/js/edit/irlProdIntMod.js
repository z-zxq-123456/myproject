
var rowData;
$(document).ready(function() {
		  var paraJson, keyFieldsJson;
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

    if (parent.$("#irlProdInt").find(".selected").length===1){
        rowData = parent.$('#irlProdInt').DataTable().rows(".selected").data()[0];
            $("#intClass").val(rowData.INT_CLASS).attr("disabled",true);
            $("#prodType").val(rowData.PROD_TYPE).attr("disabled",true);
            $("#eventType").val(rowData.EVENT_TYPE).attr("disabled",true);
            $("#intAmtId").val(rowData.INT_AMT_ID);
            $("#recalMethod").val(rowData.RECAL_METHOD);
            $("#rateAmtId").val(rowData.RATE_AMT_ID);
            $("#taxType").val(rowData.TAX_TYPE);
            $("#maxRate").val(rowData.MAX_RATE);
            $("#minRate").val(rowData.MIN_RATE);
            $("#monthBasis").val(rowData.MONTH_BASIS);
            $("#rollDay").val(rowData.ROLL_DAY);
            $("#rollFreq").val(rowData.ROLL_FREQ);
            $("#intStart").val(rowData.INT_START);
            $("#intRateInd").val(rowData.INT_RATE_IND);
            $("#intDaysType").val(rowData.INT_DAYS_TYPE);
            $("#intCalcBal").val(rowData.INT_CALC_BAL);
            $("#intApplType").val(rowData.INT_APPL_TYPE);
            $("#company").val(rowData.COMPANY);
            $("#intType").val(rowData.INT_TYPE);
            $("#groupRuleType").val(rowData.GROUP_RULE_TYPE);
            $("#splitId").val(rowData.SPLIT_ID).attr("disabled",true);
            $("#splitType").val(rowData.SPLIT_TYPE);
            $("#ruleid").val(rowData.RULEID).attr("disabled",true);
    }

    $("#irlProdIntMod").Validform({
        tiptype:2,
        callback:function(form){
            irlProdIntMod();
            return false;
        }
    });
    $('#minRate').blur(function(){
        var maxRate=$("#maxRate").val();
        var minRate=$("#minRate").val();
        if(maxRate<minRate){
            alert("最小利率不能大于最大利率！");
        }
    });
    $('#maxRate').blur(function(){
        var maxRate=$("#maxRate").val();
        var minRate=$("#minRate").val();
        if(maxRate<minRate){
            alert("最小利率不能大于最大利率！");
        }
    });

    $(".select2").select2();
});

function irlProdIntMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
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
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlProdIntMod,"json");
}

function callback_irlProdIntMod(json){
    if (json.success) {
        var dataTable=parent.$("#irlProdInt").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            INT_CLASS:$("intClass").val(),
            PROD_TYPE:$("prodType").val(),
            EVENT_TYPE:$("eventType").val(),
            SPLIT_ID:$("splitId").val(),
            RULEID:$("ruleid").val(),
            SPLIT_TYPE:$("splitType").val(),
            INT_AMT_ID:$("intAmtId").val(),
            RECAL_METHOD:$("recalMethod").val(),
            RATE_AMT_ID:$("rateAmtId").val(),
            TAX_TYPE:$("taxType").val(),
            MAX_RATE:$("maxRate").val(),
            MIN_RATE:$("minRate").val(),
            MONTH_BASIS:$("monthBasis").val(),
            ROLL_DAY:$("rollDay").val(),
            ROLL_FREQ:$("rollFreq").val(),
            INT_START:$("intStart").val(),
            INT_RATE_IND:$("intRateInd").val(),
            INT_DAYS_TYPE:$("intDaysType").val(),
            INT_CALC_BAL:$("intCalcBal").val(),
            INT_APPL_TYPE:$("intApplType").val(),
            INT_TYPE:$("intType").val(),
            GROUP_RULE_TYPE:$("groupRuleType").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlProdIntModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}