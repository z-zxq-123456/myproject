
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmError").find(".selected").length===1){
        rowData = parent.$('#fmError').DataTable().rows(".selected").data()[0];
        $("#errorNo").val(rowData.ERROR_NO).attr("disabled",true);
        $("#errorLang").val(rowData.ERROR_LANG).attr("disabled",true);
        $("#severityLevel").val(rowData.SEVERITY_LEVEL);
        $("#errorMsg").val(rowData.ERROR_MSG);
        $("#company").val(rowData.COMPANY);
    }

    $("#fmErrorMod").Validform({
        tiptype:2,
        callback:function(form){
            fmErrorMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmErrorMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_ERROR";
    keyFieldsJson.ERROR_NO=$("#errorNo").val();
    keyFieldsJson.ERROR_LANG=$("#errorLang").val();
    generalFieldsJson.SEVERITY_LEVEL=$("#severityLevel").val();
    generalFieldsJson.ERROR_MSG=$("#errorMsg").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmErrorMod,"json");
}

function callback_fmErrorMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmError").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
        ERROR_NO:$("#errorNo").val()
        ,ERROR_LANG:$("#errorLang").val()
        ,SEVERITY_LEVEL:$("#severityLevel").val()
        ,ERROR_MSG:$("#errorMsg").val()
        ,COMPANY:$("#company").val()
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmErrorModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

