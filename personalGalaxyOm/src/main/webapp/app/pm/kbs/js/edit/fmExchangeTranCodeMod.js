
var rowData;
$(document).ready(function() {

    if (parent.$("#fmExchangeTranCode").find(".selected").length===1){
        rowData = parent.$('#fmExchangeTranCode').DataTable().rows(".selected").data()[0];
        $("#incExpInd").val(rowData.INC_EXP_IND);
        $("#tranCode").val(rowData.TRAN_CODE);
        $("#tranCodeDesc").val(rowData.TRAN_CODE_DESC);
    }

    $("#fmExchangeTranCodeMod").Validform({
        tiptype:2,
        callback:function(form){
            fmExchangeTranCodeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmExchangeTranCodeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_EXCHANGE_TRAN_CODE";
    generalFieldsJson.INC_EXP_IND=$("#incExpInd").val();
    generalFieldsJson.TRAN_CODE=$("#tranCode").val();
    generalFieldsJson.TRAN_CODE_DESC=$("#tranCodeDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmExchangeTranCodeMod,"json");
}

function callback_fmExchangeTranCodeMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmExchangeTranCode").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            INC_EXP_IND:$("#incExpInd").val(),
            TRAN_CODE:$("#tranCode").val(),
            TRAN_CODE_DESC:$("#tranCodeDesc").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmExchangeTranCodeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

