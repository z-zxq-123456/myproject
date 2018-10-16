
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "intReceivable",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_PERIOD_FREQ&tableCol=PERIOD_FREQ,PERIOD_FREQ_DESC",
        id: "taxFreq",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "suspAssetAcct",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "suspContAsset",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "suspContLiab",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "osCloseBal",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "suspSettleLiab",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "retainEarnings",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "intPayable",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "interAcctgBk",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "suspSettleAsset",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCashCode",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=IRL_EXCHANGE_TYPE&tableCol=RATE_TYPE,RATE_TYPE_DESC",
        id: "defaultRateType",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
        id: "defaultBranch",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "suspLiabAcct",
        async: false
    });

    if (parent.$("#glSystem").find(".selected").length===1){
        rowData = parent.$('#glSystem').DataTable().rows(".selected").data()[0];
        $("#seqNo").val(rowData.SEQ_NO).attr("disabled",true);
        $("#twoBook").val(rowData.TWO_BOOK);
        $("#acctHistPeriodNo").val(rowData.ACCT_HIST_PERIOD_NO);
        $("#autoIntAcctCreation").val(rowData.AUTO_INT_ACCT_CREATION);
        $("#taxAcctType").val(rowData.TAX_ACCT_TYPE);
        $("#runDate").val(rowData.RUN_DATE);
        $("#revalByPost").val(rowData.REVAL_BY_POST);
        $("#profitCentre").val(rowData.PROFIT_CENTRE);
        $("#osCloseInd").val(rowData.OS_CLOSE_IND);
        $("#nextRunDate").val(rowData.NEXT_RUN_DATE);
        $("#mutiSettleMode").val(rowData.MUTI_SETTLE_MODE);
        $("#maxFuturedateDay").val(rowData.MAX_FUTUREDATE_DAY);
        $("#maxBackdateDay").val(rowData.MAX_BACKDATE_DAY);
        $("#lastRunDate").val(rowData.LAST_RUN_DATE);
        $("#isBatchFinished").val(rowData.IS_BATCH_FINISHED);
        $("#intReceivable").val(rowData.INT_RECEIVABLE);
        $("#intAcctType").val(rowData.INT_ACCT_TYPE);
        $("#histPeriodType").val(rowData.HIST_PERIOD_TYPE);
        $("#histPeriodNo").val(rowData.HIST_PERIOD_NO);
        $("#company").val(rowData.COMPANY);
        $("#taxFreq").val(rowData.TAX_FREQ);
        $("#baseCcy").val(rowData.BASE_CCY);
        $("#autoRef").val(rowData.AUTO_REF);
        $("#suspAssetAcct").val(rowData.SUSP_ASSET_ACCT);
        $("#multisettleInd").val(rowData.MULTISETTLE_IND);
        $("#suspContAsset").val(rowData.SUSP_CONT_ASSET);
        $("#nextCycleDate").val(rowData.NEXT_CYCLE_DATE);
        $("#suspContLiab").val(rowData.SUSP_CONT_LIAB);
        $("#osCloseBal").val(rowData.OS_CLOSE_BAL);
        $("#lastPostingRetentionDate").val(rowData.LAST_POSTING_RETENTION_DATE);
        $("#suspSettleLiab").val(rowData.SUSP_SETTLE_LIAB);
        $("#retainEarnings").val(rowData.RETAIN_EARNINGS);
        $("#intPayable").val(rowData.INT_PAYABLE);
        $("#refPrefix").val(rowData.REF_PREFIX);
        $("#interAcctgBk").val(rowData.INTER_ACCTG_BK);
        $("#suspSettleAsset").val(rowData.SUSP_SETTLE_ASSET);
        $("#prevCycleDate").val(rowData.PREV_CYCLE_DATE);
        $("#glCashCode").val(rowData.GL_CASH_CODE);
        $("#defaultRateType").val(rowData.DEFAULT_RATE_TYPE);
        $("#defaultChargeRateType").val(rowData.DEFAULT_CHARGE_RATE_TYPE);
        $("#defaultBranch").val(rowData.DEFAULT_BRANCH);
        $("#suspLiabAcct").val(rowData.SUSP_LIAB_ACCT);
        $("#ccyCtrlAcct").val(rowData.CCY_CTRL_ACCT);
        $("#boInd").val(rowData.BO_IND);
        $("#batchConsInd").val(rowData.BATCH_CONS_IND);
        $("#localCcy").val(rowData.LOCAL_CCY);
    }

    $("#glSystemMod").Validform({
        tiptype:2,
        callback:function(form){
            glSystemMod();
            return false;
        }
    });

    $(".select2").select2();
});

function glSystemMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="GL_SYSTEM";
    keyFieldsJson.SEQ_NO=$("#seqNo").val();
    generalFieldsJson.TWO_BOOK=$("#twoBook").val();
    generalFieldsJson.ACCT_HIST_PERIOD_NO=$("#acctHistPeriodNo").val();
    generalFieldsJson.AUTO_INT_ACCT_CREATION=$("#autoIntAcctCreation").val();
    generalFieldsJson.TAX_ACCT_TYPE=$("#taxAcctType").val();
    generalFieldsJson.RUN_DATE=$("#runDate").val();
    generalFieldsJson.REVAL_BY_POST=$("#revalByPost").val();
    generalFieldsJson.PROFIT_CENTRE=$("#profitCentre").val();
    generalFieldsJson.OS_CLOSE_IND=$("#osCloseInd").val();
    generalFieldsJson.NEXT_RUN_DATE=$("#nextRunDate").val();
    generalFieldsJson.MUTI_SETTLE_MODE=$("#mutiSettleMode").val();
    generalFieldsJson.MAX_FUTUREDATE_DAY=$("#maxFuturedateDay").val();
    generalFieldsJson.MAX_BACKDATE_DAY=$("#maxBackdateDay").val();
    generalFieldsJson.LAST_RUN_DATE=$("#lastRunDate").val();
    generalFieldsJson.IS_BATCH_FINISHED=$("#isBatchFinished").val();
    generalFieldsJson.INT_RECEIVABLE=$("#intReceivable").val();
    generalFieldsJson.INT_ACCT_TYPE=$("#intAcctType").val();
    generalFieldsJson.HIST_PERIOD_TYPE=$("#histPeriodType").val();
    generalFieldsJson.HIST_PERIOD_NO=$("#histPeriodNo").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.TAX_FREQ=$("#taxFreq").val();
    generalFieldsJson.BASE_CCY=$("#baseCcy").val();
    generalFieldsJson.AUTO_REF=$("#autoRef").val();
    generalFieldsJson.SUSP_ASSET_ACCT=$("#suspAssetAcct").val();
    generalFieldsJson.MULTISETTLE_IND=$("#multisettleInd").val();
    generalFieldsJson.SUSP_CONT_ASSET=$("#suspContAsset").val();
    generalFieldsJson.NEXT_CYCLE_DATE=$("#nextCycleDate").val();
    generalFieldsJson.SUSP_CONT_LIAB=$("#suspContLiab").val();
    generalFieldsJson.OS_CLOSE_BAL=$("#osCloseBal").val();
    generalFieldsJson.LAST_POSTING_RETENTION_DATE=$("#lastPostingRetentionDate").val();
    generalFieldsJson.SUSP_SETTLE_LIAB=$("#suspSettleLiab").val();
    generalFieldsJson.RETAIN_EARNINGS=$("#retainEarnings").val();
    generalFieldsJson.INT_PAYABLE=$("#intPayable").val();
    generalFieldsJson.REF_PREFIX=$("#refPrefix").val();
    generalFieldsJson.INTER_ACCTG_BK=$("#interAcctgBk").val();
    generalFieldsJson.SUSP_SETTLE_ASSET=$("#suspSettleAsset").val();
    generalFieldsJson.PREV_CYCLE_DATE=$("#prevCycleDate").val();
    generalFieldsJson.GL_CASH_CODE=$("#glCashCode").val();
    generalFieldsJson.DEFAULT_RATE_TYPE=$("#defaultRateType").val();
    generalFieldsJson.DEFAULT_CHARGE_RATE_TYPE=$("#defaultChargeRateType").val();
    generalFieldsJson.DEFAULT_BRANCH=$("#defaultBranch").val();
    generalFieldsJson.SUSP_LIAB_ACCT=$("#suspLiabAcct").val();
    generalFieldsJson.CCY_CTRL_ACCT=$("#ccyCtrlAcct").val();
    generalFieldsJson.BO_IND=$("#boInd").val();
    generalFieldsJson.BATCH_CONS_IND=$("#batchConsInd").val();
    generalFieldsJson.LOCAL_CCY=$("#localCcy").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_glSystemMod,"json");
}

