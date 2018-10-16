var rowData;
$(document).ready(function() {

    if (parent.$("#cmcProductLimit").find(".selected").length===1){
        getPkList({
            url: contextPath + "/baseCommon/pklistBase?tableName=CMC_CHANNEL&tableCol=CHANNEL_ID,CHANNEL_DESC",
            id: "channelType",
            async: false
        });
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
    var url = contextPath+"/cardProductLimit/update";
    var generalFieldsJson = JSON.stringify({
        cardProductCode:$("#cardProductCode").val(),
        channelType:$("#channelType").val(),
        ccy:$("#ccy").val(),
        period:$("#period").val(),
        conLimitAmt:$("#conLimitAmt").val(),
        tranInLimitAmt:$("#tranInLimitAmt").val(),
        tranOutLimitAmt:$("#tranOutLimitAmt").val(),
        conLimitTime:$("#conLimitTime").val(),
        tranInLimitTime:$("#tranInLimitTime").val(),
        tranOutLimitTime:$("#tranOutLimitTime").val(),
        reqNum:parent.parent.$(".breadcrumb").data("reqNum"),
        operateType:parent.parent.$("#operateType").val()
    });
    $.ajax({
        type: "post",
        url: url,
        data: generalFieldsJson,
        dataType: "json",
        cache: false,
        contentType: "application/json",
        beforeSend: function () {
            index1 = layer.load(4, {
                shade: [0.4, '#777777'] //0.5透明度的白色背景
            });
        },
        success: callback_cmcProductLimitMod,
        complete: function () {
            layer.close(index1);
        }
    });
}

function callback_cmcProductLimitMod(json){
    if (json.success) {
        if (parent.$("#cmcProductLimit").find(".selected").length===1){
            rowData = parent.$('#cmcProductLimit').DataTable().rows(".selected").data()[0];
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
            }).draw(false);
            parent.showMsgDuringTime("修改成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}
