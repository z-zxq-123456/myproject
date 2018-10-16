/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/

var  layer_add_index,layer_edit_index;

/*system增加*/
function system_Add(title,url,w,h){
    layer_add_index = layer.open({
    type: 2,
    content: url,
    title:title,
    area: [w+'px', h+'px']
    });
}

/*system删除*/
function system_del(){
    if ($("#paraSystemList").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }

    layer.confirm('确认要删除吗？',function(){
        systemDel();
    });
}

function systemDel(){
    var url = contextPath+"/paraSystem/deleteSystem";
    var rowData = $('#paraSystemList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url,rowData	, callback_systemDel,"json");                //将获取数据发送给后台处理
}

function callback_systemDel(json){
    if (json.success){
        $('#paraSystemList').dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime('删除成功！');
    } else if(json.errorMsg){
        showMsg(json.errorMsg,'errorMsg');
    }else if(json.retStatus == 'Info'){
        showMsg("请先删除该系统所绑定的模块！");
    }
}


function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
}

/*system修改*/
function system_Edit(title,url,w,h){
	if ($("#paraSystemList").find(".selected").length!==1){
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
	var opt = getDataTableOpt($("#paraSystemList"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
    opt.deferRender=true;
	opt.ajax= {
         "url": contextPath + "/paraSystem/findAllSystem",
         "type": "POST"
     };
	opt.columns=[
        { "data": "systemId",
            "defaultContent" : "" },
        { "data": "systemName",
            "defaultContent" : "" },
        { "data": "systemDesc",
            "defaultContent" : ""  }
    ];
	//渲染tables
	setDataTableOpt($("#paraSystemList"),opt);
	//界面美化tables
	$("#paraSystemList").beautyUi({
	    tableId:"paraSystemList"
    });
    $("#add").on("click",function(){
        system_Add('系统信息添加','add/paraSystemAdd.jsp','600','400');
    });
    $("#edit").on("click",function(){
        system_Edit('系统信息修改','edit/paraSystemEdit.jsp','600','400');
    });
    $("#delete").on("click",function(){
        system_del();
    });
    /*页面按钮根据权限实现隐藏*/

});

