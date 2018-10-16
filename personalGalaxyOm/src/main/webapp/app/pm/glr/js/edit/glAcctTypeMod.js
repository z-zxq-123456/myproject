
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCodeAl",
        async: false
    });

    if (parent.$("#glAcctType").find(".selected").length===1){
        rowData = parent.$('#glAcctType').DataTable().rows(".selected").data()[0];
        $("#acctType").val(rowData.ACCT_TYPE).attr("disabled",true);
        $("#acctTypeDesc").val(rowData.ACCT_TYPE_DESC);
        $("#depositType").val(rowData.DEPOSIT_TYPE);
        $("#glCodeAl").val(rowData.GL_CODE_AL);
    }

    $("#glAcctTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            glAcctTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function glAcctTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="GL_ACCT_TYPE";
    keyFieldsJson.ACCT_TYPE=$("#acctType").val();
    generalFieldsJson.ACCT_TYPE_DESC=$("#acctTypeDesc").val();
    generalFieldsJson.DEPOSIT_TYPE=$("#depositType").val();
    generalFieldsJson.GL_CODE_AL=$("#glCodeAl").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_glAcctTypeMod,"json");
}

function callback_glAcctTypeMod(json){
    if (json.success) {
        var dataTable=parent.$("#glAcctType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            ACCT_TYPE:$("#acctType").val(),
            ACCT_TYPE_DESC:$("#acctTypeDesc").val(),
            DEPOSIT_TYPE:$("#depositType").val(),
            GL_CODE_AL:$("#glCodeAl").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function glAcctTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

