$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_CITY&tableCol=CITY,CITY_DESC",
		id: "city",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_STATE&tableCol=STATE,STATE_DESC",
		id: "state",
		async: false
	});
    getPkList({
            url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
            id: "company",
            async: false
        });
	$("#fmDistCodeAdd").Validform({
		tiptype:2,
		callback:function(){
			fmDistCodeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmDistCodeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_DIST_CODE";
    keyFieldsJson.CITY=$("#city").val();
    keyFieldsJson.PROVINCE=$("#province").val();
    keyFieldsJson.DIST_CODE=$("#distCode").val();
    keyFieldsJson.DIST_NAME=$("#distName").val();
    generalFieldsJson.DIST_GRADE=$("#distGrade").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.STATE=$("#state").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmDistCodeAdd,"json");
}

function callback_fmDistCodeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmDistCodeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}