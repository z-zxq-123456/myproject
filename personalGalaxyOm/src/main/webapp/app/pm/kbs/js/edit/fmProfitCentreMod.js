
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmProfitCentre").find(".selected").length===1){
        rowData = parent.$('#fmProfitCentre').DataTable().rows(".selected").data()[0];
        $("#profitCentre").val(rowData.PROFIT_CENTRE).attr("disabled",true);
        $("#profitCentreDesc").val(rowData.PROFIT_CENTRE_DESC);
        $("#profitCentreType").val(rowData.PROFIT_CENTRE_TYPE);
        $("#company").val(rowData.COMPANY);
        $("#profitCentreDescEn").val(rowData.PROFIT_CENTRE_DESC_EN);
        $("#profitCentreLevel").val(rowData.PROFIT_CENTRE_LEVEL);
    }

    $("#fmProfitCentreMod").Validform({
        tiptype:2,
        callback:function(form){
            fmProfitCentreMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmProfitCentreMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_PROFIT_CENTRE";
    keyFieldsJson.PROFIT_CENTRE=$("#profitCentre").val();
    generalFieldsJson.PROFIT_CENTRE_DESC=$("#profitCentreDesc").val();
    generalFieldsJson.PROFIT_CENTRE_TYPE=$("#profitCentreType").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.PROFIT_CENTRE_DESC_EN=$("#profitCentreDescEn").val();
    generalFieldsJson.PROFIT_CENTRE_LEVEL=$("#profitCentreLevel").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmProfitCentreMod,"json");
}

function callback_fmProfitCentreMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmProfitCentre").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            PROFIT_CENTRE:$("#profitCentre").val(),
            PROFIT_CENTRE_DESC:$("#profitCentreDesc").val(),
            PROFIT_CENTRE_TYPE:$("#profitCentreType").val(),
            COMPANY:$("#company").val(),
            PROFIT_CENTRE_DESC_EN:$("#profitCentreDescEn").val(),
            PROFIT_CENTRE_LEVEL:$("#profitCentreLevel").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmProfitCentreModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

