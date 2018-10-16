
var rowData;
$(document).ready(function() {

    if (parent.$("#mbPurpose").find(".selected").length===1){
        rowData = parent.$('#mbPurpose').DataTable().rows(".selected").data()[0];
        $("#purpose").val(rowData.PURPOSE).attr("disabled",true);
        $("#purposeDesc").val(rowData.PURPOSE_DESC);
    }

    $("#mbPurposeMod").Validform({
        tiptype:2,
        callback:function(form){
            mbPurposeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbPurposeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_PURPOSE";
    keyFieldsJson.PURPOSE=$("#purpose").val();
    generalFieldsJson.PURPOSE_DESC=$("#purposeDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbPurposeMod,"json");
}

function callback_mbPurposeMod(json){
    if (json.success) {
        var dataTable=parent.$("#mbPurpose").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            PURPOSE:$("#purpose").val(),
            PURPOSE_DESC:$("#purposeDesc").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbPurposeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

