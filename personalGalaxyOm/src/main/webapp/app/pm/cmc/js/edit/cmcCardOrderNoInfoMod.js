var rowData;
$(document).ready(function() {

    if (parent.$("#cmcCardOrderNoInfo").find(".selected").length===1){
        rowData = parent.$('#cmcCardOrderNoInfo').DataTable().rows(".selected").data()[0];
        $("#productRuleNo").val(rowData.PRODUCT_RULE_NO).attr("disabled",true);
        $("#cardNoBegin").val(rowData.CARD_NO_BEGIN);
        $("#cardNoEnd").val(rowData.CARD_NO_END);
        $("#cardNoNow").val(rowData.CARD_NO_NOW);
    }

    $("#cmcCardOrderNoInfoMod").Validform({
        tiptype:2,
        callback:function(){
            cmcCardOrderNoInfoMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cmcCardOrderNoInfoMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CMC_CARD_ORDER_NO_INFO";
    keyFieldsJson.PRODUCT_RULE_NO=$("#productRuleNo").val();
    generalFieldsJson.CARD_NO_BEGIN=$("#cardNoBegin").val();
    generalFieldsJson.CARD_NO_END=$("#cardNoEnd").val();
    generalFieldsJson.CARD_NO_NOW=$("#cardNoNow").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cmcCardOrderNoInfoMod,"json");
}

function callback_cmcCardOrderNoInfoMod(json){
    if (json.success) {
        if (parent.$("#cmcCardOrderNoInfo").find(".selected").length===1){
            rowData = parent.$('#cmcCardOrderNoInfo').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#cmcCardOrderNoInfo").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                PRODUCT_RULE_NO:$("#productRuleNo").val(),
                CARD_NO_BEGIN:$("#cardNoBegin").val(),
                CARD_NO_END:$("#cardNoEnd").val(),
                CARD_NO_NOW:$("#cardNoNow").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cmcCardOrderNoInfoModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}