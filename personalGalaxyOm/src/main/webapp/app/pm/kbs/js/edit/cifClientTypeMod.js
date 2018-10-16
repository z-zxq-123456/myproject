
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifClientType").find(".selected").length===1){
        rowData = parent.$('#cifClientType').DataTable().rows(".selected").data()[0];
        $("#clientType").val(rowData.CLIENT_TYPE).attr("disabled",true);
        $("#clientTypeDesc").val(rowData.CLIENT_TYPE_DESC);
        $("#isIndividual").val(rowData.IS_INDIVIDUAL);
        $("#company").val(rowData.COMPANY);
    }

    $("#cifClientTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            cifClientTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifClientTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_CLIENT_TYPE";
    keyFieldsJson.CLIENT_TYPE=$("#clientType").val();
    generalFieldsJson.CLIENT_TYPE_DESC=$("#clientTypeDesc").val();
    generalFieldsJson.IS_INDIVIDUAL=$("#isIndividual").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifClientTypeMod,"json");
}

function callback_cifClientTypeMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifClientType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CLIENT_TYPE:$("#clientType").val(),
            CLIENT_TYPE_DESC:$("#clientTypeDesc").val(),
            IS_INDIVIDUAL:$("#isIndividual").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifClientTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

