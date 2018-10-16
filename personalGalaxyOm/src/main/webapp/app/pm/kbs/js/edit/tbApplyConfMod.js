
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#tbApplyConf").find(".selected").length===1){
        rowData = parent.$('#tbApplyConf').DataTable().rows(".selected").data()[0];
        $("#branch").val(rowData.BRANCH).attr("disabled",true);
        $("#cbranch").val(rowData.CBRANCH);
        $("#cbsflag").val(rowData.CBSFLAG);
        $("#company").val(rowData.COMPANY);
        $("#isCashApply").val(rowData.IS_CASH_APPLY);
        $("#isVoucherApply").val(rowData.IS_VOUCHER_APPLY);
        $("#vbranch").val(rowData.VBRANCH);
        $("#vbsflag").val(rowData.VBSFLAG);
    }

    $("#tbApplyConfMod").Validform({
        tiptype:2,
        callback:function(form){
            tbApplyConfMod();
            return false;
        }
    });

    $(".select2").select2();
});

function tbApplyConfMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="TB_APPLY_CONF";
    keyFieldsJson.BRANCH=$("#branch").val();
    generalFieldsJson.CBRANCH=$("#cbranch").val();
    generalFieldsJson.CBSFLAG=$("#cbsflag").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.IS_CASH_APPLY=$("#isCashApply").val();
    generalFieldsJson.IS_VOUCHER_APPLY=$("#isVoucherApply").val();
    generalFieldsJson.VBRANCH=$("#vbranch").val();
    generalFieldsJson.VBSFLAG=$("#vbsflag").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_tbApplyConfMod,"json");
}

function callback_tbApplyConfMod(json){
    if (json.success) {
        var dataTable=parent.$("#tbApplyConf").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            BRANCH:$("#branch").val(),
            CBRANCH:$("#cbranch").val(),
            CBSFLAG:$("#cbsflag").val(),
            COMPANY:$("#company").val(),
            IS_CASH_APPLY:$("#isCashApply").val(),
            IS_VOUCHER_APPLY:$("#isVoucherApply").val(),
            VBRANCH:$("#vbranch").val(),
            VBSFLAG:$("#vbsflag").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function tbApplyConfModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

