
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=TB_VOUCHER_DEF&tableCol=DOC_TYPE,DOC_TYPE_DESC",
        id: "docType",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmSettleMethod").find(".selected").length===1){
        rowData = parent.$('#fmSettleMethod').DataTable().rows(".selected").data()[0];
        $("#settleMethod").val(rowData.SETTLE_METHOD).attr("disabled",true);
        $("#payRec").val(rowData.PAY_REC).attr("disabled",true);
        $("#verifySecurity").val(rowData.VERIFY_SECURITY);
        $("#settleMethodDesc").val(rowData.SETTLE_METHOD_DESC);
        $("#settleAcctType").val(rowData.SETTLE_ACCT_TYPE);
        $("#route").val(rowData.ROUTE);
        $("#releaseSecurity").val(rowData.RELEASE_SECURITY);
        $("#media").val(rowData.MEDIA);
        $("#format").val(rowData.FORMAT);
        $("#destClientType").val(rowData.DEST_CLIENT_TYPE);
        $("#isCash").val(rowData.IS_CASH);
        $("#contactType").val(rowData.CONTACT_TYPE);
        $("#destId").val(rowData.DEST_ID);
        $("#sendersContactType").val(rowData.SENDERS_CONTACT_TYPE);
        $("#destType").val(rowData.DEST_TYPE);
        $("#docType").val(rowData.DOC_TYPE);
        $("#printMode").val(rowData.PRINT_MODE);
        $("#dpSettle").val(rowData.DP_SETTLE);
        $("#company").val(rowData.COMPANY);
    }

    $("#fmSettleMethodMod").Validform({
        tiptype:2,
        callback:function(form){
            fmSettleMethodMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmSettleMethodMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_SETTLE_METHOD";
    keyFieldsJson.SETTLE_METHOD=$("#settleMethod").val();
    keyFieldsJson.PAY_REC=$("#payRec").val();
    generalFieldsJson.VERIFY_SECURITY=$("#verifySecurity").val();
    generalFieldsJson.SETTLE_METHOD_DESC=$("#settleMethodDesc").val();
    generalFieldsJson.SETTLE_ACCT_TYPE=$("#settleAcctType").val();
    generalFieldsJson.ROUTE=$("#route").val();
    generalFieldsJson.RELEASE_SECURITY=$("#releaseSecurity").val();
    generalFieldsJson.MEDIA=$("#media").val();
    generalFieldsJson.FORMAT=$("#format").val();
    generalFieldsJson.DEST_CLIENT_TYPE=$("#destClientType").val();
    generalFieldsJson.IS_CASH=$("#isCash").val();
    generalFieldsJson.CONTACT_TYPE=$("#contactType").val();
    generalFieldsJson.DEST_ID=$("#destId").val();
    generalFieldsJson.SENDERS_CONTACT_TYPE=$("#sendersContactType").val();
    generalFieldsJson.DEST_TYPE=$("#destType").val();
    generalFieldsJson.DOC_TYPE=$("#docType").val();
    generalFieldsJson.PRINT_MODE=$("#printMode").val();
    generalFieldsJson.DP_SETTLE=$("#dpSettle").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmSettleMethodMod,"json");
}

function callback_fmSettleMethodMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmSettleMethod").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            SETTLE_METHOD:$("#settleMethod").val(),
            PAY_REC:$("#payRec").val(),
            VERIFY_SECURITY:$("#verifySecurity").val(),
            SETTLE_METHOD_DESC:$("#settleMethodDesc").val(),
            SETTLE_ACCT_TYPE:$("#settleAcctType").val(),
            ROUTE:$("#route").val(),
            RELEASE_SECURITY:$("#releaseSecurity").val(),
            MEDIA:$("#media").val(),
            FORMAT:$("#format").val(),
            DEST_CLIENT_TYPE:$("#destClientType").val(),
            IS_CASH:$("#isCash").val(),
            CONTACT_TYPE:$("#contactType").val(),
            DEST_ID:$("#destId").val(),
            SENDERS_CONTACT_TYPE:$("#sendersContactType").val(),
            DEST_TYPE:$("#destType").val(),
            DOC_TYPE:$("#docType").val(),
            PRINT_MODE:$("#printMode").val(),
            DP_SETTLE:$("#dpSettle").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmSettleMethodModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

