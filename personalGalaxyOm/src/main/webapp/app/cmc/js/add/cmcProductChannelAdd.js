$(document).ready(function() {

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

    $("#cardProductCode").val( $.session.get("cardProductCode")).attr("disabled",true);
    $("#cmcProductChannelAdd").Validform({
        tiptype:2,
        callback:function(){
            cmcProductChannelAdd();
            return false;
        }
    });

    $(".select2").select2();
});

function cmcProductChannelAdd(){

    var generalFieldsJson = JSON.stringify({
        cardProductCode:$("#cardProductCode").val(),
        limitChannel:$("#limitChannel").val(),
        authTranType:$("#authTranType").val(),
        reqNum:parent.parent.$(".breadcrumb").data("reqNum"),
        operateType:parent.parent.$("#operateType").val()
    });
    var url = contextPath+"/cardProductChannel/add";

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
        success: callback_cmcProductChannelAdd,
        complete: function () {
            layer.close(index1);
        }
    });

}

function callback_cmcProductChannelAdd(json){
    if (json.success) {
        var dataTable = parent.$('#cmcProductChannel').DataTable();
        dataTable.row.add({
            CARD_PRODUCT_CODE:$("#cardProductCode").val(),
            LIMIT_CHANNEL:$("#limitChannel").val(),
            AUTH_TRAN_TYPE:$("#authTranType").val()
        }).draw(false);
        parent.showMsgDuringTime("添加成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}