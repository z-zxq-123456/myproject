
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_STATE&tableCol=STATE,STATE_DESC",
        id: "state",
        async: false
    });

    if (parent.$("#cifDistCode").find(".selected").length===1){
        rowData = parent.$('#cifDistCode').DataTable().rows(".selected").data()[0];
        $("#city").val(rowData.CITY).attr("disabled",true);
        $("#distCode").val(rowData.DIST_CODE).attr("disabled",true);
        $("#distName").val(rowData.DIST_NAME).attr("disabled",true);
        $("#province").val(rowData.PROVINCE).attr("disabled",true);
        $("#distGrade").val(rowData.DIST_GRADE);
        $("#company").val(rowData.COMPANY);
        $("#state").val(rowData.STATE);
    }

    $("#cifDistCodeMod").Validform({
        tiptype:2,
        callback:function(form){
            cifDistCodeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifDistCodeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_DIST_CODE";
    keyFieldsJson.CITY=$("#city").val();
    keyFieldsJson.DIST_CODE=$("#distCode").val();
    keyFieldsJson.DIST_NAME=$("#distName").val();
    keyFieldsJson.PROVINCE=$("#province").val();
    generalFieldsJson.DIST_GRADE=$("#distGrade").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.STATE=$("#state").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifDistCodeMod,"json");
}

function callback_cifDistCodeMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifDistCode").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CITY:$("#city").val(),
            DIST_CODE:$("#distCode").val(),
            DIST_NAME:$("#distName").val(),
            PROVINCE:$("#province").val(),
            DIST_GRADE:$("#distGrade").val(),
            COMPANY:$("#company").val(),
            STATE:$("#state").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifDistCodeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

