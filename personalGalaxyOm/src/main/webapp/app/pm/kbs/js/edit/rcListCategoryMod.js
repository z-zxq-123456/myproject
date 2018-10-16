var rowData;
$(document).ready(function() {

    if (parent.$("#rcListCategory").find(".selected").length===1){
        rowData = parent.$('#rcListCategory').DataTable().rows(".selected").data()[0];
        $("#listCategory").val(rowData.LIST_CATEGORY).attr("disabled",true);
        $("#appInd").val(rowData.APP_IND);
        $("#listCategoryDesc").val(rowData.LIST_CATEGORY_DESC);
        $("#property").val(rowData.PROPERTY);
    }

    $("#rcListCategoryMod").Validform({
        tiptype:2,
        callback:function(){
            rcListCategoryMod();
            return false;
        }
    });

    $(".select2").select2();
});

function rcListCategoryMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="RC_LIST_CATEGORY";
    keyFieldsJson.LIST_CATEGORY=$("#listCategory").val();
    generalFieldsJson.APP_IND=$("#appInd").val();
    generalFieldsJson.LIST_CATEGORY_DESC=$("#listCategoryDesc").val();
    generalFieldsJson.PROPERTY=$("#property").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_rcListCategoryMod,"json");
}

function callback_rcListCategoryMod(json){
    if (json.success) {
        if (parent.$("#rcListCategory").find(".selected").length===1){
            rowData = parent.$('#rcListCategory').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#rcListCategory").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                LIST_CATEGORY:$("#listCategory").val(),
                APP_IND:$("#appInd").val(),
                LIST_CATEGORY_DESC:$("#listCategoryDesc").val(),
                PROPERTY:$("#property").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function rcListCategoryModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}