
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
        id: "limitCcy",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
        id: "reportCcy",
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
        id: "defaultRateTypeLocal",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=IRL_EXCHANGE_TYPE&tableCol=RATE_TYPE,RATE_TYPE_DESC",
        id: "defaultRateType",
        async: false
    });

    if (parent.$("#fmSystem").find(".selected").length===1){
        rowData = parent.$('#fmSystem').DataTable().rows(".selected").data()[0];
        $("#coyName").val(rowData.COY_NAME).attr("disabled",true);
        $("#interBranchInd").val(rowData.INTER_BRANCH_IND);
        $("#continuousRun").val(rowData.CONTINUOUS_RUN);
        $("#coyShort").val(rowData.COY_SHORT);
        $("#nextRunDate").val(rowData.NEXT_RUN_DATE);
        $("#glInd").val(rowData.GL_IND);
        $("#yrEndDate").val(rowData.YR_END_DATE);
        $("#mthEndDate").val(rowData.MTH_END_DATE);
        $("#halfEndDate").val(rowData.HALF_END_DATE);
        $("#processSplitInd").val(rowData.PROCESS_SPLIT_IND);
        $("#clientBlockFreq").val(rowData.CLIENT_BLOCK_FREQ);
        $("#autoLockBlClient").val(rowData.AUTO_LOCK_BL_CLIENT);
        $("#systemPhase").val(rowData.SYSTEM_PHASE);
        $("#autoClientGen").val(rowData.AUTO_CLIENT_GEN);
        $("#runDate").val(rowData.RUN_DATE);
        $("#product30e").val(rowData.PRODUCT_30E);
        $("#qurEndDate").val(rowData.QUR_END_DATE);
        $("#autoCollGen").val(rowData.AUTO_COLL_GEN);
        $("#lastRunDate").val(rowData.LAST_RUN_DATE);
        $("#isError").val(rowData.IS_ERROR);
        $("#isDebug").val(rowData.IS_DEBUG);
        $("#limitCcy").val(rowData.LIMIT_CCY);
        $("#localCcy").val(rowData.LOCAL_CCY);
        $("#mainBranchCode").val(rowData.MAIN_BRANCH_CODE);
        $("#batchDefaultUserId").val(rowData.BATCH_DEFAULT_USER_ID);
        $("#multiCorporationMethod").val(rowData.MULTI_CORPORATION_METHOD);
        $("#multiCorpQueryAllow").val(rowData.MULTI_CORP_QUERY_ALLOW);
        $("#npvGapType").val(rowData.NPV_GAP_TYPE);
        $("#reportCcy").val(rowData.REPORT_CCY);
        $("#rbRestraintType").val(rowData.RB_RESTRAINT_TYPE);
        $("#multiCorporationFlag").val(rowData.MULTI_CORPORATION_FLAG);
        $("#interBranchAcctHo").val(rowData.INTER_BRANCH_ACCT_HO);
        $("#crDrCheckFlag").val(rowData.CR_DR_CHECK_FLAG);
        $("#company").val(rowData.COMPANY);
        $("#clientNoStructureType").val(rowData.CLIENT_NO_STRUCTURE_TYPE);
        $("#capitalFunds").val(rowData.CAPITAL_FUNDS);
        $("#batchUnit").val(rowData.BATCH_UNIT);
        $("#batchModule").val(rowData.BATCH_MODULE);
        $("#batchCheckFlag").val(rowData.BATCH_CHECK_FLAG);
        $("#baseCcy").val(rowData.BASE_CCY);
        $("#dacInd").val(rowData.DAC_IND);
        $("#defaultBranch").val(rowData.DEFAULT_BRANCH);
        $("#internalRateChargeFlag").val(rowData.INTERNAL_RATE_CHARGE_FLAG);
        $("#headOfficeClient").val(rowData.HEAD_OFFICE_CLIENT);
        $("#defaultChargeRateType").val(rowData.DEFAULT_CHARGE_RATE_TYPE);
        $("#exchangeRateVariance").val(rowData.EXCHANGE_RATE_VARIANCE);
        $("#ebhBranch").val(rowData.EBH_BRANCH);
        $("#defaultRateTypeLocal").val(rowData.DEFAULT_RATE_TYPE_LOCAL);
        $("#defaultRateType").val(rowData.DEFAULT_RATE_TYPE);
        $("#defaultProfitCentre").val(rowData.DEFAULT_PROFIT_CENTRE);
        $("#allowBackqryDay").val(rowData.ALLOW_BACKQRY_DAY);
    }

    $("#fmSystemMod").Validform({
        tiptype:2,
        callback:function(form){
            fmSystemMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmSystemMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_SYSTEM";
    keyFieldsJson.COY_NAME=$("#coyName").val();
    generalFieldsJson.INTER_BRANCH_IND=$("#interBranchInd").val();
    generalFieldsJson.CONTINUOUS_RUN=$("#continuousRun").val();
    generalFieldsJson.COY_SHORT=$("#coyShort").val();
    generalFieldsJson.NEXT_RUN_DATE=$("#nextRunDate").val();
    generalFieldsJson.GL_IND=$("#glInd").val();
    generalFieldsJson.YR_END_DATE=$("#yrEndDate").val();
    generalFieldsJson.MTH_END_DATE=$("#mthEndDate").val();
    generalFieldsJson.HALF_END_DATE=$("#halfEndDate").val();
    generalFieldsJson.PROCESS_SPLIT_IND=$("#processSplitInd").val();
    generalFieldsJson.CLIENT_BLOCK_FREQ=$("#clientBlockFreq").val();
    generalFieldsJson.AUTO_LOCK_BL_CLIENT=$("#autoLockBlClient").val();
    generalFieldsJson.SYSTEM_PHASE=$("#systemPhase").val();
    generalFieldsJson.AUTO_CLIENT_GEN=$("#autoClientGen").val();
    generalFieldsJson.RUN_DATE=$("#runDate").val();
    generalFieldsJson.PRODUCT_30E=$("#product30e").val();
    generalFieldsJson.QUR_END_DATE=$("#qurEndDate").val();
    generalFieldsJson.AUTO_COLL_GEN=$("#autoCollGen").val();
    generalFieldsJson.LAST_RUN_DATE=$("#lastRunDate").val();
    generalFieldsJson.IS_ERROR=$("#isError").val();
    generalFieldsJson.IS_DEBUG=$("#isDebug").val();
    generalFieldsJson.LIMIT_CCY=$("#limitCcy").val();
    generalFieldsJson.LOCAL_CCY=$("#localCcy").val();
    generalFieldsJson.MAIN_BRANCH_CODE=$("#mainBranchCode").val();
    generalFieldsJson.BATCH_DEFAULT_USER_ID=$("#batchDefaultUserId").val();
    generalFieldsJson.MULTI_CORPORATION_METHOD=$("#multiCorporationMethod").val();
    generalFieldsJson.MULTI_CORP_QUERY_ALLOW=$("#multiCorpQueryAllow").val();
    generalFieldsJson.NPV_GAP_TYPE=$("#npvGapType").val();
    generalFieldsJson.REPORT_CCY=$("#reportCcy").val();
    generalFieldsJson.RB_RESTRAINT_TYPE=$("#rbRestraintType").val();
    generalFieldsJson.MULTI_CORPORATION_FLAG=$("#multiCorporationFlag").val();
    generalFieldsJson.INTER_BRANCH_ACCT_HO=$("#interBranchAcctHo").val();
    generalFieldsJson.CR_DR_CHECK_FLAG=$("#crDrCheckFlag").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.CLIENT_NO_STRUCTURE_TYPE=$("#clientNoStructureType").val();
    generalFieldsJson.CAPITAL_FUNDS=$("#capitalFunds").val();
    generalFieldsJson.BATCH_UNIT=$("#batchUnit").val();
    generalFieldsJson.BATCH_MODULE=$("#batchModule").val();
    generalFieldsJson.BATCH_CHECK_FLAG=$("#batchCheckFlag").val();
    generalFieldsJson.BASE_CCY=$("#baseCcy").val();
    generalFieldsJson.DAC_IND=$("#dacInd").val();
    generalFieldsJson.DEFAULT_BRANCH=$("#defaultBranch").val();
    generalFieldsJson.INTERNAL_RATE_CHARGE_FLAG=$("#internalRateChargeFlag").val();
    generalFieldsJson.HEAD_OFFICE_CLIENT=$("#headOfficeClient").val();
    generalFieldsJson.DEFAULT_CHARGE_RATE_TYPE=$("#defaultChargeRateType").val();
    generalFieldsJson.EXCHANGE_RATE_VARIANCE=$("#exchangeRateVariance").val();
    generalFieldsJson.EBH_BRANCH=$("#ebhBranch").val();
    generalFieldsJson.DEFAULT_RATE_TYPE_LOCAL=$("#defaultRateTypeLocal").val();
    generalFieldsJson.DEFAULT_RATE_TYPE=$("#defaultRateType").val();
    generalFieldsJson.DEFAULT_PROFIT_CENTRE=$("#defaultProfitCentre").val();
    generalFieldsJson.ALLOW_BACKQRY_DAY=$("#allowBackqryDay").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmSystemMod,"json");
}

function callback_fmSystemMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmSystem").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            COY_NAME:$("#coyName").val(),
            INTER_BRANCH_IND:$("#interBranchInd").val(),
            CONTINUOUS_RUN:$("#continuousRun").val(),
            COY_SHORT:$("#coyShort").val(),
            NEXT_RUN_DATE:$("#nextRunDate").val(),
            GL_IND:$("#glInd").val(),
            YR_END_DATE:$("#yrEndDate").val(),
            MTH_END_DATE:$("#mthEndDate").val(),
            HALF_END_DATE:$("#halfEndDate").val(),
            PROCESS_SPLIT_IND:$("#processSplitInd").val(),
            CLIENT_BLOCK_FREQ:$("#clientBlockFreq").val(),
            AUTO_LOCK_BL_CLIENT:$("#autoLockBlClient").val(),
            SYSTEM_PHASE:$("#systemPhase").val(),
            AUTO_CLIENT_GEN:$("#autoClientGen").val(),
            RUN_DATE:$("#runDate").val(),
            PRODUCT_30E:$("#product30e").val(),
            QUR_END_DATE:$("#qurEndDate").val(),
            AUTO_COLL_GEN:$("#autoCollGen").val(),
            LAST_RUN_DATE:$("#lastRunDate").val(),
            IS_ERROR:$("#isError").val(),
            IS_DEBUG:$("#isDebug").val(),
            LIMIT_CCY:$("#limitCcy").val(),
            LOCAL_CCY:$("#localCcy").val(),
            MAIN_BRANCH_CODE:$("#mainBranchCode").val(),
            BATCH_DEFAULT_USER_ID:$("#batchDefaultUserId").val(),
            MULTI_CORPORATION_METHOD:$("#multiCorporationMethod").val(),
            MULTI_CORP_QUERY_ALLOW:$("#multiCorpQueryAllow").val(),
            NPV_GAP_TYPE:$("#npvGapType").val(),
            REPORT_CCY:$("#reportCcy").val(),
            RB_RESTRAINT_TYPE:$("#rbRestraintType").val(),
            MULTI_CORPORATION_FLAG:$("#multiCorporationFlag").val(),
            INTER_BRANCH_ACCT_HO:$("#interBranchAcctHo").val(),
            CR_DR_CHECK_FLAG:$("#crDrCheckFlag").val(),
            COMPANY:$("#company").val(),
            CLIENT_NO_STRUCTURE_TYPE:$("#clientNoStructureType").val(),
            CAPITAL_FUNDS:$("#capitalFunds").val(),
            BATCH_UNIT:$("#batchUnit").val(),
            BATCH_MODULE:$("#batchModule").val(),
            BATCH_CHECK_FLAG:$("#batchCheckFlag").val(),
            BASE_CCY:$("#baseCcy").val(),
            DAC_IND:$("#dacInd").val(),
            DEFAULT_BRANCH:$("#defaultBranch").val(),
            INTERNAL_RATE_CHARGE_FLAG:$("#internalRateChargeFlag").val(),
            HEAD_OFFICE_CLIENT:$("#headOfficeClient").val(),
            DEFAULT_CHARGE_RATE_TYPE:$("#defaultChargeRateType").val(),
            EXCHANGE_RATE_VARIANCE:$("#exchangeRateVariance").val(),
            EBH_BRANCH:$("#ebhBranch").val(),
            DEFAULT_RATE_TYPE_LOCAL:$("#defaultRateTypeLocal").val(),
            DEFAULT_RATE_TYPE:$("#defaultRateType").val(),
            DEFAULT_PROFIT_CENTRE:$("#defaultProfitCentre").val(),
            ALLOW_BACKQRY_DAY:$("#allowBackqryDay").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmSystemModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

