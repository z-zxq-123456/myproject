
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
        id: "ccy",
        async: false
    });

    if (parent.$("#fmCurrencyAttach").find(".selected").length===1){
        rowData = parent.$('#fmCurrencyAttach').DataTable().rows(".selected").data()[0];
        $("#ccy").val(rowData.CCY);
        $("#country").val(rowData.COUNTRY);
        $("#spreadPointType").val(rowData.SPREAD_POINT_TYPE);
        $("#rateTypeRelated").val(rowData.RATE_TYPE_RELATED);
        $("#spreadPoint").val(rowData.SPREAD_POINT);
        $("#rateType").val(rowData.RATE_TYPE);
    }

    $("#fmCurrencyAttachMod").Validform({
        tiptype:2,
        callback:function(form){
            fmCurrencyAttachMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmCurrencyAttachMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_CURRENCY_ATTACH";
    generalFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.COUNTRY=$("#country").val();
    generalFieldsJson.SPREAD_POINT_TYPE=$("#spreadPointType").val();
    generalFieldsJson.RATE_TYPE_RELATED=$("#rateTypeRelated").val();
    generalFieldsJson.SPREAD_POINT=$("#spreadPoint").val();
    generalFieldsJson.RATE_TYPE=$("#rateType").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmCurrencyAttachMod,"json");
}

function callback_fmCurrencyAttachMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmCurrencyAttach").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
        CCY:$("#ccy").val()
        ,COUNTRY:$("#country").val()
        ,SPREAD_POINT_TYPE:$("#spreadPointType").val()
        ,RATE_TYPE_RELATED:$("#rateTypeRelated").val()
        ,SPREAD_POINT:$("#spreadPoint").val()
        ,RATE_TYPE:$("#rateType").val()
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmCurrencyAttachModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

