
$(document).ready(function() {

	$("#tbApplyConfAdd").Validform({
		tiptype:2,
		callback:function(form){
			tbApplyConfAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function tbApplyConfAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="TB_APPLY_CONF";
		keyFieldsJson.BRANCH=$("#branch").val();
		generalFieldsJson.CBRANCH=$("#cbranch").val();
		generalFieldsJson.CBSFLAG=$("#cbsflag").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.IS_CASH_APPLY=$("#isCashApply").val();
		generalFieldsJson.IS_VOUCHER_APPLY=$("#isVoucherApply").val();
		generalFieldsJson.VBRANCH=$("#vbranch").val();
		generalFieldsJson.VBSFLAG=$("#vbsflag").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_tbApplyConfAdd,"json");
}

function callback_tbApplyConfAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function tbApplyConfAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


