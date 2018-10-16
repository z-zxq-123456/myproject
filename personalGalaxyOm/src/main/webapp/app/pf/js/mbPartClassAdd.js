var form;
$(document).ready(function() {
    getPkList({  url: contextPath + "/pklist/getBmodule",
                 id: "Bmodule",
                 async: false,
                     });
    $("#partClass").blur(function(){
            if($("#partClass").val() == ""){
              return;
            }else{
                     var url = contextPath + "/part/getpartKey";
                     $.ajax({
                         url: url,
                         data: "partClass=" + $("#partClass").val(),
                         success: function(json) {
                             if (json.retStatus === 'F') {
                                   showMsg(json.retMsg, 'info');
                                    $("#partClass").val("");
                             } else if (json.retStatus === 'S') {
                                  return;
                             }
                         },
                         dataType: "json",
                     });
             }
    });
 	$('#partClassLevel').change(function(){
 		var value=$('#partClassLevel').val();
 		 getPkList({  url: contextPath + "/pklist/getparentPartClass?partClassLevel="+value,
                          id: "parentPartClass",
                          async: false,
                             });
 	});
	form = $("#form-part-add").Validform({
		tiptype:2,
		callback:function(form){
			partAdd();
			return false;
		}
	});
	$(".select2").select2();
});

function partAdd(){
	var url = contextPath+"/part/insert";
	sendPostRequest(url,getFormData("form-part-add"), callback_partAdd,"json");
}

function callback_partAdd(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
		showMsg(json.retMsg,'info');
	}
	form.resetForm();  //JS表单清空
	partAddCancel();
}
function partAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}