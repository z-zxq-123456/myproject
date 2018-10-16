var form;
$(document).ready(function() {
	form = $("#form-midwareInfo-add").Validform({
		tiptype:2,
		callback:function(form){
			midwareVerAdd();
			return false;
		}
	});
	getPkList({
		 url:contextPath + "/findParaCombox",
		 id:"queryMidwareType",
		 async:false,
		 params:{paraParentId:'0006',
				 isDefault:false }
	 });
	 getPkList({
			  url:contextPath + "/findParaCombox",
			  id:"isDefault",
			  async:false,
			  params:{paraParentId:'0009',
					  isDefault:false }
	  });
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
                $("#kfkZksId").parent().parent().hide();
           }
    });
});

function midwareVerAdd(){
     if($("#isDefault").val()=="0009001"){
			 $.ajax({
				 type : "post",
				  url : contextPath + '/checkIsHaveDefaultCombox',
				 data : "midwareType=" + $("#queryMidwareType").val(),
				async : false,//取消异步
			  success : function(result){
					   var result = eval('('+result+')');
				     if (result.errorMsg){
					     showMsg(result.errorMsg,'error');
				         return   ;
					 }else{
							addMidwareVer();
					 }
				 }
			 });
	 }else{
      addMidwareVer();
	 }
}
function   addMidwareVer(){
		var url = contextPath+"/saveEcmMidewareInfo";
	    var queryMidwareType = $("#queryMidwareType").val();
			sendPostRequest(url,{
				midwareName:$("#midwareName").val(),
				midwareType:$("#queryMidwareType").val(),
				midwarePath:$("#midwarePath").val(),
				midwareVerId:$("#midwareVerNo").val(),
				midwareVerNo:$("#midwareVerNo").text(),
				isDefault:$("#isDefault").val(),
				kfkZksId:$("#kfkZksId").val(),
                kfkZksIdName:$("#kfkZksId").text(),
			}, callback_midwareVerAdd,"json");
}
function callback_midwareVerAdd(json){
	if (json.success) {
		var dataTable=parent.$('#midwareInfoList').DataTable();
		dataTable.row.add({
			midwareId:json.id,
			midwareName:$("#midwareName").val(),
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
		parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}
//全局变量设置成同步  （取消异步）
//$ajaxSetup({
//   async:false;
//});
