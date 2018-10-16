/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/

var  layer_add_index,layer_edit_index;

/*env增加*/
function env_Add(title,url,w,h){
    layer_add_index = layer.open({
    type: 2,
    content: url,
    title:title,
    area: [w+'px', h+'px']
    });
}
/*env删除*/
function env_del(){
    if ($("#paraEnvList").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }

    layer.confirm('确认要删除吗？',function(){
        envDel();
    });
}

function envDel(){
    var url = contextPath+"/paraEnv/deleteEnv";
    var rowData = $('#paraEnvList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url,rowData	, callback_envDel,"json");                //将获取数据发送给后台处理
}

function callback_envDel(json){
    if (json.success){
        $('#paraEnvList').dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime("删除成功！");
    } else if(json.errorMsg){
        showMsg(json.errorMsg,'errorMsg');
    }
}
/*env修改*/
function env_Edit(title,url,w,h){
	if ($("#paraEnvList").find(".selected").length!==1){
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


function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
}

$(document).ready(function() {
	// 获取默认opt配置
	var opt = getDataTableOpt($("#paraEnvList"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
    opt.deferRender=true;
	opt.ajax= {
         "url": contextPath + "/paraEnv/findAllEnv",
         "type": "POST"
     };
	opt.columns=[
        { "data": "envId",
            "defaultContent" : "" },
        { "data": "systemId",
            "defaultContent" : "" },
        { "data": "envDesc",
            "defaultContent" : ""  },
        { "data": "url",
            "defaultContent" : ""  }
    ];
	//渲染tables
	setDataTableOpt($("#paraEnvList"),opt);
	//界面美化tables
	$("#paraEnvList").beautyUi({
	    tableId:"paraEnvList"
    });
    $("#add").on("click",function(){
        env_Add('环境信息添加','add/paraEnvOrgAdd.jsp','600','500');
    });
    $("#edit").on("click",function(){
        env_Edit('环境信息修改','edit/paraEnvOrgEdit.jsp','600','500');
    });
    $("#delete").on("click",function(){
        env_del();
    });
    /*页面按钮根据权限实现隐藏*/

});


