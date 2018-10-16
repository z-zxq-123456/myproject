/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/

var  layer_add_index,layer_edit_index;

/*select增加*/
function select_Add(title,url,w,h){
    layer_add_index = layer.open({
    type: 2,
    content: url,
    title:title,
    area: [w+'px', h+'px']
    });
}
/*select删除*/
function select_del(){
    if ($("#paraSelectFieldsList").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }

    layer.confirm('确认要删除吗？',function(){
        selectDel();
    });
}

function selectDel(){
    var url = contextPath+"/paraSelectFields/deleteSelect";
    var rowData = $('#paraSelectFieldsList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url,rowData,callback_selectDel,"json");                //将获取数据发送给后台处理
}

function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
}

function callback_selectDel(json){
    if (json.success){
     $('#paraSelectFieldsList').dataTable().api().row(".selected").remove().draw(false);
      showMsgDuringTime('删除成功！');
    } else if(json.errorMsg){
      showMsg(json.errorMsg,'errorMsg');
    }
}


/*select修改*/
function select_Edit(title,url,w,h){
	if ($("#paraSelectFieldsList").find(".selected").length!==1){
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
	var opt = getDataTableOpt($("#paraSelectFieldsList"));
	opt.stateSave=true;
	opt.processing=true;
    opt.deferRender=true;
    opt.autoWidth=false;
	opt.ajax= {
         "url": contextPath + "/paraSelectFields/findAllSelect",
         "type": "POST"
     };
	opt.columns=[
        { "data": "tableName",
            "defaultContent" : "" },
        { "data": "select1",
            "defaultContent" : "" },
        { "data": "select2",
            "defaultContent" : ""  },
        { "data": "select3",
            "defaultContent" : ""  }

    ];
	//渲染tables
    	setDataTableOpt($("#paraSelectFieldsList"),opt);
    	//界面美化tables
    	$("#paraSelectFieldsList").beautyUi({
    	    tableId:"paraSelectFieldsList"
        });
        $("#add").on("click",function(){
            select_Add('交易查询条件添加','add/paraSelectFieldsAdd.jsp','600','500');
        });
        $("#edit").on("click",function(){
            select_Edit('交易查询条件修改','edit/paraSelectFieldsEdit.jsp','600','500');
        });
        $("#delete").on("click",function(){
            select_del();
        });
        /*页面按钮根据权限实现隐藏*/

});

