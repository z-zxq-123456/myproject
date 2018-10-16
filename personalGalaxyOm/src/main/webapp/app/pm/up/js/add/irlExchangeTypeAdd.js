
$(document).ready(function() {
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
				id: "company",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
				id: "quoteCcy",
				async: false
			});

	$("#irlExchangeTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlExchangeTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});
 $("#hbdFlag").change(function(){

    var hbdFlag = $("#hbdFlag").val();
    if(hbdFlag==="Y"){

		   $("#quoteCcy").val("").attr("disabled",true);
		   }
		if(hbdFlag==="N"){
           	 $("#quoteCcy").val("").attr("disabled",false);
          }
  });


function irlExchangeTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_EXCHANGE_TYPE";
		keyFieldsJson.RATE_TYPE=$("#rateType").val();
		generalFieldsJson.QUOTE_CCY=$("#quoteCcy").val();
		generalFieldsJson.RATE_TYPE_DESC=$("#rateTypeDesc").val();
		generalFieldsJson.FLOAT_TYPE=$("#floatType").val();

		generalFieldsJson.HBD_FLAG=$("#hbdFlag").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlExchangeTypeAdd,"json");
}

function callback_irlExchangeTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlExchangeTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


