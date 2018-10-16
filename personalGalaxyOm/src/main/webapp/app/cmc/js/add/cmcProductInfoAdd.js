$(document).ready(function() {
    getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=CMC_CARD_BIN_INFO&tableCol=BIN_ORDER,BIN",
		id: "binOrder",
        async: false
    });
    getPkList({
        url: contextPath + "/cmcRuleNo/getAllForSelect?tableCol=RULE_CODE",
        id: "cardTypeCode",
        async: false
    });

    $("#cardTypeCode").change(function(){
        if($("#cardTypeCode").val()!=""){

            $.ajax({
                url: contextPath+"/cmcRuleNo/selectByPrimaryKey",
                data:{"ruleCode":$("#cardTypeCode").val()	},
                type:"POST",
                dataType: "json",
                async: false,
                success: function(data) {
                    $("#cardTypeName").val(data.ruleDesc).attr("disabled",true);
                    $("#cardNoType").val(data.ruleNo).attr("disabled",true);
                }
            });
        }else{
            $("#cardTypeName").val("").attr("disabled",true);
            $("#cardNoType").val("").attr("disabled",true);
        }

    });

    $("#cardProductCode").val(parent.parent.$("#newCardProductCode").val()).attr("disabled",true);
    $("#cardProductName").val(parent.parent.$("#newCardProductName").val()).attr("disabled",true);
    $("#publishChannel").val(parent.parent.$("#publishChannel").val()).attr("disabled",true);
    $("#cardProductType").val(parent.parent.$("#cardProductType").val()).attr("disabled",true);
    $("#setFixExDate").val("1").attr("disabled",true);//1：使用固定有效期
    if (parent.parent.$("#status").val() === 'A'){
        $("#enableFlag").val('0').attr("disabled",true);
    }else
        $("#enableFlag").val('1').attr("disabled",true);

    today("#enableDate")

    $("#cmcProductInfoAdd").Validform({
        tiptype:2,
        callback:function(){
            cmcProductInfoAdd();
            return false;
        }
    });
	$(".select2").select2();
});

function today(ele){
    var now=new Date();
    var m = now.getMonth()+1;
    var d = now.getDate();
    if (m<10) m = '0' + m;
    if (d<10) d = '0' + d;
    var time=""+now.getFullYear()+ m + d;
    $(ele).val(time);
}

function cmcProductInfoAdd(){
    var generalFieldsJson = JSON.stringify({
        cardProductCode:$("#cardProductCode").val(),
        cardProductName:$("#cardProductName").val(),
        binOrder:$("#binOrder").val(),
        publishChannel:$("#publishChannel").val(),
        cardTypeCode:$("#cardTypeCode").val(),
        cardTypeName:$("#cardTypeName").val(),
        cardProductType:$("#cardProductType").val(),
        cardCrdbflg:$("#cardCrdbflg").val(),
        cardNoType:$("#cardNoType").val(),
        //feeLevel:$("#feeLevel").val(),
        //cardLevel:$("#cardLevel").val(),
        enableDate:$("#enableDate").val(),
        enableFlag:$("#enableFlag").val(),
        //orderFlag:$("#orderFlag").val(),
        //beginSeq:$("#beginSeq").val(),
        //endSeq:$("#endSeq").val(),
        cardPhySort:$("#cardPhySort").val(),
        atmErrNum:$("#atmErrNum").val(),
        totalErrNum:$("#totalErrNum").val(),
        cvnTotErrNum:$("#cvnTotErrNum").val(),
        cvn2TotErrNum:$("#cvn2TotErrNum").val(),
        lastTotErrNum:$("#lastTotErrNum").val(),
        ccy:$("#ccy").val(),
        //markFlg:$("#markFlg").val(),
        //activateFee:$("#activateFee").val(),
        maxHoldNo:$("#maxHoldNo").val(),
        //pswdMark:$("#pswdMark").val(),
        //lostMark:$("#lostMark").val(),
        //exDate:$("#exDate").val(),
        setFixExDate:$("#setFixExDate").val(),
        fixExDate:$("#fixExDate").val(),
        //invalidDate:$("#invalidDate").val(),
        //interfacePermit:$("#interfacePermit").val(),
        //issueTarget:$("#issueTarget").val(),
        //beginAmt:$("#beginAmt").val(),
        //convalue:$("#convalue").val(),
        reqNum:parent.parent.$(".breadcrumb").data("reqNum"),
        operateType:parent.parent.$("#operateType").val()
    });

    var url = contextPath+"/cardProductInfo/add";

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
        success: callback_cmcProductInfoAdd,
        complete: function () {
            layer.close(index1);
        }
    });

}

function callback_cmcProductInfoAdd(json){
	if (json.success) {
        var dataTable=parent.$('#cmcProductInfo').DataTable();
        dataTable.row.add({
            CARD_PRODUCT_CODE:$("#cardProductCode").val(),
            CARD_PRODUCT_NAME:$("#cardProductName").val(),
            BIN_ORDER:$("#binOrder").val(),
            PUBLISH_CHANNEL:$("#publishChannel").val(),
            CARD_TYPE_CODE:$("#cardTypeCode").val(),
            CARD_TYPE_NAME:$("#cardTypeName").val(),
            CARD_PRODUCT_TYPE:$("#cardProductType").val(),
            CARD_CRDBFLG:$("#cardCrdbflg").val(),
            CARD_NO_TYPE:$("#cardNoType").val(),
            //FEE_LEVEL:$("#feeLevel").val(),
            //CARD_LEVEL:$("#cardLevel").val(),
            ENABLE_FLAG:$("#enableFlag").val(),
            ENABLE_DATE:$("#enableDate").val(),
            //ORDER_FLAG:$("#orderFlag").val(),
            //BEGIN_SEQ:$("#beginSeq").val(),
            //END_SEQ:$("#endSeq").val(),
            CARD_PHY_SORT:$("#cardPhySort").val(),
            ATM_ERR_NUM:$("#atmErrNum").val(),
            TOTAL_ERR_NUM:$("#totalErrNum").val(),
            CVN_TOT_ERR_NUM:$("#cvnTotErrNum").val(),
            CVN2_TOT_ERR_NUM:$("#cvn2TotErrNum").val(),
            LAST_TOT_ERR_NUM:$("#lastTotErrNum").val(),
            CCY:$("#ccy").val(),
            //MARK_FLG:$("#markFlg").val(),
            //ACTIVATE_FEE:$("#activateFee").val(),
            MAX_HOLD_NO:$("#maxHoldNo").val(),
            //PSWD_MARK:$("#pswdMark").val(),
            //EX_DATE:$("#exDate").val(),
            SET_FIX_EX_DATE:$("#setFixExDate").val(),
            FIX_EX_DATE:$("#fixExDate").val()//,
            //INVALID_DATE:$("#invalidDate").val(),
            //INTERFACE_PERMIT:$("#interfacePermit").val(),
            //ISSUE_TARGET:$("#issueTarget").val(),
            //BEGIN_AMT:$("#beginAmt").val(),
            //CONVALUE:$("#convalue").val()
        }).draw(false);
        mappingBaseInfo();
        parent.isNullProductInfo();
        parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mappingBaseInfo() {
    $.session.remove("cardProductCode");
    $.session.set("cardProductCode",$("#cardProductCode").val());
    $.session.remove("cardNoType");
    $.session.set("cardNoType",$("#cardNoType").val());
}