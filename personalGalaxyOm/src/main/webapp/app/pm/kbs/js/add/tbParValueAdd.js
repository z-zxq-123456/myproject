
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
		id: "ccy",
		async: false
	});

	$("#tbParValueAdd").Validform({
		tiptype:2,
		callback:function(form){
			tbParValueAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function tbParValueAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="TB_PAR_VALUE";
		keyFieldsJson.PAR_VALUE_ID=$("#parValueId").val();
		generalFieldsJson.CCY=$("#ccy").val();
		generalFieldsJson.IS_SPALL=$("#isSpall").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.PAR_DESC=$("#parDesc").val();
		generalFieldsJson.PAR_TYPE=$("#parType").val();
		generalFieldsJson.PAR_VALUE=$("#parValue").val();
		generalFieldsJson.UPDATE_DATE=$("#updateDate").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_tbParValueAdd,"json");
}

function callback_tbParValueAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function tbParValueAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


