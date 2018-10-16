
var rowData;
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
    if (parent.$("#mbEventLink").find(".selected").length===1){
        rowData = parent.$('#mbEventLink').DataTable().rows(".selected").data()[0];
        $("#linkEventType").val(rowData.LINK_EVENT_TYPE).attr("disabled",true);
        $("#linkProdType").val(rowData.LINK_PROD_TYPE).attr("disabled",true);
        $("#origEventType").val(rowData.ORIG_EVENT_TYPE).attr("disabled",true);
        $("#origProdType").val(rowData.ORIG_PROD_TYPE).attr("disabled",true);
        $("#prodType").val(rowData.PROD_TYPE).attr("disabled",true);
        $("#linkCondition").val(rowData.LINK_CONDITION);
        $("#status").val(rowData.STATUS);
    }

    $("#mbEventLinkMod").Validform({
        tiptype:2,
        callback:function(form){
            mbEventLinkMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbEventLinkMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_EVENT_LINK";
    keyFieldsJson.LINK_EVENT_TYPE=$("#linkEventType").val();
    keyFieldsJson.LINK_PROD_TYPE=$("#linkProdType").val();
    keyFieldsJson.ORIG_EVENT_TYPE=$("#origEventType").val();
    keyFieldsJson.ORIG_PROD_TYPE=$("#origProdType").val();
    keyFieldsJson.PROD_TYPE=$("#prodType").val();
    generalFieldsJson.LINK_CONDITION=$("#linkCondition").val();
    generalFieldsJson.STATUS=$("#status").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbEventLinkMod,"json");
}

function callback_mbEventLinkMod(json){
    if (json.success) {
       if (parent.$("#mbEventLink").find(".selected").length===1){
        rowData = parent.$('#mbEventLink').DataTable().rows(".selected").data()[0];
        var columnStatus = rowData.COLUMN_STATUS;
        var dataTable=parent.$("#mbEventLink").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            LINK_EVENT_TYPE:$("#linkEventType").val(),
            LINK_PROD_TYPE:$("#linkProdType").val(),
            ORIG_EVENT_TYPE:$("#origEventType").val(),
            ORIG_PROD_TYPE:$("#origProdType").val(),
            PROD_TYPE:$("#prodType").val(),
            LINK_CONDITION:$("#linkCondition").val(),
            STATUS:$("#status").val(),
            COLUMN_STATUS:columnStatus
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbEventLinkModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

