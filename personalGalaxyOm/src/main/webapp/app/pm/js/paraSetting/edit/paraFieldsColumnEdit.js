var form;
$(document).ready(function() {
	var rowData;
	if (parent.$("#paraFieldsColumnList").find(".selected").length==1){
		rowData = parent.$('#paraFieldsColumnList').DataTable().rows(".selected").data()[0];
		$("#columnName").val(rowData.columnName);
		$("#columnType").val(rowData.columnType);
		$("#columnDesc").val(rowData.columnDesc);
		$("#dataLength").val(rowData.dataLength);
		$("#valueMethod").val(rowData.valueMethod);
        $("#valueScore").val(rowData.valueScore);
        $("#refTable").val(rowData.refTable);
        $("#refCol").val(rowData.refCol);
		form = $("#form-column-edit").Validform({
			tiptype:2,
			callback:function(form){
				columnEdit();
				return false;
			}
		});
	}
	$(".select2").select2();
	hided();
	$("#valueMethod").change(function(){
        hided();
    });
});

function columnEdit(){
	var url = contextPath+"/paraFieldsColumn/updateColumn";
	sendPostRequest(url,{
	    columnName:$("#columnName").val(),
		columnType:$("#columnType").val(),
		columnDesc:$("#columnDesc").val(),
		dataLength:$("#dataLength").val(),
		valueMethod:$("#valueMethod").val(),
        valueScore:$("#valueScore").val(),
        refTable:$("#refTable").val(),
        refCol:$("#refCol").val(),
	}, callback_columnEdit,"json");
}

function callback_columnEdit(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
	    var dataTable=parent.$("#paraFieldsColumnList").DataTable();
		dataTable.row(".selected").remove().draw(false);
		dataTable.row.add({
			columnName:$("#columnName").val(),
			columnType:$("#columnType").val(),
			columnDesc:$("#columnDesc").val(),
			dataLength:$("#dataLength").val(),
			valueMethod:$("#valueMethod").val(),
			valueScore:$("#valueScore").val(),
			refTable:$("#refTable").val(),
			refCol:$("#refCol").val()
		}).draw(false);
		parent.showMsgDuringTime("编辑成功");
	}
}

function hided(){
    var valueMethod = $("#valueMethod").val();
    if(valueMethod == "VL"){
        $("#VL").show();
        $("#RF1").hide();
        $("#RF2").hide();
        $("#refTable").val("");
        $("#refCol").val("");
    }
    if(valueMethod == "RF"){
        $("#VL").hide();
        $("#RF1").show();
        $("#RF2").show();
        $("#valueScore").val("");
    }
    if(valueMethod == "FD" || valueMethod == "YN"){
        $("#VL").hide();
        $("#RF1").hide();
        $("#RF2").hide();
        $("#refTable").val("");
        $("#refCol").val("");
        $("#valueScore").val("");
    }
}

