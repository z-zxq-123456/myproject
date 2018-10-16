var rowData;
$(document).ready(function() {

    if (parent.$("#rcListType").find(".selected").length===1){
        rowData = parent.$('#rcListType').DataTable().rows(".selected").data()[0];
        $("#listType").val(rowData.LIST_TYPE).attr("disabled",true);
        $("#listCategory").val(rowData.LIST_CATEGORY);
        $("#verifyTerm").val(rowData.VERIFY_TERM);
        $("#verifyInd").val(rowData.VERIFY_IND);
        $("#verifyAcctInd").val(rowData.VERIFY_ACCT_IND);
        $("#status").val(rowData.STATUS);
        $("#relateAcctInd").val(rowData.RELATE_ACCT_IND);
        $("#listTypeDesc").val(rowData.LIST_TYPE_DESC);
        $("#listOrg").val(rowData.LIST_ORG);
        $("#verifyTermType").val(rowData.VERIFY_TERM_TYPE);
    }

    $("#rcListTypeMod").Validform({
        tiptype:2,
        callback:function(){
            rcListTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function rcListTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="RC_LIST_TYPE";
    keyFieldsJson.LIST_TYPE=$("#listType").val();
    generalFieldsJson.LIST_CATEGORY=$("#listCategory").val();
    generalFieldsJson.VERIFY_TERM=$("#verifyTerm").val();
    generalFieldsJson.VERIFY_IND=$("#verifyInd").val();
    generalFieldsJson.VERIFY_ACCT_IND=$("#verifyAcctInd").val();
    generalFieldsJson.STATUS=$("#status").val();
    generalFieldsJson.RELATE_ACCT_IND=$("#relateAcctInd").val();
    generalFieldsJson.LIST_TYPE_DESC=$("#listTypeDesc").val();
    generalFieldsJson.LIST_ORG=$("#listOrg").val();
    generalFieldsJson.VERIFY_TERM_TYPE=$("#verifyTermType").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_rcListTypeMod,"json");
}

function callback_rcListTypeMod(json){
    if (json.success) {
        if (parent.$("#rcListType").find(".selected").length===1){
            rowData = parent.$('#rcListType').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#rcListType").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                LIST_TYPE:$("#listType").val(),
                LIST_CATEGORY:$("#listCategory").val(),
                VERIFY_TERM:$("#verifyTerm").val(),
                VERIFY_IND:$("#verifyInd").val(),
                VERIFY_ACCT_IND:$("#verifyAcctInd").val(),
                STATUS:$("#status").val(),
                RELATE_ACCT_IND:$("#relateAcctInd").val(),
                LIST_TYPE_DESC:$("#listTypeDesc").val(),
                LIST_ORG:$("#listOrg").val(),
                VERIFY_TERM_TYPE:$("#verifyTermType").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function rcListTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}