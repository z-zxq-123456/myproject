var form;
$(document).ready(function() {
	form = $("#form-serMtdinfo-add").Validform({
		tiptype:2,
		callback:function(form){
			midwareVerAdd();
			return false;
		}
	});
	getPkList({
			 url:contextPath + "/findAppSerCombox",
			 id:"appSerId",
			 async:false,
		 });
	$(".select2").select2();
});

function midwareVerAdd(){
	var url = contextPath+"/saveEcmSerMtdinfo";
	sendPostRequest(url,{
	    serMtdEnm:$("#serMtdEnm").val(),
		appSerId:$("#appSerId").val(),
		serMtdCnm:$("#serMtdCnm").val(),
	}, callback_midwareVerAdd,"json");
}

function callback_midwareVerAdd(json){
	if (json.success) {
		var dataTable=parent.$('#serMtdinfoList').DataTable();
		dataTable.row.add({
			serMtdId:json.id,
			serMtdEnm:$("#serMtdEnm").val(),
			appSerId:$("#appSerId").val(),
			appSerName:$("#appSerId").find("option:selected").text(),
			serMtdCnm:$("#serMtdCnm").val(),
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