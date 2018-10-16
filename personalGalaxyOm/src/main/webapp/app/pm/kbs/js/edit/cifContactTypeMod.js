
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifContactType").find(".selected").length===1){
        rowData = parent.$('#cifContactType').DataTable().rows(".selected").data()[0];
        $("#contactType").val(rowData.CONTACT_TYPE).attr("disabled",true);
        $("#contactTypeDesc").val(rowData.CONTACT_TYPE_DESC);
        $("#route").val(rowData.ROUTE);
        $("#company").val(rowData.COMPANY);
        $("#swiftId").val(rowData.SWIFT_ID);
    }

    $("#cifContactTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            cifContactTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifContactTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_CONTACT_TYPE";
    keyFieldsJson.CONTACT_TYPE=$("#contactType").val();
    generalFieldsJson.CONTACT_TYPE_DESC=$("#contactTypeDesc").val();
    generalFieldsJson.ROUTE=$("#route").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.SWIFT_ID=$("#swiftId").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifContactTypeMod,"json");
}

function callback_cifContactTypeMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifContactType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CONTACT_TYPE:$("#contactType").val(),
            CONTACT_TYPE_DESC:$("#contactTypeDesc").val(),
            ROUTE:$("#route").val(),
            COMPANY:$("#company").val(),
            SWIFT_ID:$("#swiftId").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifContactTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

