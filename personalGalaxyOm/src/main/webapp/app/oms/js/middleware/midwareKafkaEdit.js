var form;
$(document).ready(function() {
	var rowData;
	if (parent.$("#midwareKafkaList").find(".selected").length==1){
		rowData = parent.$('#midwareKafkaList').DataTable().rows(".selected").data()[0];
	}
	getPkList({
		  url: contextPath + "/findMidwareCombox?midwareType=0006002",
		  id: "midwareZKId",
		  async: false
    });
	getPkList({
		 url:contextPath + "/findSerCombox",
		 id:"serId",
		 async:false,
		 params:{paraParentId:'0006',
				 isDefault:false }
	 });

	if (rowData){
	    $("#kafkaintId").val(rowData.kafkaintId);
		$("#serId").val(rowData.serId);
		$("#kafkaintName").val(rowData.kafkaintName);
		$("#kafkaintPort").val(rowData.kafkaintPort);
        $("#kafkaintDesc").val(rowData.kafkaintDesc);
		form = $("#form-kafkaInfo-edit").Validform({
			tiptype:2,
			callback:function(form){
				dataEdit();
				return false;
			}
		});
	}
	$(".select2").select2();
});

function dataEdit(){
	var url = contextPath+"/updateMidwareKafkaint";
	var rowData = parent.$('#midwareKafkaList').DataTable().rows(".selected").data()[0];
	sendPostRequest(url,{
	    kafkaintId:$("#kafkaintId").val(),
        serId:$("#serId").val(),
        kafkaintName:$("#kafkaintName").val(),
        kafkaintPort:$("#kafkaintPort").val(),
        kafkaintDesc:$("#kafkaintDesc").val(),
	}, callback_dataEdit,"json");
}

function callback_dataEdit(json){
	if (json.success) {
		parent.showMsgDuringTime("编辑成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}
function dataEditCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
function checkPort() {
		   var url = contextPath + "/findZkAndRedisCheckPort";
		   var checkValue = $("#kafkaintPort").val();
		   sendPostRequest(url, {
				   serId: $("#serId").val(),
				   port: checkValue
		           }, callback_PortEdit, "json");
    }

    function callback_PortEdit(result) {
        if (result.errorMsg) {
            showMsg(result.errorMsg, 'error');
        } else {
            if (result.isConnection == "true") {
                showMsg('该端口已被占用!', 'info');
                $("#kafkaintPort").val("");
            }
        }
    }

