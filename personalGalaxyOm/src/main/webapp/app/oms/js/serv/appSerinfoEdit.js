var form;
var rowData;
$(document).ready(function() {
	if (parent.$("#appSerinfoList").find(".selected").length==1){
		rowData = parent.$('#appSerinfoList').DataTable().rows(".selected").data()[0];
	}
	  getPkList({
				  url:contextPath + "/findParaCombox",
				  id:"appSerType",
				  async:false,
				  params:{paraParentId:'0060',
						  isDefault:false }
			  });
	if (rowData){
		$("#appSerName").val(rowData.appSerName);
		$("#appSerClsnm").val(rowData.appSerClsnm);
		$("#appSerType").val(rowData.appSerType);
		$("#appSerDesc").val(rowData.appSerDesc);
		form = $("#form-appSerinfo-edit").Validform({
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
	var url = contextPath+"/updateEcmAppSer";
	sendPostRequest(url,{
	    appSerId:rowData.appSerId,
		appSerName:$("#appSerName").val(),
        appSerClsnm:$("#appSerClsnm").val(),
        appSerType:$("#appSerType").val(),
        appSerDesc:$("#appSerDesc").val(),
	}, callback_dataEdit,"json");
}

function callback_dataEdit(json){
	if (json.success) {
		var dataTable=parent.$("#appSerinfoList").DataTable();
		dataTable.row(".selected").remove().draw(false);
		dataTable.row.add({
			appSerId:rowData.appSerId,
			appSerName:$("#appSerName").val(),
			appSerClsnm:$("#appSerClsnm").val(),
			appSerType:$("#appSerType").val(),
			appSerTypeName:$("#appSerType").find("option:selected").text(),
			appSerDesc:$("#appSerDesc").val()
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