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
	var rowData;
	if (parent.$("#paraFieldsTableList").find(".selected").length==1){
		rowData = parent.$('#paraFieldsTableList').DataTable().rows(".selected").data()[0];
	}
	if (rowData){
		$("#tableName").val(rowData.tableName);
		$("#columnName").val(rowData.columnName);
		$("#isNull").val(rowData.isNull);
		$("#isPrimary").val(rowData.isPrimary);
		form = $("#form-table-edit").Validform({
			tiptype:2,
			callback:function(form){
				tableEdit();
				return false;
			}
		});
	}
	$(".select2").select2();
});

function tableEdit(){
	var url = contextPath+"/paraFieldsTable/updateTable";
	sendPostRequest(url,{
	    tableName:$("#tableName").val(),
		columnName:$("#columnName").val(),
		isNull:$("#isNull").val(),
		isPrimary:$("#isPrimary").val()

	}, callback_tableEdit,"json");
}

function callback_tableEdit(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
	    var dataTable=parent.$("#paraFieldsTableList").DataTable();
		dataTable.row(".selected").remove().draw(false);
		dataTable.row.add({
			tableName:$("#tableName").val(),
			columnName:$("#columnName").val(),
			isNull:$("#isNull").val(),
			isPrimary:$("#isPrimary").val()
		}).draw(false);
		parent.showMsgDuringTime("编辑成功");
	}
}
