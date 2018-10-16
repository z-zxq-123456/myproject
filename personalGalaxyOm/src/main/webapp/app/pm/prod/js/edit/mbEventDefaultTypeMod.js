
var rowData;
$(document).ready(function() {

    if (parent.$("#mbEventDefaultType").find(".selected").length===1){
        rowData = parent.$('#mbEventDefaultType').DataTable().rows(".selected").data()[0];
        $("#eventDefaultType").val(rowData.EVENT_DEFAULT_TYPE).attr("disabled",true);
        $("#eventDefaultDesc").val(rowData.EVENT_DEFAULT_DESC);
    }

    $("#mbEventDefaultTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            mbEventDefaultTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbEventDefaultTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_EVENT_DEFAULT_TYPE";
    keyFieldsJson.EVENT_DEFAULT_TYPE=$("#eventDefaultType").val();
    generalFieldsJson.EVENT_DEFAULT_DESC=$("#eventDefaultDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbEventDefaultTypeMod,"json");
}

function callback_mbEventDefaultTypeMod(json){
    if (json.success) {
       if (parent.$("#mbEventDefaultType").find(".selected").length===1){
        rowData = parent.$('#mbEventDefaultType').DataTable().rows(".selected").data()[0];
        var columnStatus = rowData.COLUMN_STATUS;
        var dataTable=parent.$("#mbEventDefaultType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            EVENT_DEFAULT_TYPE:$("#eventDefaultType").val(),
            EVENT_DEFAULT_DESC:$("#eventDefaultDesc").val(),
            COLUMN_STATUS:columnStatus
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbEventDefaultTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

