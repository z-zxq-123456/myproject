
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#mbCashItem").find(".selected").length===1){
        rowData = parent.$('#mbCashItem').DataTable().rows(".selected").data()[0];
        $("#cashItem").val(rowData.CASH_ITEM).attr("disabled",true);
        $("#cashItemDesc").val(rowData.CASH_ITEM_DESC);
        $("#company").val(rowData.COMPANY);
        $("#crDrInd").val(rowData.CR_DR_IND);
    }

    $("#mbCashItemMod").Validform({
        tiptype:2,
        callback:function(form){
            mbCashItemMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbCashItemMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_CASH_ITEM";
    keyFieldsJson.CASH_ITEM=$("#cashItem").val();
    generalFieldsJson.CASH_ITEM_DESC=$("#cashItemDesc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.CR_DR_IND=$("#crDrInd").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbCashItemMod,"json");
}

function callback_mbCashItemMod(json){
    if (json.success) {
        var dataTable=parent.$("#mbCashItem").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CASH_ITEM:$("#cashItem").val(),
            CASH_ITEM_DESC:$("#cashItemDesc").val(),
            COMPANY:$("#company").val(),
            CR_DR_IND:$("#crDrInd").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbCashItemModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

