
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifSalutation").find(".selected").length===1){
        rowData = parent.$('#cifSalutation').DataTable().rows(".selected").data()[0];
        $("#salutation").val(rowData.SALUTATION).attr("disabled",true);
        $("#company").val(rowData.COMPANY);
        $("#genderFlag").val(rowData.GENDER_FLAG);
        $("#salutationDesc").val(rowData.SALUTATION_DESC);
    }

    $("#cifSalutationMod").Validform({
        tiptype:2,
        callback:function(form){
            cifSalutationMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifSalutationMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_SALUTATION";
    keyFieldsJson.SALUTATION=$("#salutation").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.GENDER_FLAG=$("#genderFlag").val();
    generalFieldsJson.SALUTATION_DESC=$("#salutationDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifSalutationMod,"json");
}

function callback_cifSalutationMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifSalutation").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            SALUTATION:$("#salutation").val(),
            COMPANY:$("#company").val(),
            GENDER_FLAG:$("#genderFlag").val(),
            SALUTATION_DESC:$("#salutationDesc").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifSalutationModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

