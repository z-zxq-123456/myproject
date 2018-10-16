var rowData;
$(document).ready(function() {

    if (parent.$("#irlFeePackageMap").find(".selected").length===1){
        rowData = parent.$('#irlFeePackageMap').DataTable().rows(".selected").data()[0];
        $("#packageId").val(rowData.PACKAGE_ID).attr("disabled",true);
        $("#feeType").val(rowData.FEE_TYPE).attr("disabled",true);
    }

    $("#irlFeePackageMapMod").Validform({
        tiptype:2,
        callback:function(){
            irlFeePackageMapMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlFeePackageMapMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="IRL_FEE_PACKAGE_MAP";
    keyFieldsJson.PACKAGE_ID=$("#packageId").val();
    keyFieldsJson.FEE_TYPE=$("#feeType").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlFeePackageMapMod,"json");
}

function callback_irlFeePackageMapMod(json){
    if (json.success) {
        if (parent.$("#irlFeePackageMap").find(".selected").length===1){
            rowData = parent.$('#irlFeePackageMap').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#irlFeePackageMap").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                PACKAGE_ID:$("#packageId").val(),
                FEE_TYPE:$("#feeType").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlFeePackageMapModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}