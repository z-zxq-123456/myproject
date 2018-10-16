var form;
$(document).ready(function() {
	getPkList({
		url:contextPath+"/attrType/getForPkList",
		id:"attrClass",
		async:false
	});
      getPkList({  url: contextPath + "/pklist/getBmodule",
                      id: "busiCategory",
                      async: false,
                          });
    $("#attrKey").blur(function(){
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
    });
	var rowData;
	if (parent.$("#attrList").find(".selected").length==1){
		rowData = parent.$('#attrList').DataTable().rows(".selected").data()[0];
	}
	if (rowData){

		$("#attrKey").val(rowData.attrKey);
		$("#attrDesc").val(rowData.attrDesc);
		$("#busiCategory").val(rowData.busiCategory.split(","));
		$("#attrClass").val(rowData.attrClass);
		$("#useMethod").val(rowData.useMethod);
		$("#valueMethod").val(rowData.valueMethod);
		$("#status").val(rowData.status);
		$("#attrType").val(rowData.attrType);
		form = $("#form-attr-edit").Validform({
			tiptype:2,
			callback:function(form){
				attrEdit();
				return false;
			}
		});
	}
     $(".select2").select2();
});

function attrEdit(){
	var rowData = parent.$('#attrList').DataTable().rows(".selected").data()[0];
	var url = contextPath+"/attrType/update";
	sendPostRequest(url,{
		attrKey:$("#attrKey").val()+","+rowData.attrKey,
		attrDesc:$("#attrDesc").val(),
		attrClass:$("#attrClass").val(),
		useMethod:$("#useMethod").val(),
		valueMethod:$("#valueMethod").val(),
		status:$("#status").val(),
		busiCategory:$("#busiCategory").val().join(","),
		attrType:$("#attrType").val()
	}, callback_attrEdit,"json");
}

function callback_attrEdit(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	   	layer_close();
	} else if(json.retStatus == 'S'){
		showMsg(json.retMsg,'info');
	}
	attrEditCancel();
}
function attrEditCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
