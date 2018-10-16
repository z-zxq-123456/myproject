var form;
$(document).ready(function() {
  getPkList({
       url:contextPath+"/pklist/getSystem",
       id:"systemId",
       async:false,
       normalSelect:false
    });
	var rowData;
	if (parent.$("#paraModuleList").find(".selected").length==1){
		rowData = parent.$('#paraModuleList').DataTable().rows(".selected").data()[0];
	}
	if (rowData){
		$("#moduleId").val(rowData.moduleId);
		$("#systemId").val(rowData.systemId);
		$("#moduleName").val(rowData.moduleName);
		$("#moduleDesc").val(rowData.moduleDesc);
		form = $("#form-module-edit").Validform({
			tiptype:2,
			callback:function(form){
				moduleEdit();
				return false;
			}
		});
	}
	$(".select2").select2();
});

function moduleEdit(){
	var url = contextPath+"/paraModule/updateModule";
	sendPostRequest(url,{
	    moduleId:$("#moduleId").val(),
		systemId:$("#systemId").val(),
		moduleName:$("#moduleName").val(),
		moduleDesc:$("#moduleDesc").val()
	}, callback_moduleEdit,"json");
}

function callback_moduleEdit(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
	    var dataTable=parent.$("#paraModuleList").DataTable();
	    dataTable.row(".selected").remove().draw(false);
		dataTable.row.add({
			moduleId:$("#moduleId").val(),
			systemId:$("#systemId").val(),
			moduleName:$("#moduleName").val(),
			moduleDesc:$("#moduleDesc").val()
		}).draw(false);
		parent.showMsgDuringTime("编辑成功");
	}
}
