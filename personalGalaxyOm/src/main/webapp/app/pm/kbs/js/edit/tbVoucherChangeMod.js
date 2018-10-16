
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#tbVoucherChange").find(".selected").length===1){
        rowData = parent.$('#tbVoucherChange').DataTable().rows(".selected").data()[0];
        $("#statusDesc").val(rowData.STATUS_DESC);
        $("#updateAfter").val(rowData.UPDATE_AFTER);
        $("#updateBefore").val(rowData.UPDATE_BEFORE);
        $("#company").val(rowData.COMPANY);
        $("#newStatus").val(rowData.NEW_STATUS);
        $("#oldStatus").val(rowData.OLD_STATUS);
    }

    $("#tbVoucherChangeMod").Validform({
        tiptype:2,
        callback:function(form){
            tbVoucherChangeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function tbVoucherChangeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="TB_VOUCHER_CHANGE";
    generalFieldsJson.STATUS_DESC=$("#statusDesc").val();
    generalFieldsJson.UPDATE_AFTER=$("#updateAfter").val();
    generalFieldsJson.UPDATE_BEFORE=$("#updateBefore").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.NEW_STATUS=$("#newStatus").val();
    generalFieldsJson.OLD_STATUS=$("#oldStatus").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_tbVoucherChangeMod,"json");
}

function callback_tbVoucherChangeMod(json){
    if (json.success) {
        var dataTable=parent.$("#tbVoucherChange").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
        ,STATUS_DESC:$("#statusDesc").val()
        ,UPDATE_AFTER:$("#updateAfter").val()
        ,UPDATE_BEFORE:$("#updateBefore").val()
        ,COMPANY:$("#company").val()
        ,NEW_STATUS:$("#newStatus").val()
        ,OLD_STATUS:$("#oldStatus").val()
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function tbVoucherChangeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

