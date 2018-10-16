var form;
$(document).ready(function() {
	var rowData;
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeA",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeL",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeIntE",
                                   async: false

                  });
      getPkList({
          url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
          id: "glCodeALoss",
          async: false
      });
      getPkList({
          url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
          id: "glCodeAdjust",
          async: false
      });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeIntPay",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeIntI",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeIntRec",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeIntAcr",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeOdpI",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeOdpRec",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeOdpAcr",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeOdiI",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeOdiRec",
                                   async: false

                  });
       getPkList({
						url: contextPath + "/pklist/getParameterPklist?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
                                   id: "glCodeOdiAcr",
                                   async: false

                  });

	if (parent.$("#glProdAccounting").find(".selected").length==1){
		rowData = parent.$('#glProdAccounting').DataTable().rows(".selected").data()[0];
	}
	if (rowData){
               $("#prodType").val(rowData.prodType).attr("disabled",true);
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
			 	$(".select2").select2();
		form = $("#glProdAccountingMod").Validform({
			tiptype:2,
			callback:function(form){
				glProdAccountingMod();
				return false;
			}
		});

});
function glProdAccountingMod(){
	var url = contextPath+"/glProdAccounting/update1";
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
                                    glCodeALoss:$("#glCodeALoss").val(),
                                    glCodeAdjust:$("#glCodeAdjust").val(),
                                    glCodeOdiI:$("#glCodeOdiI").val(),
                                    glCodeOdiRec:$("#glCodeOdiRec").val(),
                                    glCodeOdiAcr:$("#glCodeOdiAcr").val()
                    }, callback_glProdAccountingMod,"json");
}
function callback_glProdAccountingMod(json){
	if (json.retStatus == 'F'){
		showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
		parent.showMsgDuringTime("修改成功");
	}
}

function glProdAccountingModCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
