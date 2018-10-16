
var rowData;
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
    if (parent.$("#mbLinkCondition").find(".selected").length===1){
        rowData = parent.$('#mbLinkCondition').DataTable().rows(".selected").data()[0];
        $("#conditionId").val(rowData.CONDITION_ID).attr("disabled",true);
        $("#conditionDesc").val(rowData.CONDITION_DESC);
        $("#status").val(rowData.STATUS);
        $("#conditionRule").val(rowData.CONDITION_RULE);
    }

    $("#mbLinkConditionMod").Validform({
        tiptype:2,
        callback:function(form){
            mbLinkConditionMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbLinkConditionMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_LINK_CONDITION";
    keyFieldsJson.CONDITION_ID=$("#conditionId").val();
    generalFieldsJson.CONDITION_DESC=$("#conditionDesc").val();
    generalFieldsJson.STATUS=$("#status").val();
    generalFieldsJson.CONDITION_RULE=$("#conditionRule").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbLinkConditionMod,"json");
}

function callback_mbLinkConditionMod(json){
    if (json.success) {
       if (parent.$("#mbLinkCondition").find(".selected").length===1){
        rowData = parent.$('#mbLinkCondition').DataTable().rows(".selected").data()[0];
        var columnStatus = rowData.COLUMN_STATUS;
        var dataTable=parent.$("#mbLinkCondition").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CONDITION_ID:$("#conditionId").val(),
            CONDITION_DESC:$("#conditionDesc").val(),
            STATUS:$("#status").val(),
            CONDITION_RULE:$("#conditionRule").val(),
            COLUMN_STATUS:columnStatus
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbLinkConditionModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

