$(document).ready(function() {

	var paraJson, keyFieldsJson;
	paraJson = {};
	keyFieldsJson = {};
	paraJson.tableName = "FM_REF_CODE";
	paraJson.tableCol="FIELD_VALUE,MEANING";

	keyFieldsJson.DOMAIN = "MARKET_TYPE";
	paraJson.key = keyFieldsJson;
	getPkList({
		url:contextPath+"/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
		id: "marketType",
		async: false

	});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
				id: "company",
				async: false
			});

	$("#irlMarketFactorInfoAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlMarketFactorInfoAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function irlMarketFactorInfoAdd(){

			var flag=0;
			flag=submitCheck();
    		if(flag===1){
    		showMsg("失效日期必须大于生效日期", 'error');
    		}
    		if(flag===2){
    		showMsg("市场指数类型代码、生效日期、失效日期存在重复记录", 'error');
    		}
    		if(flag===3){
    		showMsg("所选市场指数类型代码生效日期与失效日期存在交叉记录。", 'error');
    		}

    		var url1 ,irlSeqNo; //自动生成利率序号

    		url1= contextPath + "/baseCommon/getMaxAutoAddID";
    		sendPostRequest(url1,
    				{
    					tableName: "IRL_MARKET_FACTOR_INFO",
    					fieldName:  "IRL_SEQ_NO"
    				}, function (json) {
    					var maxId = json;
    					irlSeqNo = maxId;
    		},"json",false);

if(flag===0){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_MARKET_FACTOR_INFO";
		keyFieldsJson.IRL_SEQ_NO=irlSeqNo;
		generalFieldsJson.EFFECT_DATE=$("#effectDate").val();
		generalFieldsJson.MARKET_TYPE=$("#marketType").val();
		generalFieldsJson.MARKET_VALUE=$("#marketValue").val();
		generalFieldsJson.END_DATE=$("#endDate").val();
		generalFieldsJson.FAC_STATUS=$("#facStatus").val();
		generalFieldsJson.REMARK=$("#remark").val();

	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlMarketFactorInfoAdd,"json");
}
}
function callback_irlMarketFactorInfoAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlMarketFactorInfoAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

function submitCheck(){
		var endDate=$("#endDate").val();
		var marketType=$("#marketType").val();
		var effectDate=$("#effectDate").val();
		var theNo;
		var no=0;
		if(endDate<=effectDate){
			no=1;//失效日期必须大于生效日期
			return no;
		}

		//MARKET_TYPE、EFFECT_DATE、END_DATE必须唯一
		var url2 = contextPath + "/baseCommon/getMaxAutoAddID";
		 params={
					tableName: "IRL_MARKET_FACTOR_INFO",
					fieldName: "IRL_SEQ_NO",
					inqueryCondition: "MARKET_TYPE='"+marketType+"' "+"AND EFFECT_DATE='"+effectDate+"'"+"AND END_DATE='"+endDate+"'"};

		sendPostRequest(url2,params, function (json) {
					var maxId = json;
					theNo = maxId;

		},"json",false);
		if(theNo!==1){
			no= 2;//市场指数类型代码、生效日期、失效日期存在重复记录
				return no;
		}


		//相同的MARKET_TYPE的生效日期与失效日期不能存在交叉记录。
		var url3 = contextPath + "/baseCommon/selectBase";
		sendPostRequest(url3, {

	   col1:"MARKET_TYPE",
	   colV1:marketType,
	   col2:"",
	   colV2:"",
	   col3:"",
       colV3:"",
		   tableName: "IRL_MARKET_FACTOR_INFO"
		},
		function(json) {
				var size = json.data.length;
					for( var i = 0; i < size; i++ ){
						var effectDate0=effectDate;
						var	endDate0=endDate;
						var	effectDate1=json.data[i].EFFECT_DATE;
						var	endDate1=json.data[i].END_DATE;
							if((endDate1>=endDate0&&endDate0>=effectDate1)||(endDate1>=effectDate0&&effectDate0>=effectDate1)||(endDate1<endDate0&&effectDate0<effectDate1)){
                                no= 3;
                                //所选市场指数类型代码生效日期与失效日期存在交叉记录。
                                	}

					}
		},
		"json",false);
return no;
}
