
var rowData;
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

    if (parent.$("#irlFeeMapping").find(".selected").length===1){
        rowData = parent.$('#irlFeeMapping').DataTable().rows(".selected").data()[0];
            $("#irlSeqNo").val(rowData.IRL_SEQ_NO).attr("disabled",true);
            $("#urgentFlag").val(rowData.URGENT_FLAG_RULE);
            $("#categoryTypeRule").val(rowData.CATEGORY_TYPE_RULE);
            $("#ccyRule").val(rowData.CCY_RULE);
            $("#clientTypeRule").val(rowData.CLIENT_TYPE_RULE);
            $("#company").val(rowData.COMPANY_RULE);
            $("#docTypeRule").val(rowData.DOC_TYPE_RULE);
            $("#eventTypeRule").val(rowData.EVENT_TYPE_RULE);
            $("#feeType").val(rowData.FEE_TYPE);
            $("#isLocal").val(rowData.IS_LOCAL_RULE);
            $("#isRule").val(rowData.IS_RULE);
            $("#newStatusRule").val(rowData.NEW_STATUS_RULE);
            $("#oldStatusRule").val(rowData.OLD_STATUS_RULE);
            $("#prodGrpRule").val(rowData.PROD_GROUP_RULE);
            $("#prodTypeRule").val(rowData.PROD_TYPE_RULE);
            $("#areaRule").val(rowData.AREA_RULE);
            $("#branchRule").val(rowData.BRANCH_RULE);
            $("#sourceTypeRule").val(rowData.SOURCE_TYPE_RULE);
            $("#tranTypeRule").val(rowData.TRAN_TYPE_RULE);
            $("#serviceIdRule").val(rowData.SERVICE_ID_RULE);
    }

    $("#irlFeeMappingMod").Validform({
        tiptype:2,
        callback:function(form){
            irlFeeMappingMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlFeeMappingMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
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
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlFeeMappingMod,"json");
}

function callback_irlFeeMappingMod(json){
    if (json.success) {
		var dataTable=parent.$("#irlFeeMapping").DataTable();
		dataTable.row(".selected").remove().draw(false);
		dataTable.row.add({
			IRL_SEQ_NO:$("irlSeqNo").val(),
			URGENT_FLAG:$("urgentFlag").val(),
			CATEGORY_TYPE_RULE:$("clientTypeRule").val(),
			CCY_RULE:$("ccyRule").val(),
			CLIENT_TYPE_RULE:$("clientTypeRule").val(),
			DOC_TYPE_RULE:$("docTypeRule").val(),
			EVENT_TYPE_RULE:$("eventTypeRule").val(),
			FEE_TYPE:$("feeType").val(),
			IS_LOCAL:$("isLocal").val(),
			IS_RULE:$("isRule").val(),
			NEW_STATUS_RULE:$("newStatusRule").val(),
			OLD_STATUS_RULE:$("oldStatusRule").val(),
			PROD_GROUP_RULE:$("prodGrpRule").val(),
			PROD_TYPE_RULE:$("prodTypeRule").val(),
			AREA_RULE:$("areaRule").val(),
			BRANCH_RULE:$("branchRule").val(),
			SOURCE_TYPE_RULE:$("sourceTypeRule").val(),
			TRAN_TYPE_RULE:$("tranTypeRule").val(),
			SERVICE_ID_RULE:$("serviceIdRule").val(),
			COLUMN_STATUS:'W'
		}).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlFeeMappingModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

