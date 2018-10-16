var form;
$(document).ready(function() {
	var rowData;

	if (parent.$("#glProdMapping").find(".selected").length==1){
		rowData = parent.$('#glProdMapping').DataTable().rows(".selected").data()[0];
	}
	if (rowData){
               $("#mappingType").val(rowData.mappingType).attr("disabled",true);

		$("#prodType").val(rowData.prodType).attr("disabled",true);


		$("#mappingDesc").val(rowData.mappingDesc);


		$("#prodDesc").val(rowData.prodDesc);

			}
		$(".select2").select2();
		form = $("#glProdMappingMod").Validform({
			tiptype:2,
			callback:function(form){
				glProdMappingMod();
				return false;
			}
		});

});

function glProdMappingMod(){
	var url = contextPath+"/glProdMapping/update1";
   sendPostRequest(url,{    prodType:$("#prodType").val(),
							mappingType:$("#mappingType").val(),
							mappingDesc:$("#mappingDesc").val(),
							prodDesc:$("#prodDesc").val()
            }, callback_glProdMappingMod,"json");
}
function callback_glProdMappingMod(json){
	if (json.retStatus == 'F'){
		showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
		parent.showMsgDuringTime("修改成功");
	}
}
function glProdMappingModCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}