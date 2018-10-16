
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#mbAcctLinkmanType").find(".selected").length===1){
        rowData = parent.$('#mbAcctLinkmanType').DataTable().rows(".selected").data()[0];
        $("#linkmanType").val(rowData.LINKMAN_TYPE).attr("disabled",true);
        $("#company").val(rowData.COMPANY);
        $("#linkmanDesc").val(rowData.LINKMAN_DESC);
    }

    $("#mbAcctLinkmanTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            mbAcctLinkmanTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbAcctLinkmanTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_ACCT_LINKMAN_TYPE";
    keyFieldsJson.LINKMAN_TYPE=$("#linkmanType").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.LINKMAN_DESC=$("#linkmanDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbAcctLinkmanTypeMod,"json");
}

function callback_mbAcctLinkmanTypeMod(json){
    if (json.success) {
        var dataTable=parent.$("#mbAcctLinkmanType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            LINKMAN_TYPE:$("#linkmanType").val(),
            COMPANY:$("#company").val(),
            LINKMAN_DESC:$("#linkmanDesc").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbAcctLinkmanTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

