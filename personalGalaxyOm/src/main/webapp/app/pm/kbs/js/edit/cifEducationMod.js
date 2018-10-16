
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifEducation").find(".selected").length===1){
        rowData = parent.$('#cifEducation').DataTable().rows(".selected").data()[0];
        $("#education").val(rowData.EDUCATION).attr("disabled",true);
        $("#company").val(rowData.COMPANY);
        $("#educationDesc").val(rowData.EDUCATION_DESC);
    }

    $("#cifEducationMod").Validform({
        tiptype:2,
        callback:function(form){
            cifEducationMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifEducationMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_EDUCATION";
    keyFieldsJson.EDUCATION=$("#education").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.EDUCATION_DESC=$("#educationDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifEducationMod,"json");
}

function callback_cifEducationMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifEducation").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            EDUCATION:$("#education").val(),
            COMPANY:$("#company").val(),
            EDUCATION_DESC:$("#educationDesc").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifEducationModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

