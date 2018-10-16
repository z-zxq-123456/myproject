var form;
$(document).ready(function() {
	form = $("#form-routerCol-add").Validform({
		tiptype:2,
		callback:function(form){
			dataAdd();
			return false;
		}
	});
	  getPkList({
			  url:contextPath + "/findParaCombox",
			  id:"routerColTypeName",
			  async:false,
			  params:{paraParentId:'0014',
					  isDefault:false }
		  });
	$(".select2").select2();
});

function dataAdd(){
	var url = contextPath+"/saveEcmRouterCol";
	sendPostRequest(url,{
		routerColCn:$("#routerColCn").val(),
		routerColCdn:$("#routerColCdn").val(),
		routerColType:$("#routerColTypeName").val(),
	}, callback_dataAdd,"json");
}

function callback_dataAdd(json){
	if (json.success) {
		var dataTable=parent.$('#routerColList').DataTable();
		dataTable.row.add({
			routerColId:json.id,
			routerColCn:$("#routerColCn").val(),
			routerColCdn:$("#routerColCdn").val(),
			routerColType:$("#routerColTypeName").val(),
			routerColTypeName:$("#routerColTypeName").find("option:selected").text(),
		}).draw(false);
		parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}
function dataAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}