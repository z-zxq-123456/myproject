/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/

var  layer_add_index,layer_edit_index;

/*module增加*/
function module_Add(title,url,w,h){
    layer_add_index = layer.open({
    type: 2,
    content: url,
    title:title,
    area: [w+'px', h+'px']
    });
}
/*module删除*/
function module_del(){
    if ($("#paraModuleList").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }

    layer.confirm('确认要删除吗？',function(){
        moduleDel();
    });
}

function moduleDel(){
    var url = contextPath+"/paraModule/deleteModule";
    var rowData = $('#paraModuleList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url,rowData	, callback_moduleDel,"json");                //将获取数据发送给后台处理
}

function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
}

function callback_moduleDel(json){
    if (json.success){
        $('#paraModuleList').dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime('删除成功！');
    } else if(json.errorMsg){
        showMsg(json.errorMsg,'errorMsg');
    } else if(json.retStatus == 'Info'){
        showMsg("请先删除该模块所绑定的参数表！");
    }
}
/*module修改*/
function module_Edit(title,url,w,h){
	if ($("#paraModuleList").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
	layer_edit_index = layer.open({
        type: 2,
        content: url,
        title:title,
        area: [w+'px', h+'px']
    });
}
$(document).ready(function() {
	// 获取默认opt配置
	var opt = getDataTableOpt($("#paraModuleList"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
    opt.deferRender=true;
	opt.ajax= {
         "url": contextPath + "/paraModule/findAllModule",
         "type": "POST"
     };
	opt.columns=[
        { "data": "moduleId",
            "defaultContent" : "" },
        { "data": "systemId",
            "defaultContent" : "" },
        { "data": "moduleName",
            "defaultContent" : ""  },
        { "data": "moduleDesc",
            "defaultContent" : ""  }
    ];
	//渲染tables
	setDataTableOpt($("#paraModuleList"),opt);
	//界面美化tables
	$("#paraModuleList").beautyUi({
	    tableId:"paraModuleList"
    });
    $("#add").on("click",function(){
        module_Add('模块信息添加','add/paraModuleAdd.jsp','600','400');
    });
    $("#edit").on("click",function(){
        module_Edit('模块信息修改','edit/paraModuleEdit.jsp','600','400');
    });
    $("#delete").on("click",function(){
        module_del();
    });
    /*页面按钮根据权限实现隐藏*/

});

