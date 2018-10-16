
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#mbAcctNatureRestraints").find(".selected").length===1){
        rowData = parent.$('#mbAcctNatureRestraints').DataTable().rows(".selected").data()[0];
        $("#acctNature").val(rowData.ACCT_NATURE).attr("disabled",true);
        $("#restraintType").val(rowData.RESTRAINT_TYPE).attr("disabled",true);
        $("#company").val(rowData.COMPANY);
    }

    $("#mbAcctNatureRestraintsMod").Validform({
        tiptype:2,
        callback:function(form){
            mbAcctNatureRestraintsMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbAcctNatureRestraintsMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_ACCT_NATURE_RESTRAINTS";
    keyFieldsJson.ACCT_NATURE=$("#acctNature").val();
    keyFieldsJson.RESTRAINT_TYPE=$("#restraintType").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbAcctNatureRestraintsMod,"json");
}

function callback_mbAcctNatureRestraintsMod(json){
    if (json.success) {
        var dataTable=parent.$("#mbAcctNatureRestraints").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            ACCT_NATURE:$("#acctNature").val(),
            RESTRAINT_TYPE:$("#restraintType").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbAcctNatureRestraintsModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

