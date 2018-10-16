var rowData;
$(document).ready(function() {

    if (parent.$("#cmcCommon").find(".selected").length===1){
        rowData = parent.$('#cmcCommon').DataTable().rows(".selected").data()[0];
        $("#paramName").val(rowData.PARAM_NAME).attr("disabled",true);
        $("#paramDesc").val(rowData.PARAM_DESC);
        $("#paramOrder").val(rowData.PARAM_ORDER);
        $("#paramValue").val(rowData.PARAM_VALUE);
    }

    $("#cmcCommonMod").Validform({
        tiptype:2,
        callback:function(){
            cmcCommonMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cmcCommonMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CMC_COMMON";
    keyFieldsJson.PARAM_NAME=$("#paramName").val();
    generalFieldsJson.PARAM_DESC=$("#paramDesc").val();
    generalFieldsJson.PARAM_ORDER=$("#paramOrder").val();
    generalFieldsJson.PARAM_VALUE=$("#paramValue").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cmcCommonMod,"json");
}

function callback_cmcCommonMod(json){
    if (json.success) {
        if (parent.$("#cmcCommon").find(".selected").length===1){
            rowData = parent.$('#cmcCommon').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#cmcCommon").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                PARAM_NAME:$("#paramName").val(),
                PARAM_DESC:$("#paramDesc").val(),
                PARAM_ORDER:$("#paramOrder").val(),
                PARAM_VALUE:$("#paramValue").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cmcCommonModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}