
$(document).ready(function() {

	$("#cdCardBinAdd").Validform({
		tiptype:2,
		callback:function(form){
			cdCardBinAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cdCardBinAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CD_CARD_BIN";
		keyFieldsJson.COMPANY=$("#company").val();
		keyFieldsJson.VALID_TIME=$("#validTime").val();
		generalFieldsJson.CARD_BIN=$("#cardBin").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cdCardBinAdd,"json");
}

function callback_cdCardBinAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cdCardBinAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


