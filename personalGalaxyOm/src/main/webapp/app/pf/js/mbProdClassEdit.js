var form;
$(document).ready(function() {
    getPkList({
                       url: contextPath + "/pklist/getParameterPklist?tableName=mb_prod_class&tableCol=PROD_CLASS,PROD_CLASS_DESC",
                                      id: "parentProdClass",
                                      async: false,
                     });
    getPkList({  url: contextPath + "/pklist/getBmodule",
                 id: "Bmodule",
                 async: false,
                     });
 	$('#prodClassLevel').change(function(){
 	    $("#parentPartClass").attr("disabled",false);
 	    $("#parentPartClass").select2();
 		var value=$('#prodClassLevel').val();
 		 getPkList({  url: contextPath + "/pklist/getparentProdClass?prodClassLevel="+value,
                          id: "parentProdClass",
                          async: false,
                             });
 	});
	var rowData;
	if (parent.$("#prodList").find(".selected").length==1){
		rowData = parent.$('#prodList').DataTable().rows(".selected").data()[0];
	}
	if (rowData){
	   if(rowData.parentProdClass == "0" || rowData.parentProdClass == ""){
	   	   $("#Bmodule").val(rowData.prodClass);
	   }else{
	   	   $("#Bmodule").val(rowData.parentProdClass);
	   }
		$("#prodClass").val(rowData.prodClass);
		$("#prodClassDesc").val(rowData.prodClassDesc);
		$("#prodClassLevel").val(rowData.prodClassLevel);
		$("#parentProdClass").val(rowData.parentProdClass);
		if(rowData.prodClassLevel =="1"){
		 $("#parentProdClass").attr("disabled",true);
		}
		form = $("#form-prod-edit").Validform({
			tiptype:2,
			callback:function(form){
				prodEdit();
				return false;
			}
		});
	}
	$(".select2").select2();
});

function prodEdit(){
	var rowData = parent.$('#prodList').DataTable().rows(".selected").data()[0];
	var url = contextPath+"/prod/update";
	sendPostRequest(url,{
		prodClass:$("#prodClass").val()+","+rowData.prodClass,
		prodClassDesc:$("#prodClassDesc").val(),
		prodClassLevel:$("#prodClassLevel").val(),
		parentProdClass:$("#parentProdClass").val(),
	}, callback_prodEdit,"json");
}

function callback_prodEdit(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
		showMsg(json.retMsg,'info');
	}
	prodEditCancel();

}
function prodEditCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}