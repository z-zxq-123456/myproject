var rowData;
$(document).ready(function() {

    if (parent.$("#fmBank").find(".selected").length===1){
        rowData = parent.$('#fmBank').DataTable().rows(".selected").data()[0];
        $("#bankCode").val(rowData.BANK_CODE).attr("disabled",true);
        $("#bankName").val(rowData.BANK_NAME);
        $("#bankType").val(rowData.BANK_TYPE);
        $("#status").val(rowData.STATUS);
        $("#company").val(rowData.COMPANY);
        $("#bankId").val(rowData.BANK_ID);
    }

    $("#fmBankMod").Validform({
        tiptype:2,
        callback:function(){
            fmBankMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmBankMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_BANK";
    keyFieldsJson.BANK_CODE=$("#bankCode").val();
    generalFieldsJson.BANK_NAME=$("#bankName").val();
    generalFieldsJson.BANK_TYPE=$("#bankType").val();
    generalFieldsJson.STATUS=$("#status").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.BANK_ID=$("#bankId").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmBankMod,"json");
}

function callback_fmBankMod(json){
    if (json.success) {
        if (parent.$("#fmBank").find(".selected").length===1){
            rowData = parent.$('#fmBank').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#fmBank").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                BANK_CODE:$("#bankCode").val(),
                BANK_NAME:$("#bankName").val(),
                BANK_TYPE:$("#bankType").val(),
                STATUS:$("#status").val(),
                COMPANY:$("#company").val(),
                BANK_ID:$("#bankId").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmBankModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}