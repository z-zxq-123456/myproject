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
    var url = contextPath+"/cardNoParam/update";
    var generalFieldsJson = JSON.stringify({
        productRuleNo:$("#productRuleNo").val(),
        cardNum:$("#cardNum").val(),
        flag:$("#flag").val(),
        thresholdNum:$("#thresholdNum").val(),
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
        success: callback_cmcCardNoParamMod,
        complete: function () {
            layer.close(index1);
        }
    });
}

function callback_cmcCardNoParamMod(json){
    if (json.success) {
        if (parent.$("#cmcCardNoParam").find(".selected").length===1){
            rowData = parent.$('#cmcCardNoParam').DataTable().rows(".selected").data()[0];
            var dataTable=parent.$("#cmcCardNoParam").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                PRODUCT_RULE_NO:$("#productRuleNo").val(),
                CARD_NUM:$("#cardNum").val(),
                FLAG:$("#flag").val(),
                THRESHOLD_NUM:$("#thresholdNum").val()
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}