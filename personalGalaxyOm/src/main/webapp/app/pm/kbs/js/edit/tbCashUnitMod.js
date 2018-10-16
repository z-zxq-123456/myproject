
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#tbCashUnit").find(".selected").length===1){
        rowData = parent.$('#tbCashUnit').DataTable().rows(".selected").data()[0];
        $("#parValueId").val(rowData.PAR_VALUE_ID).attr("disabled",true);
        $("#unitSumB").val(rowData.UNIT_SUM_B);
        $("#unitSumK").val(rowData.UNIT_SUM_K);
        $("#company").val(rowData.COMPANY);
    }

    $("#tbCashUnitMod").Validform({
        tiptype:2,
        callback:function(form){
            tbCashUnitMod();
            return false;
        }
    });

    $(".select2").select2();
});

function tbCashUnitMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="TB_CASH_UNIT";
    keyFieldsJson.PAR_VALUE_ID=$("#parValueId").val();
    generalFieldsJson.UNIT_SUM_B=$("#unitSumB").val();
    generalFieldsJson.UNIT_SUM_K=$("#unitSumK").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_tbCashUnitMod,"json");
}

function callback_tbCashUnitMod(json){
    if (json.success) {
        var dataTable=parent.$("#tbCashUnit").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            PAR_VALUE_ID:$("#parValueId").val(),
            UNIT_SUM_B:$("#unitSumB").val(),
            UNIT_SUM_K:$("#unitSumK").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function tbCashUnitModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

