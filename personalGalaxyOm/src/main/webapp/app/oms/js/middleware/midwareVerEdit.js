var form;
$(document).ready(function() {
	var rowData;
	if (parent.$("#midwareList").find(".selected").length==1){
		rowData = parent.$('#midwareList').DataTable().rows(".selected").data()[0];
	}
	 getPkList({
    		 url:contextPath + "/findParaCombox",
    		 id:"queryMidwareType",
    		 async:false,
    		 params:{paraParentId:'0006',
    				 isDefault:false }
    	 });
	if (rowData){
		$("#queryMidwareType").val(rowData.midwareType);
		$("#midwareVerNo").val(rowData.midwareVerNo);
		$("#midwareVerPath").val(rowData.midwareVerPath);
		$("#midwareVerDate").val(rowData.midwareVerDate);
		$("#midwareVerDesc").val(rowData.midwareVerDesc);
		form = $("#form-midwareVer-edit").Validform({
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
	var url = contextPath+"/updateMiddlewareVer";
	var rowData = parent.$('#midwareList').DataTable().rows(".selected").data()[0];
	sendPostRequest(url,{
	    midwareVerId:rowData.midwareVerId,
		midwareType:$("#queryMidwareType").val(),
		midwareVerPath:$("#midwareVerPath").val(),
		midwareVerDate:$("#midwareVerDate").val(),
		midwareVerNo:$("#midwareVerNo").val(),
		midwareVerDesc:$("#midwareVerDesc").val()
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