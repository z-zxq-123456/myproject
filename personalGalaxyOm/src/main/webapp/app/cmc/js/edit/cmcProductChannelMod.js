var rowData;
$(document).ready(function() {

    if (parent.$("#cmcProductChannel").find(".selected").length===1){
        getPkList({
            url: contextPath + "/baseCommon/pklistBase?tableName=CMC_CHANNEL&tableCol=CHANNEL_ID,CHANNEL_DESC",
            id: "limitChannel",
            async: false
        });

        getPkList({
            url: contextPath + "/baseCommon/pklistBase?tableName=CMC_TRAN_DEF&tableCol=TRAN_TYPE,TRAN_TYPE_DESC",
            id: "authTranType",
            async: false
        });

        rowData = parent.$('#cmcProductChannel').DataTable().rows(".selected").data()[0];
        $("#cardProductCode").val(rowData.CARD_PRODUCT_CODE).attr("disabled",true);
        $("#limitChannel").val(rowData.LIMIT_CHANNEL);
        $("#authTranType").val(rowData.AUTH_TRAN_TYPE);
    }

    $("#cardProductChannelMod").Validform({
        tiptype:2,
        callback:function(){
            cmcProductChannelMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cmcProductChannelMod(){
    var urlStr = contextPath+"/cardProductChannel/update";
    var generalFieldsJson = JSON.stringify({
        cardProductCode:$("#cardProductCode").val(),
        limitChannel:$("#limitChannel").val(),
        authTranType:$("#authTranType").val(),
        reqNum:parent.parent.$(".breadcrumb").data("reqNum"),
        operateType:parent.parent.$("#operateType").val()
    });
    $.ajax({
        type: "post",
        url: urlStr,
        data: generalFieldsJson,
        dataType: "json",
        cache: false,
        contentType: "application/json",
        beforeSend: function () {
            index1 = layer.load(4, {
                shade: [0.4, '#777777'] //0.5透明度的白色背景
            });
        },
        success: callback_cmcProductChannelMod,
        complete: function () {
            layer.close(index1);
        }
    });
}

function callback_cmcProductChannelMod(json){
    if (json.success) {
        if (parent.$("#cmcProductChannel").find(".selected").length===1){
            rowData = parent.$('#cmcProductChannel').DataTable().rows(".selected").data()[0];
            var dataTable=parent.$("#cmcProductChannel").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                CARD_PRODUCT_CODE:$("#cardProductCode").val(),
                LIMIT_CHANNEL:$("#limitChannel").val(),
                AUTH_TRAN_TYPE:$("#authTranType").val()
            }).draw(false);
            parent.showMsgDuringTime("修改成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}