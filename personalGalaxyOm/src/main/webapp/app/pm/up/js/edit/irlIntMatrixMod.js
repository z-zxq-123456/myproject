
var rowData;
$(document).ready(function() {
     $("#dayNum").attr("disabled","disabled");
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
    if (parent.$("#irlIntMatrix").find(".selected").length===1){
        rowData = parent.$('#irlIntMatrix').DataTable().rows(".selected").data()[0];
            $("#matrixNo").val(rowData.MATRIX_NO).attr("disabled",true);
            $("#matrixAmt").val(rowData.MATRIX_AMT);
            $("#irlSeqNo").val(rowData.IRL_SEQ_NO);
            $("#company").val(rowData.COMPANY);
            $("#dayNum").val(rowData.DAY_NUM);
            $("#intBasis").val(rowData.INT_BASIS);
            $("#isOver").val(rowData.IS_OVER);
            $("#actualRate").val(rowData.ACTUAL_RATE);
            $("#subIntType").val(rowData.SUB_INT_TYPE);
            $("#minRate").val(rowData.MIN_RATE);
            $("#periodFreq").val(rowData.PERIOD_FREQ);
            $("#spreadRate").val(rowData.SPREAD_RATE);
            $("#spreadPercent").val(rowData.SPREAD_PERCENT);
            $("#baseRate").val(rowData.BASE_RATE);
            $("#maxRate").val(rowData.MAX_RATE);
    }

    $("#irlIntMatrixMod").Validform({
        tiptype:2,
        callback:function(form){
            irlIntMatrixMod();
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
            }
        }
        if(minRate !=="" && baseRate !=="") {
            if (parseFloat(baseRate) < parseFloat(minRate)) {
                alert("最小利率不能大于基准利率！");
            }
        }
        if(minRate !=="" && actualRate !=="") {
            if (parseFloat(actualRate) < parseFloat(minRate)) {
                alert("最小利率不能大于实际利率！");
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
            }
        }
        if(maxRate !=="" && minRate !=="") {
            if (parseFloat(maxRate) < parseFloat(minRate)) {
                alert("最大利率不能小于最小利率！");
            }
        }
        if(maxRate !=="" && actualRate !=="") {
            if (parseFloat(maxRate) < parseFloat(actualRate)) {
                alert("最大利率不能小于实际利率！");
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
            }
        }
        if(minRate !=="" && baseRate !=="") {
            if (parseFloat(baseRate) < parseFloat(minRate)) {
                alert("基准利率不能小于最小利率！");
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
            }
        }
        if(minRate !=="" && actualRate !=="") {
            if (parseFloat(actualRate) < parseFloat(minRate)) {
                alert("实际利率不能小于最小利率！");
            }
        }
    });
    $('#spreadRate').blur(function(){
        var spreadRate=$("#spreadRate").val();
        var spreadPercent=$("#spreadPercent").val();
        if(spreadRate !=="" && spreadPercent !=="") {
            alert("浮动点数/利率浮动百分比只能输入其一！");
        }
    });
    $('#spreadPercent').blur(function(){
        var spreadRate=$("#spreadRate").val();
        var spreadPercent=$("#spreadPercent").val();
        if(spreadRate !=="" && spreadPercent !=="") {
            alert("浮动点数/利率浮动百分比只能输入其一！");
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


function irlIntMatrixMod(){
    var actualRate=$("#actualRate").val();
    var intBasis=$("#intBasis").val();
    var subIntType=$("#subIntType").val();
	var intType=$("#intType").val();
	if(subIntType===intType){
	showMsg("利率类型与子利率类型不得相等，请确认！");
     return;
	}
    if (subIntType !== "") {
	    if( intBasis !== "" || actualRate !== "") {
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
        if( subIntType !== "" || actualRate !== "") {
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
    if (actualRate !== "") {
        if( subIntType !== "" || intBasis !== "") {
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
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="IRL_INT_MATRIX";
        keyFieldsJson.MATRIX_NO=$("#matrixNo").val();
        generalFieldsJson.MATRIX_AMT=$("#matrixAmt").val();
        generalFieldsJson.IRL_SEQ_NO=$("#irlSeqNo").val();


        generalFieldsJson.DAY_NUM=$("#dayNum").val();
        generalFieldsJson.INT_BASIS=$("#intBasis").val();
        generalFieldsJson.IS_OVER=$("#isOver").val();
        generalFieldsJson.ACTUAL_RATE=parseFloat($("#actualRate").val());
        generalFieldsJson.SUB_INT_TYPE=$("#subIntType").val();
        generalFieldsJson.MIN_RATE=parseFloat($("#minRate").val());
        generalFieldsJson.PERIOD_FREQ=$("#periodFreq").val();
        generalFieldsJson.SPREAD_RATE=parseFloat($("#spreadRate").val());
        generalFieldsJson.SPREAD_PERCENT=parseFloat($("#spreadPercent").val());
        generalFieldsJson.BASE_RATE=parseFloat($("#baseRate").val());
        generalFieldsJson.MAX_RATE=parseFloat($("#maxRate").val());
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlIntMatrixMod,"json");
}

function callback_irlIntMatrixMod(json){
    if (json.success) {
        var dataTable=parent.$("#irlIntMatrix").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            MATRIX_NO:$("matrixNo").val(),
            MATRIX_AMT:$("matrixAmt").val(),
            IRL_SEQ_NO:$("irlSeqNo").val(),
            DAY_NUM:$("dayNum").val(),
            INT_BASIS:$("intBasis").val(),
            IS_OVER:$("isOver").val(),
            ACTUAL_RATE:$("actualRate").val(),
            SUB_INT_TYPE:$("subIntType").val(),
            MIN_RATE:parseFloat($("minRate").val()),
            PERIOD_FREQ:$("periodFreq").val(),
            SPREAD_RATE:parseFloat($("spreadRate").val()),
            SPREAD_PERCENT:parseFloat($("spreadPercent").val()),
            BASE_RATE:parseFloat($("baseRate").val()),
            MAX_RATE:parseFloat($("maxRate").val()),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlIntMatrixModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
