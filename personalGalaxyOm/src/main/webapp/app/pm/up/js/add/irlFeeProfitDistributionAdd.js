
$(document).ready(function() {
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_FEE_TYPE&tableCol=FEE_TYPE,FEE_DESC",
				id: "feeType",
				async: false
			});
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

	$("#irlFeeProfitDistributionAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlFeeProfitDistributionAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function irlFeeProfitDistributionAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_FEE_PROFIT_DISTRIBUTION";
		keyFieldsJson.FEE_TYPE=$("#feeType").val();
		generalFieldsJson.BRANCH=$("#branch").val();
		generalFieldsJson.PERCENT=$("#percent").val();

	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlFeeProfitDistributionAdd,"json");
}

function callback_irlFeeProfitDistributionAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlFeeProfitDistributionAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

