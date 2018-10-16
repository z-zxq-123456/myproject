
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=IRL_PROD_TYPE&tableCol=PROD_TYPE,PROD_TYPE_DESC",
        id: "prodType",
        async: false
    });

    if (parent.$("#glProdMapping").find(".selected").length===1){
        rowData = parent.$('#glProdMapping').DataTable().rows(".selected").data()[0];
        $("#mappingType").val(rowData.MAPPING_TYPE).attr("disabled",true);
        $("#prodType").val(rowData.PROD_TYPE);
        $("#mappingDesc").val(rowData.MAPPING_DESC);
        $("#prodDesc").val(rowData.PROD_DESC);
    }

    $("#glProdMappingMod").Validform({
        tiptype:2,
        callback:function(form){
            glProdMappingMod();
            return false;
        }
    });

    $(".select2").select2();
});

function glProdMappingMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="GL_PROD_MAPPING";
    paraJson.systemId="GLR";
    keyFieldsJson.MAPPING_TYPE=$("#mappingType").val();
    generalFieldsJson.PROD_TYPE=$("#prodType").val();
    generalFieldsJson.MAPPING_DESC=$("#mappingDesc").val();
    generalFieldsJson.PROD_DESC=$("#prodDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_glProdMappingMod,"json");
}

function callback_glProdMappingMod(json){
    if (json.success) {
        var dataTable=parent.$("#glProdMapping").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            MAPPING_TYPE:$("#mappingType").val(),
            PROD_TYPE:$("#prodType").val(),
            MAPPING_DESC:$("#mappingDesc").val(),
            PROD_DESC:$("#prodDesc").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function glProdMappingModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

