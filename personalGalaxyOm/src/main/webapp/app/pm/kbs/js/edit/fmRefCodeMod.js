
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmRefCode").find(".selected").length===1){
        rowData = parent.$('#fmRefCode').DataTable().rows(".selected").data()[0];
        $("#fieldValue").val(rowData.FIELD_VALUE).attr("disabled",true);
        $("#refLang").val(rowData.REF_LANG).attr("disabled",true);
        $("#domain").val(rowData.DOMAIN).attr("disabled",true);
        $("#meaning").val(rowData.MEANING);
        $("#paraRowNum").val(rowData.PARA_ROW_NUM);
        $("#paraFlag").val(rowData.PARA_FLAG);
        $("#company").val(rowData.COMPANY);
        $("#abbreviation").val(rowData.ABBREVIATION);
    }

    $("#fmRefCodeMod").Validform({
        tiptype:2,
        callback:function(form){
            fmRefCodeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmRefCodeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_REF_CODE";
    keyFieldsJson.FIELD_VALUE=$("#fieldValue").val();
    keyFieldsJson.REF_LANG=$("#refLang").val();
    keyFieldsJson.DOMAIN=$("#domain").val();
    generalFieldsJson.MEANING=$("#meaning").val();
    generalFieldsJson.PARA_ROW_NUM=$("#paraRowNum").val();
    generalFieldsJson.PARA_FLAG=$("#paraFlag").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.ABBREVIATION=$("#abbreviation").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmRefCodeMod,"json");
}

function callback_fmRefCodeMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmRefCode").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
        FIELD_VALUE:$("#fieldValue").val()
        ,REF_LANG:$("#refLang").val()
        ,DOMAIN:$("#domain").val()
        ,MEANING:$("#meaning").val()
        ,PARA_ROW_NUM:$("#paraRowNum").val()
        ,PARA_FLAG:$("#paraFlag").val()
        ,COMPANY:$("#company").val()
        ,ABBREVIATION:$("#abbreviation").val()
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmRefCodeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

