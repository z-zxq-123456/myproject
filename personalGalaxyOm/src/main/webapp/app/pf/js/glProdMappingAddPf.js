var form;
$(document).ready(function() {
         if(parent.parent.$("#operateType").val() == "add" || parent.parent.$("#operateType").val() == "copy"){
            $("#prodType").val(parent.parent.$("#newProdType").val()).attr("readonly", true);
         }else if(parent.parent.$("#operateType").val() == "update"){
            $("#prodType").val(parent.parent.$("#prodType").val()).attr("readonly", true);
         }
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=GL_PROD_MAPPING&tableCol=MAPPING_TYPE,MAPPING_DESC",
                                   id: "mappingType",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=GL_PROD_MAPPING&tableCol=MAPPING_TYPE,MAPPING_DESC",
                                   id: "prodType",
                                   async: false

                  });
         $(".select2").select2();
	form = $("#glProdMappingAdd").Validform({
		tiptype:2,
		callback:function(form){
			glProdMappingAdd();
			return false;
		}
	});
});


function glProdMappingAdd(){
	var url = contextPath+"/glProdMapping/insert1";
	sendPostRequest(url,getFormData("glProdMappingAdd"), callback_glProdMappingAdd,"json");
}
function callback_glProdMappingAdd(json){
	if (json.retStatus == 'F'){
		showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
		parent.showMsgDuringTime("添加成功");
	}
	//form.resetForm();  JS表单清空
}

function glProdMappingAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
