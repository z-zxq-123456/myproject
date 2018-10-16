var rowData;
$(document).ready(function() {

    if (parent.$("#mbTranControl").find(".selected").length===1){
        rowData = parent.$('#mbTranControl').DataTable().rows(".selected").data()[0];
        $("#cdCustGrade").val(rowData.CD_CUST_GRADE).attr("disabled",true);
        $("#prodType").val(rowData.PROD_TYPE).attr("disabled",true);
        $("#prodChannel").val(rowData.PROD_CHANNEL).attr("disabled",true);
        $("#cdAreaCode").val(rowData.CD_AREA_CODE).attr("disabled",true);
        $("#singleTranLim").val(rowData.SINGLE_TRAN_LIM);
        $("#tranCount").val(rowData.TRAN_COUNT);
        $("#dayTranLim").val(rowData.DAY_TRAN_LIM);
        $("#company").val(rowData.COMPANY);
        $("#passwordCtr").val(rowData.PASSWORD_CTR);
    }

    $("#mbTranControlMod").Validform({
        tiptype:2,
        callback:function(){
            mbTranControlMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbTranControlMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_TRAN_CONTROL";
    keyFieldsJson.CD_CUST_GRADE=$("#cdCustGrade").val();
    keyFieldsJson.PROD_TYPE=$("#prodType").val();
    keyFieldsJson.PROD_CHANNEL=$("#prodChannel").val();
    keyFieldsJson.CD_AREA_CODE=$("#cdAreaCode").val();
    generalFieldsJson.SINGLE_TRAN_LIM=$("#singleTranLim").val();
    generalFieldsJson.TRAN_COUNT=$("#tranCount").val();
    generalFieldsJson.DAY_TRAN_LIM=$("#dayTranLim").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.PASSWORD_CTR=$("#passwordCtr").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbTranControlMod,"json");
}

function callback_mbTranControlMod(json){
    if (json.success) {
        if (parent.$("#mbTranControl").find(".selected").length===1){
            rowData = parent.$('#mbTranControl').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#mbTranControl").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                CD_CUST_GRADE:$("#cdCustGrade").val(),
                PROD_TYPE:$("#prodType").val(),
                PROD_CHANNEL:$("#prodChannel").val(),
                CD_AREA_CODE:$("#cdAreaCode").val(),
                SINGLE_TRAN_LIM:$("#singleTranLim").val(),
                TRAN_COUNT:$("#tranCount").val(),
                DAY_TRAN_LIM:$("#dayTranLim").val(),
                COMPANY:$("#company").val(),
                PASSWORD_CTR:$("#passwordCtr").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbTranControlModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}