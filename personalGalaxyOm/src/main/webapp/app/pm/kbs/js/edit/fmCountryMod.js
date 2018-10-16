
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
        id: "ccy",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmCountry").find(".selected").length===1){
        rowData = parent.$('#fmCountry').DataTable().rows(".selected").data()[0];
        $("#country").val(rowData.COUNTRY).attr("disabled",true);
        $("#countryDesc").val(rowData.COUNTRY_DESC);
        $("#ccy").val(rowData.CCY);
        $("#region").val(rowData.REGION);
        $("#psc").val(rowData.PSC);
        $("#ncct").val(rowData.NCCT);
        $("#isoCode").val(rowData.ISO_CODE);
        $("#countryTel").val(rowData.COUNTRY_TEL);
        $("#company").val(rowData.COMPANY);
        $("#safeCode").val(rowData.SAFE_CODE);
    }

    $("#fmCountryMod").Validform({
        tiptype:2,
        callback:function(form){
            fmCountryMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmCountryMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_COUNTRY";
    keyFieldsJson.COUNTRY=$("#country").val();
    generalFieldsJson.COUNTRY_DESC=$("#countryDesc").val();
    generalFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.REGION=$("#region").val();
    generalFieldsJson.PSC=$("#psc").val();
    generalFieldsJson.NCCT=$("#ncct").val();
    generalFieldsJson.ISO_CODE=$("#isoCode").val();
    generalFieldsJson.COUNTRY_TEL=$("#countryTel").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.SAFE_CODE=$("#safeCode").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmCountryMod,"json");
}

function callback_fmCountryMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmCountry").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            COUNTRY:$("#country").val(),
            COUNTRY_DESC:$("#countryDesc").val(),
            CCY:$("#ccy").val(),
            REGION:$("#region").val(),
            PSC:$("#psc").val(),
            NCCT:$("#ncct").val(),
            ISO_CODE:$("#isoCode").val(),
            COUNTRY_TEL:$("#countryTel").val(),
            COMPANY:$("#company").val(),
            SAFE_CODE:$("#safeCode").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmCountryModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

