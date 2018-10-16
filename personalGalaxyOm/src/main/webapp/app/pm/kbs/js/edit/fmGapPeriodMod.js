
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmGapPeriod").find(".selected").length===1){
        rowData = parent.$('#fmGapPeriod').DataTable().rows(".selected").data()[0];
        $("#periodNo").val(rowData.PERIOD_NO).attr("disabled",true);
        $("#gapType").val(rowData.GAP_TYPE).attr("disabled",true);
        $("#periodType").val(rowData.PERIOD_TYPE).attr("disabled",true);
        $("#company").val(rowData.COMPANY);
        $("#periodDesc").val(rowData.PERIOD_DESC);
    }

    $("#fmGapPeriodMod").Validform({
        tiptype:2,
        callback:function(form){
            fmGapPeriodMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmGapPeriodMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_GAP_PERIOD";
    keyFieldsJson.PERIOD_NO=$("#periodNo").val();
    keyFieldsJson.GAP_TYPE=$("#gapType").val();
    keyFieldsJson.PERIOD_TYPE=$("#periodType").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.PERIOD_DESC=$("#periodDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmGapPeriodMod,"json");
}

function callback_fmGapPeriodMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmGapPeriod").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
        PERIOD_NO:$("#periodNo").val()
        ,GAP_TYPE:$("#gapType").val()
        ,PERIOD_TYPE:$("#periodType").val()
        ,COMPANY:$("#company").val()
        ,PERIOD_DESC:$("#periodDesc").val()
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmGapPeriodModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

