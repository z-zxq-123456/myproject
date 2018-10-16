$(document).ready(function() {

	$("#acSubjectAdd").Validform({
		tiptype:2,
		callback:function(){
			acSubjectAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function acSubjectAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="AC_SUBJECT";
    keyFieldsJson.SUBJECT_CODE=$("#subjectCode").val();
    generalFieldsJson.TFR_IND=$("#tfrInd").val();
    generalFieldsJson.BALANCE_WAY=$("#balanceWay").val();
    generalFieldsJson.BSPL_TYPE=$("#bsplType").val();
    generalFieldsJson.SUBJECT_TYPE=$("#subjectType").val();
    generalFieldsJson.SUBJECT_STATUS=$("#subjectStatus").val();
    generalFieldsJson.SUBJECT_DESC=$("#subjectDesc").val();
    generalFieldsJson.MANUAL_BATCH_RES=$("#manualBatchRes").val();
    generalFieldsJson.GL_TYPE=$("#glType").val();
    generalFieldsJson.OPERATING_TAX=$("#operatingTax").val();
    generalFieldsJson.BUSINESS_UNIT=$("#businessUnit").val();
    generalFieldsJson.OF_TRF=$("#ofTrf").val();
    generalFieldsJson.SUBJECT_DESC_EN=$("#subjectDescEn").val();
    generalFieldsJson.MANUAL_ACCOUNT=$("#manualAccount").val();
    generalFieldsJson.INTERNAL=$("#internal").val();
    generalFieldsJson.SUBJECT_LEVEL=$("#subjectLevel").val();
    generalFieldsJson.CONTROL_SUBJECT=$("#controlSubject").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_acSubjectAdd,"json");
}

function callback_acSubjectAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function acSubjectAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}