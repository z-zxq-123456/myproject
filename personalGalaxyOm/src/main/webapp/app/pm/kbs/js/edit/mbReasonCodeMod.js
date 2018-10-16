
var rowData;
$(document).ready(function() {

    if (parent.$("#mbReasonCode").find(".selected").length===1){
        rowData = parent.$('#mbReasonCode').DataTable().rows(".selected").data()[0];
        $("#reasonCode").val(rowData.REASON_CODE).attr("disabled",true);
        $("#reasonCodeDesc").val(rowData.REASON_CODE_DESC);
        $("#sourceMode").val(rowData.SOURCE_MODE);
    }

    $("#mbReasonCodeMod").Validform({
        tiptype:2,
        callback:function(form){
            mbReasonCodeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbReasonCodeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_REASON_CODE";
    keyFieldsJson.REASON_CODE=$("#reasonCode").val();
    generalFieldsJson.REASON_CODE_DESC=$("#reasonCodeDesc").val();
    generalFieldsJson.SOURCE_MODE=$("#sourceMode").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbReasonCodeMod,"json");
}

function callback_mbReasonCodeMod(json){
    if (json.success) {
        var dataTable=parent.$("#mbReasonCode").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            REASON_CODE:$("#reasonCode").val(),
            REASON_CODE_DESC:$("#reasonCodeDesc").val(),
            SOURCE_MODE:$("#sourceMode").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbReasonCodeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

