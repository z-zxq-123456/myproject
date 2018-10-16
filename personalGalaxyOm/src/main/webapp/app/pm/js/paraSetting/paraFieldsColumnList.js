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
function column_Add(title,url,w,h){
    layer_add_index = layer.open({
    type: 2,
    content: url,
    title:title,
    area: [w+'px', h+'px']
    });
}
/*table删除*/
function column_del(){
    if ($("#paraFieldsColumnList").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }

    layer.confirm('确认要删除吗？',function(){
        columnDel();
    });
}

    function columnDel(){
        var url = contextPath+"/paraFieldsColumn/deleteColumn";
        var rowData = $('#paraFieldsColumnList').DataTable().rows(".selected").data()[0];  //已经获取数据
        sendPostRequest(url,rowData	, callback_columnDel,"json");                //将获取数据发送给后台处理
    }

function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
}

function callback_columnDel(json) {
    if (json.success) {
        $('#paraFieldsColumnList').dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime("删除成功！");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }else if(json.retStatus == 'Info'){
         layer.confirm('该字段绑定表，确认要删除吗？',function(){
                 columnDel();
         });
     }
}

function deleteColume(){
    var url = contextPath+"/paraFieldsColumn/deleteFieldsColumn";
    var rowData = $('#paraFieldsColumnList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url,rowData	, callback_columnDel,"json");
}

/*table修改*/
function column_Edit(title,url,w,h){
	if ($("#paraFieldsColumnList").find(".selected").length!==1){
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
	var opt = getDataTableOpt($("#paraFieldsColumnList"));
	opt.stateSave=true;
	opt.processing=true;
	opt.scrollX=true;
    opt.deferRender=true;
	opt.ajax= {
         "url": contextPath + "/paraFieldsColumn/findAllColumn",
         "type": "POST"
     };
    opt.columnDefs=[
        {
            "render": function ( data, type, row ) {
                if(data == null || data == "undefined")
                    return '';
                if(row.valueMethod == "FD")
                    return data +' (手动输入)';
                else if(row.valueMethod == "YN")
                    return data +' (是/否)';
                else if(row.valueMethod == "VL")
                    return data +' (固定备选数据)';
                else if(row.valueMethod == "RF")
                    return data +' (数据来源他表)';
                else
                    return data +' ';
            },
            "targets": 2
        }
    ];
	opt.columns=[
        { "data": "columnDesc",
            "defaultContent" : ""  },
        { "data": "columnName",
            "defaultContent" : "" },
        { "data": "valueMethod",
            "defaultContent" : "" },
        { "data": "refTable",
            "defaultContent" : ""  },
        { "data": "refCol",
            "defaultContent" : ""  },
        { "data": "columnType",
            "defaultContent" : "" },
        { "data": "dataLength",
            "defaultContent" : ""  }
    ];
	//渲染tables
    	setDataTableOpt($("#paraFieldsColumnList"),opt);
    	//界面美化tables
    	$("#paraFieldsColumnList").beautyUi({
    	    tableId:"paraFieldsColumnList"
        });
        $("#add").on("click",function(){
            column_Add('元数据添加','add/paraFieldsColumnAdd.jsp','600','550');
        });
        $("#edit").on("click",function(){
            column_Edit('元数据修改','edit/paraFieldsColumnEdit.jsp','600','550');
        });
        $("#delete").on("click",function(){
            column_del();
        });
        /*页面按钮根据权限实现隐藏*/

});

