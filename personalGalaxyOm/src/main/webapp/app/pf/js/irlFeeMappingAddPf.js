var form;
$(document).ready(function() {
         if(parent.parent.$("#operateType").val() == "add" || parent.parent.$("#operateType").val() == "copy"){
            $("#prodType").val(parent.parent.$("#newProdType").val()).attr("readonly", true);
         }else if(parent.parent.$("#operateType").val() == "update"){
            $("#prodType").val(parent.parent.$("#prodType").val()).attr("readonly", true);
         }
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
                                   id: "branch",
                                   async: false
                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=CIF_CATEGORY_TYPE&tableCol=CATEGORY_TYPE,CATEGORY_DESC",
                                   id: "categoryType",
                                   async: false
                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
                                   id: "ccy",
                                   async: false
                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=CIF_CLIENT_TYPE&tableCol=CLIENT_TYPE,CLIENT_TYPE_DESC",
                                   id: "clientType",
                                   async: false
                  });

       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=FM_CHANNEL&tableCol=CHANNEL,CHANNEL_DESC",
                                   id: "sourceType",
                                   async: false
                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=MB_TRAN_DEF&tableCol=TRAN_TYPE,TRAN_DESC",
                                   id: "tranType",
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
	var url = contextPath+"/irlFeeMapping/insert1";
	sendPostRequest(url,getFormData("irlFeeMappingAdd"), callback_irlFeeMappingAdd,"json");
}
function callback_irlFeeMappingAdd(json){
	if (json.retStatus == 'F'){
		showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
		parent.showMsgDuringTime("添加成功");
	}
	//form.resetForm();  JS表单清空
}

function irlFeeMappingAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

