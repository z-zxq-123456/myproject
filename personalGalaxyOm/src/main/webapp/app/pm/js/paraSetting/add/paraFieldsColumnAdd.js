var form;
$(document).ready(function() {

	form = $("#form-column-add").Validform({
		tiptype:2,
		callback:function(form){

			columnAdd();
			return false;
		}
	});
	$(".select2").select2();

	$("#VL").hide();
	$("#RF1").hide();
	$("#RF2").hide();

	$("#valueMethod").change(function(){
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
    });

});

function columnAdd(){
	var url = contextPath+"/paraFieldsColumn/saveColumn";
	sendPostRequest(url,{
		columnName:$("#columnName").val(),
        columnType:$("#columnType").val(),
        columnDesc:$("#columnDesc").val(),
        dataLength:$("#dataLength").val(),
        valueMethod:$("#valueMethod").val(),
        valueScore:$("#valueScore").val(),
        refTable:$("#refTable").val(),
        refCol:$("#refCol").val(),
	}, callback_columnAdd,"json");
}

function callback_columnAdd(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
	    var dataTable=parent.$("#paraFieldsColumnList").DataTable();
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
		parent.showMsgDuringTime("添加成功");
	}
	//form.resetForm();  //JS表单清空
}
function columnAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
