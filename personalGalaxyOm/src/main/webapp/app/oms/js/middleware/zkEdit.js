var form;
$(document).ready(function() {
	var rowData;
	if (parent.$("#midwareZkList").find(".selected").length==1){
		rowData = parent.$('#midwareZkList').DataTable().rows(".selected").data()[0];
	}
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
	if (rowData){
		$("#queryMidwareId").val(rowData.midwareName);
		$("#serId").val(rowData.serId);
		$("#zkintName").val(rowData.zkintName);
		$("#zkintClientPort").val(rowData.zkintClientPort);
		$("#zkintCommPort").val(rowData.zkintCommPort);
		$("#zkintElectPort").val(rowData.zkintElectPort);
		$("#zkintNodeNum").val(rowData.zkintNodeNum);
        $("#zkintDesc").val(rowData.zkintDesc);
		form = $("#form-zkInfo-edit").Validform({
			tiptype:2,
			callback:function(form){
				dataEdit();
				return false;
			}
		});
	}
	$(".select2").select2();
});

function dataEdit(){
	var url = contextPath+"/updateZkIntantInfo";
	var rowData = parent.$('#midwareZkList').DataTable().rows(".selected").data()[0];
	sendPostRequest(url,{
	    zkintId:rowData.zkintId,
		queryMidwareId:$("#queryMidwareId").val(),
        serId:$("#serId").val(),
        zkintName:$("#zkintName").val(),
        zkintClientPort:$("#zkintClientPort").val(),
        zkintCommPort:$("#zkintCommPort").val(),
        zkintElectPort:$("#zkintElectPort").val(),
        zkintNodeNum:$("#zkintNodeNum").val(),
        zkintDesc:$("#zkintDesc").val()
	}, callback_dataEdit,"json");
}

function callback_dataEdit(json){
	if (json.success) {
		parent.showMsgDuringTime("编辑成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}
function dataEditCancel(){
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
			   }, callback_PortEdit, "json");
    }

function callback_PortEdit(result) {
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