
var rowData;
$(document).ready(function() {

    if (parent.$("#mbEventPart").find(".selected").length===1){
        rowData = parent.$('#mbEventPart').DataTable().rows(".selected").data()[0];
        $("#assembleId").val(rowData.ASSEMBLE_ID).attr("disabled",true);
        $("#attrKey").val(rowData.ATTR_KEY).attr("disabled",true);
        $("#eventType").val(rowData.EVENT_TYPE).attr("disabled",true);
        $("#attrValue").val(rowData.ATTR_VALUE);
    }

    $("#mbEventPartMod").Validform({
        tiptype:2,
        callback:function(form){
            mbEventPartMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbEventPartMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_EVENT_PART";
    keyFieldsJson.ASSEMBLE_ID=$("#assembleId").val();
    keyFieldsJson.ATTR_KEY=$("#attrKey").val();
    keyFieldsJson.EVENT_TYPE=$("#eventType").val();
    generalFieldsJson.ATTR_VALUE=$("#attrValue").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbEventPartMod,"json");
}

function callback_mbEventPartMod(json){
    if (json.success) {
       if (parent.$("#mbEventPart").find(".selected").length===1){
        rowData = parent.$('#mbEventPart').DataTable().rows(".selected").data()[0];
        var columnStatus = rowData.COLUMN_STATUS;
        var dataTable=parent.$("#mbEventPart").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            ASSEMBLE_ID:$("#assembleId").val(),
            ATTR_KEY:$("#attrKey").val(),
            EVENT_TYPE:$("#eventType").val(),
            ATTR_VALUE:$("#attrValue").val(),
            COLUMN_STATUS:columnStatus
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbEventPartModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

