var form;
$(document).ready(function() {
	var rowData;
        getPkList({
                           url: contextPath + "/pklist/getParameterPklist?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
                                          id: "branch",
                                          async: false

                         });
        getPkList({
                           url: contextPath + "/pklist/getParameterPklist?tableName=CIF_CATEGORY_TYPE&tableCol=CATEGORY_TYPE,CATEGORY_DESC",
                                          id: "categoryType",
                                          async: false

                         });
        getPkList({
                           url: contextPath + "/pklist/getParameterPklist?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
                                          id: "ccy",
                                          async: false

                         });
        getPkList({
                           url: contextPath + "/pklist/getParameterPklist?tableName=CIF_CLIENT_TYPE&tableCol=CLIENT_TYPE,CLIENT_TYPE_DESC",
                                          id: "clientType",
                                          async: false

                         });
        getPkList({
                           url: contextPath + "/pklist/getParameterPklist?tableName=IRL_PROD_TYPE&tableCol=PROD_TYPE,PROD_TYPE_DESC",
                                          id: "prodType",
                                          async: false

                         });
        getPkList({
                           url: contextPath + "/pklist/getParameterPklist?tableName=FM_CHANNEL&tableCol=CHANNEL,CHANNEL_DESC",
                                          id: "sourceType",
                                          async: false

                         });
        getPkList({
                           url: contextPath + "/pklist/getParameterPklist?tableName=MB_TRAN_DEF&tableCol=TRAN_TYPE,TRAN_DESC",
                                          id: "tranType",
                                          async: false

                         });

	if (parent.$("#irlFeeMapping").find(".selected").length==1){
		rowData = parent.$('#irlFeeMapping').DataTable().rows(".selected").data()[0];
	}
	if (rowData){
               $("#feeNo").val(rowData.feeNo).attr("disabled",true);

		$("#areaCode").val(rowData.areaCode);


		$("#branch").val(rowData.branch);


		$("#categoryType").val(rowData.categoryType);


		$("#ccy").val(rowData.ccy);


		$("#clientType").val(rowData.clientType);


		$("#company").val(rowData.company);


		$("#docType").val(rowData.docType);


		$("#eventType").val(rowData.eventType);


		$("#feeType").val(rowData.feeType);


		$("#isLocal").val(rowData.isLocal);


		$("#isRule").val(rowData.isRule);


		$("#newStatus").val(rowData.newStatus);


		$("#oldStatus").val(rowData.oldStatus);


		$("#prodGrp").val(rowData.prodGrp);


		$("#prodType").val(rowData.prodType);


		$("#serviceId").val(rowData.serviceId);


		$("#sourceType").val(rowData.sourceType);


		$("#tranType").val(rowData.tranType);


		$("#urgentFlag").val(rowData.urgentFlag);

			}
			 	$(".select2").select2();
		form = $("#irlFeeMappingMod").Validform({
			tiptype:2,
			callback:function(form){
				irlFeeMappingMod();
				return false;
			}
		});

});
    function irlFeeMappingMod(){
        var url = contextPath+"/irlFeeMapping/update1";
        sendPostRequest(url,{
                  	        feeNo:$("#feeNo").val(),
                         	areaCode:$("#areaCode").val(),
                         	branch:$("#branch").val(),
                         	categoryType:$("#categoryType").val(),
                         	ccy:$("#ccy").val(),
                         	clientType:$("#clientType").val(),
                         	company:$("#company").val(),
                         	docType:$("#docType").val(),
                         	eventType:$("#eventType").val(),
                         	feeType:$("#feeType").val(),
                         	isLocal:$("#isLocal").val(),
                         	isRule:$("#isRule").val(),
                         	newStatus:$("#newStatus").val(),
                         	oldStatus:$("#oldStatus").val(),
                         	prodGrp:$("#prodGrp").val(),
                         	prodType:$("#prodType").val(),
                         	serviceId:$("#serviceId").val(),
                         	sourceType:$("#sourceType").val(),
                         	tranType:$("#tranType").val(),
                         	urgentFlag:$("#urgentFlag").val()
        }, callback_irlFeeMappingMod,"json");
    }
function callback_irlFeeMappingMod(json){
	if (json.retStatus == 'F'){
		showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
		parent.showMsgDuringTime("修改成功");
	}
}

function irlFeeMappingModCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
