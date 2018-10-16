var rowData;
$(document).ready(function() {

    if (parent.$("#fmFtaBranch").find(".selected").length===1){
        rowData = parent.$('#fmFtaBranch').DataTable().rows(".selected").data()[0];
        $("#ftaCode").val(rowData.FTA_CODE).attr("disabled",true);
        $("#ftaDesc").val(rowData.FTA_DESC);
        $("#ftaNature").val(rowData.FTA_NATURE);
        $("#ftaRateType").val(rowData.FTA_RATE_TYPE);
        $("#ftaType").val(rowData.FTA_TYPE);
    }

    $("#fmFtaBranchMod").Validform({
        tiptype:2,
        callback:function(){
            fmFtaBranchMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmFtaBranchMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_FTA_BRANCH";
    keyFieldsJson.FTA_CODE=$("#ftaCode").val();
    generalFieldsJson.FTA_DESC=$("#ftaDesc").val();
    generalFieldsJson.FTA_NATURE=$("#ftaNature").val();
    generalFieldsJson.FTA_RATE_TYPE=$("#ftaRateType").val();
    generalFieldsJson.FTA_TYPE=$("#ftaType").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmFtaBranchMod,"json");
}

function callback_fmFtaBranchMod(json){
    if (json.success) {
        if (parent.$("#fmFtaBranch").find(".selected").length===1){
            rowData = parent.$('#fmFtaBranch').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#fmFtaBranch").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                FTA_CODE:$("#ftaCode").val(),
                FTA_DESC:$("#ftaDesc").val(),
                FTA_NATURE:$("#ftaNature").val(),
                FTA_RATE_TYPE:$("#ftaRateType").val(),
                FTA_TYPE:$("#ftaType").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmFtaBranchModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}