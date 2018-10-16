var form;
$(document).ready(function() {
		//获取tableName
    	getPkList({
    		url:contextPath+"/pklist/getTableNameByNamespace",
    		id:"tableName",
    		async:false,
    		normalSelect:false
    	});

	var rowData;
	if (parent.$("#paraSelectFieldsList").find(".selected").length==1){
		rowData = parent.$('#paraSelectFieldsList').DataTable().rows(".selected").data()[0];
	}
	if (rowData){
	$("#select1").select2();
		$("#tableName").val(rowData.tableName);
		//获取columnName
		getPkList({
			url:contextPath+"/pklist/getColumnNameByTable",
			params:{tableName:$("#tableName").val()},
			id:"select1",
			async:false
		});
		var a = $("#select1").val();
		//获取columnName
		getPkList({
			url:contextPath+"/pklist/getColumnNameByTable",
			params:{tableName:$("#tableName").val()},
			id:"select2",
			async:false
		});
		//获取columnName
		getPkList({
			url:contextPath+"/pklist/getColumnNameByTable",
			params:{tableName:$("#tableName").val()},
			id:"select3",
			async:false
		});
		$("#select1").val(rowData.select1);
		$("#select2").val(rowData.select2);
		$("#select3").val(rowData.select3);

		form = $("#form-select-edit").Validform({
			tiptype:2,
			callback:function(form){
				selectEdit();
				return false;
			}
		});
	}
	$(".select2").select2();
});

function selectEdit(){
	var url = contextPath+"/paraSelectFields/updateSelect";
	var a = $("#tableName").val();

	sendPostRequest(url,{
	    tableName:$("#tableName").val(),
		select1:$("#select1").val(),
		select2:$("#select2").val(),
		select3:$("#select3").val(),

	}, callback_selectEdit,"json");
}

function callback_selectEdit(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
	     var dataTable=parent.$("#paraSelectFieldsList").DataTable();
		dataTable.row(".selected").remove().draw(false);
		dataTable.row.add({
			tableName:$("#tableName").val(),
			select1:$("#select1").val(),
			select2:$("#select2").val(),
			select3:$("#select3").val()
		}).draw(false);
		parent.showMsgDuringTime("编辑成功");
	}
}
