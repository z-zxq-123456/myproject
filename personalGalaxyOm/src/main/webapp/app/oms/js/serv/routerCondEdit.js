var form;
$(document).ready(function() {
	var rowData;
	if (parent.$("#routerCondList").find(".selected").length==1){
		rowData = parent.$('#routerCondList').DataTable().rows(".selected").data()[0];
	}
	getPkList({
			  url:contextPath + "/findParaCombox",
			  id:"routerCondOper",
			  async:false,
			  params:{paraParentId:'0024',
					  isDefault:false }
		  });
	 getPkList({
				  url:contextPath + "/findEcmRouterCol",
				  id:"routerColId",
				  async:false,
			  });
	if (rowData){
		$("#routerColId").val(rowData.routerColId);
		$("#routerCondOper").val(rowData.routerCondOper);
		$("#routerCondName").val(rowData.routerCondName);
		$("#routerCondVal").val(rowData.routerCondVal);
		form = $("#form-routerCond-edit").Validform({
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
	var url = contextPath+"/updateEcmRouterCond";
	var rowData = parent.$('#routerCondList').DataTable().rows(".selected").data()[0];
	sendPostRequest(url,{
	    routerCondId:rowData.routerCondId,
		routerColId:$("#routerColId").val(),
		routerCondName:$("#routerCondName").val(),
		routerCondOper:$("#routerCondOper").val(),
		routerCondVal:$("#routerCondVal").val(),
	}, callback_dataEdit,"json");
}

function callback_dataEdit(json){
	if (json.success) {
		parent.showMsgDuringTime("编辑成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}
function dataEditCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}