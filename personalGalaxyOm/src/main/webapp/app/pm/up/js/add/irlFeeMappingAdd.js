$(document).ready(function() {
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CATEGORY_TYPE&tableCol=CATEGORY_TYPE,CATEGORY_DESC",
				id: "categoryTypeRule",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
				id: "ccyRule",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
				id: "branchRule",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CLIENT_TYPE&tableCol=CLIENT_TYPE,CLIENT_TYPE_DESC",
				id: "clientTypeRule",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
				id: "company",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_FEE_TYPE&tableCol=FEE_TYPE,FEE_DESC",
				id: "feeType",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=TB_VOUCHER_DEF&tableCol=DOC_TYPE,DOC_TYPE_DESC",
				id: "docTypeRule",
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
            									   id: "prodGrpRule",
            									   async: false

            					  });
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_PROD_TYPE&tableCol=PROD_TYPE,PROD_TYPE_DESC",
				id: "prodTypeRule",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
				id: "branchRule",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=MB_TRAN_DEF&tableCol=TRAN_TYPE,TRAN_TYPE_DESC",
				id: "tranTypeRule",
				async: false
			});

					  var paraJson, keyFieldsJson;
            				  paraJson = {};
            				  keyFieldsJson = {};
            				  paraJson.tableName = "FM_REF_CODE";
            				  paraJson.tableCol="FIELD_VALUE,MEANING";

            				  keyFieldsJson.DOMAIN = "EVENT_TYPE";
            				  paraJson.key = keyFieldsJson;

            		   getPkList({
            							url:contextPath+"/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
            									   id: "eventTypeRule",
            									   async: false

            					  });

							  keyFieldsJson.DOMAIN = "TB_VOUCHER_INFO.STATUS";
							  paraJson.key = keyFieldsJson;

					   getPkList({
										url:contextPath+"/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
												   id: "newStatusRule",
												   async: false

								  });
					   getPkList({
										url:contextPath+"/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
												   id: "oldStatusRule",
												   async: false

								  });
						keyFieldsJson.DOMAIN = "SOURCE_TYPE";
						paraJson.key = keyFieldsJson;

						getPkList({
							url:contextPath+"/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
							id: "sourceTypeRule",
							async: false

						});

			$('#categoryTypeRule').append("<option value='ALL'>ALL-全部</option>");
			$('#ccyRule').append("<option value='ALL'>ALL-全部</option>");
			$('#branchRule').append("<option value='ALL'>ALL-全部</option>");
			$('#clientTypeRule').append("<option value='ALL'>ALL-全部</option>");
			$('#docTypeRule').append("<option value='ALL'>ALL-全部</option>");
			$('#prodGrpRule').append("<option value='ALL'>ALL-全部</option>");
			$('#prodTypeRule').append("<option value='ALL'>ALL-全部</option>");
			$('#tranTypeRule').append("<option value='ALL'>ALL-全部</option>");
			$('#eventTypeRule').append("<option value='ALL'>ALL-全部</option>");
			$('#newStatusRule').append("<option value='ALL'>ALL-全部</option>");
			$('#oldStatusRule').append("<option value='ALL'>ALL-全部</option>");
			$('#sourceTypeRule').append("<option value='ALL'>ALL-全部</option>");




	$("#irlFeeMappingAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlFeeMappingAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function irlFeeMappingAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_FEE_MAPPING";
	paraJson.systemId="UP";
		keyFieldsJson.IRL_SEQ_NO=$("#irlSeqNo").val();
		generalFieldsJson.URGENT_FLAG_RULE=$("#urgentFlag").val();
		generalFieldsJson.CATEGORY_TYPE_RULE=$("#categoryTypeRule").val();
		generalFieldsJson.CCY_RULE=$("#ccyRule").val();
		generalFieldsJson.CLIENT_TYPE_RULE=$("#clientTypeRule").val();

		generalFieldsJson.DOC_TYPE_RULE=$("#docTypeRule").val();
		generalFieldsJson.EVENT_TYPE_RULE=$("#eventTypeRule").val();
		generalFieldsJson.FEE_TYPE=$("#feeType").val();
		generalFieldsJson.IS_LOCAL_RULE=$("#isLocal").val();
		generalFieldsJson.IS_RULE=$("#isRule").val();
		generalFieldsJson.NEW_STATUS_RULE=$("#newStatusRule").val();
		generalFieldsJson.OLD_STATUS_RULE=$("#oldStatusRule").val();
		generalFieldsJson.PROD_GROUP_RULE=$("#prodGrpRule").val();
		generalFieldsJson.PROD_TYPE_RULE=$("#prodTypeRule").val();
		generalFieldsJson.AREA_RULE=$("#areaRule").val();
		generalFieldsJson.BRANCH_RULE=$("#branchRule").val();
		generalFieldsJson.SOURCE_TYPE_RULE=$("#sourceTypeRule").val();
		generalFieldsJson.TRAN_TYPE_RULE=$("#tranTypeRule").val();
		generalFieldsJson.SERVICE_ID_RULE=$("#serviceIdRule").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlFeeMappingAdd,"json");
}

function callback_irlFeeMappingAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlFeeMappingAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

