
$(document).ready(function() {
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_PROD_TYPE&tableCol=PROD_TYPE,PROD_TYPE_DESC",
				id: "prodType",
				async: false
			});
				  var paraJson, keyFieldsJson;
            				  paraJson = {};
            				  keyFieldsJson = {};
            				  paraJson.tableName = "FM_REF_CODE";
            				  paraJson.tableCol="FIELD_VALUE,MEANING";

            				  keyFieldsJson.DOMAIN = "PROD_GRP";
            				  paraJson.key = keyFieldsJson;
            		   getPkList({
            							url:contextPath+"/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
            									   id: "prodGrp",
            									   async: false

            					  });
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
				id: "company",
				async: false
			});

	$("#irlProdTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlProdTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function irlProdTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_PROD_TYPE";
	paraJson.systemId="UP";
		keyFieldsJson.PROD_TYPE=$("#prodType").val();
		generalFieldsJson.PROD_GRP=$("#prodGrp").val();
		generalFieldsJson.PROD_TYPE_DESC=$("#prodTypeDesc").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.FIXED_CALL=$("#fixedCall").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlProdTypeAdd,"json");
}

function callback_irlProdTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlProdTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


