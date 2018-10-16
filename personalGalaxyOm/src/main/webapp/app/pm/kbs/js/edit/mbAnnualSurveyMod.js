var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_PERIOD_FREQ&tableCol=PERIOD_FREQ,PERIOD_FREQ_DESC",
        id: "periodFreq",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_RESTRAINT_TYPE&tableCol=RESTRAINT_TYPE,RESTRAINT_DESC",
        id: "restraintType",
        async: false
    });

    if (parent.$("#mbAnnualSurvey").find(".selected").length===1){
        rowData = parent.$('#mbAnnualSurvey').DataTable().rows(".selected").data()[0];
        $("#acctNature").val(rowData.ACCT_NATURE).attr("disabled",true);
        $("#periodFreq").val(rowData.PERIOD_FREQ);
        $("#resetDate").val(rowData.RESET_DATE);
        $("#stopDate").val(rowData.STOP_DATE);
        $("#stopFlag").val(rowData.STOP_FLAG);
        $("#restraintType").val(rowData.RESTRAINT_TYPE);
        $("#days").val(rowData.DAYS);
    }

    $("#mbAnnualSurveyMod").Validform({
        tiptype:2,
        callback:function(){
            mbAnnualSurveyMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbAnnualSurveyMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_ANNUAL_SURVEY";
    keyFieldsJson.ACCT_NATURE=$("#acctNature").val();
    generalFieldsJson.PERIOD_FREQ=$("#periodFreq").val();
    generalFieldsJson.RESET_DATE=$("#resetDate").val();
    generalFieldsJson.STOP_DATE=$("#stopDate").val();
    generalFieldsJson.STOP_FLAG=$("#stopFlag").val();
    generalFieldsJson.RESTRAINT_TYPE=$("#restraintType").val();
    generalFieldsJson.DAYS=$("#days").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbAnnualSurveyMod,"json");
}

function callback_mbAnnualSurveyMod(json){
    if (json.success) {
        if (parent.$("#mbAnnualSurvey").find(".selected").length===1){
            rowData = parent.$('#mbAnnualSurvey').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#mbAnnualSurvey").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                ACCT_NATURE:$("#acctNature").val(),
                PERIOD_FREQ:$("#periodFreq").val(),
                RESET_DATE:$("#resetDate").val(),
                STOP_DATE:$("#stopDate").val(),
                STOP_FLAG:$("#stopFlag").val(),
                RESTRAINT_TYPE:$("#restraintType").val(),
                DAYS:$("#days").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbAnnualSurveyModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}