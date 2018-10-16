var form;
$(document).ready(function() {

	//获取tableName
    	getPkList({
    		url:contextPath+"/pklist/getTableNameByNamespace",
    		id:"tableName",
    		async:false,
    		normalSelect:false
    	});

    //获取columnName
        	getPkList({
        		url:contextPath+"/pklist/getColumnNameByColumn",
        		id:"columnName",
        		async:false,
        		normalSelect:false
        	});

	form = $("#form-table-add").Validform({
		tiptype:2,
		callback:function(form){

			tableAdd();
			return false;
		}
	});
	$(".select2").select2();
});

function tableAdd(){
	var url = contextPath+"/paraFieldsTable/saveTable";
	sendPostRequest(url,{
		tableName:$("#tableName").val(),
		columnName:$("#columnName").val(),
		isNull:$("#isNull").val(),
		isPrimary:$("#isPrimary").val()
	}, callback_tableAdd,"json");
}

function callback_tableAdd(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
	    var dataTable=parent.$("#paraFieldsTableList").DataTable();
		dataTable.row.add({
			tableName:$("#tableName").val(),
			columnName:$("#columnName").val(),
			isNull:$("#isNull").val(),
			isPrimary:$("#isPrimary").val()
		}).draw(false);
		parent.showMsgDuringTime("添加成功");
	}
	//form.resetForm();  //JS表单清空
}
function tableAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
