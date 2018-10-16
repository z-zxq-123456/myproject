
$(document).ready(function() {

	$("#irlRuleGroupAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlRuleGroupAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function irlRuleGroupAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_RULE_GROUP";
		keyFieldsJson.GROUP_TYPE=$("#groupType").val();
		generalFieldsJson.WEIGHT=$("#weight").val();
		generalFieldsJson.GROUP_MATCH_TYPE=$("#grpMatchType").val();
		generalFieldsJson.GROUP_CLASS=$("#groupClass").val();
		generalFieldsJson.GROUP_TYPE_DESC=$("#groupTypeDesc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlRuleGroupAdd,"json");
}

function callback_irlRuleGroupAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlRuleGroupAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


