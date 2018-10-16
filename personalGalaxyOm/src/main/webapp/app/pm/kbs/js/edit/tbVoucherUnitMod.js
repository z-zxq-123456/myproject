var rowData;
$(document).ready(function() {

    if (parent.$("#tbVoucherUnit").find(".selected").length===1){
        rowData = parent.$('#tbVoucherUnit').DataTable().rows(".selected").data()[0];
        $("#docType").val(rowData.DOC_TYPE).attr("disabled",true);
        $("#unitCode").val(rowData.UNIT_CODE).attr("disabled",true);
        $("#unitBase").val(rowData.UNIT_BASE);
        $("#unitDesc").val(rowData.UNIT_DESC);
    }

    $("#tbVoucherUnitMod").Validform({
        tiptype:2,
        callback:function(){
            tbVoucherUnitMod();
            return false;
        }
    });

    $(".select2").select2();
});

function tbVoucherUnitMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="TB_VOUCHER_UNIT";
    keyFieldsJson.DOC_TYPE=$("#docType").val();
    keyFieldsJson.UNIT_CODE=$("#unitCode").val();
    generalFieldsJson.UNIT_BASE=$("#unitBase").val();
    generalFieldsJson.UNIT_DESC=$("#unitDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_tbVoucherUnitMod,"json");
}

function callback_tbVoucherUnitMod(json){
    if (json.success) {
        if (parent.$("#tbVoucherUnit").find(".selected").length===1){
            rowData = parent.$('#tbVoucherUnit').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#tbVoucherUnit").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                DOC_TYPE:$("#docType").val(),
                UNIT_CODE:$("#unitCode").val(),
                UNIT_BASE:$("#unitBase").val(),
                UNIT_DESC:$("#unitDesc").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function tbVoucherUnitModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}