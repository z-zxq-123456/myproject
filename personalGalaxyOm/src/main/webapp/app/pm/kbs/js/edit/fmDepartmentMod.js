
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmDepartment").find(".selected").length===1){
        rowData = parent.$('#fmDepartment').DataTable().rows(".selected").data()[0];
        $("#department").val(rowData.DEPARTMENT).attr("disabled",true);
        $("#departmentDesc").val(rowData.DEPARTMENT_DESC);
        $("#company").val(rowData.COMPANY);
        $("#profitSegment").val(rowData.PROFIT_SEGMENT);
    }

    $("#fmDepartmentMod").Validform({
        tiptype:2,
        callback:function(form){
            fmDepartmentMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmDepartmentMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_DEPARTMENT";
    keyFieldsJson.DEPARTMENT=$("#department").val();
    generalFieldsJson.DEPARTMENT_DESC=$("#departmentDesc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.PROFIT_SEGMENT=$("#profitSegment").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmDepartmentMod,"json");
}

function callback_fmDepartmentMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmDepartment").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            DEPARTMENT:$("#department").val(),
            DEPARTMENT_DESC:$("#departmentDesc").val(),
            COMPANY:$("#company").val(),
            PROFIT_SEGMENT:$("#profitSegment").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmDepartmentModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

