var form;
$(document).ready(function() {
	form = $("#form-zkInfo-add").Validform({
		tiptype:2,
		callback:function(form){
			midwareVerAdd();
			return false;
		}
	});
	 getPkList({
			 url:contextPath + "/findMidwareCombox",
			 id:"midwareId",
			 async:false,
			 params:{midwareType:'0006002' }
		 });
		getPkList({
    		 url:contextPath + "/findSerCombox",
    		 id:"serId",
    		 async:false,
    		 params:{paraParentId:'0006',
    				 isDefault:false }
    	 });
	$(".select2").select2();
});

function midwareVerAdd(){
	var url = contextPath+"/saveZkIntantInfo";
	sendPostRequest(url,{
		midwareId:$("#midwareId").val(),
		serId:$("#serId").val(),
		zkintName:$("#zkintName").val(),
		zkintClientPort:$("#zkintClientPort").val(),
        zkintCommPort:$("#zkintCommPort").val(),
  		zkintElectPort:$("#zkintElectPort").val(),
  		zkintNodeNum:$("#zkintNodeNum").val(),
  		zkintDesc:$("#zkintDesc").val(),
	}, callback_midwareVerAdd,"json");
}

function callback_midwareVerAdd(json){
	if (json.success) {
		parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}
function midwareVerAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
function checkClientPortProt(){
	 var checkValue = $("#zkintClientPort").val();
	 var flag =1;
	 exection(checkValue);
 }
 function checkCommPort(){
 	 var checkValue = $("#zkintCommPort").val();
 	 var flag =2;
 	 exection(checkValue);
  }
  function checkElectPort(){
  	 var checkValue = $("#zkintElectPort").val();
  	 var flag =3;
     exection(checkValue);
   }
function exection(checkValue , flag) {
	   var url = contextPath + "/findZkAndRedisCheckPort";
	   sendPostRequest(url, {
			   serId: $("#serId").val(),
			   port: checkValue
			   }, callback_PortAdd, "json");
    }

function callback_PortAdd(result) {
	if (result.errorMsg) {
		showMsg(result.errorMsg, 'error');
	} else {
		if (result.isConnection == "true") {
			showMsg('该端口已被占用!', 'info');
			 if(flag==1){
			   $("#zkintClientPort").val("");
			 }else if(flag==2){
			   $("#zkintCommPort").val("");
			 }else{
			   $("#zkintElectPort").val("");
			 }
		}
	}
}