var rowData;
$(document).ready(function() {

    if (parent.$("#glReserveOutPayType").find(".selected").length===1){
        rowData = parent.$('#glReserveOutPayType').DataTable().rows(".selected").data()[0];
        $("#payType").val(rowData.PAY_TYPE).attr("disabled",true);
        $("#adjustAcctClientNo").val(rowData.ADJUST_ACCT_CLIENT_NO);
        $("#adjustAcctType").val(rowData.ADJUST_ACCT_TYPE);
        $("#payTypeDesc").val(rowData.PAY_TYPE_DESC);
        $("#payAcctClientNo").val(rowData.PAY_ACCT_CLIENT_NO);
        $("#payAcctType").val(rowData.PAY_ACCT_TYPE);
        $("#adjustAcctClientName").val(rowData.ADJUST_ACCT_CLIENT_NAME);
        $("#payAcctClientName").val(rowData.PAY_ACCT_CLIENT_NAME);
    }

    $("#glReserveOutPayTypeMod").Validform({
        tiptype:2,
        callback:function(){
            glReserveOutPayTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function glReserveOutPayTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="GL_RESERVE_OUT_PAY_TYPE";
    keyFieldsJson.PAY_TYPE=$("#payType").val();
    generalFieldsJson.ADJUST_ACCT_CLIENT_NO=$("#adjustAcctClientNo").val();
    generalFieldsJson.ADJUST_ACCT_TYPE=$("#adjustAcctType").val();
    generalFieldsJson.PAY_TYPE_DESC=$("#payTypeDesc").val();
    generalFieldsJson.PAY_ACCT_CLIENT_NO=$("#payAcctClientNo").val();
    generalFieldsJson.PAY_ACCT_TYPE=$("#payAcctType").val();
    generalFieldsJson.ADJUST_ACCT_CLIENT_NAME=$("#adjustAcctClientName").val();
    generalFieldsJson.PAY_ACCT_CLIENT_NAME=$("#payAcctClientName").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_glReserveOutPayTypeMod,"json");
}

function callback_glReserveOutPayTypeMod(json){
    if (json.success) {
        if (parent.$("#glReserveOutPayType").find(".selected").length===1){
            rowData = parent.$('#glReserveOutPayType').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#glReserveOutPayType").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                PAY_TYPE:$("#payType").val(),
                ADJUST_ACCT_CLIENT_NO:$("#adjustAcctClientNo").val(),
                ADJUST_ACCT_TYPE:$("#adjustAcctType").val(),
                PAY_TYPE_DESC:$("#payTypeDesc").val(),
                PAY_ACCT_CLIENT_NO:$("#payAcctClientNo").val(),
                PAY_ACCT_TYPE:$("#payAcctType").val(),
                ADJUST_ACCT_CLIENT_NAME:$("#adjustAcctClientName").val(),
                PAY_ACCT_CLIENT_NAME:$("#payAcctClientName").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function glReserveOutPayTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}