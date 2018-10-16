var form;
$(document).ready(function() {
    getPkList({  url: contextPath + "/pklist/getBmodule",
                 id: "Bmodule",
                 async: false,
                     });
	form = $("#form-attr-add").Validform({
		tiptype:2,
		callback:function(form){
			attrAdd();
			return false;
		}
	});

    $("#attrClass").blur(function(){
            if($("#attrClass").val() == ""){
               return;
            }else{
                     var url = contextPath + "/attr/getOne";
                     $.ajax({
                         url: url,
                         data: "attrClass=" + $("#attrClass").val(),
                         success: function(json) {
                             if (json.retStatus === 'F') {
                                   showMsg(json.retMsg, 'info');
                                    $("#attrClass").val("");
                             } else if (json.retStatus === 'S') {
                                  return;
                             }
                         },
                         dataType: "json",
                     });
            }
    });

	$('#attrClassLevel').change(function(){
		var value=$('#attrClassLevel').val();
		 getPkList({  url: contextPath + "/pklist/getparentAttrClass?attrClassLevel="+value,
                         id: "parentAttrClass",
                         async: false,
                            });
	});

	 $(".select2").select2();
});

function attrAdd(){
	var url = contextPath+"/attr/insert";
	sendPostRequest(url,getFormData("form-attr-add"), callback_attrAdd,"json");
}

function callback_attrAdd(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == "S"){
		showMsg(json.retMsg,'info');
	}
	form.resetForm();  //JS表单清空
	attrAddCancel();
}
function attrAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
