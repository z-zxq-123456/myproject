var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
        id: "ccy",
        async: false
    });

    if (parent.$("#mbStageDefine").find(".selected").length===1){
        rowData = parent.$('#mbStageDefine').DataTable().rows(".selected").data()[0];
        $("#stageCode").val(rowData.STAGE_CODE).attr("disabled",true);
        $("#issueYear").val(rowData.ISSUE_YEAR);
        $("#issueStartDate").val(rowData.ISSUE_START_DATE);
        $("#prodType").val(rowData.PROD_TYPE);
        $("#issueEndDate").val(rowData.ISSUE_END_DATE);
        $("#issueAmt").val(rowData.ISSUE_AMT);
        $("#ccy").val(rowData.CCY);
        $("#stageCodeDesc").val(rowData.STAGE_CODE_DESC);
        $("#term").val(rowData.TERM);
        $("#termType").val(rowData.TERM_TYPE);
        $("#transferFlag").val(rowData.TRANSFER_FLAG);
        $("#tranBranch").val(rowData.TRAN_BRANCH);
        $("#tranDate").val(rowData.TRAN_DATE);
        $("#saleType").val(rowData.SALE_TYPE);
        $("#resetIntFreq").val(rowData.RESET_INT_FREQ);
        $("#preWithdrawFlag").val(rowData.PRE_WITHDRAW_FLAG);
        $("#payIntType").val(rowData.PAY_INT_TYPE);
        $("#partWithdrawNum").val(rowData.PART_WITHDRAW_NUM);
        $("#intCalcType").val(rowData.INT_CALC_TYPE);
        $("#userId").val(rowData.USER_ID);
        $("#getIntFreq").val(rowData.GET_INT_FREQ);
    }

    $("#mbStageDefineMod").Validform({
        tiptype:2,
        callback:function(){
            mbStageDefineMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbStageDefineMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_STAGE_DEFINE";
    keyFieldsJson.STAGE_CODE=$("#stageCode").val();
    generalFieldsJson.ISSUE_YEAR=$("#issueYear").val();
    generalFieldsJson.ISSUE_START_DATE=$("#issueStartDate").val();
    generalFieldsJson.PROD_TYPE=$("#prodType").val();
    generalFieldsJson.ISSUE_END_DATE=$("#issueEndDate").val();
    generalFieldsJson.ISSUE_AMT=$("#issueAmt").val();
    generalFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.STAGE_CODE_DESC=$("#stageCodeDesc").val();
    generalFieldsJson.TERM=$("#term").val();
    generalFieldsJson.TERM_TYPE=$("#termType").val();
    generalFieldsJson.TRANSFER_FLAG=$("#transferFlag").val();
    generalFieldsJson.TRAN_BRANCH=$("#tranBranch").val();
    generalFieldsJson.TRAN_DATE=$("#tranDate").val();
    generalFieldsJson.SALE_TYPE=$("#saleType").val();
    generalFieldsJson.RESET_INT_FREQ=$("#resetIntFreq").val();
    generalFieldsJson.PRE_WITHDRAW_FLAG=$("#preWithdrawFlag").val();
    generalFieldsJson.PAY_INT_TYPE=$("#payIntType").val();
    generalFieldsJson.PART_WITHDRAW_NUM=$("#partWithdrawNum").val();
    generalFieldsJson.INT_CALC_TYPE=$("#intCalcType").val();
    generalFieldsJson.USER_ID=$("#userId").val();
    generalFieldsJson.GET_INT_FREQ=$("#getIntFreq").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbStageDefineMod,"json");
}

function callback_mbStageDefineMod(json){
    if (json.success) {
        if (parent.$("#mbStageDefine").find(".selected").length===1){
            rowData = parent.$('#mbStageDefine').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#mbStageDefine").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                STAGE_CODE:$("#stageCode").val(),
                ISSUE_YEAR:$("#issueYear").val(),
                ISSUE_START_DATE:$("#issueStartDate").val(),
                PROD_TYPE:$("#prodType").val(),
                ISSUE_END_DATE:$("#issueEndDate").val(),
                ISSUE_AMT:$("#issueAmt").val(),
                CCY:$("#ccy").val(),
                STAGE_CODE_DESC:$("#stageCodeDesc").val(),
                TERM:$("#term").val(),
                TERM_TYPE:$("#termType").val(),
                TRANSFER_FLAG:$("#transferFlag").val(),
                TRAN_BRANCH:$("#tranBranch").val(),
                TRAN_DATE:$("#tranDate").val(),
                SALE_TYPE:$("#saleType").val(),
                RESET_INT_FREQ:$("#resetIntFreq").val(),
                PRE_WITHDRAW_FLAG:$("#preWithdrawFlag").val(),
                PAY_INT_TYPE:$("#payIntType").val(),
                PART_WITHDRAW_NUM:$("#partWithdrawNum").val(),
                INT_CALC_TYPE:$("#intCalcType").val(),
                USER_ID:$("#userId").val(),
                GET_INT_FREQ:$("#getIntFreq").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbStageDefineModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}