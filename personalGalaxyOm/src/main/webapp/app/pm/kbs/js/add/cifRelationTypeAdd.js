
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=CIF_RELATION_TYPE&tableCol=RELATION_TYPE,RELATION_DESC",
		id: "counterRel",
		async: false
	});

	$("#cifRelationTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			cifRelationTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cifRelationTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CIF_RELATION_TYPE";
		keyFieldsJson.RELATION_TYPE=$("#relationType").val();
		generalFieldsJson.AUTHORISED=$("#authorised").val();
		generalFieldsJson.JOINT_ACCT=$("#jointAcct").val();
		generalFieldsJson.RELATIVE=$("#relative").val();
		generalFieldsJson.RELATION_DESC=$("#relationDesc").val();
		generalFieldsJson.EMPLOYMENT=$("#employment").val();
		generalFieldsJson.EQUITY=$("#equity").val();
		generalFieldsJson.EXPOSURE=$("#exposure").val();
		generalFieldsJson.SYMMENTRIC=$("#symmentric").val();
		generalFieldsJson.JOIN_COLLAT=$("#joinCollat").val();
		generalFieldsJson.RELATION_TYPE_FLAG=$("#relationTypeFlag").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.COUNTER_REL=$("#counterRel").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cifRelationTypeAdd,"json");
}

function callback_cifRelationTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cifRelationTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


