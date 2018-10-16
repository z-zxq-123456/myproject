var form;
	var rowData;
$(document).ready(function() {

       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: ["glCodeA","glCodeL","glCodeIntE","glCodeALoss","glCodeAdjust","glCodeIntPay","glCodeIntI","glCodeIntRec","glCodeIntAcr","glCodeOdpI","glCodeOdpRec","glCodeOdpAcr","glCodeOdiI","glCodeOdiRec","glCodeOdiAcr"],
                                   async: false
                  });
	if (parent.$("#glProdAccounting").find(".selected").length===1){
		rowData = parent.$('#glProdAccounting').DataTable().rows(".selected").data()[0];
              if(parent.parent.$("#operateType").val()==="copy"){
              $("#prodType").val(parent.parent.$("#newProdType").val()).attr("disabled", true);
              }else{
               $("#prodType").val(rowData.prodType).attr("disabled",true);
               }
               $("#accountingStatus").val(rowData.accountingStatus).attr("disabled",true);
	           $("#profitCentre").val(rowData.profitCentre);
		       $("#businessUnit").val(rowData.businessUnit);
		       $("#glCodeA").val(rowData.glCodeA);
		       $("#glCodeL").val(rowData.glCodeL);
	           $("#glCodeIntE").val(rowData.glCodeIntE);
		       $("#glCodeIntPay").val(rowData.glCodeIntPay);
		       $("#glCodeIntI").val(rowData.glCodeIntI);
		       $("#glCodeIntRec").val(rowData.glCodeIntRec);
		       $("#glCodeIntAcr").val(rowData.glCodeIntAcr);
		       $("#glCodeOdpI").val(rowData.glCodeOdpI);
		       $("#glCodeALoss").val(rowData.glCodeALoss);
		       $("#glCodeAdjust").val(rowData.glCodeAdjust);
		       $("#glCodeOdpRec").val(rowData.glCodeOdpRec);
		       $("#glCodeOdpAcr").val(rowData.glCodeOdpAcr);
		       $("#glCodeOdiI").val(rowData.glCodeOdiI);
		       $("#glCodeOdiRec").val(rowData.glCodeOdiRec);
		       $("#glCodeOdiAcr").val(rowData.glCodeOdiAcr);
			}
	  $("#glProdAccountingMod").Validform({
            tiptype:2,
            callback:function(form){
                glProdAccountingMod();
                return false;
            }
        });
　　　　	 	$(".select2").select2();
});
function glProdAccountingMod(){
   var url= contextPath+"/glProdAccounting/update";
           sendPostRequest(url,{    prodType:$("#prodType").val(),
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
                                    glCodeOdpAcr:$("#glCodeOdpAcr").val(),
                                    glCodeOdiI:$("#glCodeOdiI").val(),
                                    glCodeOdiRec:$("#glCodeOdiRec").val(),
                                    glCodeOdiAcr:$("#glCodeOdiAcr").val(),
								    glCodeALoss:$("#glCodeALoss").val(),
								    glCodeAdjust:$("#glCodeAdjust").val(),
                                    reqNum:parent.parent.$(".breadcrumb").data("reqNum")
                    }, callback_glProdAccountingMod,"json");
}
function callback_glProdAccountingMod(json){
	if (json.retStatus === 'F'){
		showMsg(json.retMsg,'info');
	} else if(json.retStatus === 'S'){
		var dataTable=parent.$("#glProdAccounting").DataTable();
                    dataTable.row(".selected").remove().draw(false);
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
                            			glCodeOdiAcr:$("#glCodeOdiAcr").val()
                            		}).draw(false);
		parent.showMsgDuringTime("修改成功");
	}
}

function glProdAccountingModCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
