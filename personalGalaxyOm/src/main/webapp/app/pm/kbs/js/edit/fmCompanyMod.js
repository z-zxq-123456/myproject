
var rowData;
$(document).ready(function() {

    if (parent.$("#fmCompany").find(".selected").length===1){
        rowData = parent.$('#fmCompany').DataTable().rows(".selected").data()[0];
        $("#company").val(rowData.COMPANY).attr("disabled",true);
        $("#companyName").val(rowData.COMPANY_NAME);
    }

    $("#fmCompanyMod").Validform({
        tiptype:2,
        callback:function(form){
            fmCompanyMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmCompanyMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_COMPANY";
    keyFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.COMPANY_NAME=$("#companyName").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmCompanyMod,"json");
}

function callback_fmCompanyMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmCompany").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            COMPANY:$("#company").val(),
            COMPANY_NAME:$("#companyName").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmCompanyModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

