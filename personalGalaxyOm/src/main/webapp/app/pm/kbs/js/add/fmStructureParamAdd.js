
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#fmStructureParamAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmStructureParamAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmStructureParamAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_STRUCTURE_PARAM";
		keyFieldsJson.PARAM_TYPE=$("#paramType").val();
		keyFieldsJson.START_POS=$("#startPos").val();
		keyFieldsJson.STRUCTURE_TYPE=$("#structureType").val();
		generalFieldsJson.LENGTH=$("#length").val();
		generalFieldsJson.END_POS=$("#endPos").val();
		generalFieldsJson.PADDING_CHAR=$("#paddingChar").val();
		generalFieldsJson.STRING_VALUE=$("#stringValue").val();
		generalFieldsJson.SEQ_TYPE=$("#seqType").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmStructureParamAdd,"json");
}

function callback_fmStructureParamAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmStructureParamAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


