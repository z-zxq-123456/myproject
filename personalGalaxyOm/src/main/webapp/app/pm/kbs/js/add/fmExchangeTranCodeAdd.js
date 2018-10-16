
$(document).ready(function() {

	$("#fmExchangeTranCodeAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmExchangeTranCodeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmExchangeTranCodeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_EXCHANGE_TRAN_CODE";
		generalFieldsJson.INC_EXP_IND=$("#incExpInd").val();
		generalFieldsJson.TRAN_CODE_DESC=$("#tranCodeDesc").val();
		generalFieldsJson.TRAN_CODE=$("#tranCode").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmExchangeTranCodeAdd,"json");
}

function callback_fmExchangeTranCodeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmExchangeTranCodeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


