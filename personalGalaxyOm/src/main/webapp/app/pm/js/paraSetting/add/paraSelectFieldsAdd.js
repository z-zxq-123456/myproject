var form;
$(document).ready(function() {
		//获取tableName
    	getPkList({
    		url:contextPath+"/pklist/getTableNameByNamespace",
    		id:"tableName",
    		async:false
    	});

		form = $("#form-select-edit").Validform({
			tiptype:2,
			callback:function(form){
				selectEdit();
				return false;
			}
		});
	$(".select2").select2();
		$("#tableName").change(function(){
        	findList();
        });
});
function selectEdit(){
	var url = contextPath+"/paraSelectFields/saveSelect";
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
		dataTable.row.add({
			tableName:$("#tableName").val(),
			select1:$("#select1").val(),
			select2:$("#select2").val(),
			select3:$("#select3").val()
		}).draw(false);
		parent.showMsgDuringTime("添加成功");
	}
}
function findList(){
    	//获取columnName
        getPkList({
            url:contextPath+"/pklist/getColumnNameByTable",
            params:{tableName:$("#tableName").val()},
            id:"select1",
            async:false
        });
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
    }