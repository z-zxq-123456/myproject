
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#fmStructureTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmStructureTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmStructureTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_STRUCTURE_TYPE";
		keyFieldsJson.STRUCTURE_TYPE=$("#structureType").val();
		generalFieldsJson.STRUCTURE_LENGTH=$("#structureLength").val();
		generalFieldsJson.STRUCTURE_DESC=$("#structureDesc").val();
		generalFieldsJson.COMPLETE_IND=$("#completeInd").val();
		generalFieldsJson.DELIMITER_IND=$("#delimiterInd").val();
		generalFieldsJson.STRUCTURE_ATTR=$("#structureAttr").val();
		generalFieldsJson.STRUCTURE_CLASS=$("#structureClass").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.CHECK_DIGIT_FORMULA=$("#checkDigitFormula").val();
		generalFieldsJson.RESTRICTED_DELIMITER=$("#restrictedDelimiter").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmStructureTypeAdd,"json");
}

function callback_fmStructureTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmStructureTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


