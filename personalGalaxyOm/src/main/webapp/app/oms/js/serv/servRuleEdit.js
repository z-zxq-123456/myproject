var form;
var rowData;
$(document).ready(function() {
	getPkList({
		url:contextPath + "/findParaCombox",
		id:"routerArgsType",
		async:false,
		params:{paraParentId:'0025',
			  isDefault:false }
	  });
	getPkList({
		url:contextPath + "/findEcmSerMtdinfoCombox",
		id:"serMtdId",
		async:false
	});
	getPkList({
		url:contextPath + "/findEcmRouterCond",
		id:"routerCondId",
		async:false
	});
	getPkList({
		url:contextPath + "/findParaCombox",
		id:"ruleStatus",
		async:false,
		params:{paraParentId:'0001',
			  isDefault:false }
	});
	getPkList({
		url:contextPath + "/findParaCombox",
		id:"servRuleTypeName",
		async:false,
		params:{paraParentId:'0026',
			  isDefault:false }
	});
	if (parent.$("#servRuleList").find(".selected").length==1){
		rowData = parent.$('#servRuleList').DataTable().rows(".selected").data()[0];
		$("#servRuleTypeName").val(rowData.servRuleType);
		$("#serMtdId").val(rowData.serMtdId);
		$("#ruleStatus").val(rowData.ruleStatus);
		$("#routerCondId").val(rowData.routerCondId);
		$("#routerArgsPos").val(rowData.routerArgsPos);
		$("#routerArgsType").val(rowData.routerArgsType);
		$("#servRuleSelf").val(rowData.servRuleSelf);
	}
	form = $("#form-servRule-edit").Validform({
		tiptype:2,
		callback:function(form){
			dataEdit();
			return false;
		}
	});
	var  servRuleType = $("#servRuleTypeName").val();
	if(servRuleType=="0026001"){
			$("#condDiv").show();
			$("#posDiv").show();
			$("#typeDiv").show();
			$("#selfDiv").hide();
	 }else if(servRuleType=="0026002"){
			$("#condDiv").hide();
			$("#posDiv").hide();
			$("#typeDiv").hide();
			$("#selfDiv").show();
	 }
	$(".select2").select2();
});

function dataEdit(){
	var url = contextPath+"/updateEcmServRule";
    var  servRuleTypeName = $("#servRuleTypeName").val();
	if(servRuleTypeName=="0026001"){
	   sendPostRequest(url,{
	        servRuleId:rowData.servRuleId,
       		servRuleType:servRuleTypeName,
       		serMtdId:$("#serMtdId").val(),
       		ruleStatus:$("#ruleStatus").val(),
       		routerCondId:$("#routerCondId").val(),
       		routerArgsPos:$("#routerArgsPos").val(),
       		routerArgsType:$("#routerArgsType").val()
       	}, callback_dataEdit,"json");
	}else if(servRuleTypeName=="0026002"){
	   sendPostRequest(url,{
	        servRuleId:rowData.servRuleId,
       		servRuleType:servRuleTypeName,
       		serMtdId:$("#serMtdId").val(),
       		ruleStatus:$("#ruleStatus").val(),
       		servRuleSelf:$("#servRuleSelf").val()
       	}, callback_dataEdit,"json");
	}
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