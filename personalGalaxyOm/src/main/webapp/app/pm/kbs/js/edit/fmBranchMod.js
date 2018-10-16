
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_STATE&tableCol=STATE,STATE_DESC",
        id: "state",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COUNTRY&tableCol=COUNTRY,COUNTRY_DESC",
        id: "country",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_CITY&tableCol=CITY,CITY_DESC",
        id: "city",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmBranch").find(".selected").length===1){
        rowData = parent.$('#fmBranch').DataTable().rows(".selected").data()[0];
        $("#branch").val(rowData.BRANCH).attr("disabled",true);
        $("#voucherUserContral").val(rowData.VOUCHER_USER_CONTRAL);
        $("#state").val(rowData.STATE);
        $("#country").val(rowData.COUNTRY);
        $("#branchShort").val(rowData.BRANCH_SHORT);
        $("#branchName").val(rowData.BRANCH_NAME);
        $("#localCcy").val(rowData.LOCAL_CCY);
        $("#internalClient").val(rowData.INTERNAL_CLIENT);
        $("#pbocFundCheckFalg").val(rowData.PBOC_FUND_CHECK_FALG);
        $("#ipAddr").val(rowData.IP_ADDR);
        $("#postalCode").val(rowData.POSTAL_CODE);
        $("#profitCentre").val(rowData.PROFIT_CENTRE);
        $("#status").val(rowData.STATUS);
        $("#subBranchCode").val(rowData.SUB_BRANCH_CODE);
        $("#tranBrInd").val(rowData.TRAN_BR_IND);
        $("#hkdBusinessUnit").val(rowData.HKD_BUSINESS_UNIT);
        $("#hierarchyCode").val(rowData.HIERARCHY_CODE);
        $("#fxOrganCode").val(rowData.FX_ORGAN_CODE);
        $("#baseCcy").val(rowData.BASE_CCY);
        $("#ccyCtrlBranch").val(rowData.CCY_CTRL_BRANCH);
        $("#chequeIssuingBranch").val(rowData.CHEQUE_ISSUING_BRANCH);
        $("#city").val(rowData.CITY);
        $("#cnyBusinessUnit").val(rowData.CNY_BUSINESS_UNIT);
        $("#company").val(rowData.COMPANY);
        $("#district").val(rowData.DISTRICT);
        $("#eodInd").val(rowData.EOD_IND);
        $("#ftaCode").val(rowData.FTA_CODE);
        $("#ftaFlag").val(rowData.FTA_FLAG);
        $("#attachedTo").val(rowData.ATTACHED_TO);
    }

    $("#fmBranchMod").Validform({
        tiptype:2,
        callback:function(form){
            fmBranchMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmBranchMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_BRANCH";
    keyFieldsJson.BRANCH=$("#branch").val();
    generalFieldsJson.VOUCHER_USER_CONTRAL=$("#voucherUserContral").val();
    generalFieldsJson.STATE=$("#state").val();
    generalFieldsJson.COUNTRY=$("#country").val();
    generalFieldsJson.BRANCH_SHORT=$("#branchShort").val();
    generalFieldsJson.BRANCH_NAME=$("#branchName").val();
    generalFieldsJson.LOCAL_CCY=$("#localCcy").val();
    generalFieldsJson.INTERNAL_CLIENT=$("#internalClient").val();
    generalFieldsJson.PBOC_FUND_CHECK_FALG=$("#pbocFundCheckFalg").val();
    generalFieldsJson.IP_ADDR=$("#ipAddr").val();
    generalFieldsJson.POSTAL_CODE=$("#postalCode").val();
    generalFieldsJson.PROFIT_CENTRE=$("#profitCentre").val();
    generalFieldsJson.STATUS=$("#status").val();
    generalFieldsJson.SUB_BRANCH_CODE=$("#subBranchCode").val();
    generalFieldsJson.TRAN_BR_IND=$("#tranBrInd").val();
    generalFieldsJson.HKD_BUSINESS_UNIT=$("#hkdBusinessUnit").val();
    generalFieldsJson.HIERARCHY_CODE=$("#hierarchyCode").val();
    generalFieldsJson.FX_ORGAN_CODE=$("#fxOrganCode").val();
    generalFieldsJson.BASE_CCY=$("#baseCcy").val();
    generalFieldsJson.CCY_CTRL_BRANCH=$("#ccyCtrlBranch").val();
    generalFieldsJson.CHEQUE_ISSUING_BRANCH=$("#chequeIssuingBranch").val();
    generalFieldsJson.CITY=$("#city").val();
    generalFieldsJson.CNY_BUSINESS_UNIT=$("#cnyBusinessUnit").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.DISTRICT=$("#district").val();
    generalFieldsJson.EOD_IND=$("#eodInd").val();
    generalFieldsJson.FTA_CODE=$("#ftaCode").val();
    generalFieldsJson.FTA_FLAG=$("#ftaFlag").val();
    generalFieldsJson.ATTACHED_TO=$("#attachedTo").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmBranchMod,"json");
}

function callback_fmBranchMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmBranch").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            BRANCH:$("#branch").val(),
            VOUCHER_USER_CONTRAL:$("#voucherUserContral").val(),
            STATE:$("#state").val(),
            COUNTRY:$("#country").val(),
            BRANCH_SHORT:$("#branchShort").val(),
            BRANCH_NAME:$("#branchName").val(),
            LOCAL_CCY:$("#localCcy").val(),
            INTERNAL_CLIENT:$("#internalClient").val(),
            PBOC_FUND_CHECK_FALG:$("#pbocFundCheckFalg").val(),
            IP_ADDR:$("#ipAddr").val(),
            POSTAL_CODE:$("#postalCode").val(),
            PROFIT_CENTRE:$("#profitCentre").val(),
            STATUS:$("#status").val(),
            SUB_BRANCH_CODE:$("#subBranchCode").val(),
            TRAN_BR_IND:$("#tranBrInd").val(),
            HKD_BUSINESS_UNIT:$("#hkdBusinessUnit").val(),
            HIERARCHY_CODE:$("#hierarchyCode").val(),
            FX_ORGAN_CODE:$("#fxOrganCode").val(),
            BASE_CCY:$("#baseCcy").val(),
            CCY_CTRL_BRANCH:$("#ccyCtrlBranch").val(),
            CHEQUE_ISSUING_BRANCH:$("#chequeIssuingBranch").val(),
            CITY:$("#city").val(),
            CNY_BUSINESS_UNIT:$("#cnyBusinessUnit").val(),
            COMPANY:$("#company").val(),
            DISTRICT:$("#district").val(),
            EOD_IND:$("#eodInd").val(),
            FTA_CODE:$("#ftaCode").val(),
            FTA_FLAG:$("#ftaFlag").val(),
            ATTACHED_TO:$("#attachedTo").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmBranchModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

