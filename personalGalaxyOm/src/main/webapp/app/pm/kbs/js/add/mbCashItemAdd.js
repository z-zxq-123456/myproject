
$(document).ready(function() {

	$("#mbCashItemAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbCashItemAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbCashItemAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_CASH_ITEM";
		keyFieldsJson.CASH_ITEM=$("#cashItem").val();
		generalFieldsJson.CASH_ITEM_DESC=$("#cashItemDesc").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.CR_DR_IND=$("#crDrInd").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbCashItemAdd,"json");
}

function callback_mbCashItemAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbCashItemAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


