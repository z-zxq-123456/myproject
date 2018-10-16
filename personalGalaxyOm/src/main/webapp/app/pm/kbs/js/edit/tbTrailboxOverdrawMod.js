
var rowData;
$(document).ready(function() {

    if (parent.$("#tbTrailboxOverdraw").find(".selected").length===1){
        rowData = parent.$('#tbTrailboxOverdraw').DataTable().rows(".selected").data()[0];
        $("#programId").val(rowData.PROGRAM_ID).attr("disabled",true);
        $("#overdrawAmt").val(rowData.OVERDRAW_AMT);
    }

    $("#tbTrailboxOverdrawMod").Validform({
        tiptype:2,
        callback:function(form){
            tbTrailboxOverdrawMod();
            return false;
        }
    });

    $(".select2").select2();
});

function tbTrailboxOverdrawMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="TB_TRAILBOX_OVERDRAW";
    keyFieldsJson.PROGRAM_ID=$("#programId").val();
    generalFieldsJson.OVERDRAW_AMT=$("#overdrawAmt").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_tbTrailboxOverdrawMod,"json");
}

function callback_tbTrailboxOverdrawMod(json){
    if (json.success) {
        var dataTable=parent.$("#tbTrailboxOverdraw").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            PROGRAM_ID:$("#programId").val(),
            OVERDRAW_AMT:$("#overdrawAmt").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function tbTrailboxOverdrawModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

