
var rowData;
$(document).ready(function() {

    if (parent.$("#glAmountType").find(".selected").length===1){
        rowData = parent.$('#glAmountType').DataTable().rows(".selected").data()[0];
        $("#amountType").val(rowData.AMOUNT_TYPE).attr("disabled",true);
        $("#amountTypeDesc").val(rowData.AMOUNT_TYPE_DESC);
    }

    $("#glAmountTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            glAmountTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function glAmountTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="GL_AMOUNT_TYPE";
    keyFieldsJson.AMOUNT_TYPE=$("#amountType").val();
    generalFieldsJson.AMOUNT_TYPE_DESC=$("#amountTypeDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_glAmountTypeMod,"json");
}

function callback_glAmountTypeMod(json){
    if (json.success) {
        var dataTable=parent.$("#glAmountType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            AMOUNT_TYPE:$("#amountType").val(),
            AMOUNT_TYPE_DESC:$("#amountTypeDesc").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function glAmountTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

