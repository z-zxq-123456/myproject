var form;
$(document).ready(function() {
	form = $("#form-routerCond-add").Validform({
		tiptype:2,
		callback:function(form){
			midwareVerAdd();
			return false;
		}
	});
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
	$(".select2").select2();
});

function midwareVerAdd(){
	var url = contextPath+"/saveEcmRouterCond";
	sendPostRequest(url,{
		routerColId:$("#routerColId").val(),
		routerCondName:$("#routerCondName").val(),
		routerCondOper:$("#routerCondOper").val(),
		routerCondVal:$("#routerCondVal").val(),
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