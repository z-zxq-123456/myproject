var rowData;
$(document).ready(function() {

    if (parent.$("#cmcCardNoRoleEx").find(".selected").length===1){
        rowData = parent.$('#cmcCardNoRoleEx').DataTable().rows(".selected").data()[0];
        $("#paramName").val(rowData.PARAM_NAME).attr("disabled",true);
        $("#paramValue").val(rowData.PARAM_VALUE);
        $("#expand1").val(rowData.EXPAND1);
        $("#expand2").val(rowData.EXPAND2);
        $("#expand3").val(rowData.EXPAND3);
        $("#expand4").val(rowData.EXPAND4);
        $("#expand5").val(rowData.EXPAND5);
        $("#expand6").val(rowData.EXPAND6);
        $("#expand7").val(rowData.EXPAND7);
    }

    $("#cmcCardNoRoleExMod").Validform({
        tiptype:2,
        callback:function(){
            cmcCardNoRoleExMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cmcCardNoRoleExMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CMC_CARD_NO_ROLE_EX";
    keyFieldsJson.PARAM_NAME=$("#paramName").val();
    generalFieldsJson.PARAM_VALUE=$("#paramValue").val();
    generalFieldsJson.EXPAND1=$("#expand1").val();
    generalFieldsJson.EXPAND2=$("#expand2").val();
    generalFieldsJson.EXPAND3=$("#expand3").val();
    generalFieldsJson.EXPAND4=$("#expand4").val();
    generalFieldsJson.EXPAND5=$("#expand5").val();
    generalFieldsJson.EXPAND6=$("#expand6").val();
    generalFieldsJson.EXPAND7=$("#expand7").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cmcCardNoRoleExMod,"json");
}

function callback_cmcCardNoRoleExMod(json){
    if (json.success) {
        if (parent.$("#cmcCardNoRoleEx").find(".selected").length===1){
            rowData = parent.$('#cmcCardNoRoleEx').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#cmcCardNoRoleEx").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                PARAM_NAME:$("#paramName").val(),
                PARAM_VALUE:$("#paramValue").val(),
                EXPAND1:$("#expand1").val(),
                EXPAND2:$("#expand2").val(),
                EXPAND3:$("#expand3").val(),
                EXPAND4:$("#expand4").val(),
                EXPAND5:$("#expand5").val(),
                EXPAND6:$("#expand6").val(),
                EXPAND7:$("#expand7").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cmcCardNoRoleExModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}