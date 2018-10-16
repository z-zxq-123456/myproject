var form;
$(document).ready(function() {
	form = $("#form-kafkaInfo-add").Validform({
		tiptype:2,
		callback:function(form){
			midwareVerAdd();
			return false;
		}
	});
	getPkList({
		 url:contextPath + "/findSerCombox",
		 id:"serId",
		 async:false,
		 params:{paraParentId:'0006',
				 isDefault:false }
	 });
	 getPkList({
     		 url:contextPath + "/findMidwareCombox",
     		 id:"midwareId",
     		 async:false,
     		 params:{midwareType:'0006004' }
     	 });

	$(".select2").select2();

});

function midwareVerAdd(){
	var url = contextPath+"/saveMidwareKafkaint";
	sendPostRequest(url,{
		midwareId:$("#midwareId").val(),
		serId:$("#serId").val(),
		kafkaintName:$("#kafkaintName").val(),
        kafkaintPort:$("#kafkaintPort").val(),
  		kafkaintDesc:$("#kafkaintDesc").val(),
	}, callback_midwareVerAdd,"json");
}

function callback_midwareVerAdd(json){
	if (json.success) {
		parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}
function checkPort() {
		   var url = contextPath + "/findZkAndRedisCheckPort";
		   var checkValue = $("#kafkaintPort").val();
		   sendPostRequest(url, {
				   serId: $("#serId").val(),
				   port: checkValue
		           }, callback_PortAdd, "json");
    }

    function callback_PortAdd(result) {
        if (result.errorMsg) {
            showMsg(result.errorMsg, 'error');
        } else {
            if (result.isConnection == "true") {
                showMsg('该端口已被占用!', 'info');
                $("#kafkaintPort").val("");
            }
        }
    }