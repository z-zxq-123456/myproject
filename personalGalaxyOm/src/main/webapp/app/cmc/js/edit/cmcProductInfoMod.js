var rowData;
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
    rowData = parent.$('#cmcProductInfo').DataTable().rows(".selected").data()[0];
    inherit();

    if (parent.parent.$("#status").val() === 'A'){
        $("#enableFlag").val('0').attr("disabled",true);
    }else
        $("#enableFlag").val('1').attr("disabled",true);

    if (parent.$("#cmcProductInfo").find(".selected").length===1)
        mappingData();

    $("#cmcProductInfoMod").Validform({
        tiptype:2,
        callback:function(){
            cmcProductInfoMod();
            return false;
        }
    });
    $(".select2").select2();
});

function inherit() {
    $("#cardProductCode").val(rowData.CARD_PRODUCT_CODE).attr("disabled",true);
    $("#cardProductName").val(rowData.CARD_PRODUCT_NAME).attr("disabled",true);
    $("#cardProductType").val(rowData.CARD_PRODUCT_TYPE).select2().attr("disabled",true);
    $("#publishChannel").val(rowData.PUBLISH_CHANNEL).select2().attr("disabled",true);
    $("#binOrder").val(rowData.BIN_ORDER).select2().attr("disabled",false);
    $("#cardTypeCode").val(rowData.CARD_TYPE_CODE).select2().attr("disabled",false);
    $("#cardNoType").val(rowData.CARD_NO_TYPE).attr("disabled",false);
    $("#cardTypeName").val(rowData.CARD_TYPE_NAME).attr("disabled",false);
    $("#cardPhySort").val(rowData.CARD_PHY_SORT).select2().attr("disabled",true);
    $("#atmErrNum").val(rowData.ATM_ERR_NUM).attr("disabled",true);
    $("#cvnTotErrNum").val(rowData.CVN_TOT_ERR_NUM).attr("disabled",true);
    $("#lastTotErrNum").val(rowData.LAST_TOT_ERR_NUM).attr("disabled",true);
    $("#ccy").val(rowData.CCY).attr("disabled",true);
    $("#setFixExDate").val(rowData.SET_FIX_EX_DATE).select2().attr("disabled",true);
    $("#fixExDate").val(rowData.FIX_EX_DATE).attr("disabled",true);
}

function mappingData() {

    $("#binOrder").val(rowData.BIN_ORDER);
    $("#cardTypeCode").val(rowData.CARD_TYPE_CODE);
    $("#cardTypeName").val(rowData.CARD_TYPE_NAME);
    $("#cardCrdbflg").val(rowData.CARD_CRDBFLG);
    $("#cardNoType").val(rowData.CARD_NO_TYPE);
    //$("#feeLevel").val(rowData.FEE_LEVEL);
    //$("#cardLevel").val(rowData.CARD_LEVEL);
    $("#enableDate").val(rowData.ENABLE_DATE);
    //$("#orderFlag").val(rowData.ORDER_FLAG);
    //$("#beginSeq").val(rowData.BEGIN_SEQ);
    //$("#endSeq").val(rowData.END_SEQ);
    $("#totalErrNum").val(rowData.TOTAL_ERR_NUM);
    $("#cvn2TotErrNum").val(rowData.CVN2_TOT_ERR_NUM);
    //$("#markFlg").val(rowData.MARK_FLG);
    //$("#activateFee").val(rowData.ACTIVATE_FEE);
    $("#maxHoldNo").val(rowData.MAX_HOLD_NO);
    //$("#pswdMark").val(rowData.PSWD_MARK);
    //$("#lostMark").val(rowData.LOST_MARK);
    //$("#exDate").val(rowData.EX_DATE);


    //$("#invalidDate").val(rowData.INVALID_DATE);
    //$("#interfacePermit").val(rowData.INTERFACE_PERMIT);
    //$("#issueTarget").val(rowData.ISSUE_TARGET);
    //$("#beginAmt").val(rowData.BEGIN_AMT);
    //$("#convalue").val(rowData.CONVALUE);
}
function cmcProductInfoMod(){
    var url = contextPath+"/cardProductInfo/update";
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
        success: callback_cmcProductInfoMod,
        complete: function () {
            layer.close(index1);
        }
    });
}

function callback_cmcProductInfoMod(json){
    if (json.success) {
        if (parent.$("#cmcProductInfo").find(".selected").length===1){
            rowData = parent.$('#cmcProductInfo').DataTable().rows(".selected").data()[0];
            var dataTable=parent.$("#cmcProductInfo").DataTable();
            dataTable.row(".selected").remove().draw(false);
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
                //LOST_MARK:$("#lostMark").val(),
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
            parent.showMsgDuringTime("修改成功");
        }
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