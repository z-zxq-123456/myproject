
$(document).ready(function() {

	$("#tbVoucherDefAdd").Validform({
		tiptype:2,
		callback:function(form){
			tbVoucherDefAdd();
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
            $('#startDate').val("");
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

function tbVoucherDefAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
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
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_tbVoucherDefAdd,"json");
}

function callback_tbVoucherDefAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function tbVoucherDefAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


