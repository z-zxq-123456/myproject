
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=MB_EVENT_DEFAULT_TYPE&tableCol=EVENT_DEFAULT_TYPE",
        id: "eventType",
        async: false
    });

    if (parent.$("#glEventMapping").find(".selected").length===1){
        rowData = parent.$('#glEventMapping').DataTable().rows(".selected").data()[0];
        $("#mappingType").val(rowData.MAPPING_TYPE).attr("disabled",true);
        $("#eventType").val(rowData.EVENT_TYPE);
        $("#eventTypeDesc").val(rowData.EVENT_TYPE_DESC);
        $("#mappingName").val(rowData.MAPPING_NAME);
    }

    $("#glEventMappingMod").Validform({
        tiptype:2,
        callback:function(form){
            glEventMappingMod();
            return false;
        }
    });

    $(".select2").select2();
});

function glEventMappingMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="GL_EVENT_MAPPING";
    keyFieldsJson.MAPPING_TYPE=$("#mappingType").val();
    generalFieldsJson.EVENT_TYPE=$("#eventType").val();
    generalFieldsJson.EVENT_TYPE_DESC=$("#eventTypeDesc").val();
    generalFieldsJson.MAPPING_NAME=$("#mappingName").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_glEventMappingMod,"json");
}

function callback_glEventMappingMod(json){
    if (json.success) {
        var dataTable=parent.$("#glEventMapping").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            MAPPING_TYPE:$("#mappingType").val(),
            EVENT_TYPE:$("#eventType").val(),
            EVENT_TYPE_DESC:$("#eventTypeDesc").val(),
            MAPPING_NAME:$("#mappingName").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function glEventMappingModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

