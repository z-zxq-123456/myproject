var form;
$(document).ready(function() {
     getPkList({
          url:contextPath+"/pklist/getSystem",
          id:"systemId",
          async:false
       });
	form = $("#form-env-add").Validform({
		tiptype:2,
		callback:function(form){
			envAdd();
			return false;
		}
	});
	$(".select2").select2();
});

function envAdd(){
	var url = contextPath+"/paraEnv/saveEnv";
	sendPostRequest(url,{
		envId:$("#envId").val(),
		systemId:$("#systemId").val(),
		envDesc:$("#envDesc").val(),
		url:$("#url").val(),
		module:$("#module").val(),
		serviceCode:$("#serviceCode").val(),
		messageType:$("#messageType").val(),
		messageCode:$("#messageCode").val()
	}, callback_envAdd,"json");
}

function callback_envAdd(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
	     var dataTable=parent.$("#paraEnvList").DataTable();
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
		parent.showMsgDuringTime("添加成功");
	}
}
function envAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
