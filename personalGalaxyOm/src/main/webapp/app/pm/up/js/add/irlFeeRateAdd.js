
$(document).ready(function() {
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
				id: "branch",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
				id: "ccy",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
				id: "company",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_FEE_TYPE&tableCol=FEE_TYPE,FEE_DESC",
				id: "feeType",
				async: false
			});

	$('#branch').append("<option value='ALL'>ALL-全部</option>");
	$("#irlFeeRateAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlFeeRateAdd();
			return false;
		}
	});
    $('#effectDate').blur(function(){
        var value=$('#effectDate').val();
        var effectDate = new Date(value.replace(/^(\d{4})(\d{2})(\d{2})$/,"$1/$2/$3"));
        effectDate.setDate(effectDate.getDate()+1);
        var sysDate = new Date();
        if(effectDate<sysDate){
            alert("生效日期不能小于当前系统日期！");
            $('#effectDate').val("");
        }
    });
/*    $('#highLimit').blur(function(){
        var highLimit=$("#highLimit").val();
        var lowLimit=$("#lowLimit").val();
        if(highLimit<lowLimit){
            alert("上限不能小于下限！");
            $('#highLimit').val("");
        }
    });
    $('#lowLimit').blur(function(){
        var highLimit=$("#highLimit").val();
        var lowLimit=$("#lowLimit").val();
        if(highLimit<lowLimit){
            alert("上限不能小于下限！");
            $('#lowLimit').val("");
        }
    });*/

	$(".select2").select2();
});

function irlFeeRateAdd(){
    var highLimit=$("#highLimit").val();
    var lowLimit=$("#lowLimit").val();
    if(highLimit != ""&& lowLimit !="" && Number(highLimit) < Number(lowLimit)){
        alert("上限不能小于下限！");
        $('#lowLimit').val("");
        $('#highLimit').val("");
        return;
    }
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_FEE_RATE";
		keyFieldsJson.IRL_SEQ_NO=$("#irlSeqNo").val();
		generalFieldsJson.BRANCH=$("#branch").val();
		generalFieldsJson.CCY=$("#ccy").val();

		generalFieldsJson.EFFECT_DATE=$("#effectDate").val();
		generalFieldsJson.FEE_TYPE=$("#feeType").val();
		generalFieldsJson.HIGH_LIMIT=$("#highLimit").val();
		generalFieldsJson.LOW_LIMIT=$("#lowLimit").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlFeeRateAdd,"json");
}

function callback_irlFeeRateAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlFeeRateAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

