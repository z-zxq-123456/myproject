var rowData;
$(document).ready(function() {

    if (parent.$("#cmcProductLimit").find(".selected").length===1){
        rowData = parent.$('#cmcProductLimit').DataTable().rows(".selected").data()[0];
        $("#cardProductCode").val(rowData.CARD_PRODUCT_CODE).attr("disabled",true);
        $("#channelType").val(rowData.CHANNEL_TYPE).attr("disabled",true);
        $("#ccy").val(rowData.CCY);
        $("#period").val(rowData.PERIOD).attr("disabled",true);
        $("#conLimitAmt").val(rowData.CON_LIMIT_AMT);
        $("#tranInLimitAmt").val(rowData.TRAN_IN_LIMIT_AMT);
        $("#tranOutLimitAmt").val(rowData.TRAN_OUT_LIMIT_AMT);
        $("#conLimitTime").val(rowData.CON_LIMIT_TIME);
        $("#tranInLimitTime").val(rowData.TRAN_IN_LIMIT_TIME);
        $("#tranOutLimitTime").val(rowData.TRAN_OUT_LIMIT_TIME);
    }

    $("#cmcProductLimitMod").Validform({
        tiptype:2,
        callback:function(){
            cmcProductLimitMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cmcProductLimitMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CMC_PRODUCT_LIMIT";
    keyFieldsJson.CARD_PRODUCT_CODE=$("#cardProductCode").val();
    keyFieldsJson.CHANNEL_TYPE=$("#channelType").val();
    generalFieldsJson.CCY=$("#ccy").val();
    keyFieldsJson.PERIOD=$("#period").val();
    generalFieldsJson.CON_LIMIT_AMT=$("#conLimitAmt").val();
    generalFieldsJson.TRAN_IN_LIMIT_AMT=$("#tranInLimitAmt").val();
    generalFieldsJson.TRAN_OUT_LIMIT_AMT=$("#tranOutLimitAmt").val();
    generalFieldsJson.CON_LIMIT_TIME=$("#conLimitTime").val();
    generalFieldsJson.TRAN_IN_LIMIT_TIME=$("#tranInLimitTime").val();
    generalFieldsJson.TRAN_OUT_LIMIT_TIME=$("#tranOutLimitTime").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cmcProductLimitMod,"json");
}

function callback_cmcProductLimitMod(json){
    if (json.success) {
        if (parent.$("#cmcProductLimit").find(".selected").length===1){
            rowData = parent.$('#cmcProductLimit').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#cmcProductLimit").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                CARD_PRODUCT_CODE:$("#cardProductCode").val(),
                CHANNEL_TYPE:$("#channelType").val(),
                CCY:$("#ccy").val(),
                PERIOD:$("#period").val(),
                CON_LIMIT_AMT:$("#conLimitAmt").val(),
                TRAN_IN_LIMIT_AMT:$("#tranInLimitAmt").val(),
                TRAN_OUT_LIMIT_AMT:$("#tranOutLimitAmt").val(),
                CON_LIMIT_TIME:$("#conLimitTime").val(),
                TRAN_IN_LIMIT_TIME:$("#tranInLimitTime").val(),
                TRAN_OUT_LIMIT_TIME:$("#tranOutLimitTime").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cmcProductLimitModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}