$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CLIENT_TYPE&tableCol=CLIENT_TYPE,CLIENT_TYPE_DESC",
		id: "clientType",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_PROD_TYPE&tableCol=PROD_TYPE,PROD_DESC",
		id: "ctrlValue",
		async: false
	});

	$('#ctrlAttr').change(function(){
		var value=$('#ctrlAttr').val();
		if(value == "P"){
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=MB_PROD_TYPE&tableCol=PROD_TYPE,PROD_DESC",
				id: "ctrlValue",
				async: false
			});
		}else if(value == "A"){
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=MB_ACCT_NATURE_DEF&tableCol=ACCT_NATURE,DESCRIPTION",
				id: "ctrlValue",
				async: false
			});
		}else if(value == "D"){
			getPkList({  url: contextPath + "/pklist/getCtrlValue",
				id: "ctrlValue",
				async: false
			});
		}
	});


	$("#mbOpenCtlAdd").Validform({
		tiptype:2,
		callback:function(){
			mbOpenCtlAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbOpenCtlAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_OPEN_CTL";
    keyFieldsJson.CLIENT_TYPE=$("#clientType").val();
    keyFieldsJson.CTRL_ATTR=$("#ctrlAttr").val();
    generalFieldsJson.CTRL_VALUE=$("#ctrlValue").val();
    generalFieldsJson.DEAL_FLOW=$("#dealFlow").val();
    generalFieldsJson.QUANTITY=$("#quantity").val();
    generalFieldsJson.COMPANY=$("#company").val();
    //generalFieldsJson.TRAN_TIME=$("#tranTime").val();
    //generalFieldsJson.TRAN_TIMESTAMP=$("#tranTimestamp").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbOpenCtlAdd,"json");
}

function callback_mbOpenCtlAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbOpenCtlAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}