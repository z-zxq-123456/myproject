var rowData;
$(document).ready(function() {

    if (parent.$("#irlFeeItem").find(".selected").length===1){
        rowData = parent.$('#irlFeeItem').DataTable().rows(".selected").data()[0];
        $("#feeItem").val(rowData.FEE_ITEM).attr("disabled",true);
        $("#feeItemDesc").val(rowData.FEE_ITEM_DESC);
    }

    $("#irlFeeItemMod").Validform({
        tiptype:2,
        callback:function(){
            irlFeeItemMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlFeeItemMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="IRL_FEE_ITEM";
    keyFieldsJson.FEE_ITEM=$("#feeItem").val();
    generalFieldsJson.FEE_ITEM_DESC=$("#feeItemDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlFeeItemMod,"json");
}

function callback_irlFeeItemMod(json){
    if (json.success) {
        if (parent.$("#irlFeeItem").find(".selected").length===1){
            rowData = parent.$('#irlFeeItem').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#irlFeeItem").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                FEE_ITEM:$("#feeItem").val(),
                FEE_ITEM_DESC:$("#feeItemDesc").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlFeeItemModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}