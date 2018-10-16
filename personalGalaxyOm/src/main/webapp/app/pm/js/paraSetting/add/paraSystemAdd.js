var form;
$(document).ready(function() {
	form = $("#form-system-add").Validform({
		tiptype:2,
		callback:function(form){
			systemAdd();
			return false;
		}
	});
});

function systemAdd(){
	var url = contextPath+"/paraSystem/saveSystem";
	sendPostRequest(url,{
		systemId:$("#systemId").val(),
		systemName:$("#systemName").val(),
		systemDesc:$("#systemDesc").val()
	}, callback_systemAdd,"json");
}

function callback_systemAdd(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
		var dataTable=parent.$("#paraSystemList").DataTable();
		dataTable.row.add({
			systemId:$("#systemId").val(),
			systemName:$("#systemName").val(),
			systemDesc:$("#systemDesc").val()
		}).draw(false);
		parent.showMsgDuringTime("添加成功");
	}
	//form.resetForm();  //JS表单清空
}
function systemAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
