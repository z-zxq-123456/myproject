$(document).ready(function() {
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
				id: "ccy",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_BASIS&tableCol=INT_BASIS,INT_BASIS_DESC",
				id: "intBasis",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
				id: "company",
				async: false
			});

	$("#irlBasisRateAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlBasisRateAdd();
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

	$(".select2").select2();
});

function irlBasisRateAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_BASIS_RATE";
		keyFieldsJson.CCY=$("#ccy").val();
		keyFieldsJson.EFFECT_DATE=$("#effectDate").val();
		keyFieldsJson.INT_BASIS=$("#intBasis").val();
		generalFieldsJson.INT_BASIS_RATE=$("#intBasisRate").val();

	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlBasisRateAdd,"json");
}

function callback_irlBasisRateAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlBasisRateAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


