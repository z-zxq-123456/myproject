var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=MB_PROD_TYPE&tableCol=PROD_TYPE,PROD_DESC",
        id: "prodTypeExclusive",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=MB_PROD_TYPE&tableCol=PROD_TYPE,PROD_DESC",
        id: "prodTypeMut",
        async: false
    });

    if (parent.$("#prodMutualExclusive").find(".selected").length===1){
        rowData = parent.$('#prodMutualExclusive').DataTable().rows(".selected").data()[0];
        $("#clientTypeMe").val(rowData.CLIENT_TYPE_ME).attr("disabled",true);
        $("#prodTypeExclusive").val(rowData.PROD_TYPE_EXCLUSIVE);
        $("#prodTypeMut").val(rowData.PROD_TYPE_MUT);
    }

    $("#prodMutualExclusiveMod").Validform({
        tiptype:2,
        callback:function(){
            prodMutualExclusiveMod();
            return false;
        }
    });

    $(".select2").select2();
});

function prodMutualExclusiveMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="PROD_MUTUAL_EXCLUSIVE";
    keyFieldsJson.CLIENT_TYPE_ME=$("#clientTypeMe").val();
    generalFieldsJson.PROD_TYPE_EXCLUSIVE=$("#prodTypeExclusive").val();
    generalFieldsJson.PROD_TYPE_MUT=$("#prodTypeMut").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_prodMutualExclusiveMod,"json");
}

function callback_prodMutualExclusiveMod(json){
    if (json.success) {
        if (parent.$("#prodMutualExclusive").find(".selected").length===1){
            rowData = parent.$('#prodMutualExclusive').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#prodMutualExclusive").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                CLIENT_TYPE_ME:$("#clientTypeMe").val(),
                PROD_TYPE_EXCLUSIVE:$("#prodTypeExclusive").val(),
                PROD_TYPE_MUT:$("#prodTypeMut").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function prodMutualExclusiveModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}