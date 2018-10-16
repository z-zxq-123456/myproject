var rowData;
$(document).ready(function() {

    if (parent.$("#rcListCheckRule").find(".selected").length===1){
        rowData = parent.$('#rcListCheckRule').DataTable().rows(".selected").data()[0];
        $("#listType").val(rowData.LIST_TYPE).attr("disabled",true);
        $("#eventForbidInd").val(rowData.EVENT_FORBID_IND);
        $("#forbidChannels").val(rowData.FORBID_CHANNELS);
        $("#verifyFlag").val(rowData.VERIFY_FLAG);
        $("#channelForbidInd").val(rowData.CHANNEL_FORBID_IND);
        $("#forbidEvents").val(rowData.FORBID_EVENTS);
        $("#forbidTerm").val(rowData.FORBID_TERM);
        $("#forbidTermType").val(rowData.FORBID_TERM_TYPE);
    }

    $("#rcListCheckRuleMod").Validform({
        tiptype:2,
        callback:function(){
            rcListCheckRuleMod();
            return false;
        }
    });

    $(".select2").select2();
});

function rcListCheckRuleMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="RC_LIST_CHECK_RULE";
    keyFieldsJson.LIST_TYPE=$("#listType").val();
    generalFieldsJson.EVENT_FORBID_IND=$("#eventForbidInd").val();
    generalFieldsJson.FORBID_CHANNELS=$("#forbidChannels").val();
    generalFieldsJson.VERIFY_FLAG=$("#verifyFlag").val();
    generalFieldsJson.CHANNEL_FORBID_IND=$("#channelForbidInd").val();
    generalFieldsJson.FORBID_EVENTS=$("#forbidEvents").val();
    generalFieldsJson.FORBID_TERM=$("#forbidTerm").val();
    generalFieldsJson.FORBID_TERM_TYPE=$("#forbidTermType").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_rcListCheckRuleMod,"json");
}

function callback_rcListCheckRuleMod(json){
    if (json.success) {
        if (parent.$("#rcListCheckRule").find(".selected").length===1){
            rowData = parent.$('#rcListCheckRule').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#rcListCheckRule").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                LIST_TYPE:$("#listType").val(),
                EVENT_FORBID_IND:$("#eventForbidInd").val(),
                FORBID_CHANNELS:$("#forbidChannels").val(),
                VERIFY_FLAG:$("#verifyFlag").val(),
                CHANNEL_FORBID_IND:$("#channelForbidInd").val(),
                FORBID_EVENTS:$("#forbidEvents").val(),
                FORBID_TERM:$("#forbidTerm").val(),
                FORBID_TERM_TYPE:$("#forbidTermType").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function rcListCheckRuleModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}