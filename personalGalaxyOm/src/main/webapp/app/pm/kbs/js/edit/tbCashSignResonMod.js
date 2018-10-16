
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=MB_TRAN_DEF&tableCol=TRAN_TYPE,TRAN_TYPE_DESC",
        id: "overTranType",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=MB_TRAN_DEF&tableCol=TRAN_TYPE,TRAN_TYPE_DESC",
        id: "shortTranType",
        async: false
    });

    if (parent.$("#tbCashSignReson").find(".selected").length===1){
        rowData = parent.$('#tbCashSignReson').DataTable().rows(".selected").data()[0];
        $("#shtgId").val(rowData.SHTG_ID).attr("disabled",true);
        $("#shtgType").val(rowData.SHTG_TYPE).attr("disabled",true);
        $("#company").val(rowData.COMPANY);
        $("#overTranType").val(rowData.OVER_TRAN_TYPE);
        $("#shortTranType").val(rowData.SHORT_TRAN_TYPE);
        $("#shtgDesc").val(rowData.SHTG_DESC);
    }

    $("#tbCashSignResonMod").Validform({
        tiptype:2,
        callback:function(form){
            tbCashSignResonMod();
            return false;
        }
    });

    $(".select2").select2();
});

function tbCashSignResonMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="TB_CASH_SIGN_RESON";
    keyFieldsJson.SHTG_ID=$("#shtgId").val();
    keyFieldsJson.SHTG_TYPE=$("#shtgType").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.OVER_TRAN_TYPE=$("#overTranType").val();
    generalFieldsJson.SHORT_TRAN_TYPE=$("#shortTranType").val();
    generalFieldsJson.SHTG_DESC=$("#shtgDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_tbCashSignResonMod,"json");
}

function callback_tbCashSignResonMod(json){
    if (json.success) {
        var dataTable=parent.$("#tbCashSignReson").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            SHTG_ID:$("#shtgId").val(),
            SHTG_TYPE:$("#shtgType").val(),
            COMPANY:$("#company").val(),
            OVER_TRAN_TYPE:$("#overTranType").val(),
            SHORT_TRAN_TYPE:$("#shortTranType").val(),
            SHTG_DESC:$("#shtgDesc").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function tbCashSignResonModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

