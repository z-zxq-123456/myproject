   var form;
$(document).ready(function() {
         if(parent.parent.$("#operateType").val() == "add" || parent.parent.$("#operateType").val() == "copy"){
            $("#prodType").val(parent.parent.$("#newProdType").val()).attr("readonly", true);
         }else if(parent.parent.$("#operateType").val() == "update"){
            $("#prodType").val(parent.parent.$("#prodType").val()).attr("readonly", true);
         }
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeA",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeL",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeIntE",
                                   async: false

                  });
        getPkList({
            url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
            id: "glCodeALoss",
            async: false
        });
        getPkList({
            url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
            id: "glCodeAdjust",
            async: false
        });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeIntPay",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeIntI",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeIntRec",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeIntAcr",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeOdpI",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeOdpRec",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeOdpAcr",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeOdiI",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeOdiRec",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeOdiAcr",
                                   async: false

                  });
             $(".select2").select2();
	form = $("#glProdAccountingAdd").Validform({
		tiptype:2,
		callback:function(form){
			glProdAccountingAdd();
			return false;
		}
	});
});

function glProdAccountingAdd(){
	var url = contextPath+"/glProdAccounting/insert1";
	sendPostRequest(url,getFormData("glProdAccountingAdd"), callback_glProdAccountingAdd,"json");
}
function callback_glProdAccountingAdd(json){
	if (json.retStatus == 'F'){
		showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
		parent.showMsgDuringTime("添加成功");
	}
	//form.resetForm();  JS表单清空
}

function glProdAccountingAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
