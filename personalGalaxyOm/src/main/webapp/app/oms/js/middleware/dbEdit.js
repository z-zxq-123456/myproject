var form;
$(document).ready(function() {
	var rowData;
	if (parent.$("#midwareDbList").find(".selected").length==1){
		rowData = parent.$('#midwareDbList').DataTable().rows(".selected").data()[0];
	}
	getPkList({
			 url:contextPath + "/findMidwareCombox",
			 id:"midwareId",
			 async:false,
			 params:{midwareType:'0006003' }
	 });
	getPkList({
		 url:contextPath + "/findSerCombox",
		 id:"serId",
		 async:false,
		 params:{paraParentId:'0006',
				 isDefault:false }
	 });
	 getPkList({
			 url:contextPath + "/findParaCombox",
			 id:"dbType",
			 async:false,
			 params:{paraParentId:'0008',
					 isDefault:false }
		 });
	if (rowData){
		$("#midwareId").val(rowData.midwareId);
		$("#serId").val(rowData.serId);
		$("#dbintName").val(rowData.dbintName);
		$("#dbType").val(rowData.dbType);
		$("#dbintUserName").val(rowData.dbintUserName);
		$("#dbintUserPwd").val(rowData.dbintUserPwd);
		$("#password").val(rowData.dbintUserPwd);
        $("#dbintNodeNum").val(rowData.dbintNodeNum);
        $("#dbintDesc").val(rowData.dbintDesc);
        $("#dbintPort").val(rowData.dbintPort);
        $("#dbintServiceName").val(rowData.dbintServiceName);
		form = $("#form-dbInfo-edit").Validform({
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
	var url = contextPath+"/updateDbIntantInfo";
	var rowData = parent.$('#midwareDbList').DataTable().rows(".selected").data()[0];
	sendPostRequest(url,{
	    dbintId:rowData.dbintId,
		MidwareId:$("#midwareId").val(),
        serId:$("#serId").val(),
        dbintName:$("#dbintName").val(),
        dbType:$("#dbType").val(),
        dbintUserName:$("#dbintUserName").val(),
        dbintUserPwd:$("#dbintUserPwd").val(),
        password:$("#password").val(),
        dbintNodeNum:$("#dbintNodeNum").val(),
        dbintDesc:$("#dbintDesc").val(),
        dbintPort:$("#dbintPort").val(),
        dbintServiceName:$("#dbintServiceName").val()
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