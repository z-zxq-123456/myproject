
$(document).ready(function() {

	$("#mbAnalysis3Add").Validform({
		tiptype:2,
		callback:function(form){
			mbAnalysis3Add();
			return false;
		}
	});

	$(".select2").select2();
});

function mbAnalysis3Add(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_ANALYSIS3";
		keyFieldsJson.ANALYSIS3=$("#analysis3").val();
		generalFieldsJson.ANALYSIS3_DESC=$("#analysis3Desc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbAnalysis3Add,"json");
}

function callback_mbAnalysis3Add(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbAnalysis3AddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


