var rowData;
$(document).ready(function() {

    if (parent.$("#mbBranchProd").find(".selected").length===1){
        rowData = parent.$('#mbBranchProd').DataTable().rows(".selected").data()[0];
        $("#branch").val(rowData.BRANCH).attr("disabled",true);
        $("#prodType").val(rowData.PROD_TYPE).attr("disabled",true);
        $("#prodDesc").val(rowData.PROD_DESC);
    }

    $("#mbBranchProdMod").Validform({
        tiptype:2,
        callback:function(){
            mbBranchProdMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbBranchProdMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_BRANCH_PROD";
    keyFieldsJson.BRANCH=$("#branch").val();
    keyFieldsJson.PROD_TYPE=$("#prodType").val();
    generalFieldsJson.PROD_DESC=$("#prodDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbBranchProdMod,"json");
}

function callback_mbBranchProdMod(json){
    if (json.success) {
        if (parent.$("#mbBranchProd").find(".selected").length===1){
            rowData = parent.$('#mbBranchProd').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#mbBranchProd").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                BRANCH:$("#branch").val(),
                PROD_TYPE:$("#prodType").val(),
                PROD_DESC:$("#prodDesc").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbBranchProdModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}