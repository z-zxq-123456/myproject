var rowData;
$(document).ready(function() {

    if (parent.$("#cmcCardBinInfo").find(".selected").length===1){
        rowData = parent.$('#cmcCardBinInfo').DataTable().rows(".selected").data()[0];
        $("#binOrder").val(rowData.BIN_ORDER).attr("disabled",true);
        $("#bin").val(rowData.BIN);
        $("#binLength").val(rowData.BIN_LENGTH);
        $("#delayFlag").val(rowData.DELAY_FLAG);
        $("#lastTime").val(rowData.LAST_TIME);
    }

    $("#cmcCardBinInfoMod").Validform({
        tiptype:2,
        callback:function(){
            cmcCardBinInfoMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cmcCardBinInfoMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CMC_CARD_BIN_INFO";
    keyFieldsJson.BIN_ORDER=$("#binOrder").val();
    generalFieldsJson.BIN=$("#bin").val();
    generalFieldsJson.BIN_LENGTH=$("#binLength").val();
    generalFieldsJson.DELAY_FLAG=$("#delayFlag").val();
    generalFieldsJson.LAST_TIME=$("#lastTime").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cmcCardBinInfoMod,"json");
}

function callback_cmcCardBinInfoMod(json){
    if (json.success) {
        if (parent.$("#cmcCardBinInfo").find(".selected").length===1){
            rowData = parent.$('#cmcCardBinInfo').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#cmcCardBinInfo").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                BIN_ORDER:$("#binOrder").val(),
                BIN:$("#bin").val(),
                BIN_LENGTH:$("#binLength").val(),
                DELAY_FLAG:$("#delayFlag").val(),
                LAST_TIME:$("#lastTime").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cmcCardBinInfoModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}