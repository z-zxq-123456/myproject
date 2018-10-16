var rowData;
$(document).ready(function() {

    if (parent.$("#cmcCardNoSpecial").find(".selected").length===1){
        rowData = parent.$('#cmcCardNoSpecial').DataTable().rows(".selected").data()[0];
        $("#orderNo").val(rowData.ORDER_NO).attr("disabled",true);
        $("#productRuleNo").val(rowData.PRODUCT_RULE_NO).attr("disabled",true);
        $("#status").val(rowData.STATUS);
    }

    $("#cmcCardNoSpecialMod").Validform({
        tiptype:2,
        callback:function(){
            cmcCardNoSpecialMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cmcCardNoSpecialMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CMC_CARD_NO_SPECIAL";
    keyFieldsJson.ORDER_NO=$("#orderNo").val();
    keyFieldsJson.PRODUCT_RULE_NO=$("#productRuleNo").val();
    generalFieldsJson.STATUS=$("#status").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cmcCardNoSpecialMod,"json");
}

function callback_cmcCardNoSpecialMod(json){
    if (json.success) {
        if (parent.$("#cmcCardNoSpecial").find(".selected").length===1){
            rowData = parent.$('#cmcCardNoSpecial').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#cmcCardNoSpecial").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                ORDER_NO:$("#orderNo").val(),
                PRODUCT_RULE_NO:$("#productRuleNo").val(),
                STATUS:$("#status").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cmcCardNoSpecialModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}