
$(document).ready(function() {
	$("#elementDesc").attr("disabled",true);
	$("#elementId").change(function(){
	var elementDesc = $("#elementId option:selected").text().split(" ")[1];
	$("#elementDesc").val(elementDesc);
	});

	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=IRL_RULE_GROUP&tableCol=GROUP_TYPE,GROUP_TYPE_DESC",
		id: "groupType",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=IRL_ELEMENT&tableCol=ELEMENT_ID,ELEMENT_DESC",
		id: "elementId",
		async: false
	});
	$("#irlElementGroupAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlElementGroupAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function irlElementGroupAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_ELEMENT_GROUP";
		keyFieldsJson.ELEMENT_ID=$("#elementId").val();
		keyFieldsJson.GROUP_TYPE=$("#groupType").val();
		generalFieldsJson.ELEMENT_DESC=$("#elementDesc").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlElementGroupAdd,"json");
}

function callback_irlElementGroupAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlElementGroupAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


