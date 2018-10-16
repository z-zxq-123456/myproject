
$(document).ready(function() {

	$("#tbCashUnitAdd").Validform({
		tiptype:2,
		callback:function(form){
			tbCashUnitAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function tbCashUnitAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="TB_CASH_UNIT";
		keyFieldsJson.PAR_VALUE_ID=$("#parValueId").val();
		generalFieldsJson.UNIT_SUM_B=$("#unitSumB").val();
		generalFieldsJson.UNIT_SUM_K=$("#unitSumK").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_tbCashUnitAdd,"json");
}

function callback_tbCashUnitAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function tbCashUnitAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


