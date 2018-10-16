
var rowData;
$(document).ready(function() {

    if (parent.$("#mbPartTypeEvent").find(".selected").length===1){
        rowData = parent.$('#mbPartTypeEvent').DataTable().rows(".selected").data()[0];
    }

    $("#mbPartTypeEventMod").Validform({
        tiptype:2,
        callback:function(form){
            mbPartTypeEventMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbPartTypeEventMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_PART_TYPE_EVENT";
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbPartTypeEventMod,"json");
}

function callback_mbPartTypeEventMod(json){
    if (json.success) {
       if (parent.$("#mbPartTypeEvent").find(".selected").length===1){
        rowData = parent.$('#mbPartTypeEvent').DataTable().rows(".selected").data()[0];
        var columnStatus = rowData.COLUMN_STATUS;
        var dataTable=parent.$("#mbPartTypeEvent").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            COLUMN_STATUS:columnStatus
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbPartTypeEventModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

