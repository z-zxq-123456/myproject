var form;
$(document).ready(function() {
     getPkList({
                       url: contextPath + "/pklist/getParameterPklist?tableName=mb_attr_class&tableCol=ATTR_CLASS,ATTR_CLASS_DESC",
                                      id: "parentAttrClass",
                                      async: false,

                     });
      getPkList({  url: contextPath + "/pklist/getBmodule",
                      id: "Bmodule",
                      async: false,
                          });
       $("#attrClass").blur(function(){
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
         });
		$('#attrClassLevel').change(function(){
		 	    $("#parentAttrClass").attr("disabled",false);
         	    $("#parentAttrClass").select2();
    		var value=$('#attrClassLevel').val();
    		 getPkList({  url: contextPath + "/pklist/getparentAttrClass?attrClassLevel="+value,
                             id: "parentAttrClass",
                             async: false,
                                });
    	});
	var rowData;
	if (parent.$("#attrList").find(".selected").length==1){
		rowData = parent.$('#attrList').DataTable().rows(".selected").data()[0];
	}
	if (rowData){
	    var bmodule = rowData.attrClass.substring(0,2);
		$("#attrClass").val(rowData.attrClass);
		$("#attrClassDesc").val(rowData.attrClassDesc);
		$("#Bmodule").val(bmodule);
		$("#attrClassLevel").val(rowData.attrClassLevel);
		$("#parentAttrClass").val(rowData.parentAttrClass);
		if(rowData.attrClassLevel =="1"){
		 $("#parentAttrClass").attr("disabled",true);
		}
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
	var url = contextPath+"/attr/update";
	sendPostRequest(url,{
		attrClass:$("#attrClass").val()+","+rowData.attrClass,
		attrClassDesc:$("#attrClassDesc").val(),
		attrClassLevel:$("#attrClassLevel").val(),
		parentAttrClass:$("#parentAttrClass").val()
	}, callback_attrEdit,"json");
}

function callback_attrEdit(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
		showMsg(json.retMsg,'info');
	}
	attrEditCancel();
}
function attrEditCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
