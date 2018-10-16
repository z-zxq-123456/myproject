var rowData;
$(document).ready(function() {

    if (parent.$("#mbCommissionParameter").find(".selected").length===1){
        rowData = parent.$('#mbCommissionParameter').DataTable().rows(".selected").data()[0];
        $("#clientType").val(rowData.CLIENT_TYPE).attr("disabled",true);
        $("#programId").val(rowData.PROGRAM_ID).attr("disabled",true);
        $("#status").val(rowData.STATUS);
    }

    $("#mbCommissionParameterMod").Validform({
        tiptype:2,
        callback:function(){
            mbCommissionParameterMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbCommissionParameterMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_COMMISSION_PARAMETER";
    keyFieldsJson.CLIENT_TYPE=$("#clientType").val();
    keyFieldsJson.PROGRAM_ID=$("#programId").val();
    generalFieldsJson.STATUS=$("#status").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbCommissionParameterMod,"json");
}

function callback_mbCommissionParameterMod(json){
    if (json.success) {
        if (parent.$("#mbCommissionParameter").find(".selected").length===1){
            rowData = parent.$('#mbCommissionParameter').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#mbCommissionParameter").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                CLIENT_TYPE:$("#clientType").val(),
                PROGRAM_ID:$("#programId").val(),
                STATUS:$("#status").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbCommissionParameterModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}