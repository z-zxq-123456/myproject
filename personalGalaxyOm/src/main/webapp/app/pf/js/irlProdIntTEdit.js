 var form;
    $(document).ready(function (){
         getPkList({
           url: contextPath + "/pklist/getParameterPklist?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
                          id: "company",
                          async: false,
         });

        getPkList({
            url: contextPath + "/pklist/getParameterPklist?tableName=IRL_AMT_EXPRESSION&tableCol=EXPRESS_ID,EXPRESSION_DESC",
            id: "intAmtId",
            async: false,
        });
        getPkList({
            url: contextPath + "/pklist/getParameterPklist?tableName=IRL_AMT_EXPRESSION&tableCol=EXPRESS_ID,EXPRESSION_DESC",
            id: "rateAmtId",
            async: false,
        });
        getPkList({
            url: contextPath + "/pklist/getParameterPklist?tableName=IRL_INT_TYPE&tableCol=INT_TYPE,INT_TYPE_DESC",
            id: "taxType",
            async: false,
        });
        var rowData;
        if (parent.$("#irlProdInt").find(".selected").length == 1) {
            rowData = parent.$('#irlProdInt').DataTable().rows(".selected").data()[0];
        }
        if (rowData) {
            $("#eventType").val(rowData.eventType).attr("disabled", true);
            $("#intClass").val(rowData.intClass).attr("disabled", true);
            $("#prodType").val(rowData.prodType).attr("disabled", true);

            $("#company").val(rowData.company);


            $("#intAmtId").val(rowData.intAmtId);


            $("#intCalcBal").val(rowData.intCalcBal);


            $("#intDaysType").val(rowData.intDaysType);


            $("#intStart").val(rowData.intStart);


            $("#intType").val(rowData.intType);


            $("#rateAmtId").val(rowData.rateAmtId);


            $("#recalMethod").val(rowData.recalMethod);
            $("#taxType").val(rowData.taxType);
            $("#intApplType").val(rowData.intApplType);
            $("#maxRate").val(rowData.maxRate);
            $("#minRate").val(rowData.minRate);
            $("#rollDay").val(rowData.rollDay);
            $("#rollFreq").val(rowData.rollFreq);
            $("#monthlyBasis").val(rowData.monthlyBasis);
            $("#intRateInd").val(rowData.intRateInd);

        }
        $(".select2").select2();
        form = $("#irlProdIntMod").Validform({
            tiptype: 2,
            callback: function (form) {
                irlProdIntMod();
                return false;
            }
        });
    });
function irlProdIntMod(){
	var rowData = parent.$('#irlProdInt').DataTable().rows(".selected").data()[0];
	var url = contextPath+"/irlProdInt/update1";
	sendPostRequest(url,{
		eventType:$("#eventType").val(),
		intClass:$("#intClass").val(),
		prodType:$("#prodType").val(),
		company:$("#company").val(),
		intAmtId:$("#intAmtId").val(),
		intCalcBal:$("#intCalcBal").val(),
		intDaysType:$("#intDaysType").val(),
        intStart:$("#intStart").val(),
        intType:$("#intType").val(),
        rateAmtId:$("#rateAmtId").val(),
        recalMethod:$("#recalMethod").val(),
        taxType:$("#taxType").val(),
        intApplType:$("#intApplType").val(),
        monthlyBasis:$("#monthlyBasis").val(),
        maxRate:$("#maxRate").val(),
        minRate:$("#minRate").val(),
        rollDay:$("#rollDay").val(),
        rollFreq:$("#rollFreq").val(),
        intRateInd:$("#intRateInd").val()
	}, callback_irlProdIntMod,"json");
}
function callback_irlProdIntMod(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
        parent.showMsgDuringTime("修改成功");
	}
}

    function irlProdIntModCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }

