var form;
$(document).ready(function() {
         if(parent.parent.$("#operateType").val() === "add" || parent.parent.$("#operateType").val() === "copy"){
            $("#prodType").val(parent.parent.$("#newProdType").val()).attr("readonly", true);
         }else if(parent.parent.$("#operateType").val() === "update"){
            $("#prodType").val(parent.parent.$("#prodType").val()).attr("readonly", true);
         }
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
						url: contextPath + "/pklist/getParameterPklist?tableName=FM_CHANNEL&tableCol=CHANNEL,CHANNEL_DESC",
                                   id: "sourceTypeRule",
                                   async: false
                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=MB_TRAN_DEF&tableCol=TRAN_TYPE,TRAN_TYPE_DESC",
                                   id: "tranTypeRule",
                                   async: false
                  });
          $(".select2").select2();
	form = $("#irlFeeMappingAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlFeeMappingAdd();
			return false;
		}
	});
});

function irlFeeMappingAdd(){
	var url = contextPath+"/irlFeeMapping/insert?reqNum="+parent.parent.$(".breadcrumb").data("reqNum");
	sendPostRequest(url,getFormData("irlFeeMappingAdd"), callback_irlFeeMappingAdd,"json");
}
function callback_irlFeeMappingAdd(json){
	if (json.retStatus === 'F'){
		showMsg(json.retMsg,'info');
	} else if(json.retStatus === 'S'){
	var dataTable=parent.$('#irlFeeMapping').DataTable();
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
    			prodGrpRule:$("#prodGrpRule").val(),
    			serviceIdRule:$("#serviceIdRule").val(),
    			sourceTypeRule:$("#sourceTypeRule").val(),
    			tranTypeRule:$("#tranTypeRule").val(),
    			urgentFlagRule:$("#urgentFlagRule").val()
    		}).draw(false);
    		parent.showMsgDuringTime("添加成功");
	}
	//form.resetForm();  JS表单清空
}

function irlFeeMappingAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

