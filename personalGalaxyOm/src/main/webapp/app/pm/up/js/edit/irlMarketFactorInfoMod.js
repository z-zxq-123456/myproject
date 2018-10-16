var rowData;
$(document).ready(function() {
         $("#IRLSEQNO").hide();

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

    if (parent.$("#irlMarketFactorInfo").find(".selected").length===1){
        rowData = parent.$('#irlMarketFactorInfo').DataTable().rows(".selected").data()[0];
            $("#irlSeqNo").val(rowData.IRL_SEQ_NO).attr("disabled",true);
            $("#effectDate").val(rowData.EFFECT_DATE).attr("disabled",true);
            $("#marketType").val(rowData.MARKET_TYPE).attr("disabled",true);
            $("#marketValue").val(rowData.MARKET_VALUE);
            $("#endDate").val(rowData.END_DATE).attr("disabled",true);
            $("#facStatus").val(rowData.FAC_STATUS);
            $("#remark").val(rowData.REMARK);
            $("#company").val(rowData.COMPANY);
    }

    $("#irlMarketFactorInfoMod").Validform({
        tiptype:2,
        callback:function(form){
            irlMarketFactorInfoMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlMarketFactorInfoMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="IRL_MARKET_FACTOR_INFO";
        keyFieldsJson.IRL_SEQ_NO=$("#irlSeqNo").val();
        generalFieldsJson.EFFECT_DATE=$("#effectDate").val();
        generalFieldsJson.MARKET_TYPE=$("#marketType").val();
        generalFieldsJson.MARKET_VALUE=$("#marketValue").val();
        generalFieldsJson.END_DATE=$("#endDate").val();
        generalFieldsJson.FAC_STATUS=$("#facStatus").val();
        generalFieldsJson.REMARK=$("#remark").val();

    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlMarketFactorInfoMod,"json");
}


function callback_irlMarketFactorInfoMod(json){
    if (json.success) {
        var dataTable=parent.$("#irlMarketFactorInfo").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            IRL_SEQ_NO:$("irlSeqNo").val(),
            EFFECT_DATE:$("effectDate").val(),
            MARKET_TYPE:$("marketType").val(),
            MARKET_VALUE:$("marketValue").val(),
            END_DATE:$("endDate").val(),
            FAC_STATUS:$("facStatus").val(),
            REMARK:$("remark").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlMarketFactorInfoModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

