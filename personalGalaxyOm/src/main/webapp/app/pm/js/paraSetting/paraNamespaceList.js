/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/

var  layer_add_index,layer_edit_index;

/*namespace增加*/
function namespace_Add(title,url,w,h){
    layer_add_index = layer.open({
    type: 2,
    content: url,
    title:title,
    area: [w+'px', h+'px']

    });
}
/*namespace删除*/
function namespace_del(){
    if ($("#paraNamespaceList").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }

    layer.confirm('确认要删除吗？',function(){
        namespaceDel();
    });
}

function namespaceDel(){
    var url = contextPath+"/paraNamespace/deleteNamespace";
    var rowData = $('#paraNamespaceList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url,rowData	, callback_namespaceDel,"json");                //将获取数据发送给后台处理
}

function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
}

function callback_namespaceDel(json) {
    if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    } else {
        $('#paraNamespaceList').dataTable().api().row(".selected").remove().draw(false);
        if (json.success) {
            showMsgDuringTime('删除成功！');
        } else if (json.retStatus == 'Info') {
            showMsgDuringTime("删除成功！未删除该参数表所绑定的表字段！");
        }
    }
}

/*namespace修改*/
function namespace_Edit(title,url,w,h){
	if ($("#paraNamespaceList").find(".selected").length!==1){
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
	var opt = getDataTableOpt($("#paraNamespaceList"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
    opt.deferRender=true;
	opt.ajax= {
         "url": contextPath + "/paraNamespace/findAllTable",
         "type": "POST"
     };
    opt.columnDefs=[
        {
            "render": function ( data, type, row ) {
                if(data == null || data == "undefined")
                    return '';
                return data == "Y"?"是":"否";
            },
            "targets": 3
        },
        {
            "render": function ( data, type, row ) {
                if(data == null || data == "undefined")
                    return '';
                return data == "Y"?"能":"否";
            },
            "targets": 4
        }
    ];
	opt.columns=[
	    { "data": "transactionDesc",
	        "defaultContent" : ""},
        { "data": "systemId",
            "defaultContent" : "" },
        { "data": "moduleId",
            "defaultContent" : "" },
        { "data": "bandEnteringcheck",
           "defaultContent" : ""  },
        { "data": "deleteAuth",
           "defaultContent" : "" }
    ];
	//渲染tables
	setDataTableOpt($("#paraNamespaceList"),opt);
	//界面美化tables
	$("#paraNamespaceList").beautyUi({
	    tableId:"paraNamespaceList"
    });

    $("#add").on("click",function(){
        namespace_Add('参数表信息添加','add/paraNamespaceAdd.jsp','600','620');
    });
    $("#edit").on("click",function(){
        namespace_Edit('参数表信息修改','edit/paraNamespaceEdit.jsp','600','620');
    });
    $("#delete").on("click",function(){
        namespace_del();
    });
    /*页面按钮根据权限实现隐藏*/

});


