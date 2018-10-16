var form;
$(document).ready(function() {
    getPkList({  url: contextPath + "/pklist/getBmodule",
                 id: "busiCategory",
                 async: false,
                     });
	getPkList({
		url:contextPath+"/attrType/getForPkList",
		id:"attrClass",
		async:false
	});
    $("#attrKey").blur(function(){
            if($("#attrKey").val() == ""){
              return;
            }else{
                     var url = contextPath + "/attrType/getOne";
                     $.ajax({
                         url: url,
                         data: "attrKey=" + $("#attrKey").val(),
                         success: function(json) {
                             if (json.retStatus === 'F') {
                                   showMsg(json.retMsg, 'info');
                                    $("#attrKey").val("");
                             } else if (json.retStatus === 'S') {
                                  return;
                             }
                         },
                         dataType: "json",
                     });
            }
    });
	form = $("#form-attr-add").Validform({
		tiptype:2,
		callback:function(form){
			attrAdd();
			return false;
		}
	});
	 $(".select2").select2();
});

function attrAdd(){
	var url = contextPath+"/attrType/insert";
		sendPostRequest(url,{
    		attrKey:$("#attrKey").val(),
    		attrDesc:$("#attrDesc").val(),
    		busiCategory:$("#busiCategory").val().join(","),
    		attrClass:$("#attrClass").val(),
    		useMethod:$("#useMethod").val(),
    		valueMethod:$("#valueMethod").val(),
    		status:$("#status").val(),
    		attrType:$("#attrType").val()
    	}, callback_attrAdd,"json");
}

function callback_attrAdd(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	   	layer_close();
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
