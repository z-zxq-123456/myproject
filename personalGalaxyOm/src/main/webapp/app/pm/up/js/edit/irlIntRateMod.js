
var rowData;
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
    if (parent.$("#irlIntRate").find(".selected").length===1){
        rowData = parent.$('#irlIntRate').DataTable().rows(".selected").data()[0];
            $("#irlSeqNo").val(rowData.IRL_SEQ_NO).attr("disabled",true);
            $("#ccy").val(rowData.CCY);
            $("#yearBasis").val(rowData.YEAR_BASIS);
            $("#branch").val(rowData.BRANCH);
            $("#effectDate").val(rowData.EFFECT_DATE);
            $("#intType").val(rowData.INT_TYPE);
            $("#endDate").val(rowData.END_DATE);
            $("#lastChgRunDate").val(rowData.LAST_CHG_RUN_DATE);
            $("#company").val(rowData.COMPANY);
    }

    $("#irlIntRateMod").Validform({
        tiptype:2,
        callback:function(form){
            irlIntRateMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlIntRateMod(){
		var flag = 0;
		flag = submitCheck();
		if(flag===1){
		showMsg("失效日期必须大于生效日期", 'error');
		}
		if(flag===2){
		showMsg("利率类型代码、币种、生效日期、失效日期、机构存在重复记录", 'error');
		}
/*		if(flag===3){
		showMsg("所选利率序号的 相同的利率类型代码、币种、机构的生效日期与失效日期存在交叉记录。", 'error');
		}*/

if(flag===0){
        var paraJson,generalFieldsJson,keyFieldsJson;
        paraJson = {};
        generalFieldsJson={};
        keyFieldsJson={};
        var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
        paraJson.tableName="IRL_INT_RATE";
            keyFieldsJson.IRL_SEQ_NO=$("#irlSeqNo").val();
            generalFieldsJson.CCY=$("#ccy").val();
            generalFieldsJson.YEAR_BASIS=$("#yearBasis").val();
            generalFieldsJson.BRANCH=$("#branch").val();
            generalFieldsJson.EFFECT_DATE=$("#effectDate").val();
            generalFieldsJson.INT_TYPE=$("#intType").val();
            generalFieldsJson.END_DATE=$("#endDate").val();
            generalFieldsJson.LAST_CHG_RUN_DATE=$("#lastChgRunDate").val();

        paraJson.key = keyFieldsJson;
        paraJson.general=generalFieldsJson;
        paraJson.status=rowData.COLUMN_STATUS;
        paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
        var params = {
            paraJson:JSON.stringify(paraJson)
        };
        sendPostRequest(url,params,callback_irlIntRateMod,"json");
    }
}

function callback_irlIntRateMod(json){
    if (json.success) {
        var dataTable=parent.$("#irlIntRate").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            IRL_SEQ_NO:$("irlSeqNo").val(),
            CCY:$("ccy").val(),
            YEAR_BASIS:$("yearBasis").val(),
            BRANCH:$("branch").val(),
            EFFECT_DATE:$("effectDate").val(),
            INT_TYPE:$("intType").val(),
            END_DATE:$("endDate").val(),
            LAST_CHG_RUN_DATE:$("lastChgRunDate").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlIntRateModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
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
			return flag;//失效日期必须大于生效日期
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
					irlSeqNo = maxId;

		},"json",false);
		if(irlSeqNo!==1){
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
                                // flag = 3;
                                return flag;
                                //所选利率序号的 相同的利率类型代码、币种的生效日期与失效日期存在交叉记录。
					}
					}
		},
		"json",false);
		return flag;



}

