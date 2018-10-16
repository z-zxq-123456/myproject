
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COUNTRY&tableCol=COUNTRY,COUNTRY_DESC",
        id: "country",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmCity").find(".selected").length===1){
        rowData = parent.$('#fmCity').DataTable().rows(".selected").data()[0];
        $("#city").val(rowData.CITY).attr("disabled",true);
        $("#state").val(rowData.STATE).attr("disabled",true);
        $("#country").val(rowData.COUNTRY).attr("disabled",true);
        $("#cityDesc").val(rowData.CITY_DESC);
        $("#cityTel").val(rowData.CITY_TEL);
        $("#company").val(rowData.COMPANY);
    }

    $("#fmCityMod").Validform({
        tiptype:2,
        callback:function(form){
            fmCityMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmCityMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_CITY";
    keyFieldsJson.CITY=$("#city").val();
    keyFieldsJson.STATE=$("#state").val();
    keyFieldsJson.COUNTRY=$("#country").val();
    generalFieldsJson.CITY_DESC=$("#cityDesc").val();
    generalFieldsJson.CITY_TEL=$("#cityTel").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmCityMod,"json");
}

function callback_fmCityMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmCity").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CITY:$("#city").val(),
            STATE:$("#state").val(),
            COUNTRY:$("#country").val(),
            CITY_DESC:$("#cityDesc").val(),
            CITY_TEL:$("#cityTel").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmCityModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

