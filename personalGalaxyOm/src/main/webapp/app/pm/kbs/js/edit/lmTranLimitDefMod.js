
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
        id: "ccy",
        async: false
    });

    if (parent.$("#lmTranLimitDef").find(".selected").length===1){
        rowData = parent.$('#lmTranLimitDef').DataTable().rows(".selected").data()[0];
        $("#limitRef").val(rowData.LIMIT_REF).attr("disabled",true);
        $("#minAmt").val(rowData.MIN_AMT);
        $("#maxAmt").val(rowData.MAX_AMT);
        $("#limitType").val(rowData.LIMIT_TYPE);
        $("#limitDesc").val(rowData.LIMIT_DESC);
        $("#effectDate").val(rowData.EFFECT_DATE);
        $("#dealFlow").val(rowData.DEAL_FLOW);
        $("#ccy").val(rowData.CCY);
        $("#status").val(rowData.STATUS);
        $("#otherLevel").val(rowData.LIMIT_LEVEL);
        $("#enableDefine").val(rowData.ENABLE_DEFINE);
    }

    $("#lmTranLimitDefMod").Validform({
        tiptype:2,
        callback:function(form){
            lmTranLimitDefMod();
            return false;
        }
    });

    $(".select2").select2();
});

function lmTranLimitDefMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="LM_TRAN_LIMIT_DEF";
    keyFieldsJson.LIMIT_REF=$("#limitRef").val();
    generalFieldsJson.MIN_AMT=$("#minAmt").val();
    generalFieldsJson.MAX_AMT=$("#maxAmt").val();
    generalFieldsJson.LIMIT_TYPE=$("#limitType").val();
    generalFieldsJson.LIMIT_DESC=$("#limitDesc").val();
    generalFieldsJson.EFFECT_DATE=$("#effectDate").val();
    generalFieldsJson.DEAL_FLOW=$("#dealFlow").val();
    generalFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.STATUS=$("#status").val();
    generalFieldsJson.LIMIT_LEVEL=$("#otherLevel").val();
    generalFieldsJson.ENABLE_DEFINE=$("#enableDefine").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_lmTranLimitDefMod,"json");
}

function callback_lmTranLimitDefMod(json){
    if (json.success) {
        var dataTable=parent.$("#lmTranLimitDef").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            LIMIT_REF:$("#limitRef").val(),
            MIN_AMT:$("#minAmt").val(),
            MAX_AMT:$("#maxAmt").val(),
            LIMIT_TYPE:$("#limitType").val(),
            LIMIT_DESC:$("#limitDesc").val(),
            EFFECT_DATE:$("#effectDate").val(),
            DEAL_FLOW:$("#dealFlow").val(),
            CCY:$("#ccy").val(),
            STATUS:$("#status").val(),
            LIMIT_LEVEL:$("#otherLevel").val(),
            ENABLE_DEFINE:$("#enableDefine").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function lmTranLimitDefModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

