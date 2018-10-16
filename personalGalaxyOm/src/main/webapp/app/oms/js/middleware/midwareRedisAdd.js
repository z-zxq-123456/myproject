var form;
$(document).ready(function() {
	form = $("#form-redisInfo-add").Validform({
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
     		 params:{midwareType:'0006001' }
     	 });
	 getPkList({
			  url:contextPath + "/findParaCombox",
			  id:"redisintType",
			  async:false,
			  params:{paraParentId:'0007',
					  isDefault:false }
	  });
	$(".select2").select2();
	$("#redisintType").change(function(){
	    var redisintType = $("#redisintType").val();
	    if(redisintType==""){
	        showMsg('中间件类型不能为空！','error');
	    }else if(redisintType=="0007003"){
	         $("#redisintNodeNum").removeAttr("datatype");
	          $("#redisintMemory").removeAttr("datatype");
	          $("#redisintNodeNum").val("");
	          $("#redisintMemory").val("");
             $("#redisintNodeNum").parent().parent().hide();
             $("#redisintMemory").parent().parent().hide();
	    }else {
              $("#redisintNodeNum").parent().parent().show();
			  $("#redisintMemory").parent().parent().show();
			  $("#redisintNodeNum").attr("datatype","n1-5");
			  $("#redisintMemory").attr("datatype","n1-2");
	    }
	});
});

function midwareVerAdd(){
	var url = contextPath+"/saveMidwareRedisint";
	sendPostRequest(url,{
		midwareId:$("#midwareId").val(),
		serId:$("#serId").val(),
		redisintName:$("#redisintName").val(),
		redisintType:$("#redisintType").val(),
        redisintPort:$("#redisintPort").val(),
  		redisintNodeNum:$("#redisintNodeNum").val(),
  		redisintMemory:$("#redisintMemory").val(),
  		redisintDesc:$("#redisintDesc").val(),
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
		   var checkValue = $("#redisintPort").val();
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
                $("#redisintPort").val("");
            }
        }
    }