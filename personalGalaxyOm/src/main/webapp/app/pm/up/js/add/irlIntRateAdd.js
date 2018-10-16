
$(document).ready(function() {
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
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
				id: "company",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_TYPE&tableCol=INT_TAX_TYPE,INT_TAX_TYPE_DESC",
				id: "intType",
				async: false
			});

	$("#irlIntRateAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlIntRateAdd();
			return false;
		}
	});
	$(".select2").select2();
});


function irlIntRateAdd(){
		var flag = 0;
		flag = submitCheck();
		if(flag===1){
		showMsg("失效日期必须大于生效日期", 'error');
		}
		if(flag===2){
		showMsg("利率类型代码、币种、生效日期、失效日期、机构存在重复记录", 'error');
		}
		if(flag===3){
		showMsg("所选利率序号的 相同的利率类型代码、币种、机构的生效日期与失效日期存在交叉记录。", 'error');
		}
		if(flag===0){
				var url1 ,irlSeqNo; //自动生成利率序号

				url1= contextPath + "/baseCommon/getMaxAutoAddID";
				sendPostRequest(url1,
						{
							tableName: "IRL_INT_RATE",
							fieldName:  "IRL_SEQ_NO"
						}, function (json) {
							var maxId = json;
							irlSeqNo = maxId;
				},"json",false);
			var paraJson,generalFieldsJson,keyFieldsJson;
			paraJson = {};
			generalFieldsJson={};
			keyFieldsJson={};
			var url = contextPath+"/baseCommon/updateAndInsertForSave";
			paraJson.tableName="IRL_INT_RATE";
				keyFieldsJson.IRL_SEQ_NO=irlSeqNo;
				generalFieldsJson.CCY=$("#ccy").val();
				generalFieldsJson.YEAR_BASIS=$("#yearBasis").val();
				generalFieldsJson.BRANCH=$("#branch").val();
				generalFieldsJson.EFFECT_DATE=$("#effectDate").val();
				generalFieldsJson.INT_TYPE=$("#intType").val();
				generalFieldsJson.END_DATE=$("#endDate").val();
				generalFieldsJson.LAST_CHG_RUN_DATE=$("#lastChgRunDate").val();
			paraJson.key = keyFieldsJson;
			paraJson.general=generalFieldsJson;
			paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
			var params = {
				paraJson:JSON.stringify(paraJson)
			};
			sendPostRequest(url,params, callback_irlIntRateAdd,"json");
		}
}

function callback_irlIntRateAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}


function submitCheck(){
		var intType,ccy,effectDate,endDate,branch,irlSeqNo,flag;
		flag = 0;
		intType = $("#intType").val();
		ccy = $("#ccy").val();
		effectDate = $("#effectDate").val();
		endDate = $("#endDate").val();
		branch = $("#branch").val();

		if(endDate<=effectDate){
		    flag = 1;
			return flag;//失效日期必须大于生效日期IRL_INT_TYPE
		}

		//INT_TYPE、CCY、EFFECT_DATE、END_DATE、BRANCH必须唯一
		var url2 = contextPath + "/baseCommon/getMaxAutoAddID";
         		 params={
         					tableName: "IRL_INT_RATE",
         					fieldName: "IRL_SEQ_NO",
         					inqueryCondition: "INT_TYPE='"+intType+"' "+"AND CCY='"+ccy+"'"+"AND BRANCH='"
         										+branch+"'"+"AND EFFECT_DATE='"+effectDate+"'"+"AND END_DATE='"+endDate+"'"};

         		sendPostRequest(url2,params, function (json) {
         					var maxId = json;
         					rateNo = maxId;

         		},"json",false);
         		if(rateNo!==1){
         		    flag = 2;
         			return flag;//利率类型代码、币种、生效日期、失效日期存在重复记录
         		}


		//相同的INT_TYPE、CCY、BRANCH的生效日期与失效日期不能存在交叉记录。
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
 						if((endDate1>=endDate&&endDate>=effectDate1)||(endDate1>=effectDate&&effectDate>=effectDate1)||(endDate1<endDate&&effectDate<effectDate1)){
                                flag = 3;
                                return flag;
                                //所选利率序号的 相同的利率类型代码、币种的生效日期与失效日期存在交叉记录。
					}
					}
		},
		"json",false);
		return flag;
}
function irlIntRateAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
