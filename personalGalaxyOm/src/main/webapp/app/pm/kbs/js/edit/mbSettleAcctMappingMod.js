var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
        id: "amtTypeList",
        async: false
    });

    if (parent.$("#mbSettleAcctMapping").find(".selected").length===1){
        rowData = parent.$('#mbSettleAcctMapping').DataTable().rows(".selected").data()[0];
        $("#eventType").val(rowData.EVENT_TYPE).attr("disabled",true);
        $("#settleAcctClass").val(rowData.SETTLE_ACCT_CLASS).attr("disabled",true);
        $("#payRecInd").val(rowData.PAY_REC_IND);
        if(rowData.AMT_TYPE_LIST!==undefined){
            $("#amtTypeList").val(rowData.AMT_TYPE_LIST.split(","));
        }
        $("#company").val(rowData.COMPANY);
    }

    $("#mbSettleAcctMappingMod").Validform({
        tiptype:2,
        callback:function(){
            mbSettleAcctMappingMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbSettleAcctMappingMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_SETTLE_ACCT_MAPPING";
    keyFieldsJson.EVENT_TYPE=$("#eventType").val();
    keyFieldsJson.SETTLE_ACCT_CLASS=$("#settleAcctClass").val();
    generalFieldsJson.PAY_REC_IND=$("#payRecInd").val();
    if($("#amtTypeList").val()!==null) {
        generalFieldsJson.AMT_TYPE_LIST = $("#amtTypeList").val().toString();
    }
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbSettleAcctMappingMod,"json");
}

function callback_mbSettleAcctMappingMod(json){
    if (json.success) {
        if (parent.$("#mbSettleAcctMapping").find(".selected").length===1){
            rowData = parent.$('#mbSettleAcctMapping').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#mbSettleAcctMapping").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                EVENT_TYPE:$("#eventType").val(),
                SETTLE_ACCT_CLASS:$("#settleAcctClass").val(),
                PAY_REC_IND:$("#payRecInd").val(),
                AMT_TYPE_LIST:$("#amtTypeList").val(),
                COMPANY:$("#company").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbSettleAcctMappingModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}