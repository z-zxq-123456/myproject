
$(document).ready(function() {

	$("#mbAnalysis1Add").Validform({
		tiptype:2,
		callback:function(form){
			mbAnalysis1Add();
			return false;
		}
	});

	$(".select2").select2();
});

function mbAnalysis1Add(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_ANALYSIS1";
		keyFieldsJson.ANALYSIS1=$("#analysis1").val();
		generalFieldsJson.ANALYSIS1_DESC=$("#analysis1Desc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbAnalysis1Add,"json");
}

function callback_mbAnalysis1Add(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbAnalysis1AddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


