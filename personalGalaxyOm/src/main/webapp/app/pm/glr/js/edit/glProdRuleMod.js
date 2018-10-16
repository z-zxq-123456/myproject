
var rowData;
$(document).ready(function() {

    if (parent.$("#glProdRule").find(".selected").length===1){
        rowData = parent.$('#glProdRule').DataTable().rows(".selected").data()[0];
        $("#tranType").val(rowData.TRAN_TYPE).attr("disabled",true);
        $("#accountingStatus").val(rowData.ACCOUNTING_STATUS).attr("disabled",true);
        $("#ccy").val(rowData.CCY).attr("disabled",true);
        $("#clientType").val(rowData.CLIENT_TYPE).attr("disabled",true);
        $("#tranEventType").val(rowData.TRAN_EVENT_TYPE).attr("disabled",true);
        $("#prodType").val(rowData.PROD_TYPE).attr("disabled",true);
        $("#sourceType").val(rowData.SOURCE_TYPE).attr("disabled",true);
        $("#sysName").val(rowData.SYS_NAME).attr("disabled",true);
        $("#accountingNo").val(rowData.ACCOUNTING_NO);
        $("#customRule").val(rowData.CUSTOM_RULE);
        $("#accountingDesc").val(rowData.ACCOUNTING_DESC);
    }

    $("#glProdRuleMod").Validform({
        tiptype:2,
        callback:function(form){
            glProdRuleMod();
            return false;
        }
    });

    $(".select2").select2();
});

function glProdRuleMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="GL_PROD_RULE";
    paraJson.systemId="GLR";
    keyFieldsJson.TRAN_TYPE=$("#tranType").val();
    keyFieldsJson.ACCOUNTING_STATUS=$("#accountingStatus").val();
    keyFieldsJson.CCY=$("#ccy").val();
    keyFieldsJson.CLIENT_TYPE=$("#clientType").val();
    keyFieldsJson.TRAN_EVENT_TYPE=$("#tranEventType").val();
    keyFieldsJson.PROD_TYPE=$("#prodType").val();
    keyFieldsJson.SOURCE_TYPE=$("#sourceType").val();
    keyFieldsJson.SYS_NAME=$("#sysName").val();
    generalFieldsJson.ACCOUNTING_NO=$("#accountingNo").val();
    generalFieldsJson.CUSTOM_RULE=$("#customRule").val();
    generalFieldsJson.ACCOUNTING_DESC=$("#accountingDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_glProdRuleMod,"json");
}

function callback_glProdRuleMod(json){
    if (json.success) {
        var dataTable=parent.$("#glProdRule").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            TRAN_TYPE:$("#tranType").val(),
            ACCOUNTING_STATUS:$("#accountingStatus").val(),
            CCY:$("#ccy").val(),
            CLIENT_TYPE:$("#clientType").val(),
            TRAN_EVENT_TYPE:$("#tranEventType").val(),
            PROD_TYPE:$("#prodType").val(),
            SOURCE_TYPE:$("#sourceType").val(),
            SYS_NAME:$("#sysName").val(),
            ACCOUNTING_NO:$("#accountingNo").val(),
            CUSTOM_RULE:$("#customRule").val(),
            ACCOUNTING_DESC:$("#accountingDesc").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function glProdRuleModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

