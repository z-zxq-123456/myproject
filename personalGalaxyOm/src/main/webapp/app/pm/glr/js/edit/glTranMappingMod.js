
var rowData;
$(document).ready(function() {

    if (parent.$("#glTranMapping").find(".selected").length===1){
        rowData = parent.$('#glTranMapping').DataTable().rows(".selected").data()[0];
        $("#indexNo").val(rowData.INDEX_NO).attr("disabled",true);
        $("#keyName").val(rowData.KEY_NAME);
        $("#objectName").val(rowData.OBJECT_NAME);
        $("#isAmount").val(rowData.IS_AMOUNT);
        $("#keyDesc").val(rowData.KEY_DESC);
    }

    $("#glTranMappingMod").Validform({
        tiptype:2,
        callback:function(form){
            glTranMappingMod();
            return false;
        }
    });

    $(".select2").select2();
});

function glTranMappingMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="GL_TRAN_MAPPING";
    keyFieldsJson.INDEX_NO=$("#indexNo").val();
    generalFieldsJson.KEY_NAME=$("#keyName").val();
    generalFieldsJson.OBJECT_NAME=$("#objectName").val();
    generalFieldsJson.IS_AMOUNT=$("#isAmount").val();
    generalFieldsJson.KEY_DESC=$("#keyDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_glTranMappingMod,"json");
}

function callback_glTranMappingMod(json){
    if (json.success) {
        var dataTable=parent.$("#glTranMapping").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            INDEX_NO:$("#indexNo").val(),
            KEY_NAME:$("#keyName").val(),
            OBJECT_NAME:$("#objectName").val(),
            IS_AMOUNT:$("#isAmount").val(),
            KEY_DESC:$("#keyDesc").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function glTranMappingModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

