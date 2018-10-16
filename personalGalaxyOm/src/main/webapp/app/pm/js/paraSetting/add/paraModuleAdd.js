var form;
$(document).ready(function() {
     getPkList({
          url:contextPath+"/pklist/getSystem",
          id:"systemId",
          async:false
       });
	form = $("#form-module-add").Validform({
		tiptype:2,
		callback:function(form){
			moduleAdd();
			return false;
		}
	});
	$(".select2").select2();
});

function moduleAdd(){
	var url = contextPath+"/paraModule/saveModule";
	sendPostRequest(url,{
		moduleId:$("#moduleId").val(),
		systemId:$("#systemId").val(),
		moduleName:$("#moduleName").val(),
		moduleDesc:$("#moduleDesc").val()
	}, callback_moduleAdd,"json");
}

function callback_moduleAdd(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
	    var dataTable = $("#paraModuleList").DataTable();
	    dataTable.row.add({
	    	moduleId:$("#moduleId").val(),
			systemId:$("#systemId").val(),
			moduleName:$("#moduleName").val(),
			moduleDesc:$("#moduleDesc").val()
	    }).draw(false);
		parent.showMsgDuringTime("添加成功");
	}
	//form.resetForm();  //JS表单清空
}
function moduleAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
