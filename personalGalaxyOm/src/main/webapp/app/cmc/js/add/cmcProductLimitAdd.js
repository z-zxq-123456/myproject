$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=CMC_CHANNEL&tableCol=CHANNEL_ID,CHANNEL_DESC",
        id: "channelType",
        async: false

    });
    $("#cardProductCode").val( $.session.get("cardProductCode")).attr("disabled",true);
	$("#cmcProductLimitAdd").Validform({
		tiptype:2,
		callback:function(){
			cmcProductLimitAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cmcProductLimitAdd(){
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
    var url = contextPath+"/cardProductLimit/add";
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
        success: callback_cmcProductLimitAdd,
        complete: function () {
            layer.close(index1);
        }
    });
}

function callback_cmcProductLimitAdd(json) {
    if (json.success) {
        var dataTable = parent.$('#cmcProductLimit').DataTable();
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
            TRAN_OUT_LIMIT_TIME:$("#tranOutLimitTime").val()
		}).draw(false);
        parent.showMsgDuringTime("添加成功");
    }else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

