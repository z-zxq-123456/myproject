
var rowData;
$(document).ready(function() {

    if (parent.$("#cdTranControl").find(".selected").length===1){
        rowData = parent.$('#cdTranControl').DataTable().rows(".selected").data()[0];
        $("#cdAreaCode").val(rowData.CD_AREA_CODE).attr("disabled",true);
        $("#prodType").val(rowData.PROD_TYPE).attr("disabled",true);
        $("#prodChannel").val(rowData.PROD_CHANNEL).attr("disabled",true);
        $("#cdCustGrade").val(rowData.CD_CUST_GRADE).attr("disabled",true);
        $("#merchantCode").val(rowData.MERCHANT_CODE);
        $("#passwordCtr").val(rowData.PASSWORD_CTR);
        $("#dayTranLim").val(rowData.DAY_TRAN_LIM);
        $("#singleTranLim").val(rowData.SINGLE_TRAN_LIM);
        $("#terminalId").val(rowData.TERMINAL_ID);
        $("#tranCount").val(rowData.TRAN_COUNT);
    }

    $("#cdTranControlMod").Validform({
        tiptype:2,
        callback:function(form){
            cdTranControlMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cdTranControlMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CD_TRAN_CONTROL";
    keyFieldsJson.CD_AREA_CODE=$("#cdAreaCode").val();
    keyFieldsJson.PROD_TYPE=$("#prodType").val();
    keyFieldsJson.PROD_CHANNEL=$("#prodChannel").val();
    keyFieldsJson.CD_CUST_GRADE=$("#cdCustGrade").val();
    generalFieldsJson.MERCHANT_CODE=$("#merchantCode").val();
    generalFieldsJson.PASSWORD_CTR=$("#passwordCtr").val();
    generalFieldsJson.DAY_TRAN_LIM=$("#dayTranLim").val();
    generalFieldsJson.SINGLE_TRAN_LIM=$("#singleTranLim").val();
    generalFieldsJson.TERMINAL_ID=$("#terminalId").val();
    generalFieldsJson.TRAN_COUNT=$("#tranCount").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cdTranControlMod,"json");
}

function callback_cdTranControlMod(json){
    if (json.success) {
      if (parent.$("#cdTranControl").find(".selected").length===1){
        rowData = parent.$('#cdTranControl').DataTable().rows(".selected").data()[0];
        var columnStatus = rowData.COLUMN_STATUS;
        var dataTable=parent.$("#cdTranControl").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CD_AREA_CODE:$("#cdAreaCode").val(),
            PROD_TYPE:$("#prodType").val(),
            PROD_CHANNEL:$("#prodChannel").val(),
            CD_CUST_GRADE:$("#cdCustGrade").val(),
            MERCHANT_CODE:$("#merchantCode").val(),
            PASSWORD_CTR:$("#passwordCtr").val(),
            DAY_TRAN_LIM:$("#dayTranLim").val(),
            SINGLE_TRAN_LIM:$("#singleTranLim").val(),
            TERMINAL_ID:$("#terminalId").val(),
            TRAN_COUNT:$("#tranCount").val(),
            COLUMN_STATUS:columnStatus
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cdTranControlModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

