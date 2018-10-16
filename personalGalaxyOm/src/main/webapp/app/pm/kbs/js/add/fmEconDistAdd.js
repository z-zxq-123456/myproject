$(document).ready(function() {
    getPkList({
            url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
            id: "company",
            async: false
        });
	$("#fmEconDistAdd").Validform({
		tiptype:2,
		callback:function(){
			fmEconDistAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmEconDistAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_ECON_DIST";
    keyFieldsJson.ECON_DIST=$("#econDist").val();
    generalFieldsJson.ECON_DIST_DESC=$("#econDistDesc").val();
    generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmEconDistAdd,"json");
}

function callback_fmEconDistAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmEconDistAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}