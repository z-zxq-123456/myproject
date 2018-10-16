var rowData;
$(document).ready(function () {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });
    var paraJson, keyFieldsJson;
    paraJson = {};
    keyFieldsJson = {};
    paraJson.tableName = "FM_REF_CODE";
    paraJson.tableCol = "FIELD_VALUE,MEANING";
    keyFieldsJson.DOMAIN = "PROD_GRP";
    paraJson.key = keyFieldsJson;
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
        id: "prodGrp",
        async: false

    });

    var paraJson, keyFieldsJson;
    paraJson = {};
    keyFieldsJson = {};
    paraJson.tableName = "FM_REF_CODE";
    paraJson.tableCol="FIELD_VALUE,MEANING";

    keyFieldsJson.DOMAIN = "RATE_MODEL";
    keyFieldsJson.REF_LANG = "CHINESE";
    paraJson.key = keyFieldsJson;
    getPkList({
        url:contextPath+"/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
        id: "rateLadder",
        async: false
    });

    if (parent.$("#irlIntType").find(".selected").length === 1) {
        rowData = parent.$('#irlIntType').DataTable().rows(".selected").data()[0];
        $("#intTaxType").val(rowData.INT_TAX_TYPE).attr("disabled", true);
        $("#intTaxTypeDesc").val(rowData.INT_TAX_TYPE_DESC);
        $("#intTaxFlag").val(rowData.INT_TAX_FLAG);
        $("#rateLadder").val(rowData.RATE_LADDER);
        $("#prodGrp").val(rowData.PROD_GRP);
        $("#company").val(rowData.COMPANY);
        $("#taxLadder").val(rowData.TAX_LADDER);
    }

    $("#irlIntTypeMod").Validform({
        tiptype: 2,
        callback: function (form) {
            irlIntTypeMod();
            return false;
        }
    });
    $('#prodGrp').append("<option value='ALL'>ALL-全部</option>");

    $(".select2").select2();
});

function irlIntTypeMod() {
    var paraJson, generalFieldsJson, keyFieldsJson;
    paraJson = {};
    generalFieldsJson = {};
    keyFieldsJson = {};
    var url = contextPath + "/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName = "IRL_INT_TYPE";
    keyFieldsJson.INT_TAX_TYPE = $("#intTaxType").val();
    generalFieldsJson.INT_TAX_TYPE_DESC = $("#intTaxTypeDesc").val();
    generalFieldsJson.INT_TAX_FLAG = $("#intTaxFlag").val();
    generalFieldsJson.RATE_LADDER = $("#rateLadder").val();
    generalFieldsJson.PROD_GRP = $("#prodGrp").val();
    generalFieldsJson.TAX_LADDER = $("#taxLadder").val();
    paraJson.key = keyFieldsJson;
    paraJson.general = generalFieldsJson;
    paraJson.status = rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson: JSON.stringify(paraJson)
    };
    sendPostRequest(url, params, callback_irlIntTypeMod, "json");
}

function callback_irlIntTypeMod(json) {
    if (json.success) {
        var dataTable = parent.$("#irlIntType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            INT_TAX_TYPE: $("intTaxType").val(),
            INT_TAX_TYPE_DESC: $("intTaxTypeDesc").val(),
            INT_TAX_FLAG: $("intTaxFlag").val(),
            RATE_LADDER: $("rateLadder").val(),
            PROD_GRP: $("prodGrp").val(),
            TAX_LADDER : $("#taxLadder").val(),
            COLUMN_STATUS: 'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlIntTypeModCancel() {
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

