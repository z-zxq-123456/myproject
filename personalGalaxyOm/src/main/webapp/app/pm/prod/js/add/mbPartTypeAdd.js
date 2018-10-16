
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_PART_CLASS&tableCol=PART_CLASS",
		id: "partClass",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_PROD_CLASS&tableCol=PROD_CLASS",
		id: "busiCategory",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
	$("#mbPartTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbPartTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbPartTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_PART_TYPE";
		keyFieldsJson.PART_TYPE=$("#partType").val();
		generalFieldsJson.IS_STANDARD=$("#isStandard").val();
		generalFieldsJson.PART_CLASS=$("#partClass").val();
		generalFieldsJson.PART_DESC=$("#partDesc").val();
		generalFieldsJson.BUSI_CATEGORY=$("#busiCategory").val();
		generalFieldsJson.DEFAULT_PART=$("#defaultPart").val();
		generalFieldsJson.PROCESS_METHOD=$("#processMethod").val();
		generalFieldsJson.STATUS=$("#status").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbPartTypeAdd,"json");
}

function callback_mbPartTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbPartTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


