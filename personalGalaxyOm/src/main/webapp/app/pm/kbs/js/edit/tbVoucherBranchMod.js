
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#tbVoucherBranch").find(".selected").length===1){
        rowData = parent.$('#tbVoucherBranch').DataTable().rows(".selected").data()[0];
        $("#branch").val(rowData.BRANCH).attr("disabled",true);
        $("#docType").val(rowData.DOC_TYPE).attr("disabled",true);
        $("#company").val(rowData.COMPANY);
    }

    $("#tbVoucherBranchMod").Validform({
        tiptype:2,
        callback:function(form){
            tbVoucherBranchMod();
            return false;
        }
    });

    $(".select2").select2();
});

function tbVoucherBranchMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="TB_VOUCHER_BRANCH";
    keyFieldsJson.BRANCH=$("#branch").val();
    keyFieldsJson.DOC_TYPE=$("#docType").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_tbVoucherBranchMod,"json");
}

function callback_tbVoucherBranchMod(json){
    if (json.success) {
        var dataTable=parent.$("#tbVoucherBranch").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            BRANCH:$("#branch").val(),
            DOC_TYPE:$("#docType").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function tbVoucherBranchModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

