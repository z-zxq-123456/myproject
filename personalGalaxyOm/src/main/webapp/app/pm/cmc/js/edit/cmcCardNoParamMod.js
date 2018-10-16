var rowData;
$(document).ready(function() {

    if (parent.$("#cmcCardNoParam").find(".selected").length===1){
        rowData = parent.$('#cmcCardNoParam').DataTable().rows(".selected").data()[0];
        $("#productRuleNo").val(rowData.PRODUCT_RULE_NO).attr("disabled",true);
        $("#cardNum").val(rowData.CARD_NUM);
        $("#flag").val(rowData.FLAG);
        $("#thresholdNum").val(rowData.THRESHOLD_NUM);
    }

    $("#cmcCardNoParamMod").Validform({
        tiptype:2,
        callback:function(){
            cmcCardNoParamMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cmcCardNoParamMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CMC_CARD_NO_PARAM";
    keyFieldsJson.PRODUCT_RULE_NO=$("#productRuleNo").val();
    generalFieldsJson.CARD_NUM=$("#cardNum").val();
    generalFieldsJson.FLAG=$("#flag").val();
    generalFieldsJson.THRESHOLD_NUM=$("#thresholdNum").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cmcCardNoParamMod,"json");
}

function callback_cmcCardNoParamMod(json){
    if (json.success) {
        if (parent.$("#cmcCardNoParam").find(".selected").length===1){
            rowData = parent.$('#cmcCardNoParam').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#cmcCardNoParam").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                PRODUCT_RULE_NO:$("#productRuleNo").val(),
                CARD_NUM:$("#cardNum").val(),
                FLAG:$("#flag").val(),
                THRESHOLD_NUM:$("#thresholdNum").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cmcCardNoParamModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}