var form;
$(document).ready(function() {
	var rowData;
	if (parent.$("#midwareInfoList").find(".selected").length==1){
		rowData = parent.$('#midwareInfoList').DataTable().rows(".selected").data()[0];
	}
	getPkList({
		 url:contextPath + "/findParaCombox",
		 id:"queryMidwareType",
		 async:false,
		 params:{paraParentId:'0006',
				 isDefault:false }
	 });
	 getPkList({
			  url:contextPath + "/findParaCombox",
			  id:"isDefaultParaName",
			  async:false,
			  params:{paraParentId:'0009',
					  isDefault:false }
	  });
	if (rowData){
        if(rowData.midwareType=="0006003"){
             $("#midwareVerNo").parent().parent().hide();
        }
	    if(rowData.midwareType!="0006004"){
			 $("#kfkZksId").parent().parent().hide();
		}
        getPkList({
			 url:contextPath + "/findMiddlewareNo",
			 id:"midwareVerNo",
			 async:false,
			 params:{midwareType:rowData.midwareType },
			 normalSelect:false
		});
	    getPkList({
			 url:contextPath + "/findMidwareCombox",
			 id:"kfkZksId",
			 async:false,
			 params:{midwareType:'0006002' },
			 normalSelect:false
		});
		$("#midwareName").val(rowData.midwareName);
		$("#queryMidwareType").val(rowData.midwareType);
		$("#midwarePath").val(rowData.midwarePath);
		$("#isDefaultParaName").val(rowData.isDefault);
		form = $("#form-midwareInfo-edit").Validform({
           			tiptype:2,
           			callback:function(form){
           				dataEdit();
           				return false;
           			}
           		});
	}
	$(".select2").select2();
	$("#queryMidwareType").change(function(){
           var queryMidwareType = $("#queryMidwareType").val();
           if(queryMidwareType==""){
             showMsg('中间件类型不能为空！','warning');
             return;
           }
           if(queryMidwareType=="0006003"){
		      $("#midwareVerNo").parent().parent().hide();
		   }
		   if(queryMidwareType!="0006003"){
				 $("#midwareVerNo").parent().parent().show();
				 getPkList({
					 url:contextPath + "/findMiddlewareNo",
					 id:"midwareVerNo",
					 async:false,
					 params:{midwareType:queryMidwareType }
				 });
	  	  }
		   if(queryMidwareType=="0006004"){
		    $("#kfkZksId").parent().parent().show();
			 getPkList({
				url: contextPath + "/findMidwareCombox",
				id: "kfkZksId",
				async: false,
				params: {midwareType: '0006002'}
			 });
		   }
		   if(queryMidwareType!="0006004"){
		       $("#kfkZksId").val("");
		       $("#kfkZksId").parent().parent().hide();
		   }
    });
});

function dataEdit(){
	var url = contextPath+"/updateEcmMidewareInfo";
	var rowData = parent.$('#midwareInfoList').DataTable().rows(".selected").data()[0];
    var queryMidwareType = $("#queryMidwareType").val();
    var kfkZksId = "";
    if(queryMidwareType =="0006004"){
        kfkZksId = $("#kfkZksId").val();
    }
	sendPostRequest(url,{
		midwareId:rowData.midwareId,
		midwareName:$("#midwareName").val(),
		midwareType:$("#queryMidwareType").val(),
		midwareVerId:rowData.midwareVerId,
		midwarePath:$("#midwarePath").val(),
		isDefault:$("#isDefaultParaName").val(),
		kfkZksId:kfkZksId,
	}, callback_dataEdit,"json");

}

function callback_dataEdit(json){
	if (json.success) {
		var rowData = parent.$('#midwareInfoList').DataTable().rows(".selected").data()[0];
		var dataTable=parent.$("#midwareInfoList").DataTable();
		dataTable.row(".selected").remove().draw(false);
		dataTable.row.add({
			midwareId:rowData.midwareId,
			midwareName:rowData.midwareName,
			midwareType:$("#queryMidwareType").val(),
			midwareTypeName:$("#queryMidwareType").find("option:selected").text(),
			midwarePath:$("#midwarePath").val(),
			midwareVerId:$("#midwareVerNo").val(),
			midwareVerNo:$("#midwareVerNo").find("option:selected").text(),
			isDefault:$("#isDefault").val(),
			isDefaultParaName:$("#isDefault").find("option:selected").text(),
			kfkZksId:$("#kfkZksId").val(),
			kfkZksIdName:$("#kfkZksId").find("option:selected").text(),
		}).draw(false);
		parent.refresh();
		parent.showMsgDuringTime("编辑成功");

	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}
function dataEditCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
