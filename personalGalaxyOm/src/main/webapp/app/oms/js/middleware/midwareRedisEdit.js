var form;
$(document).ready(function() {
	var rowData;
	if (parent.$("#midwareRedisList").find(".selected").length==1){
		rowData = parent.$('#midwareRedisList').DataTable().rows(".selected").data()[0];
	}
	getPkList({
		 url:contextPath + "/findSerCombox",
		 id:"serId",
		 async:false,
		 params:{paraParentId:'0006',
				 isDefault:false }
	 });
	 getPkList({
		  url:contextPath + "/findParaCombox",
		  id:"redisintType",
		  async:false,
		  params:{paraParentId:'0007',
				  isDefault:false }
	  });
	if (rowData){
		$("#queryMidwareId").val(rowData.midwareName);
		$("#serId").val(rowData.serId);
		$("#redisintName").val(rowData.redisintName);
		$("#redisintType").val(rowData.redisintType);
		$("#redisintPort").val(rowData.redisintPort);
        $("#redisintDesc").val(rowData.redisintDesc);
		form = $("#form-redisInfo-edit").Validform({
			tiptype:2,
			callback:function(form){
				dataEdit();
				return false;
			}
		});
	}
	$(".select2").select2();
	if(rowData.redisintType=="0007003"){
		 $("#redisintNodeNum").removeAttr("datatype");
		 $("#redisintMemory").removeAttr("datatype");
		 $("#redisintNodeNum").parent().parent().hide();
		 $("#redisintMemory").parent().parent().hide();
	}else{
		 $("#redisintNodeNum").parent().parent().show();
		 $("#redisintMemory").parent().parent().show();
		 $("#redisintNodeNum").attr("datatype","n1-5");
		 $("#redisintMemory").attr("datatype","n1-2");
		 $("#redisintNodeNum").val(rowData.redisintNodeNum);
		 $("#redisintMemory").val(rowData.redisintMemory);
	}
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

function dataEdit(){
	var url = contextPath+"/updateMidwareRedisint";
	var rowData = parent.$('#midwareRedisList').DataTable().rows(".selected").data()[0];
	sendPostRequest(url,{
	    redisintId:rowData.redisintId,
		queryMidwareId:$("#queryMidwareId").val(),
        serId:$("#serId").val(),
        redisintName:$("#redisintName").val(),
        redisintType:$("#redisintType").val(),
        redisintPort:$("#redisintPort").val(),
        redisintNodeNum:$("#redisintNodeNum").val(),
        redisintMemory:$("#redisintMemory").val(),
        redisintDesc:$("#redisintDesc").val(),
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