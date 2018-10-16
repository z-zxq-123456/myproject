
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmPeriodFreq").find(".selected").length===1){
        rowData = parent.$('#fmPeriodFreq').DataTable().rows(".selected").data()[0];
        $("#periodFreq").val(rowData.PERIOD_FREQ).attr("disabled",true);
        $("#addNo").val(rowData.ADD_NO);
        $("#periodFreqDesc").val(rowData.PERIOD_FREQ_DESC);
        $("#halfMonth").val(rowData.HALF_MONTH);
        $("#forceWorkDay").val(rowData.FORCE_WORK_DAY);
        $("#firstLast").val(rowData.FIRST_LAST);
        $("#dayNum").val(rowData.DAY_NUM);
        $("#dayMth").val(rowData.DAY_MTH);
        $("#company").val(rowData.COMPANY);
        $("#clientSpread").val(rowData.CLIENT_SPREAD);
        $("#priorDays").val(rowData.PRIOR_DAYS);
    }

    $("#fmPeriodFreqMod").Validform({
        tiptype:2,
        callback:function(form){
            fmPeriodFreqMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmPeriodFreqMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_PERIOD_FREQ";
    keyFieldsJson.PERIOD_FREQ=$("#periodFreq").val();
    generalFieldsJson.ADD_NO=$("#addNo").val();
    generalFieldsJson.PERIOD_FREQ_DESC=$("#periodFreqDesc").val();
    generalFieldsJson.HALF_MONTH=$("#halfMonth").val();
    generalFieldsJson.FORCE_WORK_DAY=$("#forceWorkDay").val();
    generalFieldsJson.FIRST_LAST=$("#firstLast").val();
    generalFieldsJson.DAY_NUM=$("#dayNum").val();
    generalFieldsJson.DAY_MTH=$("#dayMth").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.CLIENT_SPREAD=$("#clientSpread").val();
    generalFieldsJson.PRIOR_DAYS=$("#priorDays").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmPeriodFreqMod,"json");
}

function callback_fmPeriodFreqMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmPeriodFreq").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            PERIOD_FREQ:$("#periodFreq").val(),
            ADD_NO:$("#addNo").val(),
            PERIOD_FREQ_DESC:$("#periodFreqDesc").val(),
            HALF_MONTH:$("#halfMonth").val(),
            FORCE_WORK_DAY:$("#forceWorkDay").val(),
            FIRST_LAST:$("#firstLast").val(),
            DAY_NUM:$("#dayNum").val(),
            DAY_MTH:$("#dayMth").val(),
            COMPANY:$("#company").val(),
            CLIENT_SPREAD:$("#clientSpread").val(),
            PRIOR_DAYS:$("#priorDays").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmPeriodFreqModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

