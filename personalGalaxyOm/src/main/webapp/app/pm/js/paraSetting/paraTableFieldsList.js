/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/

var  layer_add_index,layer_edit_index;

/*table增加*/
function table_Add(title,url,w,h){
    layer_add_index = layer.open({
    type: 2,
    content: url,
    title:title,
    area: [w+'px', h+'px']
    });
}

/*table删除*/
function table_del(){
    if ($("#paraFieldsTableList").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }

    layer.confirm('确认要删除吗？',function(){
        tableDel();
    });
}

function tableDel(){
    var url = contextPath+"/paraFieldsTable/deleteTable";
    var rowData = $('#paraFieldsTableList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url,rowData	, callback_tableDel,"json");  //将获取数据发送给后台处理
}

function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
}

function callback_tableDel(json){
    if (json.success){
       $('#paraFieldsTableList').dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime('删除成功！');
    } else if(json.errorMsg){
        showMsg(json.errorMsg,'errorMsg');
    } else if(json.retStatus == 'Info'){
        showMsg("删除成功，未删除该表所绑定的表条件！");
    }
}

/*table修改*/
function table_Edit(title,url,w,h){
	if ($("#paraFieldsTableList").find(".selected").length!==1){
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
	var opt = getDataTableOpt($("#paraFieldsTableList"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
    opt.deferRender=true;
	opt.ajax= {
         "url": contextPath + "/paraFieldsTable/findAllTable",
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
            "targets": 2
        }
    ];
	opt.columns=[
        { "data": "tableName",
            "defaultContent" : "" },
        { "data": "columnName",
            "defaultContent" : "" },
        { "data": "isNull",
            "defaultContent" : ""  },
        { "data": "isPrimary",
            "defaultContent" : ""  }

    ];
	//渲染tables
    setDataTableOpt($("#paraFieldsTableList"),opt);
    //界面美化tables
    $("#paraFieldsTableList").beautyUi({
        tableId:"paraFieldsTableList"
    });
    $("#add").on("click",function(){
        table_Add('交易内容添加','add/paraTableFieldsAdd.jsp','600','500');
    });
    $("#edit").on("click",function(){
        table_Edit('交易内容修改','edit/paraTableFieldsEdit.jsp','600','500');
    });
    $("#delete").on("click",function(){
        table_del();
    });
    /*页面按钮根据权限实现隐藏*/

});


