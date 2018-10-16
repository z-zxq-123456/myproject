
var rowData;
$(document).ready(function() {
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_EXCHANGE_TYPE&tableCol=RATE_TYPE,RATE_TYPE_DESC",
				id: "defaultChargeRateType",
				async: false
			});
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
                id: "baseCcy",
                async: false
            });
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
                id: "company",
                async: false
            });
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
                id: "defaultBranch",
                async: false
            });
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=IRL_EXCHANGE_TYPE&tableCol=RATE_TYPE,RATE_TYPE_DESC",
                id: "defaultRateType",
                async: false
            });
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
                id: "localCcy",
                async: false
            });

    if (parent.$("#irlSystem").find(".selected").length===1){
        rowData = parent.$('#irlSystem').DataTable().rows(".selected").data()[0];
            $("#coyName").val(rowData.COY_NAME).attr("disabled",true);
            $("#baseCcy").val(rowData.BASE_CCY);
            $("#company").val(rowData.COMPANY);
            $("#coyShort").val(rowData.COY_SHORT);
            $("#defaultBranch").val(rowData.DEFAULT_BRANCH);
            $("#glMergeType").val(rowData.GL_MERGE_TYPE);
            $("#halfEndDate").val(rowData.HALF_END_DATE);
            $("#intEventValue").val(rowData.INT_EVENT_VALUE);
            $("#lastRunDate").val(rowData.LAST_RUN_DATE);
            $("#localCcy").val(rowData.LOCAL_CCY);
            $("#mthEndDate").val(rowData.MTH_END_DATE);
            $("#nextRunDate").val(rowData.NEXT_RUN_DATE);
            $("#quoteBalanceType").val(rowData.QUOTE_BALANCE_TYPE);
            $("#defaultRateType").val(rowData.DEFAULT_RATE_TYPE);
            $("#qurEndDate").val(rowData.QUR_END_DATE);
            $("#runDate").val(rowData.RUN_DATE);
            $("#systemPhase").val(rowData.SYSTEM_PHASE);
            $("#yrEndDate").val(rowData.YR_END_DATE);
            $("#defaultChargeRateType").val(rowData.DEFAULT_CHARGE_RATE_TYPE);
    }

    $("#irlSystemMod").Validform({
        tiptype:2,
        callback:function(form){
            irlSystemMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlSystemMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="IRL_SYSTEM";
        keyFieldsJson.COY_NAME=$("#coyName").val();
        generalFieldsJson.BASE_CCY=$("#baseCcy").val();
        generalFieldsJson.COY_SHORT=$("#coyShort").val();
        generalFieldsJson.DEFAULT_BRANCH=$("#defaultBranch").val();
        generalFieldsJson.DEFAULT_RATE_TYPE=$("#defaultRateType").val();
        generalFieldsJson.ENSEMBLE_EOD_END=$("#ensembleEodEnd").val();
        generalFieldsJson.GL_MERGE_TYPE=$("#glMergeType").val();
        generalFieldsJson.HALF_END_DATE=$("#halfEndDate").val();
        generalFieldsJson.INT_EVENT_VALUE=$("#intEventValue").val();
        generalFieldsJson.IRL_EOD_END=$("#irlEodEnd").val();
        generalFieldsJson.LAST_RUN_DATE=$("#lastRunDate").val();
        generalFieldsJson.LOCAL_CCY=$("#localCcy").val();
        generalFieldsJson.MTH_END_DATE=$("#mthEndDate").val();
        generalFieldsJson.NEXT_RUN_DATE=$("#nextRunDate").val();
        generalFieldsJson.QUOTE_BALANCE_TYPE=$("#quoteBalanceType").val();
        generalFieldsJson.QUR_END_DATE=$("#qurEndDate").val();
        generalFieldsJson.RUN_DATE=$("#runDate").val();
        generalFieldsJson.SYSTEM_PHASE=$("#systemPhase").val();
        generalFieldsJson.YR_END_DATE=$("#yrEndDate").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlSystemMod,"json");
}

function callback_irlSystemMod(json){
    if (json.success) {
        var dataTable=parent.$("#irlSystem").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            COY_NAME:$("#coyName").val(),
        BASE_CCY:$("#baseCcy").val(),
        COY_SHORT:$("#coyShort").val(),
        DEFAULT_BRANCH:$("#defaultBranch").val(),
        DEFAULT_RATE_TYPE:$("#defaultRateType").val(),
        ENSEMBLE_EOD_END:$("#ensembleEodEnd").val(),
        GL_MERGE_TYPE:$("#glMergeType").val(),
        HALF_END_DATE:$("#halfEndDate").val(),
        INT_EVENT_VALUE:$("#intEventValue").val(),
        IRL_EOD_END:$("#irlEodEnd").val(),
        LAST_RUN_DATE:$("#lastRunDate").val(),
        LOCAL_CCY:$("#localCcy").val(),
        MTH_END_DATE:$("#mthEndDate").val(),
        NEXT_RUN_DATE:$("#nextRunDate").val(),
        QUOTE_BALANCE_TYPE:$("#quoteBalanceType").val(),
        QUR_END_DATE:$("#qurEndDate").val(),
        RUN_DATE:$("#runDate").val(),
        SYSTEM_PHASE:$("#systemPhase").val(),
        YR_END_DATE:$("#yrEndDate").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlSystemModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

