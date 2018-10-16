
$(document).ready(function() {

	$("#tbTrailboxOverdrawAdd").Validform({
		tiptype:2,
		callback:function(form){
			tbTrailboxOverdrawAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function tbTrailboxOverdrawAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="TB_TRAILBOX_OVERDRAW";
		keyFieldsJson.PROGRAM_ID=$("#programId").val();
		generalFieldsJson.OVERDRAW_AMT=$("#overdrawAmt").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_tbTrailboxOverdrawAdd,"json");
}

function callback_tbTrailboxOverdrawAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function tbTrailboxOverdrawAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


