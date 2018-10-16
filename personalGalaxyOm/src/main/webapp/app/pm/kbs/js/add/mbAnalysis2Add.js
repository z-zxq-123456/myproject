
$(document).ready(function() {

	$("#mbAnalysis2Add").Validform({
		tiptype:2,
		callback:function(form){
			mbAnalysis2Add();
			return false;
		}
	});

	$(".select2").select2();
});

function mbAnalysis2Add(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_ANALYSIS2";
		keyFieldsJson.ANALYSIS2=$("#analysis2").val();
		generalFieldsJson.ANALYSIS2_DESC=$("#analysis2Desc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbAnalysis2Add,"json");
}

function callback_mbAnalysis2Add(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbAnalysis2AddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


