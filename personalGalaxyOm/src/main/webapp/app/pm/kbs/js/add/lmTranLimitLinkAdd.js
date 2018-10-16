$(document).ready(function () {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=LM_TRAN_LIMIT_DEF&tableCol=LIMIT_REF,LIMIT_DESC",
        id: "limitRef",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CLIENT_TYPE&tableCol=CLIENT_TYPE,CLIENT_TYPE_DESC",
        id: "clientType",
        async: false
    });

    $("#lmTranLimitLinkAdd").Validform({
        tiptype: 2,
        callback: function (form) {
            lmTranLimitLinkAdd();
            return false;
        }
    });

    $(".select2").select2();
});

function lmTranLimitLinkAdd() {
    var paraJson, generalFieldsJson, keyFieldsJson;
    paraJson = {};
    generalFieldsJson = {};
    keyFieldsJson = {};
    var url = contextPath + "/baseCommon/updateAndInsertForSave";
    paraJson.tableName = "LM_TRAN_LIMIT_LINK";
    keyFieldsJson.LIMIT_REF = $("#limitRef").val();
    keyFieldsJson.TRAN_TYPE_LINK = $("#tranTypeLink").val();
    generalFieldsJson.CLIENT_TYPE = $("#clientType").val();
    generalFieldsJson.PROD_TYPE = $("#prodType").val();
    generalFieldsJson.SOURCE_TYPE_LINK = $("#sourceTypeLink").val();
    generalFieldsJson.BALANCE_TYPE = $("#balanceType").val();
    generalFieldsJson.AREA_CODE_LINK = $("#areaCodeLink").val();
    generalFieldsJson.INLAND_IND = $("#inlandInd").val();
    generalFieldsJson.IS_APP_FLAG = $("#isAppFlag").val();
    generalFieldsJson.BRANCH_LINK = $("#branchLink").val();
    paraJson.key = keyFieldsJson;
    paraJson.general = generalFieldsJson;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson: JSON.stringify(paraJson)
    };
    sendPostRequest(url, params, callback_lmTranLimitLinkAdd, "json");
}

function callback_lmTranLimitLinkAdd(json) {
    if (json.success) {
        parent.showMsgDuringTime("添加成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function lmTranLimitLinkAddCancel() {
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


