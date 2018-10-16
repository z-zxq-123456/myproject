var rowData;
$(document).ready(function() {

    if (parent.$("#mbAcctCloseReason").find(".selected").length===1){
        rowData = parent.$('#mbAcctCloseReason').DataTable().rows(".selected").data()[0];
        $("#reasonCode").val(rowData.REASON_CODE).attr("disabled",true);
        $("#reasonCodeDesc").val(rowData.REASON_CODE_DESC);
        $("#company").val(rowData.COMPANY);
    }

    $("#mbAcctCloseReasonMod").Validform({
        tiptype:2,
        callback:function(){
            mbAcctCloseReasonMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbAcctCloseReasonMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_ACCT_CLOSE_REASON";
    keyFieldsJson.REASON_CODE=$("#reasonCode").val();
    generalFieldsJson.REASON_CODE_DESC=$("#reasonCodeDesc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbAcctCloseReasonMod,"json");
}

function callback_mbAcctCloseReasonMod(json){
    if (json.success) {
        if (parent.$("#mbAcctCloseReason").find(".selected").length===1){
            rowData = parent.$('#mbAcctCloseReason').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#mbAcctCloseReason").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                REASON_CODE:$("#reasonCode").val(),
                REASON_CODE_DESC:$("#reasonCodeDesc").val(),
                COMPANY:$("#company").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbAcctCloseReasonModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}