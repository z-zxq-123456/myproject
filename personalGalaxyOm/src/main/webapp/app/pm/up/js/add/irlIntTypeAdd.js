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
    $('#prodGrp').append("<option value='ALL'>ALL-全部</option>");

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

    $("#irlIntTypeAdd").Validform({
        tiptype: 2,
        callback: function (form) {
            irlIntTypeAdd();
            return false;
        }
    });
    $(".select2").select2();
});


function irlIntTypeAdd() {
    var paraJson, generalFieldsJson, keyFieldsJson;
    paraJson = {};
    generalFieldsJson = {};
    keyFieldsJson = {};
    var url = contextPath + "/baseCommon/updateAndInsertForSave";
    paraJson.tableName = "IRL_INT_TYPE";
    keyFieldsJson.INT_TAX_TYPE = $("#intTaxType").val();
    generalFieldsJson.INT_TAX_TYPE_DESC = $("#intTaxTypeDesc").val();
    generalFieldsJson.INT_TAX_FLAG = $("#intTaxFlag").val();
    generalFieldsJson.RATE_LADDER = $("#rateLadder").val();
    generalFieldsJson.PROD_GRP = $("#prodGrp").val();
    generalFieldsJson.TAX_LADDER = $("#taxLadder").val();
    paraJson.key = keyFieldsJson;
    paraJson.general = generalFieldsJson;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson: JSON.stringify(paraJson)
    };
    sendPostRequest(url, params, callback_irlIntTypeAdd, "json");
}

function callback_irlIntTypeAdd(json) {
    if (json.success) {
        parent.showMsgDuringTime("添加成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlIntTypeAddCancel() {
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

