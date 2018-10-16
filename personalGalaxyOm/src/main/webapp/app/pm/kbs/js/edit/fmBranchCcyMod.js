
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmBranchCcy").find(".selected").length===1){
        rowData = parent.$('#fmBranchCcy').DataTable().rows(".selected").data()[0];
        $("#branch").val(rowData.BRANCH).attr("disabled",true);
        $("#ccy").val(rowData.CCY).attr("disabled",true);
        $("#company").val(rowData.COMPANY);
    }

    $("#fmBranchCcyMod").Validform({
        tiptype:2,
        callback:function(form){
            fmBranchCcyMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmBranchCcyMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_BRANCH_CCY";
    keyFieldsJson.BRANCH=$("#branch").val();
    keyFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmBranchCcyMod,"json");
}

function callback_fmBranchCcyMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmBranchCcy").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            BRANCH:$("#branch").val(),
            CCY:$("#ccy").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmBranchCcyModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

