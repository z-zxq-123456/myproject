
$(document).ready(function() {
     $("#dayNum").attr("disabled","disabled");
       $("#irlSeqNo").attr("disabled","disabled");
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
				id: "company",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_PERIOD_FREQ&tableCol=PERIOD_FREQ,PERIOD_FREQ_DESC",
				id: "periodFreq",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_BASIS&tableCol=INT_BASIS,INT_BASIS_DESC",
				id: "intBasis",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_TYPE&tableCol=INT_TAX_TYPE,INT_TAX_TYPE_DESC",
				id: "subIntType",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
				id: "ccy",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
				id: "branch",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_TYPE&tableCol=INT_TAX_TYPE,INT_TAX_TYPE_DESC",
				id: "intType",
				async: false
			});

	$("#irlIntMatrixAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlIntMatrixAdd();
			return false;
		}
	});
    $('#minRate').blur(function(){
        var maxRate=$("#maxRate").val();
        var minRate=$("#minRate").val();
        var baseRate=$("#baseRate").val();
        var actualRate=$("#actualRate").val();
        if(maxRate !=="" && minRate !=="") {
            if (parseFloat(maxRate) < parseFloat(minRate)) {
                alert("最小利率不能大于最大利率！");
                $('#minRate').val("");
            }
        }
        if(minRate !=="" && baseRate !=="") {
            if (parseFloat(baseRate) < parseFloat(minRate)) {
                alert("最小利率不能大于基准利率！");
                $('#minRate').val("");
            }
        }
        if(minRate !=="" && actualRate !=="") {
            if (parseFloat(actualRate) < parseFloat(minRate)) {
                alert("最小利率不能大于实际利率！");
                $('#minRate').val("");
            }
        }
    });
    $('#maxRate').blur(function(){
        var maxRate=$("#maxRate").val();
        var minRate=$("#minRate").val();
        var baseRate=$("#baseRate").val();
        var actualRate=$("#actualRate").val();
        if(maxRate !=="" && baseRate !=="") {
            if (parseFloat(maxRate) < parseFloat(baseRate)) {
                alert("最大利率不能小于基准利率！");
                $('#maxRate').val("");
            }
        }
        if(maxRate !=="" && minRate !=="") {
            if (parseFloat(maxRate) < parseFloat(minRate)) {
                alert("最大利率不能小于最小利率！");
                $('#maxRate').val("");
            }
        }
        if(maxRate !=="" && actualRate !=="") {
            if (parseFloat(maxRate) < parseFloat(actualRate)) {
                alert("最大利率不能小于实际利率！");
                $('#maxRate').val("");
            }
        }
    });
    $('#baseRate').blur(function(){
        var maxRate=$("#maxRate").val();
        var minRate=$("#minRate").val();
        var baseRate=$("#baseRate").val();
        if(maxRate !=="" && baseRate !=="") {
            if (parseFloat(baseRate) > parseFloat(maxRate)) {
                alert("基准利率不能大于最大利率！");
                $('#baseRate').val("");
            }
        }
        if(minRate !=="" && baseRate !=="") {
            if (parseFloat(baseRate) < parseFloat(minRate)) {
                alert("基准利率不能小于最小利率！");
                $('#baseRate').val("");
            }
        }
    });
    $('#actualRate').blur(function(){
        var maxRate=$("#maxRate").val();
        var minRate=$("#minRate").val();
        var actualRate=$("#actualRate").val();
        var baseRate=$("#baseRate").val();
        if(actualRate !=="" && maxRate !=="") {
            if (parseFloat(actualRate) > parseFloat(maxRate)) {
                alert("实际利率不能大于最大利率！");
                $('#actualRate').val("");
            }
        }
        if(minRate !=="" && actualRate !=="") {
            if (parseFloat(actualRate) < parseFloat(minRate)) {
                alert("实际利率不能小于最小利率！");
                $('#actualRate').val("");
            }
        }
    });
    $('#spreadRate').blur(function(){
        var spreadRate=$("#spreadRate").val();
        var spreadPercent=$("#spreadPercent").val();
        if(spreadRate !=="" && spreadPercent !=="") {
			alert("浮动点数/利率浮动百分比只能输入其一！");
			$('#spreadRate').val("");
            $('#spreadPercent').val("");
        }
    });
    $('#spreadPercent').blur(function(){
        var spreadRate=$("#spreadRate").val();
        var spreadPercent=$("#spreadPercent").val();
        if(spreadRate !=="" && spreadPercent !=="") {
            alert("浮动点数/利率浮动百分比只能输入其一！");
            $('#spreadRate').val("");
            $('#spreadPercent').val("");
        }
    });
	$(".select2").select2();

 $("#periodFreq").change(function(){
  		var periodFreq=$("#periodFreq").val();
  		if(periodFreq !== "") {
            var url = contextPath + "/baseCommon/selectBase";
            sendPostRequest(url, {
                    col1: "PERIOD_FREQ",
                    colV1: periodFreq,
                    col2: "",
                    colV2: "",
                    col3: "",
                    colV3: "",
                    tableName: "FM_PERIOD_FREQ"
                },
                function (json) {
                    var attr = json.data[0].DAY_NUM;
                    $("#dayNum").val(attr);
                },
                "json");
        }else {
            $("#dayNum").val("");
        }
           });
});





