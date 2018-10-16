var rowData;
$(document).ready(function() {

    if (parent.$("#mbSettleTranMapping").find(".selected").length===1){
        rowData = parent.$('#mbSettleTranMapping').DataTable().rows(".selected").data()[0];
        $("#acctType").val(rowData.ACCT_TYPE).attr("disabled",true);
        $("#payRecInd").val(rowData.PAY_REC_IND).attr("disabled",true);
        $("#settleGlType").val(rowData.SETTLE_GL_TYPE).attr("disabled",true);
        $("#tranTypeList").val(rowData.TRAN_TYPE_LIST);
        $("#company").val(rowData.COMPANY);
    }

    $("#mbSettleTranMappingMod").Validform({
        tiptype:2,
        callback:function(){
            mbSettleTranMappingMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbSettleTranMappingMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_SETTLE_TRAN_MAPPING";
    keyFieldsJson.ACCT_TYPE=$("#acctType").val();
    keyFieldsJson.PAY_REC_IND=$("#payRecInd").val();
    keyFieldsJson.SETTLE_GL_TYPE=$("#settleGlType").val();
    generalFieldsJson.TRAN_TYPE_LIST=$("#tranTypeList").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbSettleTranMappingMod,"json");
}

function callback_mbSettleTranMappingMod(json){
    if (json.success) {
        if (parent.$("#mbSettleTranMapping").find(".selected").length===1){
            rowData = parent.$('#mbSettleTranMapping').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#mbSettleTranMapping").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                ACCT_TYPE:$("#acctType").val(),
                PAY_REC_IND:$("#payRecInd").val(),
                SETTLE_GL_TYPE:$("#settleGlType").val(),
                TRAN_TYPE_LIST:$("#tranTypeList").val(),
                COMPANY:$("#company").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbSettleTranMappingModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}