
var  layer_add_index,layer_edit_index;

$(document).ready(function() {
	// 获取默认opt配置
	var opt = getDataTableOpt($("#userStep"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
    opt.deferRender=true;
	opt.ajax= {
         "url": contextPath + "/authority/findAll",
         "type": "POST"
     };
	opt.columns=[
        { "data": "userId",
            "defaultContent" : "" },
        { "data": "authApplication",
            "defaultContent" : "" },
        { "data": "authEntering",
            "defaultContent" : ""  },
        { "data": "authCheck",
            "defaultContent" : ""  },
        { "data": "authPublish",
            "defaultContent" : ""  }
    ];
    opt.columnDefs=[
        {
            "render": function ( data ) {
                if(data == "Y"){
                    return "有";
                }else if(data == "N"){
                    return "无";
                }else{
                    return "未定义";}
            },
            "targets": 1
        },
        {
            "render": function ( data) {
                if(data == "Y"){
                    return "有";
                }else if(data == "N"){
                    return "无";
                }else{
                    return "未定义";}
            },
            "targets": 2
        },
        {
            "render": function ( data) {
                if(data == "Y"){
                    return "有";
                }else if(data == "N"){
                    return "无";
                }else{
                    return "未定义";}
            },
            "targets": 3
        },
        {
            "render": function ( data) {
                if(data == "Y"){
                    return "有";
                }else if(data == "N"){
                    return "无";
                }else{
                    return "未定义";}
            },
            "targets": 4
        }
    ];
	//渲染tables
	setDataTableOpt($("#userStep"),opt);
	//界面美化tables
	$("#userStep").beautyUi({
	    tableId:"userStep"
    });
    $('#add').click(function(){
      add('用户参数流程授权','add/userStepAdd.jsp','600','400');
    });
    $("#edit").on("click",function(){edit('修改用户参数流程权限','edit/userStepEdit.jsp','600','400')});
    $("#delete").on("click",function(){del()});
    /*页面按钮根据权限实现隐藏*/

});

function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
}


/*role增加*/
function add(title,url,w,h){
	layer_add_index = layer.open({
            type: 2,
            content: url,
            area: [w+'px', h+'px'],
            title:title
            });
}
/*role删除*/
function del(){
	if ($("#userStep").find(".selected").length!==1){
            showMsg('请选择一行记录！','warning');
            return;
        }

        layer.confirm('确认要删除吗？',function(){
                userStepDel();
            });
    }

    function userStepDel(){
    	var url = contextPath+"/authority/delete";
    	var rowData = $('#userStep').DataTable().rows(".selected").data()[0];  //已经获取数据
    	sendPostRequest(url,rowData	, callback_userStepDel,"json");                //将获取数据发送给后台处理
    }
    function callback_userStepDel(json){
    	if (json.success) {
    	    $('#userStep').dataTable().api().row(".selected").remove().draw(false);
            showMsgDuringTime('删除成功！');
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
    }
/*role修改*/
function edit(title,url,w,h){
	if ($("#userStep").find(".selected").length!==1){
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

