var form;
$(document).ready(function() {
	var rowData;
        getPkList({
                           url: contextPath + "/pklist/getParameterPklist?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
                                          id: "branchRule",
                                          async: false

                         });
        getPkList({
                           url: contextPath + "/pklist/getParameterPklist?tableName=CIF_CATEGORY_TYPE&tableCol=CATEGORY_TYPE,CATEGORY_DESC",
                                          id: "categoryTypeRule",
                                          async: false

                         });
        getPkList({
                           url: contextPath + "/pklist/getParameterPklist?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
                                          id: "ccyRule",
                                          async: false

                         });
        getPkList({
                           url: contextPath + "/pklist/getParameterPklist?tableName=CIF_CLIENT_TYPE&tableCol=CLIENT_TYPE,CLIENT_TYPE_DESC",
                                          id: "clientTypeRule",
                                          async: false

                         });
        getPkList({
                           url: contextPath + "/pklist/getParameterPklist?tableName=IRL_PROD_TYPE&tableCol=PROD_TYPE,PROD_TYPE_DESC",
                                          id: "prodTypeRule",
                                          async: false

                         });
        getPkList({
                           url: contextPath + "/pklist/getParameterPklist?tableName=FM_CHANNEL&tableCol=CHANNEL,CHANNEL_DESC",
                                          id: "sourceTypeRule",
                                          async: false

                         });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=MB_TRAN_DEF&tableCol=TRAN_TYPE,TRAN_TYPE_DESC",
                                   id: "tranTypeRule",
                                   async: false
                  });

	if (parent.$("#irlFeeMapping").find(".selected").length===1){
		rowData = parent.$('#irlFeeMapping').DataTable().rows(".selected").data()[0];
	}
	if (rowData){
        $("#irlSeqNo").val(rowData.irlSeqNo).attr("disabled",true);

		$("#areaRule").val(rowData.areaRule);


		$("#branchRule").val(rowData.branchRule);


		$("#categoryTypeRule").val(rowData.categoryTypeRule);


		$("#ccyRule").val(rowData.ccyRule);


		$("#clientTypeRule").val(rowData.clientTypeRule);


		$("#companyRule").val(rowData.companyRule);


		$("#docTypeRule").val(rowData.docTypeRule);


		$("#eventTypeRule").val(rowData.eventTypeRule);


		$("#feeType").val(rowData.feeType);


		$("#isLocalRule").val(rowData.isLocalRule);


		$("#isRule").val(rowData.isRule);


		$("#newStatusRule").val(rowData.newStatusRule);


		$("#oldStatusRule").val(rowData.oldStatusRule);


		$("#prodGroupRule").val(rowData.prodGroupRule);

       if(parent.parent.$("#operateType").val()==="copy"){
        $("#prodTypeRule").val(parent.parent.$("#newProdType").val()).attr("disabled", true);
        }else{
		$("#prodTypeRule").val(rowData.prodTypeRule);
        }

		$("#serviceIdRule").val(rowData.serviceIdRule);


		$("#sourceTypeRule").val(rowData.sourceTypeRule);


		$("#tranTypeRule").val(rowData.tranTypeRule);


		$("#urgentFlagRule").val(rowData.urgentFlagRule);

			}
			 	$(".select2").select2();
		form = $("#irlFeeMappingMod").Validform({
			tiptype:2,
			callback:function(form){
				irlFeeMappingMod();
				return false;
			}
		});

});
    function irlFeeMappingMod(){
        var url = contextPath+"/irlFeeMapping/update";
        sendPostRequest(url,{
                  	        irlSeqNo:$("#irlSeqNo").val(),
                         	areaRule:$("#areaRule").val(),
                         	branchRule:$("#branchRule").val(),
                         	categoryTypeRule:$("#categoryTypeRule").val(),
                         	ccyRule:$("#ccyRule").val(),
                         	clientTypeRule:$("#clientTypeRule").val(),
                         	companyRule:$("#companyRule").val(),
                         	docTypeRule:$("#docTypeRule").val(),
                         	eventTypeRule:$("#eventTypeRule").val(),
                         	feeType:$("#feeType").val(),
                         	isLocalRule:$("#isLocalRule").val(),
                         	isRule:$("#isRule").val(),
                         	newStatusRule:$("#newStatusRule").val(),
                         	oldStatusRule:$("#oldStatusRule").val(),
                         	prodGroupRule:$("#prodGroupRule").val(),
                         	prodTypeRule:$("#prodTypeRule").val(),
                         	serviceIdRule:$("#serviceIdRule").val(),
                         	sourceTypeRule:$("#sourceTypeRule").val(),
                         	tranTypeRule:$("#tranTypeRule").val(),
                         	urgentFlagRule:$("#urgentFlagRule").val(),
                         	reqNum:parent.parent.$(".breadcrumb").data("reqNum")
        }, callback_irlFeeMappingMod,"json");
    }
function callback_irlFeeMappingMod(json){
	if (json.retStatus === 'F'){
		showMsg(json.retMsg,'info');
	} else if(json.retStatus === 'S'){
		var dataTable=parent.$("#irlFeeMapping").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
              	prodTypeRule:$("#prodTypeRule").val(),
                  			areaRule:$("#areaRule").val(),
                  			branchRule:$("#branchRule").val(),
                  			categoryTypeRule:$("#categoryTypeRule").val(),
                  			ccyRule:$("#ccyRule").val(),
                  			clientTypeRule:$("#clientTypeRule").val(),
                  			companyRule:$("#companyRule").val(),
                  			docTypeRule:$("#docTypeRule").val(),
                  			eventTypeRule:$("#eventTypeRule").val(),
                  			irlSeqNo:$("#irlSeqNo").val(),
                  			feeType:$("#feeType").val(),
                  			isLocalRule:$("#isLocalRule").val(),
                  			isRule:$("#isRule").val(),
                  			newStatusRule:$("#newStatusRule").val(),
                  			oldStatusRule:$("#oldStatusRule").val(),
                  			prodGroupRule:$("#prodGroupRule").val(),
                  			serviceIdRule:$("#serviceIdRule").val(),
                  			sourceTypeRule:$("#sourceTypeRule").val(),
                  			tranTypeRule:$("#tranTypeRule").val(),
                  			urgentFlagRule:$("#urgentFlagRule").val()
    		}).draw(false);
		parent.showMsgDuringTime("修改成功");
	}
}

function irlFeeMappingModCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
