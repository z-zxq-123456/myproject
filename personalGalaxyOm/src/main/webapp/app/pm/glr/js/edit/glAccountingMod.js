
var rowData;
$(document).ready(function() {

    if (parent.$("#glAccounting").find(".selected").length===1){
        rowData = parent.$('#glAccounting').DataTable().rows(".selected").data()[0];
        $("#accountingNo").val(rowData.ACCOUNTING_NO).attr("disabled",true);
        $("#amountType").val(rowData.AMOUNT_TYPE).attr("disabled",true);
        $("#counter").val(rowData.COUNTER).attr("disabled",true);
        $("#profitCentreExp").val(rowData.PROFIT_CENTRE_EXP);
        $("#prodTypeExp").val(rowData.PROD_TYPE_EXP);
        $("#glCodeExp").val(rowData.GL_CODE_EXP);
        $("#crDr").val(rowData.CR_DR);
        $("#companyExp").val(rowData.COMPANY_EXP);
        $("#clientNoExp").val(rowData.CLIENT_NO_EXP);
        $("#ccyExp").val(rowData.CCY_EXP);
        $("#businessUnitExp").val(rowData.BUSINESS_UNIT_EXP);
        $("#branchExp").val(rowData.BRANCH_EXP);
        $("#amountExp").val(rowData.AMOUNT_EXP);
        $("#seqNo").val(rowData.SEQ_NO);
    }

    $("#glAccountingMod").Validform({
        tiptype:2,
        callback:function(form){
            glAccountingMod();
            return false;
        }
    });

    $(".select2").select2();
});

function glAccountingMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="GL_ACCOUNTING";
    keyFieldsJson.ACCOUNTING_NO=$("#accountingNo").val();
    keyFieldsJson.AMOUNT_TYPE=$("#amountType").val();
    keyFieldsJson.COUNTER=$("#counter").val();
    generalFieldsJson.PROFIT_CENTRE_EXP=$("#profitCentreExp").val();
    generalFieldsJson.PROD_TYPE_EXP=$("#prodTypeExp").val();
    generalFieldsJson.GL_CODE_EXP=$("#glCodeExp").val();
    generalFieldsJson.CR_DR=$("#crDr").val();
    generalFieldsJson.COMPANY_EXP=$("#companyExp").val();
    generalFieldsJson.CLIENT_NO_EXP=$("#clientNoExp").val();
    generalFieldsJson.CCY_EXP=$("#ccyExp").val();
    generalFieldsJson.BUSINESS_UNIT_EXP=$("#businessUnitExp").val();
    generalFieldsJson.BRANCH_EXP=$("#branchExp").val();
    generalFieldsJson.AMOUNT_EXP=$("#amountExp").val();
    generalFieldsJson.SEQ_NO=$("#seqNo").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_glAccountingMod,"json");
}

function callback_glAccountingMod(json){
    if (json.success) {
        var dataTable=parent.$("#glAccounting").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            ACCOUNTING_NO:$("#accountingNo").val(),
            AMOUNT_TYPE:$("#amountType").val(),
            COUNTER:$("#counter").val(),
            PROFIT_CENTRE_EXP:$("#profitCentreExp").val(),
            PROD_TYPE_EXP:$("#prodTypeExp").val(),
            GL_CODE_EXP:$("#glCodeExp").val(),
            CR_DR:$("#crDr").val(),
            COMPANY_EXP:$("#companyExp").val(),
            CLIENT_NO_EXP:$("#clientNoExp").val(),
            CCY_EXP:$("#ccyExp").val(),
            BUSINESS_UNIT_EXP:$("#businessUnitExp").val(),
            BRANCH_EXP:$("#branchExp").val(),
            AMOUNT_EXP:$("#amountExp").val(),
            SEQ_NO:$("#seqNo").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function glAccountingModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

