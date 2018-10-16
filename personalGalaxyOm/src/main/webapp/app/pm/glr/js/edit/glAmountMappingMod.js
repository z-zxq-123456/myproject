
var rowData;
$(document).ready(function() {
    	getPkList({
    		url: contextPath + "/baseCommon/pklistBase?tableName=GL_AMOUNT_TYPE&tableCol=AMOUNT_TYPE,AMOUNT_TYPE_DESC",
    		id: "mappingType",
    		async: false
    	});

    if (parent.$("#glAmountMapping").find(".selected").length===1){
        rowData = parent.$('#glAmountMapping').DataTable().rows(".selected").data()[0];
        $("#mappingType").val(rowData.MAPPING_TYPE).attr("disabled",true);
        $("#amountType").val(rowData.AMOUNT_TYPE);
        $("#amountTypeDesc").val(rowData.AMOUNT_TYPE_DESC);
        $("#mappingName").val(rowData.MAPPING_NAME);
    }

    $("#glAmountMappingMod").Validform({
        tiptype:2,
        callback:function(form){
            glAmountMappingMod();
            return false;
        }
    });

    $(".select2").select2();
});

function glAmountMappingMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="GL_AMOUNT_MAPPING";
    keyFieldsJson.MAPPING_TYPE=$("#mappingType").val();
    generalFieldsJson.AMOUNT_TYPE=$("#amountType").val();
    generalFieldsJson.AMOUNT_TYPE_DESC=$("#amountTypeDesc").val();
    generalFieldsJson.MAPPING_NAME=$("#mappingName").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_glAmountMappingMod,"json");
}

function callback_glAmountMappingMod(json){
    if (json.success) {
        var dataTable=parent.$("#glAmountMapping").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            MAPPING_TYPE:$("#mappingType").val(),
            AMOUNT_TYPE:$("#amountType").val(),
            AMOUNT_TYPE_DESC:$("#amountTypeDesc").val(),
            MAPPING_NAME:$("#mappingName").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function glAmountMappingModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

