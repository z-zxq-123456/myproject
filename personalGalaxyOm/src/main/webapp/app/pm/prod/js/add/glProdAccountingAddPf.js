   var form;
$(document).ready(function() {
         if(parent.parent.$("#operateType").val() === "add" || parent.parent.$("#operateType").val() === "copy"){
            $("#prodType").val(parent.parent.$("#newProdType").val()).attr("readonly", true);
         }else if(parent.parent.$("#operateType").val() === "update"){
            $("#prodType").val(parent.parent.$("#prodType").val()).attr("readonly", true);
         }
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                         id: ["glCodeA","glCodeL","glCodeIntE","glCodeALoss","glCodeAdjust","glCodeIntPay","glCodeIntI","glCodeIntRec","glCodeIntAcr","glCodeOdpI","glCodeOdpRec","glCodeOdpAcr","glCodeOdiI","glCodeOdiRec","glCodeOdiAcr"],
                      async: false

                  });

             $(".select2").select2();
	form = $("#glProdAccountingAdd").Validform({
		tiptype:2,
		callback:function(form){
			glProdAccountingAdd();
			return false;
		}
	});
});

function glProdAccountingAdd(){
   var url;
   url = contextPath+"/glProdAccounting/insert?reqNum="+parent.parent.$(".breadcrumb").data("reqNum");
   	sendPostRequest(url,getFormData("glProdAccountingAdd"), callback_glProdAccountingAdd,"json");
}
function callback_glProdAccountingAdd(json){
	if (json.retStatus === 'F'){
		showMsg(json.retMsg,'info');
	} else if(json.retStatus === 'S'){
			var dataTable=parent.$("#glProdAccounting").DataTable();
			dataTable.row.add({
                                        			prodType:$("#prodType").val(),
                                        			accountingStatus:$("#accountingStatus").val(),
                                        			profitCentre:$("#profitCentre").val(),
                                        			businessUnit:$("#businessUnit").val(),
                                        			glCodeA:$("#glCodeA").val(),
                                        			glCodeL:$("#glCodeL").val(),
                                        			glCodeIntE:$("#glCodeIntE").val(),
                                        			glCodeIntPay:$("#glCodeIntPay").val(),
                                        			glCodeIntI:$("#glCodeIntI").val(),
                                        			glCodeIntRec:$("#glCodeIntRec").val(),
                                        			glCodeIntAcr:$("#glCodeIntAcr").val(),
                                        			glCodeOdpI:$("#glCodeOdpI").val(),
                                        			glCodeOdpRec:$("#glCodeOdpRec").val(),
                                        			glCOdeOdpAcr:$("#glCOdeOdpAcr").val(),
                                        			glCodeOdiI:$("#glCodeOdiI").val(),
                                        			glCodeOdiRec:$("#glCodeOdiRec").val(),
                									glCodeALoss:$("#glCodeALoss").val(),
                									glCodeAdjust:$("#glCodeAdjust").val(),
                                        			glCodeOdiAcr:$("#glCodeOdiAcr").val()
                                        		}).draw(false);
		parent.showMsgDuringTime("添加成功");
	}
}

function glProdAccountingAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
