
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCodeOdpRec",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCodeOdpI",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCodeOdpAcr",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCodeOdiRec",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCodeOdiI",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCodeOdiAcr",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCodeL",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCodeIntRec",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCodeIntPay",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCodeIntI",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCodeIntE",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCodeIntAcr",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCodeALoss",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCodeAdjust",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCodeA",
        async: false
    });

    if (parent.$("#glProdAccounting").find(".selected").length===1){
        rowData = parent.$('#glProdAccounting').DataTable().rows(".selected").data()[0];
        $("#accountingStatus").val(rowData.ACCOUNTING_STATUS).attr("disabled",true);
        $("#prodType").val(rowData.PROD_TYPE).attr("disabled",true);
        $("#glCodeOdpRec").val(rowData.GL_CODE_ODP_REC);
        $("#glCodeOdpI").val(rowData.GL_CODE_ODP_I);
        $("#glCodeOdpAcr").val(rowData.GL_CODE_ODP_ACR);
        $("#glCodeOdiRec").val(rowData.GL_CODE_ODI_REC);
        $("#glCodeOdiI").val(rowData.GL_CODE_ODI_I);
        $("#glCodeOdiAcr").val(rowData.GL_CODE_ODI_ACR);
        $("#glCodeL").val(rowData.GL_CODE_L);
        $("#glCodeIntRec").val(rowData.GL_CODE_INT_REC);
        $("#glCodeIntPay").val(rowData.GL_CODE_INT_PAY);
        $("#glCodeIntI").val(rowData.GL_CODE_INT_I);
        $("#glCodeIntE").val(rowData.GL_CODE_INT_E);
        $("#glCodeIntAcr").val(rowData.GL_CODE_INT_ACR);
        $("#glCodeALoss").val(rowData.GL_CODE_A_LOSS);
        $("#glCodeAdjust").val(rowData.GL_CODE_ADJUST);
        $("#glCodeA").val(rowData.GL_CODE_A);
        $("#businessUnit").val(rowData.BUSINESS_UNIT);
        $("#profitCentre").val(rowData.PROFIT_CENTRE);
    }

    $("#glProdAccountingMod").Validform({
        tiptype:2,
        callback:function(form){
            glProdAccountingMod();
            return false;
        }
    });

    $(".select2").select2();
});

function glProdAccountingMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="GL_PROD_ACCOUNTING";
    paraJson.systemId="GLR";
    keyFieldsJson.ACCOUNTING_STATUS=$("#accountingStatus").val();
    keyFieldsJson.PROD_TYPE=$("#prodType").val();
    generalFieldsJson.GL_CODE_ODP_REC=$("#glCodeOdpRec").val();
    generalFieldsJson.GL_CODE_ODP_I=$("#glCodeOdpI").val();
    generalFieldsJson.GL_CODE_ODP_ACR=$("#glCodeOdpAcr").val();
    generalFieldsJson.GL_CODE_ODI_REC=$("#glCodeOdiRec").val();
    generalFieldsJson.GL_CODE_ODI_I=$("#glCodeOdiI").val();
    generalFieldsJson.GL_CODE_ODI_ACR=$("#glCodeOdiAcr").val();
    generalFieldsJson.GL_CODE_L=$("#glCodeL").val();
    generalFieldsJson.GL_CODE_INT_REC=$("#glCodeIntRec").val();
    generalFieldsJson.GL_CODE_INT_PAY=$("#glCodeIntPay").val();
    generalFieldsJson.GL_CODE_INT_I=$("#glCodeIntI").val();
    generalFieldsJson.GL_CODE_INT_E=$("#glCodeIntE").val();
    generalFieldsJson.GL_CODE_INT_ACR=$("#glCodeIntAcr").val();
    generalFieldsJson.GL_CODE_A_LOSS=$("#glCodeALoss").val();
    generalFieldsJson.GL_CODE_ADJUST=$("#glCodeAdjust").val();
    generalFieldsJson.GL_CODE_A=$("#glCodeA").val();
    generalFieldsJson.BUSINESS_UNIT=$("#businessUnit").val();
    generalFieldsJson.PROFIT_CENTRE=$("#profitCentre").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_glProdAccountingMod,"json");
}

function callback_glProdAccountingMod(json){
    if (json.success) {
        var dataTable=parent.$("#glProdAccounting").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            ACCOUNTING_STATUS:$("#accountingStatus").val(),
            PROD_TYPE:$("#prodType").val(),
            GL_CODE_ODP_REC:$("#glCodeOdpRec").val(),
            GL_CODE_ODP_I:$("#glCodeOdpI").val(),
            GL_CODE_ODP_ACR:$("#glCodeOdpAcr").val(),
            GL_CODE_ODI_REC:$("#glCodeOdiRec").val(),
            GL_CODE_ODI_I:$("#glCodeOdiI").val(),
            GL_CODE_ODI_ACR:$("#glCodeOdiAcr").val(),
            GL_CODE_L:$("#glCodeL").val(),
            GL_CODE_INT_REC:$("#glCodeIntRec").val(),
            GL_CODE_INT_PAY:$("#glCodeIntPay").val(),
            GL_CODE_INT_I:$("#glCodeIntI").val(),
            GL_CODE_INT_E:$("#glCodeIntE").val(),
            GL_CODE_INT_ACR:$("#glCodeIntAcr").val(),
            GL_CODE_A_LOSS:$("#glCodeALoss").val(),
            GL_CODE_ADJUST:$("#glCodeAdjust").val(),
            GL_CODE_A:$("#glCodeA").val(),
            BUSINESS_UNIT:$("#businessUnit").val(),
            PROFIT_CENTRE:$("#profitCentre").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function glProdAccountingModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

