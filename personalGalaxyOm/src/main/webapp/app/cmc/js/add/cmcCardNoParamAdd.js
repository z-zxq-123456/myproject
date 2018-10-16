$(document).ready(function() {


	$("#cmcCardNoParamAdd").Validform({
		tiptype:2,
		callback:function(){
			cmcCardNoParamAdd();
			return false;
		}
	});
    $("#productRuleNo").val($.session.get("cardNoType")).attr("disabled",true);
	$(".select2").select2();
});

function cmcCardNoParamAdd(){
    var generalFieldsJson = JSON.stringify({
        productRuleNo:$("#productRuleNo").val(),
        cardNum:$("#cardNum").val(),
        flag:$("#flag").val(),
        thresholdNum:$("#thresholdNum").val(),
        reqNum:parent.parent.$(".breadcrumb").data("reqNum")
	});
    var url = contextPath+"/cardNoParam/add";
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
        success: callback_cmcCardNoParamAdd,
        complete: function () {
            layer.close(index1);
        }
    });
}

function callback_cmcCardNoParamAdd(json){
    if (json.success) {
        var dataTable=parent.$('#cmcCardNoParam').DataTable();
        dataTable.row.add({
            PRODUCT_RULE_NO:$("#productRuleNo").val(),
            CARD_NUM:$("#cardNum").val(),
            FLAG:$("#flag").val(),
            THRESHOLD_NUM:$("#thresholdNum").val(),
            OPERATE_TYPE:parent.parent.$("#operateType").val()
        }).draw(false);
        parent.showMsgDuringTime("添加成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}