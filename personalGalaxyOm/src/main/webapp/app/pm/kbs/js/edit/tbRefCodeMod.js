
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#tbRefCode").find(".selected").length===1){
        rowData = parent.$('#tbRefCode').DataTable().rows(".selected").data()[0];
        $("#domain").val(rowData.DOMAIN).attr("disabled",true);
        $("#refGroup").val(rowData.REF_GROUP).attr("disabled",true);
        $("#refLang").val(rowData.REF_LANG).attr("disabled",true);
        $("#fieldValue").val(rowData.FIELD_VALUE);
        $("#meaning").val(rowData.MEANING);
        $("#abbreviation").val(rowData.ABBREVIATION);
        $("#company").val(rowData.COMPANY);
    }

    $("#tbRefCodeMod").Validform({
        tiptype:2,
        callback:function(form){
            tbRefCodeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function tbRefCodeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="TB_REF_CODE";
    keyFieldsJson.DOMAIN=$("#domain").val();
    keyFieldsJson.REF_GROUP=$("#refGroup").val();
    keyFieldsJson.REF_LANG=$("#refLang").val();
    generalFieldsJson.FIELD_VALUE=$("#fieldValue").val();
    generalFieldsJson.MEANING=$("#meaning").val();
    generalFieldsJson.ABBREVIATION=$("#abbreviation").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_tbRefCodeMod,"json");
}

function callback_tbRefCodeMod(json){
    if (json.success) {
        var dataTable=parent.$("#tbRefCode").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
        DOMAIN:$("#domain").val()
        ,REF_GROUP:$("#refGroup").val()
        ,REF_LANG:$("#refLang").val()
        ,FIELD_VALUE:$("#fieldValue").val()
        ,MEANING:$("#meaning").val()
        ,ABBREVIATION:$("#abbreviation").val()
        ,COMPANY:$("#company").val()
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function tbRefCodeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

