
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmSubBranchTbl").find(".selected").length===1){
        rowData = parent.$('#fmSubBranchTbl').DataTable().rows(".selected").data()[0];
        $("#subBranchCode").val(rowData.SUB_BRANCH_CODE).attr("disabled",true);
        $("#company").val(rowData.COMPANY);
        $("#subBranchName").val(rowData.SUB_BRANCH_NAME);
    }

    $("#fmSubBranchTblMod").Validform({
        tiptype:2,
        callback:function(form){
            fmSubBranchTblMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmSubBranchTblMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_SUB_BRANCH_TBL";
    keyFieldsJson.SUB_BRANCH_CODE=$("#subBranchCode").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.SUB_BRANCH_NAME=$("#subBranchName").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmSubBranchTblMod,"json");
}

function callback_fmSubBranchTblMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmSubBranchTbl").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            SUB_BRANCH_CODE:$("#subBranchCode").val(),
            COMPANY:$("#company").val(),
            SUB_BRANCH_NAME:$("#subBranchName").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmSubBranchTblModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

