var form;
$(document).ready(function() {
	var rowData;
	if (parent.$("#serMtdinfoList").find(".selected").length==1){
		rowData = parent.$('#serMtdinfoList').DataTable().rows(".selected").data()[0];
	}
	getPkList({
			 url:contextPath + "/findAppSerCombox",
			 id:"appSerId",
			 async:false,
		 });
	if (rowData){
		$("#appSerId").val(rowData.appSerId);
		$("#serMtdCnm").val(rowData.serMtdCnm);
		$("#serMtdEnm").val(rowData.serMtdEnm);
		form = $("#form-serMtdinfo-edit").Validform({
			tiptype:2,
			callback:function(form){
				dataEdit();
				return false;
			}
		});
	}
	$(".select2").select2();
});

function dataEdit(){
	var url = contextPath+"/updateEcmSerMtdinfo";
	var rowData = parent.$('#serMtdinfoList').DataTable().rows(".selected").data()[0];
	sendPostRequest(url,{
	    serMtdId:rowData.serMtdId,
		appSerId:$("#appSerId").val(),
		serMtdCnm:$("#serMtdCnm").val(),
		serMtdEnm:$("#serMtdEnm").val(),
	}, callback_dataEdit,"json");
}

function callback_dataEdit(json){
	if (json.success) {
			var rowData = parent.$('#serMtdinfoList').DataTable().rows(".selected").data()[0];
			var dataTable=parent.$("#serMtdinfoList").DataTable();
			dataTable.row(".selected").remove().draw(false);
			dataTable.row.add({
				serMtdId:rowData.serMtdId,
				serMtdEnm:$("#serMtdEnm").val(),
				appSerId:$("#appSerId").val(),
				appSerName:$("#appSerId").find("option:selected").text(),
				serMtdCnm:$("#serMtdCnm").val(),
			}).draw(false);
			parent.showMsgDuringTime("编辑成功");
		} else if (json.errorMsg) {
			showMsg(json.errorMsg, 'errorMsg');
		}
}
function dataEditCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}