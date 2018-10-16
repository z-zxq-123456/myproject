var form;
$(document).ready(function() {
  getPkList({
       url:contextPath+"/pklist/getSystem",
       id:"systemId",
       async:false,
       normalSelect:false
    });
	var rowData;
	if (parent.$("#paraEnvList").find(".selected").length==1){
		rowData = parent.$('#paraEnvList').DataTable().rows(".selected").data()[0];
	}
	if (rowData){
		$("#envId").val(rowData.envId);
		$("#systemId").val(rowData.systemId);
		$("#envDesc").val(rowData.envDesc);
		$("#url").val(rowData.url);
		$("#module").val(rowData.module);
		$("#messageCode").val(rowData.messageCode);
		$("#messageType").val(rowData.messageType);
		$("#serviceCode").val(rowData.serviceCode);
		form = $("#form-env-edit").Validform({
			tiptype:2,
			callback:function(form){
				envEdit();
				return false;
			}
		});
	}
	$(".select2").select2();
});

function envEdit(){
	var url = contextPath+"/paraEnv/updateEnv";
	sendPostRequest(url,{
	    envId:$("#envId").val(),
		systemId:$("#systemId").val(),
		envDesc:$("#envDesc").val(),
		url:$("#url").val(),
		module:$("#module").val(),
		serviceCode:$("#serviceCode").val(),
		messageType:$("#messageType").val(),
		messageCode:$("#messageCode").val()
	}, callback_envEdit,"json");
}

function callback_envEdit(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
	    var dataTable=parent.$("#paraEnvList").DataTable();
		dataTable.row(".selected").remove().draw(false);
		dataTable.row.add({
			envId:$("#envId").val(),
			systemId:$("#systemId").val(),
			envDesc:$("#envDesc").val(),
			url:$("#url").val(),
			module:$("#module").val(),
			serviceCode:$("#serviceCode").val(),
			messageType:$("#messageType").val(),
			messageCode:$("#messageCode").val()
		}).draw(false);
		parent.showMsgDuringTime("编辑成功");
	}
}
