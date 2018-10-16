
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
		id: "branch",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#tbTrailboxAdd").Validform({
		tiptype:2,
		callback:function(form){
			tbTrailboxAdd();
			return false;
		}
	});

	var $example =  $("#branch").select2();
	$example.val("xa");         //赋单个值


	$(".select2").select2();
$("#branch").select2({
createSearchChoice: function(term ,data){

return {
  id:term.mid,
  text:term.name
};
}

});

});

function tbTrailboxAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="TB_TRAILBOX";
		keyFieldsJson.TRAILBOX_ID=$("#trailboxId").val();
		generalFieldsJson.BRANCH=$("#branch").val();
		generalFieldsJson.TRAILBOX_TYPE=$("#trailboxType").val();
		generalFieldsJson.TRAILBOX_STATUS=$("#trailboxStatus").val();
		generalFieldsJson.VOUCHER_EQUAL_DATE=$("#voucherEqualDate").val();
		generalFieldsJson.USER_ID=$("#userId").val();
		generalFieldsJson.UPDATE_DATE=$("#updateDate").val();
		generalFieldsJson.TRAILBOX_PROPERTY=$("#trailboxProperty").val();
		generalFieldsJson.ASSIGN_USER_ID=$("#assignUserId").val();
		generalFieldsJson.LAST_USER_ID=$("#lastUserId").val();
		generalFieldsJson.CREATE_DATE=$("#createDate").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.CASH_RUN_DATE=$("#cashRunDate").val();
		generalFieldsJson.CASH_EQUAL_DATE=$("#cashEqualDate").val();
		generalFieldsJson.VOUCHER_RUN_DATE=$("#voucherRunDate").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_tbTrailboxAdd,"json");
}

function callback_tbTrailboxAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function tbTrailboxAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


