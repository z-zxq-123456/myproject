
var rowData;
$(document).ready(function() {
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
                id: "branch",
                async: false
            });
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
                id: "company",
                async: false
            });

    if (parent.$("#irlFeeProfitDistribution").find(".selected").length===1){
        rowData = parent.$('#irlFeeProfitDistribution').DataTable().rows(".selected").data()[0];
            $("#feeType").val(rowData.FEE_TYPE).attr("disabled",true);
            $("#branch").val(rowData.BRANCH);
            $("#percent").val(rowData.PERCENT);
            $("#company").val(rowData.COMPANY);
    }

    $("#irlFeeProfitDistributionMod").Validform({
        tiptype:2,
        callback:function(form){
            irlFeeProfitDistributionMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlFeeProfitDistributionMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="IRL_FEE_PROFIT_DISTRIBUTION";
        keyFieldsJson.FEE_TYPE=$("#feeType").val();
        generalFieldsJson.BRANCH=$("#branch").val();
        generalFieldsJson.PERCENT=$("#percent").val();

    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlFeeProfitDistributionMod,"json");
}

function callback_irlFeeProfitDistributionMod(json){
    if (json.success) {
        var dataTable=parent.$("#irlFeeProfitDistribution").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            FEE_TYPE:$("feeType").val(),
            BRANCH:$("branch").val(),
            PERCENT:$("percent").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlFeeProfitDistributionModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

