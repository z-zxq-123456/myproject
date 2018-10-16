
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#fmStructureDigitPosAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmStructureDigitPosAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmStructureDigitPosAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_STRUCTURE_DIGIT_POS";
		keyFieldsJson.DIGIT_POS=$("#digitPos").val();
		keyFieldsJson.STRUCTURE_TYPE=$("#structureType").val();
		generalFieldsJson.CHECK_DIGIT_IND=$("#checkDigitInd").val();
		generalFieldsJson.WEIGHT=$("#weight").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmStructureDigitPosAdd,"json");
}

function callback_fmStructureDigitPosAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmStructureDigitPosAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


