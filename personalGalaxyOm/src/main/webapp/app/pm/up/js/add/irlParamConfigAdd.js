
$(document).ready(function() {
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
				id: "company",
				async: false
			});

	$("#irlParamConfigAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlParamConfigAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function irlParamConfigAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_PARAM_CONFIG";
		keyFieldsJson.PARAM_NAME=$("#paramName").val();
		generalFieldsJson.PARAM_DESC=$("#paramDesc").val();
		generalFieldsJson.PARAM_VALUE=$("#paramValue").val();
		generalFieldsJson.REMARK=$("#remark").val();

	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlParamConfigAdd,"json");
}

function callback_irlParamConfigAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlParamConfigAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

