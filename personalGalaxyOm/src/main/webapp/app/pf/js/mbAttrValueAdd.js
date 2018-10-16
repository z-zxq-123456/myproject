var form;
$(document).ready(function() {
    getPkList({  url: contextPath + "/pklist/getattrKey1",
                 id: "attrKey",
                 async: false,
                     });
	form = $("#form-attr-add").Validform({
		tiptype:2,
		callback:function(form){
		    check();
			return false;
		}
	});
    $("#attrKey").change(function(){
                   if($("#attrKey").val()  =="" )  {
                             return;
                   }else{
                     var url = contextPath + "/attrType/getattrKey";
                     $.ajax({
                         url: url,
                         data: "attrKey=" + $("#attrKey").val(),
                         success: function(json) {
                             if (json.retStatus === 'F') {
                                   showMsg(json.retMsg, 'info');
                                    $("#attrKey").val("");
                             } else if (json.retStatus === 'S') {
                                   $("#attrDesc").val(json.data.attrDesc);
                                   $("#valueMethod").val(json.data.valueMethod);
                                   $("#valueMethod").select2();
                                 	 if(json.data.valueMethod=="VL"){
                                 	   $("#refTable").attr("disabled",false);
                                 	   $("#refCondition").attr("disabled",false);
                                       $("#refColumns").attr("disabled",false);
                                 	   $("#attrValue").attr("disabled",false);
                                 	   $("#valueDesc").attr("disabled",false);
                                       $("#attrValue").attr("datatype","*1-40");
                                       $("#valueDesc").attr("datatype","*1-40");
                                 	   $("#refTable").attr("disabled",true);
                                 	   $("#refCondition").attr("disabled",true);
                                 	   $("#refColumns").attr("disabled",true);
                                     }else if(json.data.valueMethod=="RF"){
                                 	   $("#refTable").attr("disabled",false);
                                       $("#refCondition").attr("disabled",false);
                                       $("#refColumns").attr("disabled",false);
                                 	   $("#attrValue").attr("disabled",false);
                                 	   $("#valueDesc").attr("disabled",false);
									   $("#attrValue").val(" ");
									   $("#valueDesc").val(" ");
                                 	   $("#attrValue").attr("disabled",true);
                                 	   $("#valueDesc").attr("disabled",true);
                                 	   $("#refTable").attr("datatype","*1-40");
                                 	   $("#refCondition").attr("datatype","*1-40");
                                 	   $("#refColumns").attr("datatype","*1-40");
                                     }
                                    return;
                             }
                         },
                         dataType: "json",
                     });
                     }
    });
    $('#valueMethod').change(function(){
    	var value=$('#valueMethod').val();
    	if(json.data.valueMethod=="FD"){
    	  $("#attrValue").attr("disabled",true);
    	  $("#valueDesc").attr("disabled",true);
    	  $("#refTable").attr("disabled",true);
    	  $("#refCondition").attr("disabled",true);
    	  $("#refColumns").attr("disabled",true);
            alert("请在产品定义时输入参数值！")
        }else if(json.data.valueMethod=="VL"){
          $("#attrValue").attr("datatype","*1-40");
          $("#valueDesc").attr("datatype","*1-40");
    	  $("#refTable").attr("disabled",true);
    	  $("#refCondition").attr("disabled",true);
    	  $("#refColumns").attr("disabled",true);
        }else if(json.data.valueMethod=="RF"){
    	  $("#attrValue").attr("disabled",true);
    	  $("#valueDesc").attr("disabled",true);
    	  $("#attrValue").val(" ");
    	  $("#valueDesc").val(" ");
    	  $("#refTable").attr("datatype","*1-40");
    	  $("#refCondition").attr("datatype","*1-40");
    	  $("#refColumns").attr("datatype","*1-40");
        }else if(json.data.valueMethod=="YN"){
    	  $("#attrValue").attr("disabled",true);
    	  $("#valueDesc").attr("disabled",true);
    	  $("#refTable").attr("disabled",true);
    	  $("#refCondition").attr("disabled",true);
    	  $("#refColumns").attr("disabled",true);
          alert("请在产品定义时输入参数值！")
        }
    	});
	$(".select2").select2();
});
function check(){
		var url = contextPath + "/attrValue/getOne";
		$.ajax({
			url: url,
			data:{
			attrKey: $("#attrKey").val(),
			attrValue: $("#attrValue").val()
			},
			success: function(json) {
				if (json.retStatus === 'F') {
					  showMsg(json.retMsg, 'info');
					   $("#attrKey").val("");
					   $("#attrValue").val("");
				} else if (json.retStatus === 'S') {
				   attrAdd();
					 return;
				}
			},
			dataType: "json",
		});
}
function attrAdd(){
	var url = contextPath+"/attrValue/insert";
		sendPostRequest(url,{
    		attrKey:$("#attrKey").val(),
    		attrValue:$("#attrValue").val(),
    		valueDesc:$("#valueDesc").val(),
    		refTable:$("#refTable").val(),
    		refCondition:$("#refCondition").val(),
    		refColumns:$("#refColumns").val()
    	}, callback_attrAdd,"json");
}

function callback_attrAdd(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
		showMsg(json.retMsg,'info');
	}
	form.resetForm();  //JS表单清空
	attrAddCancel();
}
function attrAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
