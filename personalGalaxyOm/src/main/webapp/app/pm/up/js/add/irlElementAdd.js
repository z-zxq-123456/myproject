
$(document).ready(function() {
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
				id: "company",
				async: false
			});

	$("#irlElementAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlElementAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function irlElementAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_ELEMENT";
		keyFieldsJson.ELEMENT_ID=$("#elementId").val();
		keyFieldsJson.ELEMENT_TYPE=$("#elementType").val();
		generalFieldsJson.ELEMENT_ATTR=$("#elementAttr").val();
		generalFieldsJson.ELEMENT_DESC=$("#elementDesc").val();
		generalFieldsJson.TABLE_NAME=$("#tableName").val();
		generalFieldsJson.ELEMENT_LENGTH=$("#elementLength").val();
		generalFieldsJson.IRL_FIELD_VALUE=$("#fieldValue").val();

	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlElementAdd,"json");
}

function callback_irlElementAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlElementAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


