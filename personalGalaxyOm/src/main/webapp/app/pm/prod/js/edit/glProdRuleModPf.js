var form;
var rowData;
$(document).ready(function() {
	var rowData;

	if (parent.$("#glProdRule").find(".selected").length===1){
		rowData = parent.$('#glProdRule').DataTable().rows(".selected").data()[0];
               if(parent.parent.$("#operateType").val()==="copy"){
               $("#prodType").val(parent.parent.$("#newProdType").val()).attr("disabled", true);
               }else{
               $("#prodType").val(rowData.prodType).attr("disabled",true);
               }
               $("#sysName").val(rowData.sysName).attr("disabled",true);
               $("#sourceType").val(rowData.sourceType).attr("disabled",true);
               $("#clientType").val(rowData.clientType).attr("disabled",true);
               $("#accountingStatus").val(rowData.accountingStatus).attr("disabled",true);
               $("#tranEventType").val(rowData.tranEventType).attr("disabled",true);
               $("#ccy").val(rowData.ccy).attr("disabled",true);
               $("#tranType").val(rowData.tranType).attr("disabled",true);
	        	$("#accountingNo").val(rowData.accountingNo);
	        	$("#customRule").val(rowData.customRule);
	        	$("#accountingDesc").val(rowData.accountingDesc);
	        	$("#sourceModule").val(rowData.sourceModule);
	}
			 	$(".select2").select2();
		form = $("#glProdRuleMod").Validform({
			tiptype:2,
			callback:function(form){
				glProdRuleMod();
				return false;
			}
		});

});
function glProdRuleMod(){
	var url= contextPath+"/glProdRule/update";
	   sendPostRequest(url,{    prodType:$("#prodType").val(),
    							sysName:$("#sysName").val(),
    							sourceType:$("#sourceType").val(),
    							clientType:$("#clientType").val(),
								accountingStatus:$("#accountingStatus").val(),
								tranEventType:$("#tranEventType").val(),
								ccy:$("#ccy").val(),
								tranType:$("#tranType").val(),
								customRule:$("#customRule").val(),
								accountingNo:$("#accountingNo").val(),
								accountingDesc:$("#accountingDesc").val(),
								sourceModule:$("#sourceModule").val(),
								reqNum:parent.parent.$(".breadcrumb").data("reqNum")
                }, callback_glProdRuleMod,"json");
}

function callback_glProdRuleMod(json){
	if (json.retStatus === 'F'){
		showMsg(json.retMsg,'info');
	} else if(json.retStatus === 'S'){
		var dataTable=parent.$("#glProdRule").DataTable();
                dataTable.row(".selected").remove().draw(false);
                	dataTable.row.add({
                        			prodType:$("#prodType").val(),
                        			sysName:$("#sysName").val(),
                        			sourceType:$("#sourceType").val(),
                        			clientType:$("#clientType").val(),
                        			accountingStatus:$("#accountingStatus").val(),
                        			tranEventType:$("#tranEventType").val(),
                        			accountingNo:$("#accountingNo").val(),
                        			ccy:$("#ccy").val(),
                        			customRule:$("#customRule").val(),
                        			accountingDesc:$("#accountingDesc").val(),
                        			tranType:$("#tranType").val(),
                        			sourceModule:$("#sourceModule").val()
                        		}).draw(false);
		parent.showMsgDuringTime("修改成功");
	}
	//form.resetForm();  JS表单清空
}

function glProdRuleModCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}