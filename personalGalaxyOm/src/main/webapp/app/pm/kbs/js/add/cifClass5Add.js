
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#cifClass5Add").Validform({
		tiptype:2,
		callback:function(form){
			cifClass5Add();
			return false;
		}
	});

	$(".select2").select2();
});

function cifClass5Add(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CIF_CLASS_5";
		keyFieldsJson.CLASS_5=$("#class5").val();
		generalFieldsJson.CLASS_5_DESC=$("#class5Desc").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cifClass5Add,"json");
}

function callback_cifClass5Add(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cifClass5AddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