function getIntNo(){
		var intType,ccy,effectDate,branch,irlSeqNo,flag;
    		flag = 0;
    		intType = $("#intType").val();
    		ccy = $("#ccy").val();
    		effectDate = $("#effectDate").val();
    		branch = $("#branch").val();

    			var url3 = contextPath + "/baseCommon/selectBase";
            		sendPostRequest(url3, {

            	   col1:"INT_TYPE",
            	   colV1:intType,
            		  col2:"CCY",
            		  colV2:ccy,
            		 col3:"BRANCH",
            		 colV3:branch,
            		   tableName: "IRL_INT_RATE"
            		},
            		function(json) {
            				var size = json.data.length;
            					for( var i = 0; i < size; i++ ){
            						var effectDate1=json.data[i].EFFECT_DATE;
            						var	endDate1=json.data[i].END_DATE;
             						if(effectDate>=effectDate1&&effectDate<=endDate1){
                                            flag =json.data[i].IRL_SEQ_NO;
                                            return flag;

            					}
            					}
            		},
            		"json",false);

	$("#irlSeqNo").val(flag);

}



function irlIntMatrixAdd(){

	var subIntType=$("#subIntType").val();
	var intType=$("#intType").val();
    var actualRate=$("#actualRate").val();
    var intBasis=$("#intBasis").val();
	if(subIntType===intType){
	showMsg("利率类型与子利率类型不得相等，请确认！");
     return;
	}
    if (actualRate !== "") {
        if(subIntType !== "" || intBasis !== "") {
            alert("基准利率类型/实际利率/子利率类型 只能输入其一！");
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_BASIS&tableCol=INT_BASIS,INT_BASIS_DESC",
                id: "intBasis",
                async: false
            });
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_TYPE&tableCol=INT_TAX_TYPE,INT_TAX_TYPE_DESC",
                id: "subIntType",
                async: false
            });
            $('#actualRate').val("");
            $('#subIntType').val("");
            $('#intBasis').val("");
            return;
        }
    }
    if (intBasis !== "") {
        if(subIntType !== "" || actualRate !== "") {
            alert("基准利率类型/实际利率/子利率类型 只能输入其一！");
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_BASIS&tableCol=INT_BASIS,INT_BASIS_DESC",
                id: "intBasis",
                async: false
            });
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_TYPE&tableCol=INT_TAX_TYPE,INT_TAX_TYPE_DESC",
                id: "subIntType",
                async: false
            });
            $('#actualRate').val("");
            $('#subIntType').val("");
            $('#intBasis').val("");
            return;
        }
    }
    if (subIntType !== "") {
	    if(intBasis !== "" || actualRate !== "") {
            alert("基准利率类型/实际利率/子利率类型 只能输入其一！");
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_BASIS&tableCol=INT_BASIS,INT_BASIS_DESC",
                id: "intBasis",
                async: false
            });
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_TYPE&tableCol=INT_TAX_TYPE,INT_TAX_TYPE_DESC",
                id: "subIntType",
                async: false
            });
            $('#actualRate').val("");
            $('#subIntType').val("");
            $('#intBasis').val("");
            return;
        }
    }
    if (subIntType == "" && intBasis == "" && actualRate == "") {
        alert("基准利率类型/实际利率/子利率类型 必须输入其一！");
        return;
    }

	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_INT_MATRIX";
		keyFieldsJson.MATRIX_NO=$("#matrixNo").val();
		generalFieldsJson.MATRIX_AMT=$("#matrixAmt").val();
		generalFieldsJson.IRL_SEQ_NO=$("#irlSeqNo").val();
		generalFieldsJson.DAY_NUM=$("#dayNum").val();
		generalFieldsJson.INT_BASIS=$("#intBasis").val();
		generalFieldsJson.IS_OVER=$("#isOver").val();
		generalFieldsJson.ACTUAL_RATE=$("#actualRate").val();
		generalFieldsJson.SUB_INT_TYPE=$("#subIntType").val();
		generalFieldsJson.MIN_RATE=$("#minRate").val();
		generalFieldsJson.PERIOD_FREQ=$("#periodFreq").val();
		generalFieldsJson.SPREAD_RATE=$("#spreadRate").val();
		generalFieldsJson.SPREAD_PERCENT=$("#spreadPercent").val();
		generalFieldsJson.BASE_RATE=$("#baseRate").val();
		generalFieldsJson.MAX_RATE=$("#maxRate").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlIntMatrixAdd,"json");
}

function callback_irlIntMatrixAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlIntMatrixAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


