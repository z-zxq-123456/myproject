
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CLIENT_TYPE&tableCol=CLIENT_TYPE,CLIENT_TYPE_DESC",
        id: "clientType",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_CHANNEL&tableCol=CHANNEL,CHANNEL_DESC",
        id: "sourceTypeLink",
        async: false
    });

    if (parent.$("#lmTranLimitLink").find(".selected").length===1){
        rowData = parent.$('#lmTranLimitLink').DataTable().rows(".selected").data()[0];
        $("#limitRef").val(rowData.LIMIT_REF).attr("disabled",true);
        $("#tranTypeLink").val(rowData.TRAN_TYPE_LINK).attr("disabled",true);
        $("#areaCodeLink").val(rowData.AREA_CODE_LINK);
        $("#balanceType").val(rowData.BALANCE_TYPE);
        $("#clientType").val(rowData.CLIENT_TYPE);
        $("#inlandInd").val(rowData.INLAND_IND);
        $("#prodType").val(rowData.PROD_TYPE);
        $("#sourceTypeLink").val(rowData.SOURCE_TYPE_LINK);
    }

    $("#lmTranLimitLinkMod").Validform({
        tiptype:2,
        callback:function(form){
            lmTranLimitLinkMod();
            return false;
        }
    });

    $(".select2").select2();
});

function lmTranLimitLinkMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="LM_TRAN_LIMIT_LINK";
    keyFieldsJson.LIMIT_REF=$("#limitRef").val();
    keyFieldsJson.TRAN_TYPE_LINK=$("#tranTypeLink").val();
    generalFieldsJson.AREA_CODE_LINK=$("#areaCodeLink").val();
    generalFieldsJson.BALANCE_TYPE=$("#balanceType").val();
    generalFieldsJson.CLIENT_TYPE=$("#clientType").val();
    generalFieldsJson.INLAND_IND=$("#inlandInd").val();
    generalFieldsJson.PROD_TYPE=$("#prodType").val();
    generalFieldsJson.SOURCE_TYPE_LINK=$("#sourceTypeLink").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_lmTranLimitLinkMod,"json");
}

function callback_lmTranLimitLinkMod(json){
    if (json.success) {
        var dataTable=parent.$("#lmTranLimitLink").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            LIMIT_REF:$("#limitRef").val(),
            TRAN_TYPE_LINK:$("#tranTypeLink").val(),
            AREA_CODE_LINK:$("#areaCodeLink").val(),
            BALANCE_TYPE:$("#balanceType").val(),
            CLIENT_TYPE:$("#clientType").val(),
            INLAND_IND:$("#inlandInd").val(),
            PROD_TYPE:$("#prodType").val(),
            SOURCE_TYPE_LINK:$("#sourceTypeLink").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function lmTranLimitLinkModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

