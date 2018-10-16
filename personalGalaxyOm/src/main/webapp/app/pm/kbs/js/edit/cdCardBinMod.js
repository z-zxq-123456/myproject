
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cdCardBin").find(".selected").length===1){
        rowData = parent.$('#cdCardBin').DataTable().rows(".selected").data()[0];
        $("#cardBin").val(rowData.CARD_BIN).attr("disabled",true);
        $("#company").val(rowData.COMPANY);
        $("#validTime").val(rowData.VALID_TIME);
    }

    $("#cdCardBinMod").Validform({
        tiptype:2,
        callback:function(form){
            cdCardBinMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cdCardBinMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CD_CARD_BIN";
    keyFieldsJson.CARD_BIN=$("#cardBin").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.VALID_TIME=$("#validTime").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cdCardBinMod,"json");
}

function callback_cdCardBinMod(json){
    if (json.success) {
        var dataTable=parent.$("#cdCardBin").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CARD_BIN:$("#cardBin").val(),
            COMPANY:$("#company").val(),
            VALID_TIME:$("#validTime").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cdCardBinModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

