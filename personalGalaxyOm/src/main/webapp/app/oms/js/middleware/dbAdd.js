var form;
$(document).ready(function() {
	form = $("#form-dbInfo-add").Validform({
		tiptype:2,
		callback:function(form){
			midwareVerAdd();
			return false;
		}
	});
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

	$(".select2").select2();
});

function midwareVerAdd(){
	var url = contextPath+"/saveDbIntantInfo";
	sendPostRequest(url,{
		midwareId:$("#midwareId").val(),
		serId:$("#serId").val(),
		dbintName:$("#dbintName").val(),
		dbType:$("#dbType").val(),
        dbintUserName:$("#dbintUserName").val(),
  		dbintUserPwd:$("#dbintUserPwd").val(),
  		password:$("#password").val(),
  		dbintServiceName:$("#dbintServiceName").val(),
  		dbintNodeNum:$("#dbintNodeNum").val(),
  		dbintDesc:$("#dbintDesc").val(),
        dbintPort:$("#dbintPort").val()
	}, callback_midwareVerAdd,"json");
}

function callback_midwareVerAdd(json){
	if (json.success) {
		parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}
function midwareVerAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}