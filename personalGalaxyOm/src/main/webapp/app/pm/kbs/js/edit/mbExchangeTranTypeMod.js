
var rowData;
$(document).ready(function() {

    if (parent.$("#mbExchangeTranType").find(".selected").length===1){
        rowData = parent.$('#mbExchangeTranType').DataTable().rows(".selected").data()[0];
        $("#tranType").val(rowData.TRAN_TYPE).attr("disabled",true);
        $("#exType").val(rowData.EX_TYPE);
        $("#opType").val(rowData.OP_TYPE);
    }

    $("#mbExchangeTranTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            mbExchangeTranTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbExchangeTranTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_EXCHANGE_TRAN_TYPE";
    keyFieldsJson.TRAN_TYPE=$("#tranType").val();
    generalFieldsJson.EX_TYPE=$("#exType").val();
    generalFieldsJson.OP_TYPE=$("#opType").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbExchangeTranTypeMod,"json");
}

function callback_mbExchangeTranTypeMod(json){
    if (json.success) {
        var dataTable=parent.$("#mbExchangeTranType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            TRAN_TYPE:$("#tranType").val(),
            EX_TYPE:$("#exType").val(),
            OP_TYPE:$("#opType").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbExchangeTranTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

