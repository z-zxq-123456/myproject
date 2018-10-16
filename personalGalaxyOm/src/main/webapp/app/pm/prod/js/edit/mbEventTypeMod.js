
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=MB_EVENT_CLASS&tableCol=EVENT_CLASS,EVENT_CLASS_DESC",
        id: "eventClass",
        async: false
    });
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
    if (parent.$("#mbEventType").find(".selected").length===1){
        rowData = parent.$('#mbEventType').DataTable().rows(".selected").data()[0];
        $("#eventType").val(rowData.EVENT_TYPE).attr("disabled",true);
        $("#eventClass").val(rowData.EVENT_CLASS);
        $("#eventDesc").val(rowData.EVENT_DESC);
        $("#isStandard").val(rowData.IS_STANDARD);
        $("#processMethod").val(rowData.PROCESS_METHOD);
        $("#status").val(rowData.STATUS);
    }

    $("#mbEventTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            mbEventTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbEventTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_EVENT_TYPE";
    keyFieldsJson.EVENT_TYPE=$("#eventType").val();
    generalFieldsJson.EVENT_CLASS=$("#eventClass").val();
    generalFieldsJson.EVENT_DESC=$("#eventDesc").val();
    generalFieldsJson.IS_STANDARD=$("#isStandard").val();
    generalFieldsJson.PROCESS_METHOD=$("#processMethod").val();
    generalFieldsJson.STATUS=$("#status").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbEventTypeMod,"json");
}

function callback_mbEventTypeMod(json){
    if (json.success) {
       if (parent.$("#mbEventType").find(".selected").length===1){
        rowData = parent.$('#mbEventType').DataTable().rows(".selected").data()[0];
        var columnStatus = rowData.COLUMN_STATUS;
        var dataTable=parent.$("#mbEventType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            EVENT_TYPE:$("#eventType").val(),
            EVENT_CLASS:$("#eventClass").val(),
            EVENT_DESC:$("#eventDesc").val(),
            IS_STANDARD:$("#isStandard").val(),
            PROCESS_METHOD:$("#processMethod").val(),
            STATUS:$("#status").val(),
            COLUMN_STATUS:columnStatus
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbEventTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

