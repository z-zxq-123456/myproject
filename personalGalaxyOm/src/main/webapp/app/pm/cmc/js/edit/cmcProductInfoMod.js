var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=CMC_CARD_BIN_INFO&tableCol=BIN_ORDER",
        id: "binOrder",
        async: false
    });

    if (parent.$("#cmcProductInfo").find(".selected").length===1){
        rowData = parent.$('#cmcProductInfo').DataTable().rows(".selected").data()[0];
        $("#cardProductCode").val(rowData.CARD_PRODUCT_CODE).attr("disabled",true);
        $("#cardProductName").val(rowData.CARD_PRODUCT_NAME);
        $("#binOrder").val(rowData.BIN_ORDER);
        $("#publishChannel").val(rowData.PUBLISH_CHANNEL);
        $("#cardTypeCode").val(rowData.CARD_TYPE_CODE);
        $("#cardTypeName").val(rowData.CARD_TYPE_NAME);
        $("#cardProductType").val(rowData.CARD_PRODUCT_TYPE);
        $("#cardCrdbflg").val(rowData.CARD_CRDBFLG);
        $("#cardNoType").val(rowData.CARD_NO_TYPE);
        $("#feeLevel").val(rowData.FEE_LEVEL);
        $("#cardLevel").val(rowData.CARD_LEVEL);
        $("#enableFlag").val(rowData.ENABLE_FLAG);
        $("#enableDate").val(rowData.ENABLE_DATE);
        $("#orderFlag").val(rowData.ORDER_FLAG);
        $("#beginSeq").val(rowData.BEGIN_SEQ);
        $("#endSeq").val(rowData.END_SEQ);
        $("#cardPhySort").val(rowData.CARD_PHY_SORT);
        $("#atmErrNum").val(rowData.ATM_ERR_NUM);
        $("#totalErrNum").val(rowData.TOTAL_ERR_NUM);
        $("#cvnTotErrNum").val(rowData.CVN_TOT_ERR_NUM);
        $("#cvn2TotErrNum").val(rowData.CVN2_TOT_ERR_NUM);
        $("#lastTotErrNum").val(rowData.LAST_TOT_ERR_NUM);
        $("#ccy").val(rowData.CCY);
        $("#markFlg").val(rowData.MARK_FLG);
        $("#activateFee").val(rowData.ACTIVATE_FEE);
        $("#maxHoldNo").val(rowData.MAX_HOLD_NO);
        $("#pswdMark").val(rowData.PSWD_MARK);
        $("#lostMark").val(rowData.LOST_MARK);
        $("#exDate").val(rowData.EX_DATE);
        $("#setFixExDate").val(rowData.SET_FIX_EX_DATE);
        $("#fixExDate").val(rowData.FIX_EX_DATE);
        $("#invalidDate").val(rowData.INVALID_DATE);
        $("#interfacePermit").val(rowData.INTERFACE_PERMIT);
        $("#issueTarget").val(rowData.ISSUE_TARGET);
        $("#beginAmt").val(rowData.BEGIN_AMT);
        $("#convalue").val(rowData.CONVALUE);
        $("#lastTranDate").val(rowData.LAST_TRAN_DATE);
        $("#tranTimestamp").val(rowData.TRAN_TIMESTAMP);
       $("#tranTime").val(rowData.TRAN_TIME);
    }

    $("#cmcProductInfoMod").Validform({
        tiptype:2,
        callback:function(){
            cmcProductInfoMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cmcProductInfoMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CMC_PRODUCT_INFO";
    keyFieldsJson.CARD_PRODUCT_CODE=$("#cardProductCode").val();
    generalFieldsJson.CARD_PRODUCT_NAME=$("#cardProductName").val();
    generalFieldsJson.BIN_ORDER=$("#binOrder").val();
    generalFieldsJson.PUBLISH_CHANNEL=$("#publishChannel").val();
    generalFieldsJson.CARD_TYPE_CODE=$("#cardTypeCode").val();
    generalFieldsJson.CARD_TYPE_NAME=$("#cardTypeName").val();
    generalFieldsJson.CARD_PRODUCT_TYPE=$("#cardProductType").val();
    generalFieldsJson.CARD_CRDBFLG=$("#cardCrdbflg").val();
    generalFieldsJson.CARD_NO_TYPE=$("#cardNoType").val();
    generalFieldsJson.FEE_LEVEL=$("#feeLevel").val();
    generalFieldsJson.CARD_LEVEL=$("#cardLevel").val();
    generalFieldsJson.ENABLE_FLAG=$("#enableFlag").val();
    generalFieldsJson.ENABLE_DATE=$("#enableDate").val();
    generalFieldsJson.ORDER_FLAG=$("#orderFlag").val();
    generalFieldsJson.BEGIN_SEQ=$("#beginSeq").val();
    generalFieldsJson.END_SEQ=$("#endSeq").val();
    generalFieldsJson.CARD_PHY_SORT=$("#cardPhySort").val();
    generalFieldsJson.ATM_ERR_NUM=$("#atmErrNum").val();
    generalFieldsJson.TOTAL_ERR_NUM=$("#totalErrNum").val();
    generalFieldsJson.CVN_TOT_ERR_NUM=$("#cvnTotErrNum").val();
    generalFieldsJson.CVN2_TOT_ERR_NUM=$("#cvn2TotErrNum").val();
    generalFieldsJson.LAST_TOT_ERR_NUM=$("#lastTotErrNum").val();
    generalFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.MARK_FLG=$("#markFlg").val();
    generalFieldsJson.ACTIVATE_FEE=$("#activateFee").val();
    generalFieldsJson.MAX_HOLD_NO=$("#maxHoldNo").val();
    generalFieldsJson.PSWD_MARK=$("#pswdMark").val();
    generalFieldsJson.LOST_MARK=$("#lostMark").val();
    generalFieldsJson.EX_DATE=$("#exDate").val();
    generalFieldsJson.SET_FIX_EX_DATE=$("#setFixExDate").val();
    generalFieldsJson.FIX_EX_DATE=$("#fixExDate").val();
    generalFieldsJson.INVALID_DATE=$("#invalidDate").val();
    generalFieldsJson.INTERFACE_PERMIT=$("#interfacePermit").val();
    generalFieldsJson.ISSUE_TARGET=$("#issueTarget").val();
    generalFieldsJson.BEGIN_AMT=$("#beginAmt").val();
    generalFieldsJson.CONVALUE=$("#convalue").val();
    generalFieldsJson.LAST_TRAN_DATE=$("#lastTranDate").val();
    generalFieldsJson.TRAN_TIMESTAMP=$("#tranTimestamp").val();
    generalFieldsJson.TRAN_TIME=$("#tranTime").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cmcProductInfoMod,"json");
}

function callback_cmcProductInfoMod(json){
    if (json.success) {
        if (parent.$("#cmcProductInfo").find(".selected").length===1){
            rowData = parent.$('#cmcProductInfo').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#cmcProductInfo").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                CARD_PRODUCT_CODE:$("#cardProductCode").val(),
                CARD_PRODUCT_NAME:$("#cardProductName").val(),
                BIN_ORDER:$("#binOrder").val(),
                PUBLISH_CHANNEL:$("#publishChannel").val(),
                CARD_TYPE_CODE:$("#cardTypeCode").val(),
                CARD_TYPE_NAME:$("#cardTypeName").val(),
                CARD_PRODUCT_TYPE:$("#cardProductType").val(),
                CARD_CRDBFLG:$("#cardCrdbflg").val(),
                CARD_NO_TYPE:$("#cardNoType").val(),
                FEE_LEVEL:$("#feeLevel").val(),
                CARD_LEVEL:$("#cardLevel").val(),
                ENABLE_FLAG:$("#enableFlag").val(),
                ENABLE_DATE:$("#enableDate").val(),
                ORDER_FLAG:$("#orderFlag").val(),
                BEGIN_SEQ:$("#beginSeq").val(),
                END_SEQ:$("#endSeq").val(),
                CARD_PHY_SORT:$("#cardPhySort").val(),
                ATM_ERR_NUM:$("#atmErrNum").val(),
                TOTAL_ERR_NUM:$("#totalErrNum").val(),
                CVN_TOT_ERR_NUM:$("#cvnTotErrNum").val(),
                CVN2_TOT_ERR_NUM:$("#cvn2TotErrNum").val(),
                LAST_TOT_ERR_NUM:$("#lastTotErrNum").val(),
                CCY:$("#ccy").val(),
                MARK_FLG:$("#markFlg").val(),
                ACTIVATE_FEE:$("#activateFee").val(),
                MAX_HOLD_NO:$("#maxHoldNo").val(),
                PSWD_MARK:$("#pswdMark").val(),
                LOST_MARK:$("#lostMark").val(),
                EX_DATE:$("#exDate").val(),
                SET_FIX_EX_DATE:$("#setFixExDate").val(),
                FIX_EX_DATE:$("#fixExDate").val(),
                INVALID_DATE:$("#invalidDate").val(),
                INTERFACE_PERMIT:$("#interfacePermit").val(),
                ISSUE_TARGET:$("#issueTarget").val(),
                BEGIN_AMT:$("#beginAmt").val(),
                CONVALUE:$("#convalue").val(),
                LAST_TRAN_DATE:$("#lastTranDate").val(),
                TRAN_TIMESTAMP:$("#tranTimestamp").val(),
                TRAN_TIME:$("#tranTime").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cmcProductInfoModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}