var form;
$(document).ready(function() {
	var rowData;
	if (parent.$("#routerColList").find(".selected").length==1){
		rowData = parent.$('#routerColList').DataTable().rows(".selected").data()[0];
	}
	 getPkList({
		  url:contextPath + "/findParaCombox",
		  id:"routerColTypeName",
		  async:false,
		  params:{paraParentId:'0014',
				  isDefault:false }
	  });
	if (rowData){
		$("#routerColCn").val(rowData.routerColCn);
		$("#routerColCdn").val(rowData.routerColCdn);
		$("#routerColTypeName").val(rowData.routerColType);
		form = $("#form-routerCol-edit").Validform({
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
	var url = contextPath+"/updateEcmRouterCol";
	var rowData = parent.$('#routerColList').DataTable().rows(".selected").data()[0];
	sendPostRequest(url,{
	    routerColId:rowData.routerColId,
		routerColCn:$("#routerColCn").val(),
        routerColCdn:$("#routerColCdn").val(),
        routerColType:$("#routerColTypeName").val(),
	}, callback_dataEdit,"json");
}

function callback_dataEdit(json){
	if (json.success) {
		var rowData = parent.$('#routerColList').DataTable().rows(".selected").data()[0];
		var dataTable=parent.$("#routerColList").DataTable();
		dataTable.row(".selected").remove().draw(false);
		dataTable.row.add({
			routerColId:rowData.routerColId,
			routerColCn:$("#routerColCn").val(),
			routerColCdn:$("#routerColCdn").val(),
			routerColType:$("#routerColTypeName").val(),
			routerColTypeName:$("#routerColTypeName").find("option:selected").text(),
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