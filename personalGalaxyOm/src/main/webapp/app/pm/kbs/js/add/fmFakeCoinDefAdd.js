$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
		id: "ccy",
		async: false
	});

	$("#fmFakeCoinDefAdd").Validform({
		tiptype:2,
		callback:function(){
			fmFakeCoinDefAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmFakeCoinDefAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_FAKE_COIN_DEF";
    keyFieldsJson.BONDNOTES=$("#bondnotes").val();
    keyFieldsJson.BONDNUMBER=$("#bondnumber").val();
    keyFieldsJson.BONDTYPEID=$("#bondtypeid").val();
    keyFieldsJson.BONDVERSIONNUM=$("#bondversionnum").val();
    keyFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.BONDNAME=$("#bondname").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmFakeCoinDefAdd,"json");
}

function callback_fmFakeCoinDefAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmFakeCoinDefAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}