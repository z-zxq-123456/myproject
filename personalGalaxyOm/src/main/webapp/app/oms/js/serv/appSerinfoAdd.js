var form;
$(document).ready(function() {
	form = $("#form-appSerinfo-add").Validform({
		tiptype:2,
		callback:function(form){
			midwareVerAdd();
			return false;
		}
	});
	  getPkList({
			  url:contextPath + "/findParaCombox",
			  id:"appSerType",
			  async:false,
			  params:{paraParentId:'0060',
					  isDefault:false }
		  });
	$(".select2").select2();
});

function midwareVerAdd(){
	var url = contextPath+"/saveEcmAppSer";
	sendPostRequest(url,{
		appSerName:$("#appSerName").val(),
		appSerClsnm:$("#appSerClsnm").val(),
		appSerType:$("#appSerType").val(),
		appSerDesc:$("#appSerDesc").val()
	}, callback_midwareVerAdd,"json");
}

function callback_midwareVerAdd(json){
	if (json.success) {
		var dataTable=parent.$('#appSerinfoList').DataTable();
		dataTable.row.add({
			appSerId:json.id,
			appSerName:$("#appSerName").val(),
			appSerClsnm:$("#appSerClsnm").val(),
			appSerType:$("#appSerType").val(),
			appSerTypeName:$("#appSerType").find("option:selected").text(),
			appSerDesc:$("#appSerDesc").val()
		}).draw(false);
		parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}
function midwareVerAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}