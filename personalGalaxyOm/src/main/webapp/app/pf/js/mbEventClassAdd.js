var form;
$(document).ready(function() {
    getPkList({  url: contextPath + "/pklist/getBmodule",
                 id: "Bmodule",
                 async: false,
                     });
 	$('#eventClassLevel').change(function(){
 		var value=$('#eventClassLevel').val();
 		 getPkList({  url: contextPath + "/pklist/getparentEventClass?eventClassLevel="+value,
                          id: "parentEventClass",
                          async: false,
                             });
 	});
	form = $("#form-event-add").Validform({
		tiptype:2,
		callback:function(form){
			eventAdd();
			return false;
		}
	});
    $("#eventClass").blur(function(){
          if($("#eventClass").val() == ""){
            return;
          }else{
                     var url = contextPath + "/event/geteventKey";
                     $.ajax({
                         url: url,
                         data: "eventClass=" + $("#eventClass").val(),
                         success: function(json) {
                             if (json.retStatus === 'F') {
                                   showMsg(json.retMsg, 'info');
                                    $("#eventClass").val("");
                             } else if (json.retStatus === 'S') {
                                  return;
                             }
                         },
                         dataType: "json",
                     });
          }
    });
	$(".select2").select2();
});

function eventAdd(){
	var url = contextPath+"/event/insert";
	var str = "";
	if($("#parentEventClass").val() == null){
	  str="0";
	}else{
	str=$("#parentEventClass").val().join(",");
	}
	sendPostRequest(url,{
                        		eventClass:$("#eventClass").val(),
                        		eventClassDesc:$("#eventClassDesc").val(),
                        		eventClassLevel:$("#eventClassLevel").val(),
                        		parentEventClass:str
                        	}, callback_eventAdd,"json");
}

function callback_eventAdd(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
		showMsg(json.retMsg,'info');
	}
	form.resetForm();  //JS表单清空
	eventAddCancel();
}
function eventAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
