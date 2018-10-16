var form;
    $(document).ready(function () {
         if(parent.parent.$("#operateType").val() === "add" || parent.parent.$("#operateType").val() === "copy"){
            $("#prodType").val(parent.parent.$("#newProdType").val()).attr("readonly", true);
         }else if(parent.parent.$("#operateType").val() === "update"){
            $("#prodType").val(parent.parent.$("#prodType").val()).attr("readonly", true);
         }
         getPkList({
           url: contextPath + "/pklist/getParameterPklist?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
                          id: "company",
                          async: false
         });
         getPkList({
             url: contextPath + "/pklist/getParameterPklist?tableName=IRL_AMT_EXPRESSION&tableCol=EXPRESS_ID,EXPRESSION_DESC",
             id: ["intAmtId","rateAmtId"],
             async: false
         });
        getPkList({
            url: contextPath + "/pklist/getParameterPklist?tableName=IRL_INT_TYPE&tableCol=INT_TAX_TYPE,INT_TAX_TYPE_DESC",
            id: "taxType",
            async: false
        });
        $(".select2").select2();
        form = $("#irlProdIntAdd").Validform({
            tiptype: 2,
            callback: function (form) {
                irlProdIntAdd();
                return false;
            }
        });
    });
    function irlProdIntAdd(){
    	var url = contextPath+"/irlProdInt/insert?reqNum="+parent.parent.$(".breadcrumb").data("reqNum");
    	sendPostRequest(url,getFormData("irlProdIntAdd"), callback_irlProdIntAdd,"json");
    }

    function callback_irlProdIntAdd(json){
    	if (json.retStatus === 'F'){
    	   	showMsg(json.retMsg,'info');
    	} else if(json.retStatus === 'S'){
    			var dataTable=parent.$('#irlProdInt').DataTable();
    		    dataTable.row.add({
                                 	            prodType:$("#prodType").val(),
                                     			eventType:$("#eventType").val(),
                                     			intClass:$("#intClass").val(),
                                     			intAmtId:$("#intAmtId").val(),
                                     			intCalcBal:$("#intCalcBal").val(),
                                     			intDaysType:$("#intDaysType").val(),
                                     			intStart:$("#intStart").val(),
                                     			intType:$("#intType").val(),
                                     			rateAmtId:$("#rateAmtId").val(),
                                     			recalMethod:$("#recalMethod").val(),
                                     			taxType:$("#taxType").val(),
                                     			company:$("#company").val(),
                                     			intApplType:$("#intApplType").val(),
                                     			rollFreq:$("#rollFreq").val(),
                                     			rollDay:$("#rollDay").val(),
                                     			maxRate:$("#maxRate").val(),
                                     			minRate:$("#minRate").val(),
                                     			monthBasis:$("#monthBasis").val(),
                                     			intRateInd:$("#intRateInd").val(),
                                     			prodMatchType:$("#prodMatchType").val(),
                                     			groupRuleType:$("#groupRuleType").val(),
                                                splitId:$("#splitId").val(),
                                                splitType:$("#splitType").val(),
                                                ruleId:$("#ruleId").val()
                       		}).draw(false);
                       		    		parent.showMsgDuringTime("添加成功");
    	}
    	//form.resetForm();  JS表单清空
    }

    function irlProdIntAddCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }
