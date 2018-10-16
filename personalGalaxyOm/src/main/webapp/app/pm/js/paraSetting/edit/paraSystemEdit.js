var form;
$(document).ready(function() {
	var rowData;
	if (parent.$("#paraSystemList").find(".selected").length==1){
		rowData = parent.$('#paraSystemList').DataTable().rows(".selected").data()[0];
	}
	if (rowData){
		$("#systemId").val(rowData.systemId);
		$("#systemName").val(rowData.systemName);
		$("#systemDesc").val(rowData.systemDesc);
		form = $("#form-system-edit").Validform({
			tiptype:2,
			callback:function(form){
				systemEdit();
				return false;
			}
		});
	}
});

function systemEdit(){
	var url = contextPath+"/paraSystem/updateSystem";
	sendPostRequest(url,{
		systemId:$("#systemId").val(),
		systemName:$("#systemName").val(),
		systemDesc:$("#systemDesc").val()
	}, callback_systemEdit,"json");
}

function callback_systemEdit(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
	    var dataTable=parent.$("#paraSystemList").DataTable();
		dataTable.row(".selected").remove().draw(false);
		dataTable.row.add({
			systemId:$("#systemId").val(),
			systemName:$("#systemName").val(),
			systemDesc:$("#systemDesc").val()
		}).draw(false);
		parent.showMsgDuringTime("编辑成功");
	}
}
function systemEditCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
