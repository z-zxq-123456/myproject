
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#mbAcctNatureDef").find(".selected").length===1){
        rowData = parent.$('#mbAcctNatureDef').DataTable().rows(".selected").data()[0];
        $("#acctNature").val(rowData.ACCT_NATURE).attr("disabled",true);
        $("#description").val(rowData.DESCRIPTION);
        $("#company").val(rowData.COMPANY);
    }

    $("#mbAcctNatureDefMod").Validform({
        tiptype:2,
        callback:function(form){
            mbAcctNatureDefMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbAcctNatureDefMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_ACCT_NATURE_DEF";
    keyFieldsJson.ACCT_NATURE=$("#acctNature").val();
    generalFieldsJson.DESCRIPTION=$("#description").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbAcctNatureDefMod,"json");
}

function callback_mbAcctNatureDefMod(json){
    if (json.success) {
        var dataTable=parent.$("#mbAcctNatureDef").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            ACCT_NATURE:$("#acctNature").val(),
            DESCRIPTION:$("#description").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbAcctNatureDefModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

