var rowData;
$(document).ready(function() {

    if (parent.$("#mbCollatType").find(".selected").length===1){
        rowData = parent.$('#mbCollatType').DataTable().rows(".selected").data()[0];
        $("#collatType").val(rowData.COLLAT_TYPE).attr("disabled",true);
        $("#collatTypeDesc").val(rowData.COLLAT_TYPE_DESC);
    }

    $("#mbCollatTypeMod").Validform({
        tiptype:2,
        callback:function(){
            mbCollatTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbCollatTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_COLLAT_TYPE";
    keyFieldsJson.COLLAT_TYPE=$("#collatType").val();
    generalFieldsJson.COLLAT_TYPE_DESC=$("#collatTypeDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbCollatTypeMod,"json");
}

function callback_mbCollatTypeMod(json){
    if (json.success) {
        if (parent.$("#mbCollatType").find(".selected").length===1){
            rowData = parent.$('#mbCollatType').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#mbCollatType").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                COLLAT_TYPE:$("#collatType").val(),
                COLLAT_TYPE_DESC:$("#collatTypeDesc").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbCollatTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}