function callback_glSystemMod(json){
    if (json.success) {
        var dataTable=parent.$("#glSystem").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
        SEQ_NO:$("#seqNo").val()
        ,TWO_BOOK:$("#twoBook").val()
        ,ACCT_HIST_PERIOD_NO:$("#acctHistPeriodNo").val()
        ,AUTO_INT_ACCT_CREATION:$("#autoIntAcctCreation").val()
        ,TAX_ACCT_TYPE:$("#taxAcctType").val()
        ,RUN_DATE:$("#runDate").val()
        ,REVAL_BY_POST:$("#revalByPost").val()
        ,PROFIT_CENTRE:$("#profitCentre").val()
        ,OS_CLOSE_IND:$("#osCloseInd").val()
        ,NEXT_RUN_DATE:$("#nextRunDate").val()
        ,MUTI_SETTLE_MODE:$("#mutiSettleMode").val()
        ,MAX_FUTUREDATE_DAY:$("#maxFuturedateDay").val()
        ,MAX_BACKDATE_DAY:$("#maxBackdateDay").val()
        ,LAST_RUN_DATE:$("#lastRunDate").val()
        ,IS_BATCH_FINISHED:$("#isBatchFinished").val()
        ,INT_RECEIVABLE:$("#intReceivable").val()
        ,INT_ACCT_TYPE:$("#intAcctType").val()
        ,HIST_PERIOD_TYPE:$("#histPeriodType").val()
        ,HIST_PERIOD_NO:$("#histPeriodNo").val()
        ,COMPANY:$("#company").val()
        ,TAX_FREQ:$("#taxFreq").val()
        ,BASE_CCY:$("#baseCcy").val()
        ,AUTO_REF:$("#autoRef").val()
        ,SUSP_ASSET_ACCT:$("#suspAssetAcct").val()
        ,MULTISETTLE_IND:$("#multisettleInd").val()
        ,SUSP_CONT_ASSET:$("#suspContAsset").val()
        ,NEXT_CYCLE_DATE:$("#nextCycleDate").val()
        ,SUSP_CONT_LIAB:$("#suspContLiab").val()
        ,OS_CLOSE_BAL:$("#osCloseBal").val()
        ,LAST_POSTING_RETENTION_DATE:$("#lastPostingRetentionDate").val()
        ,SUSP_SETTLE_LIAB:$("#suspSettleLiab").val()
        ,RETAIN_EARNINGS:$("#retainEarnings").val()
        ,INT_PAYABLE:$("#intPayable").val()
        ,REF_PREFIX:$("#refPrefix").val()
        ,INTER_ACCTG_BK:$("#interAcctgBk").val()
        ,SUSP_SETTLE_ASSET:$("#suspSettleAsset").val()
        ,PREV_CYCLE_DATE:$("#prevCycleDate").val()
        ,GL_CASH_CODE:$("#glCashCode").val()
        ,DEFAULT_RATE_TYPE:$("#defaultRateType").val()
        ,DEFAULT_CHARGE_RATE_TYPE:$("#defaultChargeRateType").val()
        ,DEFAULT_BRANCH:$("#defaultBranch").val()
        ,SUSP_LIAB_ACCT:$("#suspLiabAcct").val()
        ,CCY_CTRL_ACCT:$("#ccyCtrlAcct").val()
        ,BO_IND:$("#boInd").val()
        ,BATCH_CONS_IND:$("#batchConsInd").val()
        ,LOCAL_CCY:$("#localCcy").val(),
        COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function glSystemModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

