$(document).ready(function() {

    $("#productRuleNo").val($.session.get("cardNoType")).attr("disabled",true);
	$("#cmcCardOrderNoInfoAdd").Validform({
		tiptype:2,
		callback:function(){
			cmcCardOrderNoInfoAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cmcCardOrderNoInfoAdd(){

    var generalFieldsJson = JSON.stringify({
        productRuleNo:$("#productRuleNo").val(),
        cardNoBegin:$("#cardNoBegin").val(),
        cardNoEnd:$("#cardNoEnd").val(),
        cardNoNow:$("#cardNoNow").val(),
        reqNum:parent.parent.$(".breadcrumb").data("reqNum"),
        operateType:parent.parent.$("#operateType").val()
    });
    var url = contextPath+"/cardOrderNoInfo/add";

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
        success: callback_cmcCardOrderNoInfoAdd,
        complete: function () {
            layer.close(index1);
        }
    });

}

function callback_cmcCardOrderNoInfoAdd(json){
    if (json.success) {
        var dataTable = parent.$('#cmcCardOrderNoInfo').DataTable();
        dataTable.row.add({
            PRODUCT_RULE_NO:$("#productRuleNo").val(),
            CARD_NO_BEGIN:$("#cardNoBegin").val(),
            CARD_NO_END:$("#cardNoEnd").val(),
            CARD_NO_NOW:$("#cardNoNow").val()
        }).draw(false);
  		parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}