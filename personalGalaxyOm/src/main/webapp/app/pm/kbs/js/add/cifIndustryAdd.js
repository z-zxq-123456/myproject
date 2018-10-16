
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#cifIndustryAdd").Validform({
		tiptype:2,
		callback:function(form){
			cifIndustryAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cifIndustryAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CIF_INDUSTRY";
		keyFieldsJson.INDUSTRY=$("#industry").val();
		generalFieldsJson.DETAIL_IND=$("#detailInd").val();
		generalFieldsJson.INDUSTRY_DESC=$("#industryDesc").val();
		generalFieldsJson.INDUSTRY_LEVEL=$("#industryLevel").val();
		generalFieldsJson.STANDARD_IND=$("#standardInd").val();
		generalFieldsJson.RISK_LEVEL=$("#riskLevel").val();
		generalFieldsJson.PARENT_INDUSTRY=$("#parentIndustry").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cifIndustryAdd,"json");
}

function callback_cifIndustryAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cifIndustryAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


