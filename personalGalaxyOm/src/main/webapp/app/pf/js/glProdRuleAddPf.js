var form;
$(document).ready(function() {
         if(parent.parent.$("#operateType").val() == "add" || parent.parent.$("#operateType").val() == "copy"){
            $("#prodType").val(parent.parent.$("#newProdType").val()).attr("readonly", true);
         }else if(parent.parent.$("#operateType").val() == "update"){
            $("#prodType").val(parent.parent.$("#prodType").val()).attr("readonly", true);
         }
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=GL_EVENT_MAPPING&tableCol=EVENT_TYPE,EVENT_TYPE_DESC",
                                   id: "tranEventType",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
                                   id: "ccy",
                                   async: false

                  });
            $(".select2").select2();
	form = $("#glProdRuleAdd").Validform({
		tiptype:2,
		callback:function(form){
			glProdRuleAdd();
			return false;
		}
	});
});

function glProdRuleAdd(){
	var url = contextPath+"/glProdRule/insert1";
	sendPostRequest(url,getFormData("glProdRuleAdd"), callback_glProdRuleAdd,"json");
}
function callback_glProdRuleAdd(json){
	if (json.retStatus == 'F'){
		showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
		parent.showMsgDuringTime("添加成功");
	}
	//form.resetForm();  JS表单清空
}
function glProdRuleAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
