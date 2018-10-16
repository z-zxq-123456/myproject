
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmBankHierarchy").find(".selected").length===1){
        rowData = parent.$('#fmBankHierarchy').DataTable().rows(".selected").data()[0];
        $("#hierarchyCode").val(rowData.HIERARCHY_CODE).attr("disabled",true);
        $("#company").val(rowData.COMPANY);
        $("#hierarchyLevel").val(rowData.HIERARCHY_LEVEL);
        $("#hierarchyName").val(rowData.HIERARCHY_NAME);
    }

    $("#fmBankHierarchyMod").Validform({
        tiptype:2,
        callback:function(form){
            fmBankHierarchyMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmBankHierarchyMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_BANK_HIERARCHY";
    keyFieldsJson.HIERARCHY_CODE=$("#hierarchyCode").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.HIERARCHY_LEVEL=$("#hierarchyLevel").val();
    generalFieldsJson.HIERARCHY_NAME=$("#hierarchyName").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmBankHierarchyMod,"json");
}

function callback_fmBankHierarchyMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmBankHierarchy").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            HIERARCHY_CODE:$("#hierarchyCode").val(),
            COMPANY:$("#company").val(),
            HIERARCHY_LEVEL:$("#hierarchyLevel").val(),
            HIERARCHY_NAME:$("#hierarchyName").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmBankHierarchyModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

