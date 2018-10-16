
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CLIENT_TYPE&tableCol=CLIENT_TYPE,CLIENT_TYPE_DESC",
        id: "clientType",
        async: false
    });

    if (parent.$("#cifCategoryType").find(".selected").length===1){
        rowData = parent.$('#cifCategoryType').DataTable().rows(".selected").data()[0];
        $("#categoryType").val(rowData.CATEGORY_TYPE).attr("disabled",true);
        $("#bank").val(rowData.BANK);
        $("#other").val(rowData.OTHER);
        $("#joint").val(rowData.JOINT);
        $("#intlInstitution").val(rowData.INTL_INSTITUTION);
        $("#individual").val(rowData.INDIVIDUAL);
        $("#government").val(rowData.GOVERNMENT);
        $("#finInstitution").val(rowData.FIN_INSTITUTION);
        $("#corporation").val(rowData.CORPORATION);
        $("#centralBank").val(rowData.CENTRAL_BANK);
        $("#categoryDesc").val(rowData.CATEGORY_DESC);
        $("#broker").val(rowData.BROKER);
        $("#repOffice").val(rowData.REP_OFFICE);
        $("#company").val(rowData.COMPANY);
        $("#clientType").val(rowData.CLIENT_TYPE);
    }

    $("#cifCategoryTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            cifCategoryTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifCategoryTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_CATEGORY_TYPE";
    keyFieldsJson.CATEGORY_TYPE=$("#categoryType").val();
    generalFieldsJson.BANK=$("#bank").val();
    generalFieldsJson.OTHER=$("#other").val();
    generalFieldsJson.JOINT=$("#joint").val();
    generalFieldsJson.INTL_INSTITUTION=$("#intlInstitution").val();
    generalFieldsJson.INDIVIDUAL=$("#individual").val();
    generalFieldsJson.GOVERNMENT=$("#government").val();
    generalFieldsJson.FIN_INSTITUTION=$("#finInstitution").val();
    generalFieldsJson.CORPORATION=$("#corporation").val();
    generalFieldsJson.CENTRAL_BANK=$("#centralBank").val();
    generalFieldsJson.CATEGORY_DESC=$("#categoryDesc").val();
    generalFieldsJson.BROKER=$("#broker").val();
    generalFieldsJson.REP_OFFICE=$("#repOffice").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.CLIENT_TYPE=$("#clientType").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifCategoryTypeMod,"json");
}

function callback_cifCategoryTypeMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifCategoryType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CATEGORY_TYPE:$("#categoryType").val(),
            BANK:$("#bank").val(),
            OTHER:$("#other").val(),
            JOINT:$("#joint").val(),
            INTL_INSTITUTION:$("#intlInstitution").val(),
            INDIVIDUAL:$("#individual").val(),
            GOVERNMENT:$("#government").val(),
            FIN_INSTITUTION:$("#finInstitution").val(),
            CORPORATION:$("#corporation").val(),
            CENTRAL_BANK:$("#centralBank").val(),
            CATEGORY_DESC:$("#categoryDesc").val(),
            BROKER:$("#broker").val(),
            REP_OFFICE:$("#repOffice").val(),
            COMPANY:$("#company").val(),
            CLIENT_TYPE:$("#clientType").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifCategoryTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

