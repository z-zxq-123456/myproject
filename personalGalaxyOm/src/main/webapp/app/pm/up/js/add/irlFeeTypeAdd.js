
$(document).ready(function() {
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_FEE_TYPE&tableCol=FEE_TYPE,FEE_DESC",
				id: "feeType",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_TYPE&tableCol=INT_TAX_TYPE,INT_TAX_TYPE_DESC",
				id: "taxType",
				async: false
			});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=IRL_FEE_ITEM&tableCol=FEE_ITEM,FEE_ITEM_DESC",
		id: "feeItem",
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
		$('#prodGrp').append("<option value='ALL'>ALL-全部</option>");

			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
				id: "company",
				async: false
			});

	$("#irlFeeTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlFeeTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function irlFeeTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_FEE_TYPE";
		keyFieldsJson.FEE_TYPE=$("#feeType").val();
		generalFieldsJson.FEE_MODE=$("#feeMode").val();
		generalFieldsJson.FEE_DESC=$("#feeDesc").val();
		generalFieldsJson.PROD_GRP=$("#prodGrp").val();
		generalFieldsJson.DIS_TYPE=$("#disType").val();
		generalFieldsJson.CCY_FLAG=$("#ccyFlag").val();
		generalFieldsJson.BO_IND=$("#boInd").val();
		generalFieldsJson.MB_CCY_TYPE=$("#mbCcyType").val();
		generalFieldsJson.FEE_AMT_ID=$("#feeAmtId").val();
		generalFieldsJson.CONVERT_FLAG=$("#convertFlag").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.BOUNDARY_DESC=$("#boundaryDesc").val();
		generalFieldsJson.BOUNDARY_AMT_ID=$("#boundaryAmtId").val();
		generalFieldsJson.TAX_TYPE=$("#taxType").val();
		generalFieldsJson.FEE_ITEM=$("#feeItem").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlFeeTypeAdd,"json");
}

function callback_irlFeeTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlFeeTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

