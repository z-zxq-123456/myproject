var form;
$(document).ready(function() {
    getPkList({
                       url: contextPath + "/pklist/getParameterPklist?tableName=mb_part_class&tableCol=PART_CLASS,PART_CLASS_DESC",
                                      id: "parentPartClass",
                                      async: false,
                     });
    getPkList({  url: contextPath + "/pklist/getBmodule",
                 id: "Bmodule",
                 async: false,
                     });
 	$('#partClassLevel').change(function(){
 	    $("#parentPartClass").attr("disabled",false);
 	    $("#parentPartClass").select2();
 		var value=$('#partClassLevel').val();
 		 getPkList({  url: contextPath + "/pklist/getparentPartClass?partClassLevel="+value,
                          id: "parentPartClass",
                          async: false,
                             });
 	});

	var rowData;
	if (parent.$("#partList").find(".selected").length==1){
		rowData = parent.$('#partList').DataTable().rows(".selected").data()[0];
	}
	if (rowData){
	   if(rowData.parentPartClass == "0" || rowData.parentPartClass == ""){
	   	   $("#Bmodule").val(rowData.partClass);
	   }else{
	   	   $("#Bmodule").val(rowData.parentPartClass);
	   }
		$("#partClass").val(rowData.partClass);
		$("#partClassDesc").val(rowData.partClassDesc);
		$("#partClassLevel").val(rowData.partClassLevel);
		$("#parentPartClass").val(rowData.parentPartClass);
		if(rowData.partClassLevel =="1"){
		 $("#parentPartClass").attr("disabled",true);
		}
		form = $("#form-part-edit").Validform({
			tiptype:2,
			callback:function(form){
				partEdit();
				return false;
			}
		});
	}
	$(".select2").select2();
});

function partEdit(){
	var rowData = parent.$('#partList').DataTable().rows(".selected").data()[0];
	var url = contextPath+"/part/update";
	sendPostRequest(url,{
		partClass:$("#partClass").val()+","+rowData.partClass,
		partClassDesc:$("#partClassDesc").val(),
		partClassLevel:$("#partClassLevel").val(),
		parentPartClass:$("#parentPartClass").val(),
	}, callback_partEdit,"json");
}

function callback_partEdit(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
		showMsg(json.retMsg,'info');
	}
	partEditCancel();

}
function partEditCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
