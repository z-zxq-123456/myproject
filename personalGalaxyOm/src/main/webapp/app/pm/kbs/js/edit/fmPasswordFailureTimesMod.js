
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH_TBL&tableCol=BRANCH,BRANCH_NAME",
        id: "branch",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmPasswordFailureTimes").find(".selected").length===1){
        rowData = parent.$('#fmPasswordFailureTimes').DataTable().rows(".selected").data()[0];
        $("#channel").val(rowData.CHANNEL).attr("disabled",true);
        $("#passwordType").val(rowData.PASSWORD_TYPE).attr("disabled",true);
        $("#branch").val(rowData.BRANCH);
        $("#company").val(rowData.COMPANY);
        $("#lastChangeDate").val(rowData.LAST_CHANGE_DATE);
        $("#lastChangeOfficer").val(rowData.LAST_CHANGE_OFFICER);
        $("#lastChangeTime").val(rowData.LAST_CHANGE_TIME);
        $("#maxFailuerTimes").val(rowData.MAX_FAILUER_TIMES);
        $("#resetInd").val(rowData.RESET_IND);
    }

    $("#fmPasswordFailureTimesMod").Validform({
        tiptype:2,
        callback:function(form){
            fmPasswordFailureTimesMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmPasswordFailureTimesMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_PASSWORD_FAILURE_TIMES";
    keyFieldsJson.CHANNEL=$("#channel").val();
    keyFieldsJson.PASSWORD_TYPE=$("#passwordType").val();
    generalFieldsJson.BRANCH=$("#branch").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.LAST_CHANGE_DATE=$("#lastChangeDate").val();
    generalFieldsJson.LAST_CHANGE_OFFICER=$("#lastChangeOfficer").val();
    generalFieldsJson.LAST_CHANGE_TIME=$("#lastChangeTime").val();
    generalFieldsJson.MAX_FAILUER_TIMES=$("#maxFailuerTimes").val();
    generalFieldsJson.RESET_IND=$("#resetInd").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmPasswordFailureTimesMod,"json");
}

function callback_fmPasswordFailureTimesMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmPasswordFailureTimes").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CHANNEL:$("#channel").val(),
            PASSWORD_TYPE:$("#passwordType").val(),
            BRANCH:$("#branch").val(),
            COMPANY:$("#company").val(),
            LAST_CHANGE_DATE:$("#lastChangeDate").val(),
            LAST_CHANGE_OFFICER:$("#lastChangeOfficer").val(),
            LAST_CHANGE_TIME:$("#lastChangeTime").val(),
            MAX_FAILUER_TIMES:$("#maxFailuerTimes").val(),
            RESET_IND:$("#resetInd").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmPasswordFailureTimesModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

