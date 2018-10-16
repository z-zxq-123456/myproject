
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifQualification").find(".selected").length===1){
        rowData = parent.$('#cifQualification').DataTable().rows(".selected").data()[0];
        $("#qualification").val(rowData.QUALIFICATION).attr("disabled",true);
        $("#company").val(rowData.COMPANY);
        $("#qualificationDesc").val(rowData.QUALIFICATION_DESC);
    }

    $("#cifQualificationMod").Validform({
        tiptype:2,
        callback:function(form){
            cifQualificationMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifQualificationMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_QUALIFICATION";
    keyFieldsJson.QUALIFICATION=$("#qualification").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.QUALIFICATION_DESC=$("#qualificationDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifQualificationMod,"json");
}

function callback_cifQualificationMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifQualification").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            QUALIFICATION:$("#qualification").val(),
            COMPANY:$("#company").val(),
            QUALIFICATION_DESC:$("#qualificationDesc").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifQualificationModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

