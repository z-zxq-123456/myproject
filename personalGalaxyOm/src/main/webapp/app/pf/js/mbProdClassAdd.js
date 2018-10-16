var form;
$(document).ready(function() {
    getPkList({  url: contextPath + "/pklist/getBmodule",
                 id: "Bmodule",
                 async: false,
                     });
 	$('#prodClassLevel').change(function(){
 		var value=$('#prodClassLevel').val();
 		 getPkList({  url: contextPath + "/pklist/getparentProdClass?prodClassLevel="+value,
                          id: "parentProdClass",
                          async: false,
                             });
 	});

    $("#prodClass").blur(function(){
       if($("#prodClass") == ""){
        return;
       }else{
                     var url = contextPath + "/prod/getprodKey";
                     $.ajax({
                         url: url,
                         data: "prodClass=" + $("#prodClass").val(),
                         success: function(json) {
                             if (json.retStatus === 'F') {
                                   showMsg(json.retMsg, 'info');
                                    $("#prodClass").val("");
                             } else if (json.retStatus === 'S') {
                                  return;
                             }
                         },
                         dataType: "json",
                     });
        }
    });
	form = $("#form-prod-add").Validform({
		tiptype:2,
		callback:function(form){
			prodAdd();
			return false;
		}
	});
	$(".select2").select2();
});

function prodAdd(){
	var url = contextPath+"/prod/insert";
	sendPostRequest(url,getFormData("form-prod-add"), callback_prodAdd,"json");
}

function callback_prodAdd(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
		showMsg(json.retMsg,'info');
	}
	form.resetForm();  //JS表单清空
	prodAddCancel();
}
function prodAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}