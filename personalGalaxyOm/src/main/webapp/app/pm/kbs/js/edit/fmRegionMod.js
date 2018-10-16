
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmRegion").find(".selected").length===1){
        rowData = parent.$('#fmRegion').DataTable().rows(".selected").data()[0];
        $("#region").val(rowData.REGION).attr("disabled",true);
        $("#regionDesc").val(rowData.REGION_DESC);
        $("#company").val(rowData.COMPANY);
        $("#internalCode").val(rowData.INTERNAL_CODE);
    }

    $("#fmRegionMod").Validform({
        tiptype:2,
        callback:function(form){
            fmRegionMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmRegionMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_REGION";
    keyFieldsJson.REGION=$("#region").val();
    generalFieldsJson.REGION_DESC=$("#regionDesc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.INTERNAL_CODE=$("#internalCode").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmRegionMod,"json");
}

function callback_fmRegionMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmRegion").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            REGION:$("#region").val(),
            REGION_DESC:$("#regionDesc").val(),
            COMPANY:$("#company").val(),
            INTERNAL_CODE:$("#internalCode").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmRegionModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

