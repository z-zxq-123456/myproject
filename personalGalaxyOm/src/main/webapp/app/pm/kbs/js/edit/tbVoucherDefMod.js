
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#tbVoucherDef").find(".selected").length===1){
        rowData = parent.$('#tbVoucherDef').DataTable().rows(".selected").data()[0];
        $("#docType").val(rowData.DOC_TYPE).attr("disabled",true);
        $("#isChequeBook").val(rowData.IS_CHEQUE_BOOK);
        $("#prefixReq").val(rowData.PREFIX_REQ);
        $("#status").val(rowData.STATUS);
        $("#inContral").val(rowData.IN_CONTRAL);
        $("#haveNumber").val(rowData.HAVE_NUMBER);
        $("#docTypeDesc").val(rowData.DOC_TYPE_DESC);
        $("#docClass").val(rowData.DOC_CLASS);
        $("#otherBankFlag").val(rowData.OTHER_BANK_FLAG);
        $("#allowDistr").val(rowData.ALLOW_DISTR);
        $("#branchRestraint").val(rowData.BRANCH_RESTRAINT);
        $("#voucherBillInd").val(rowData.VOUCHER_BILL_IND);
        $("#useByOrder").val(rowData.USE_BY_ORDER);
        $("#voucherLength").val(rowData.VOUCHER_LENGTH);
        $("#tcDenomGroup").val(rowData.TC_DENOM_GROUP);
        $("#startDate").val(rowData.START_DATE);
        $("#profitCentre").val(rowData.PROFIT_CENTRE);
        $("#userId").val(rowData.USER_ID);
        $("#lastChangeUserId").val(rowData.LAST_CHANGE_USER_ID);
        $("#checkUserId").val(rowData.CHECK_USER_ID);
        $("#commissionVouLostDays").val(rowData.COMMISSION_VOU_LOST_DAYS);
        $("#company").val(rowData.COMPANY);
        $("#depositType").val(rowData.DEPOSIT_TYPE);
        $("#expireDate").val(rowData.EXPIRE_DATE);
        $("#isCashCheque").val(rowData.IS_CASH_CHEQUE);
        $("#allowChequeDenom").val(rowData.ALLOW_CHEQUE_DENOM);
        $("#lastChangeDate").val(rowData.LAST_CHANGE_DATE);
        $("#vouLostDays").val(rowData.VOU_LOST_DAYS);
    }

    $("#tbVoucherDefMod").Validform({
        tiptype:2,
        callback:function(form){
            tbVoucherDefMod();
            return false;
        }
    });
    $('#startDate').blur(function(){
        var value=$('#startDate').val();
        var effectDate = new Date(value.replace(/^(\d{4})(\d{2})(\d{2})$/,"$1/$2/$3"));
        effectDate.setDate(effectDate.getDate()+1);
        var sysDate = new Date();
        if(effectDate<sysDate){
            alert("生效日期不能小于当前系统日期！");
        }
    });
    $('#expireDate').blur(function(){
        var value=$('#startDate').val();
        var value1=$('#expireDate').val();
        var startDate = new Date(value.replace(/^(\d{4})(\d{2})(\d{2})$/,"$1/$2/$3"));
        var expireDate = new Date(value1.replace(/^(\d{4})(\d{2})(\d{2})$/,"$1/$2/$3"));
        if(expireDate<startDate){
            alert("失效日期不能小于生效日期！");
        }
    });

    $(".select2").select2();
});

function tbVoucherDefMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="TB_VOUCHER_DEF";
    keyFieldsJson.DOC_TYPE=$("#docType").val();
    generalFieldsJson.IS_CHEQUE_BOOK=$("#isChequeBook").val();
    generalFieldsJson.PREFIX_REQ=$("#prefixReq").val();
    generalFieldsJson.STATUS=$("#status").val();
    generalFieldsJson.IN_CONTRAL=$("#inContral").val();
    generalFieldsJson.HAVE_NUMBER=$("#haveNumber").val();
    generalFieldsJson.DOC_TYPE_DESC=$("#docTypeDesc").val();
    generalFieldsJson.DOC_CLASS=$("#docClass").val();
    generalFieldsJson.OTHER_BANK_FLAG=$("#otherBankFlag").val();
    generalFieldsJson.ALLOW_DISTR=$("#allowDistr").val();
    generalFieldsJson.BRANCH_RESTRAINT=$("#branchRestraint").val();
    generalFieldsJson.VOUCHER_BILL_IND=$("#voucherBillInd").val();
    generalFieldsJson.USE_BY_ORDER=$("#useByOrder").val();
    generalFieldsJson.VOUCHER_LENGTH=$("#voucherLength").val();
    generalFieldsJson.TC_DENOM_GROUP=$("#tcDenomGroup").val();
    generalFieldsJson.START_DATE=$("#startDate").val();
    generalFieldsJson.PROFIT_CENTRE=$("#profitCentre").val();
    generalFieldsJson.USER_ID=$("#userId").val();
    generalFieldsJson.LAST_CHANGE_USER_ID=$("#lastChangeUserId").val();
    generalFieldsJson.CHECK_USER_ID=$("#checkUserId").val();
    generalFieldsJson.COMMISSION_VOU_LOST_DAYS=$("#commissionVouLostDays").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.DEPOSIT_TYPE=$("#depositType").val();
    generalFieldsJson.EXPIRE_DATE=$("#expireDate").val();
    generalFieldsJson.IS_CASH_CHEQUE=$("#isCashCheque").val();
    generalFieldsJson.ALLOW_CHEQUE_DENOM=$("#allowChequeDenom").val();
    generalFieldsJson.LAST_CHANGE_DATE=$("#lastChangeDate").val();
    generalFieldsJson.VOU_LOST_DAYS=$("#vouLostDays").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_tbVoucherDefMod,"json");
}

function callback_tbVoucherDefMod(json){
    if (json.success) {
        var dataTable=parent.$("#tbVoucherDef").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            DOC_TYPE:$("#docType").val(),
            IS_CHEQUE_BOOK:$("#isChequeBook").val(),
            PREFIX_REQ:$("#prefixReq").val(),
            STATUS:$("#status").val(),
            IN_CONTRAL:$("#inContral").val(),
            HAVE_NUMBER:$("#haveNumber").val(),
            DOC_TYPE_DESC:$("#docTypeDesc").val(),
            DOC_CLASS:$("#docClass").val(),
            OTHER_BANK_FLAG:$("#otherBankFlag").val(),
            ALLOW_DISTR:$("#allowDistr").val(),
            BRANCH_RESTRAINT:$("#branchRestraint").val(),
            VOUCHER_BILL_IND:$("#voucherBillInd").val(),
            USE_BY_ORDER:$("#useByOrder").val(),
            VOUCHER_LENGTH:$("#voucherLength").val(),
            TC_DENOM_GROUP:$("#tcDenomGroup").val(),
            START_DATE:$("#startDate").val(),
            PROFIT_CENTRE:$("#profitCentre").val(),
            USER_ID:$("#userId").val(),
            LAST_CHANGE_USER_ID:$("#lastChangeUserId").val(),
            CHECK_USER_ID:$("#checkUserId").val(),
            COMMISSION_VOU_LOST_DAYS:$("#commissionVouLostDays").val(),
            COMPANY:$("#company").val(),
            DEPOSIT_TYPE:$("#depositType").val(),
            EXPIRE_DATE:$("#expireDate").val(),
            IS_CASH_CHEQUE:$("#isCashCheque").val(),
            ALLOW_CHEQUE_DENOM:$("#allowChequeDenom").val(),
            LAST_CHANGE_DATE:$("#lastChangeDate").val(),
            VOU_LOST_DAYS:$("#vouLostDays").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function tbVoucherDefModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

