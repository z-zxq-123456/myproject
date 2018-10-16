
var rowData;
$(document).ready(function() {

    if (parent.$("#glEventType").find(".selected").length===1){
        rowData = parent.$('#glEventType').DataTable().rows(".selected").data()[0];
        $("#eventType").val(rowData.EVENT_TYPE).attr("disabled",true);
        $("#crDr").val(rowData.CR_DR);
        $("#eventTypeDesc").val(rowData.EVENT_TYPE_DESC);
    }

    $("#glEventTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            glEventTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function glEventTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="GL_EVENT_TYPE";
    keyFieldsJson.EVENT_TYPE=$("#eventType").val();
    generalFieldsJson.CR_DR=$("#crDr").val();
    generalFieldsJson.EVENT_TYPE_DESC=$("#eventTypeDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_glEventTypeMod,"json");
}

function callback_glEventTypeMod(json){
    if (json.success) {
        var dataTable=parent.$("#glEventType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            EVENT_TYPE:$("#eventType").val(),
            CR_DR:$("#crDr").val(),
            EVENT_TYPE_DESC:$("#eventTypeDesc").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function glEventTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

