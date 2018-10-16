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
    var url = contextPath+"/cardOrderNoInfo/update";
    var generalFieldsJson = JSON.stringify({
        productRuleNo:$("#productRuleNo").val(),
        cardNoBegin:$("#cardNoBegin").val(),
        cardNoEnd:$("#cardNoEnd").val(),
        cardNoNow:$("#cardNoNow").val(),
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
        success: callback_cmcCardOrderNoInfoMod,
        complete: function () {
            layer.close(index1);
        }
    });
}

function callback_cmcCardOrderNoInfoMod(json){
    if (json.success) {
        if (parent.$("#cmcCardOrderNoInfo").find(".selected").length===1){
            rowData = parent.$('#cmcCardOrderNoInfo').DataTable().rows(".selected").data()[0];
            var dataTable=parent.$("#cmcCardOrderNoInfo").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                PRODUCT_RULE_NO:$("#productRuleNo").val(),
                CARD_NO_BEGIN:$("#cardNoBegin").val(),
                CARD_NO_END:$("#cardNoEnd").val(),
                CARD_NO_NOW:$("#cardNoNow").val()
            }).draw(false);
            parent.showMsgDuringTime("修改成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}