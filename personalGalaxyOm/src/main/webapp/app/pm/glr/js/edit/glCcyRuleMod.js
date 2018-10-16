
var rowData;
$(document).ready(function() {

    if (parent.$("#glCcyRule").find(".selected").length===1){
        rowData = parent.$('#glCcyRule').DataTable().rows(".selected").data()[0];
        $("#buyCcy").val(rowData.BUY_CCY).attr("disabled",true);
        $("#eventType").val(rowData.EVENT_TYPE).attr("disabled",true);
        $("#sellCcy").val(rowData.SELL_CCY).attr("disabled",true);
        $("#accountingNo").val(rowData.ACCOUNTING_NO);
    }

    $("#glCcyRuleMod").Validform({
        tiptype:2,
        callback:function(form){
            glCcyRuleMod();
            return false;
        }
    });

    $(".select2").select2();
});

function glCcyRuleMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="GL_CCY_RULE";
    keyFieldsJson.BUY_CCY=$("#buyCcy").val();
    keyFieldsJson.EVENT_TYPE=$("#eventType").val();
    keyFieldsJson.SELL_CCY=$("#sellCcy").val();
    generalFieldsJson.ACCOUNTING_NO=$("#accountingNo").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_glCcyRuleMod,"json");
}

function callback_glCcyRuleMod(json){
    if (json.success) {
        var dataTable=parent.$("#glCcyRule").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            BUY_CCY:$("#buyCcy").val(),
            EVENT_TYPE:$("#eventType").val(),
            SELL_CCY:$("#sellCcy").val(),
            ACCOUNTING_NO:$("#accountingNo").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function glCcyRuleModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

