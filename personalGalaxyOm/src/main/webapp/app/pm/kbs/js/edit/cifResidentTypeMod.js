
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifResidentType").find(".selected").length===1){
        rowData = parent.$('#cifResidentType').DataTable().rows(".selected").data()[0];
        $("#residentType").val(rowData.RESIDENT_TYPE).attr("disabled",true);
        $("#company").val(rowData.COMPANY);
        $("#residentTypeDesc").val(rowData.RESIDENT_TYPE_DESC);
    }

    $("#cifResidentTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            cifResidentTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifResidentTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_RESIDENT_TYPE";
    keyFieldsJson.RESIDENT_TYPE=$("#residentType").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.RESIDENT_TYPE_DESC=$("#residentTypeDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifResidentTypeMod,"json");
}

function callback_cifResidentTypeMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifResidentType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            RESIDENT_TYPE:$("#residentType").val(),
            COMPANY:$("#company").val(),
            RESIDENT_TYPE_DESC:$("#residentTypeDesc").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifResidentTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

