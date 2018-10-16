var form;
$(document).ready(function() {
    getPkList({
                       url: contextPath + "/pklist/getBmodule",
                                      id: "parentEventClass",
                                      async: false,
                     });
    getPkList({  url: contextPath + "/pklist/getBmodule",
                 id: "Bmodule",
                 async: false,
                     });
 	$('#eventClassLevel').change(function(){
		$("#parentEventClass").attr("disabled",false);
		$("#parentEventClass").select2();
 		var value=$('#eventClassLevel').val();
 		 getPkList({  url: contextPath + "/pklist/getparentEventClass?eventClassLevel="+value,
                          id: "parentEventClass",
                          async: false,
                             });
 	});

	var rowData;
	if (parent.$("#eventList").find(".selected").length==1){
		rowData = parent.$('#eventList').DataTable().rows(".selected").data()[0];
	}
	if (rowData){
	   if(rowData.parentEventClass == "0" || rowData.parentEventClass == ""){
	   	   $("#Bmodule").val(rowData.eventClass);
	   }else{
	   	   $("#Bmodule").val(rowData.parentEventClass);
	   }
		$("#eventClass").val(rowData.eventClass);
		$("#eventClassDesc").val(rowData.eventClassDesc);
		$("#eventClassLevel").val(rowData.eventClassLevel);
		$("#parentEventClass").val(rowData.parentEventClass.split(","));
		if(rowData.eventClassLevel =="1"){
		 $("#parentEventClass").attr("disabled",true);
		}
		form = $("#form-event-edit").Validform({
			tiptype:2,
			callback:function(form){
				eventEdit();
				return false;
			}
		});
	}
	$(".select2").select2();
});

function eventEdit(){
	var rowData = parent.$('#eventList').DataTable().rows(".selected").data()[0];
	var url = contextPath+"/event/update";
		var str = "";
    	if($("#parentEventClass").val() == null){
    	  str="0";
    	}else{
    	str=$("#parentEventClass").val().join(",");
    	}
	sendPostRequest(url,{
		eventClass:$("#eventClass").val()+","+rowData.eventClass,
		eventClassDesc:$("#eventClassDesc").val(),
		eventClassLevel:$("#eventClassLevel").val(),
		parentEventClass:str
	}, callback_eventEdit,"json");
}

function callback_eventEdit(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
		showMsg(json.retMsg,'info');
	}
	eventEditCancel();

}
function eventEditCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}