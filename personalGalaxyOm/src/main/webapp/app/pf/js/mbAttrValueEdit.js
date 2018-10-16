var form;
$(document).ready(function() {
	var rowData;
	if (parent.$("#attrList").find(".selected").length==1){
		rowData = parent.$('#attrList').DataTable().rows(".selected").data()[0];
	}
	if (rowData){
		$("#attrKey").val(rowData.attrKey);
		$("#attrDesc").val(rowData.attrDesc);
		$("#attrValue").val(rowData.attrValue);
		$("#valueDesc").val(rowData.valueDesc);
		$("#refTable").val(rowData.refTable);
		$("#refCondition").val(rowData.refCondition);
		$("#refColumns").val(rowData.refColumns);
                    var url = contextPath + "/attrType/getattrKey";
                     $.ajax({
                         url: url,
                         data: "attrKey=" + rowData.attrKey,
                         success: function(json) {
                                   $("#valueMethod").val(json.data.valueMethod);
                                   $("#valueMethod").select2();
                                 	if(json.data.valueMethod=="FD"){
                                 	  $("#attrValue").attr("disabled",true);
                                 	  $("#valueDesc").attr("disabled",true);
                                 	  $("#refTable").attr("disabled",true);
                                 	  $("#refCondition").attr("disabled",true);
                                 	  $("#refColumns").attr("disabled",true);
                                         alert("请在产品定义时输入参数值！")
                                     }else if(json.data.valueMethod=="VL"){
                                       $("#attrValue").attr("datatype","*1-40");
                                       $("#valueDesc").attr("datatype","*1-40");
                                       $("#attrValue").attr("disabled",true);
                                 	  $("#refTable").attr("disabled",true);
                                 	  $("#refCondition").attr("disabled",true);
                                 	  $("#refColumns").attr("disabled",true);
                                     }else if(json.data.valueMethod=="RF"){
                                 	  $("#attrValue").attr("disabled",true);
                                 	  $("#valueDesc").attr("disabled",true);
                                 	  $("#attrValue").val(" ");
                                 	  $("#valueDesc").val(" ");
                                 	  $("#refTable").attr("datatype","*1-40");
                                 	  $("#refCondition").attr("datatype","*1-40");
                                 	  $("#refColumns").attr("datatype","*1-40");
                                     }else if(json.data.valueMethod=="YN"){
                                 	  $("#attrValue").attr("disabled",true);
                                 	  $("#valueDesc").attr("disabled",true);
                                 	  $("#refTable").attr("disabled",true);
                                 	  $("#refCondition").attr("disabled",true);
                                 	  $("#refColumns").attr("disabled",true);
                                       alert("请在产品定义时输入参数值！")
                                     }
                                    return;
                         },
                         dataType: "json",
                     });

		form = $("#form-attr-edit").Validform({
			tiptype:2,
			callback:function(form){
				attrEdit();
				return false;
			}
		});
	}
      $(".select2").select2();

});

function attrEdit(){
	var rowData = parent.$('#attrList').DataTable().rows(".selected").data()[0];
	var url = contextPath+"/attrValue/update";
	sendPostRequest(url,{
		attrKey:$("#attrKey").val(),
		attrValue:$("#attrValue").val(),
		valueDesc:$("#valueDesc").val(),
		refTable:$("#refTable").val(),
		refCondition:$("#refCondition").val(),
		refColumns:$("#refColumns").val()
	}, callback_attrEdit,"json");
}

function callback_attrEdit(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
		showMsg(json.retMsg,'info');
	}
	attrEditCancel();

}
function attrEditCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

