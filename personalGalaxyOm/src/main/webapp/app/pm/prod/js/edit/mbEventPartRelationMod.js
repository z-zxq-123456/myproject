
var rowData;
$(document).ready(function() {

    if (parent.$("#mbEventPartRelation").find(".selected").length===1){
        rowData = parent.$('#mbEventPartRelation').DataTable().rows(".selected").data()[0];
        $("#assembleId").val(rowData.ASSEMBLE_ID).attr("disabled",true);
        $("#assembleType").val(rowData.ASSEMBLE_TYPE).attr("disabled",true);
        $("#eventType").val(rowData.EVENT_TYPE).attr("disabled",true);
        $("#partDesc").val(rowData.PART_DESC);
        $("#status").val(rowData.STATUS);
    }

    $("#mbEventPartRelationMod").Validform({
        tiptype:2,
        callback:function(form){
            mbEventPartRelationMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbEventPartRelationMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_EVENT_PART_RELATION";
    keyFieldsJson.ASSEMBLE_ID=$("#assembleId").val();
    keyFieldsJson.ASSEMBLE_TYPE=$("#assembleType").val();
    keyFieldsJson.EVENT_TYPE=$("#eventType").val();
    generalFieldsJson.PART_DESC=$("#partDesc").val();
    generalFieldsJson.STATUS=$("#status").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbEventPartRelationMod,"json");
}

function callback_mbEventPartRelationMod(json){
    if (json.success) {
       if (parent.$("#mbEventPartRelation").find(".selected").length===1){
        rowData = parent.$('#mbEventPartRelation').DataTable().rows(".selected").data()[0];
        var columnStatus = rowData.COLUMN_STATUS;
        var dataTable=parent.$("#mbEventPartRelation").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            ASSEMBLE_ID:$("#assembleId").val(),
            ASSEMBLE_TYPE:$("#assembleType").val(),
            EVENT_TYPE:$("#eventType").val(),
            PART_DESC:$("#partDesc").val(),
            STATUS:$("#status").val(),
            COLUMN_STATUS:columnStatus
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbEventPartRelationModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

