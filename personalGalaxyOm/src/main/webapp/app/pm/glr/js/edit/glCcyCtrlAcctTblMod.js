
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCodeLoss",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCodeProfit",
        async: false
    });

    if (parent.$("#glCcyCtrlAcctTbl").find(".selected").length===1){
        rowData = parent.$('#glCcyCtrlAcctTbl').DataTable().rows(".selected").data()[0];
        $("#glCode").val(rowData.GL_CODE).attr("disabled",true);
        $("#glCodeLoss").val(rowData.GL_CODE_LOSS);
        $("#glCodeProfit").val(rowData.GL_CODE_PROFIT);
    }

    $("#glCcyCtrlAcctTblMod").Validform({
        tiptype:2,
        callback:function(form){
            glCcyCtrlAcctTblMod();
            return false;
        }
    });

    $(".select2").select2();
});

function glCcyCtrlAcctTblMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="GL_CCY_CTRL_ACCT_TBL";
    keyFieldsJson.GL_CODE=$("#glCode").val();
    generalFieldsJson.GL_CODE_LOSS=$("#glCodeLoss").val();
    generalFieldsJson.GL_CODE_PROFIT=$("#glCodeProfit").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_glCcyCtrlAcctTblMod,"json");
}

function callback_glCcyCtrlAcctTblMod(json){
    if (json.success) {
        var dataTable=parent.$("#glCcyCtrlAcctTbl").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            GL_CODE:$("#glCode").val(),
            GL_CODE_LOSS:$("#glCodeLoss").val(),
            GL_CODE_PROFIT:$("#glCodeProfit").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function glCcyCtrlAcctTblModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

