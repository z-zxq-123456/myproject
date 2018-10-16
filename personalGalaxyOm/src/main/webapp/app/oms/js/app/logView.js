$(document).ready(function() {
      getPkList({
		  url:contextPath + "/findComboxAppIntant"+urlArgs,
		  id:"appIntantId",
		  async:false
	  });
	  getPkList({
			url:contextPath + "/findParaCombox",
			id:"lineNumber",
			async:false,
			params:{paraParentId:'0053',
					  isDefault:false }
	  });
	  $(".select2").select2();
});
function searchRec(){
   if($("#appIntantId").val()==""){
       showMsg('实例名称不能为空，谢谢！','warning');
       return ;
   }else if($("#lineNumber").val()==""){
       showMsg('单次查看次数不能为空，谢谢！','warning');
	   return ;
   }else{
       url = contextPath + "/checkLog";
       var  lineNumber= $("#lineNumber").find("option:selected").text();
       sendPostRequest(url,{
      		appIntantId:$("#appIntantId").val(),
      		lineNumber:lineNumber
       }, callback_dataEdit,"json");
   }
}
function callback_dataEdit(json){
	if (json.success){
      var nowTime = new Date().toLocaleTimeString();
      var divShow = $("#dlg");
      var length = json.data.length;
      for(var i=length-1;i>=0;i--){
	    var log = json.data[i];
			if(i==0) {
				log = "<font color='blue'>访问时间:"+nowTime+'</font><br><br>'+log;
			}
			if(i==length-1){
				log = log+'<br><br><br>';
			}
			$("#dlg").prepend(log+'<br>');
	  }
   }else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}
