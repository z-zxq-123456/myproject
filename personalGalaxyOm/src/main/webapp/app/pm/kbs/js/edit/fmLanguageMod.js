
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmLanguage").find(".selected").length===1){
        rowData = parent.$('#fmLanguage').DataTable().rows(".selected").data()[0];
        $("#languageCode").val(rowData.LANGUAGE_CODE).attr("disabled",true);
        $("#charValue").val(rowData.CHAR_VALUE);
        $("#languageDesc").val(rowData.LANGUAGE_DESC);
        $("#company").val(rowData.COMPANY);
    }

    $("#fmLanguageMod").Validform({
        tiptype:2,
        callback:function(form){
            fmLanguageMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmLanguageMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_LANGUAGE";
    keyFieldsJson.LANGUAGE_CODE=$("#languageCode").val();
    generalFieldsJson.CHAR_VALUE=$("#charValue").val();
    generalFieldsJson.LANGUAGE_DESC=$("#languageDesc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmLanguageMod,"json");
}

function callback_fmLanguageMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmLanguage").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
        LANGUAGE_CODE:$("#languageCode").val()
        ,CHAR_VALUE:$("#charValue").val()
        ,LANGUAGE_DESC:$("#languageDesc").val()
        ,COMPANY:$("#company").val()
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmLanguageModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

