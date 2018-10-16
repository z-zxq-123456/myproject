$(document).ready(function () {
    $("#ruleType").change(function () {
        $(".select2").select2();
//	      $("#groupType").val("");
//	      $("#factInfo1").val("");
//	      $("#inValue").val("");
        $("#CCY").hide();//非汇率类型，币种组默认隐藏
        $("#groupId0").show();//非费率，需要选择分组类型
        var ruleType = $("#ruleType").val();
        $("#float0").show();
        var elementType;
        $("#floatValue").val("").attr("disabled", false);
        if (ruleType === "INRATE") {
            $("#GroupLi").show();
            $("#GroupFee").hide();
            $("#GroupExch").hide();
            $("#GroupDisc").hide();
            $("#float0").show();
            elementType = "L";
            $('#floatType').append("<option value='1'>按行内利率</option>");
        }
        else if (ruleType === "FEE") {
            $("#GroupLi").hide();
            $("#GroupFee").show();
            $("#GroupExch").hide();
            $("#GroupDisc").hide();
            $("#groupId0").hide();
            $("#floatValue").val("true").attr("disabled", "disabled");
            elementType = "F";
            var paraJson, keyFieldsJson;
            paraJson = {};
            keyFieldsJson = {};

            paraJson.tableName = "IRL_ELEMENT";
            paraJson.tableCol = "ELEMENT_ID,ELEMENT_DESC";

            keyFieldsJson.ELEMENT_TYPE = elementType;
            paraJson.key = keyFieldsJson;

            getPkList({
                url: contextPath + "/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
                id: "factInfo1",
                async: false
            });
        }
        else if (ruleType === "EXRATE") {
            $("#GroupLi").hide();
            $("#GroupFee").hide();
            $("#GroupExch").show();
            $("#GroupDisc").hide();
            elementType = "E";
        }
        else if (ruleType === "DISC") {
            $("#GroupLi").hide();
            $("#GroupFee").hide();
            $("#GroupExch").hide();
            $("#GroupDisc").show();
            elementType = "D";
        }

        var paraJson, keyFieldsJson;
        paraJson = {};
        keyFieldsJson = {};
        paraJson.tableName = "IRL_RULE_GROUP";
        paraJson.tableCol = "GROUP_TYPE,GROUP_TYPE_DESC";

        keyFieldsJson.GROUP_CLASS = elementType;
        paraJson.key = keyFieldsJson;

        getPkList({
            url: contextPath + "/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
            id: "groupType",
            async: false
        });


    });// $("#ruleType").change(function()  END
    $("#groupType").change(function () {
        $("#ruleWeight0").hide();
        groupType = $("#groupType").val();
        var url = contextPath + "/baseCommon/selectBase";
        var paramRuleGroup = {
            col1: "GROUP_TYPE",
            colV1: groupType,
            col2: "",
            colV2: "",
            col3: "",
            colV3: "",
            tableName: "IRL_RULE_GROUP"
        }
        sendPostRequest(url, paramRuleGroup, callbak_ruleGroup, "json");

    });
    $("#prodGroupLi").change(function () {

        var prodType = $("#prodGroupLi").val();
        var $prodType = $("#prodType").select2();

        $("#prod0").show();
        $("#prod1").hide();
        if (prodType === "RB" || prodType === "CL") {

            var paraJson, keyFieldsJson;
            paraJson = {};
            keyFieldsJson = {};
            paraJson.tableName = "IRL_PROD_TYPE";
            paraJson.tableCol = "PROD_TYPE,PROD_TYPE_DESC";

            keyFieldsJson.PROD_GRP = prodType;
            paraJson.key = keyFieldsJson;

            getPkList({
                url: contextPath + "/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
                id: "prodType",
                async: false
            });
        }
        if (prodType === "GLOBAL") {
            $prodType.val("");
            $("#prodType").attr("disabled", "disabled");
            $(".select2-input").attr("disabled", "disabled");
        } else {
            $prodType.val("");
            $("#prodType").attr("disabled", false);
            $(".select2-input").attr("disabled", false);
        }
    });
    $("#prodGroupFee").change(function () {

        var prodType = $("#prodGroupFee").val();
        var $prodType = $("#prodType").select2();
        $("#prod0").show();
        $("#prod1").hide();
        if (prodType === "GLOBAL") {
            $prodType.val("");
            $("#prodType").attr("disabled", "disabled");
            $(".select2-input").attr("disabled", "disabled");
        } else if (prodType === "GROUP") {
            $("#prod0").hide();
            $("#prod1").show();
        } else if (prodType === "FEE") {
            $("#prodType").attr("disabled", false);
            $(".select2-input").attr("disabled", false);
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=IRL_FEE_TYPE&tableCol=FEE_TYPE,FEE_DESC",
                id: "prodType",
                async: false
            });
        }


    });

    $("#prodGroupExch").change(function () {
        $("#CCY").hide();
        var prodType = $("#prodGroupExch").val();
        var $prodType = $("#prodType").select2();
        $("#prodType").attr("disabled", false);
        $(".select2-input").attr("disabled", false);
        if (prodType === "GLOBAL") {
            $prodType.val("");
            $("#prodType").attr("disabled", "disabled");
            $(".select2-input").attr("disabled", "disabled");
        } else if (prodType === "GROUP") {
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=IRL_EXCHANGE_TYPE&tableCol=RATE_TYPE,RATE_TYPE_DESC",
                id: "prodType",
                async: false
            });
        } else if (prodType === "RATETYPE") {
            $("#CCY").show();
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=IRL_EXCHANGE_TYPE&tableCol=RATE_TYPE,RATE_TYPE_DESC",
                id: "prodType",
                async: false
            });
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
                id: "SCcy",
                async: false
            });
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
                id: "TCcy",
                async: false
            });
        }
    });

    $("#prodGroupDisc").change(function () {

        var prodType = $("#prodGroupDisc").val();
        var $prodType = $("#prodType").select2();
        $("#prod0").show();
        $("#prod1").hide();


        if (prodType === "GLOBAL") {
            $prodType.val("");
            $("#prodType").attr("disabled", "disabled");
            $(".select2-input").attr("disabled", "disabled");
        } else if (prodType === "GROUP") {
            $("#prod0").hide();
            $("#prod1").show();
        } else if (prodType === "DISC") {
            $("#prodType").attr("disabled", false);
            $(".select2-input").attr("disabled", false);
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=IRL_FEE_TYPE&tableCol=FEE_TYPE,FEE_DESC",
                id: "prodType",
                async: false
            });
        }
    });

    $("#factInfo1").change(function () { //因子取值
        $("#inValue").val("");
        $("#inValue1").val("");
        var elementType, ruleType;
        ruleType = $("#ruleType").val();
        if (ruleType === "INRATE") {
            elementType = "L";
        } else if (ruleType === "FEE") {
            elementType = "F";
        } else if (ruleType === "EXRATE") {
            elementType = "E";
        } else if (ruleType === "DISC") {//费用折扣因子不区分
            elementType = "F";
        }
        var info = $("#factInfo1").val();
        var url = contextPath + "/baseCommon/selectBase";
        var paramElement = {
            col1: "ELEMENT_ID",
            colV1: info,
            col2: "ELEMENT_TYPE",
            colV2: elementType,
            col3: "",
            colV3: "",
            tableName: "IRL_ELEMENT"
        }
        sendPostRequest(url, paramElement, callbak_element, "json");
    });

    $(".select2").select2();
});

function addClick() {
    var ruleType = $("#ruleType").val();
    var str, value, str1;
    var attr = $("#elementAttr").val();
    var facInfo1 = $("#factInfo1").val();
    var inValueStr = $("#inValue option:selected").text();
    str1 = $("#factInfo1 option:selected").text().split(" ")[1] + $("#condition option:selected").text();
    var value = $("#inValue").val();
    var value1 = $("#inValue1").val();
    if (attr === "NUMBER") {
        str = "(BigDecimalUtil.toBigDecimal(factInfo.getInfoMap().get(" + '"' + facInfo1 + '"' + "))).compareTo(BigDecimalUtil.toBigDecimal(" + '"' + $("#inValue1").val() + '"' + "))" + $("#condition").val() + "0";
        str1 += '"' + $("#inValue1").val() + '"';
    } else {
        if (inValueStr === "请选择") {
            inValueStr = "";
        }
        if (value === null) {
            value = "";
        }
        str = "(StringUtil.ObjectToEmpty(factInfo.getInfoMap().get(" + '"' + facInfo1 + '"' + "))).compareTo(" + '"' + value + value1 + '"' + ")" + $("#condition").val() + "0";
        str1 += '"' + inValueStr + value1 + '"';
    }

    $("#details").val($("#details").val() + str);
    $("#details1").val($("#details1").val() + str1);
}
function clsClick() {
    $("#details").val("");
    $("#details1").val("");
}
function andClick() {
    $("#details").val($("#details").val() + " && ");
    $("#details1").val($("#details1").val() + "   并且   ");
}
function orClick() {
    $("#details").val($("#details").val() + " || ");
    $("#details1").val($("#details1").val() + "    或者    ");
}
function leftClick() {
    $("#details").val($("#details").val() + "(");
    $("#details1").val($("#details1").val() + "   (   ");
}
function rightClick() {
    $("#details").val($("#details").val() + ")");
    $("#details1").val($("#details1").val() + "   )   ");
}
function finish() {
    layer.confirm('确认完成规则配置？', function () {
        ruleConfig();
    });
}
function ruleConfig() {
    var value = $("#salience").val();
    var ruleType = $("#ruleType").val();
    var matchType = $("#matchType").val();
    if ($("#userId").val().replace(/(^\s*)|(\s*$)/g, "") === "" || $("#userId").val() === null) {
        showMsg('创建人不能为空，谢谢！', 'error');
        return;
    }
    else if ($('#ruleType').val() === "") {
        showMsg('规则类型不能为空，谢谢！', 'error');
        return;
    }
    else if ($('#prodGroup').val() === "") {
        showMsg('规则大类不能为空，谢谢！', 'error');
        return;
    }
    else if (matchType === "06" && ($('#ruleWeight').val() === "" || $('#ruleWeight').val() === null)) {
        showMsg('所选规则分组，内规则关系为权重，请输入权重值，谢谢！', 'warning');
        return;
    }
//     	else if ($("#salience").val().replace(/(^\s*)|(\s*$)/g, "")=="" || $("#salience").val()== null )
//     	{
//     		showMsg('优先级不能为空，谢谢！','error');
//     		return ;
//     	}
//
//     	else if ( value.length > 6 )
//     	{
//     		showMsg('优先级输入过长！','error');
//     		return ;
//     	}

    else if ($('#startDate').val() === "") {
        showMsg('生效日期不能为空，谢谢！', 'error');
        return;
    }
    else if ($('#endDate').val() === "") {
        showMsg('失效日期不能为空，谢谢！', 'error');
        return;
    }
    else if ($('#status').val() === "") {
        showMsg('规则状态不能为空，谢谢！', 'error');
        return;
    }
    else if ($("#startDate").val() >= $("#endDate").val()) {
        showMsg('生效日期必须小于失效日期，谢谢！', 'error');
        return;
    }
    var reg = /^(-|\+)?\d+(\.\d+)?$/;
    if ($("#details").val() === "") {
        showMsg('规则条件配置不能为空，谢谢！', 'error');
        return;
    }
//     	else if(ruleType=="INRATE"){
//                if ($("#groupType").val() == ""){
//                        showMsg('规则分组类型不能为空，谢谢！','error');
//                        return ;
//                 }
//     	}
    else {
        var id = "";
        var url1 = contextPath + "/baseCommon/getRuleId";
        sendPostRequest(url1, {}, function (json) {
            var maxId = json;
            id = maxId;

        }, "json", false);


        var ruleType = $('#ruleType').val();
        var prodGroup;
        var prodType = "";
        var groupC = "";

        if (ruleType === "INRATE") {
            prodGroup = $('#prodGroupLi').val();
        } else if (ruleType === "FEE") {
            prodGroup = $('#prodGroupFee').val();
        } else if (ruleType === "EXRATE") {
            prodGroup = $('#prodGroupExch').val();
        } else if (ruleType === "DISC") {
            prodGroup = $('#prodGroupDisc').val();
        }

        //费用和折扣的产品组为GROUP时 产品为 clglobal/rbglobal
        if (prodGroup === "GROUP" && (ruleType === "FEE" || ruleType === "DISC")) {
            prodType = $('#prodGroupRB').val();
        } else {
            if (prodGroup === "RATETYPE") {
                groupC = $('#TCcy').val() + $('#SCcy').val();
                ;
            }
            prodType = $('#prodType').val();
        }

        if (prodType === "" || prodType === "undefined" || prodType === null) {
            prodType = "global";
        }

        //获取创建时间
        var date = new Date();
        var month = date.getMonth();
        var createDate;
        var year = date.getFullYear();
        if (month < 9) {
            createDate = date.getFullYear() + "0" + (date.getMonth() + 1) + date.getDate();

        }
        else {
            createDate = date.getFullYear() + (date.getMonth() + 1) + date.getDate();
        }

        //编辑上送表达式描述
        var ruleDesc = $("#details1").val();
        var intFloatTypeStr = $("#floatType option:selected").text();
        var floatValue = $("#floatValue").val();
        var finalDesc;
        //汇率默认上送固定值
        var intFloatType = $("#floatType").val();
        if (ruleType === "DISC") {
            finalDesc = "如果" + ruleDesc + ",则折扣" + floatValue;
        } else {
            finalDesc = "如果" + ruleDesc + ",则按照" + intFloatTypeStr + "上浮" + floatValue;
        }


        var paraJson, generalFieldsJson, keyFieldsJson;
        paraJson = {};
        generalFieldsJson = {};
        keyFieldsJson = {};
        var url = contextPath + "/baseCommon/updateAndInsertForSave";
        paraJson.tableName = "IRL_RULE_MESSAGE";
        keyFieldsJson.IRL_SEQ_NO = id;
        generalFieldsJson.COMPANY = $("#company").val();
        generalFieldsJson.RULE_CLASS_1 = ruleType;
        generalFieldsJson.CREATE_DATE = createDate;
        generalFieldsJson.END_DATE_TIME = $("#endDate").val();
        generalFieldsJson.INT_FLOAT_TYPE = intFloatType;
        generalFieldsJson.FLOAT_VALUE = floatValue;
        generalFieldsJson.IMPORT_MESSAGE = $("#importMessage").val();
        generalFieldsJson.RULE_CLASS_2 = prodGroup;
        generalFieldsJson.RULE_CLASS_3 = prodType;
        generalFieldsJson.RULE_DESC = finalDesc;
        generalFieldsJson.RULE_EXPRESS = $("#details").val();
        generalFieldsJson.SPECIAL_RULE_PROCESS = $("#specialRuleProcess").val();
        generalFieldsJson.START_DATE_TIME = $("#startDate").val();
        generalFieldsJson.RULE_STATUS = $("#status").val();
        generalFieldsJson.USER_ID = $("#userId").val();
        generalFieldsJson.GROUP_CCY = groupC;
        generalFieldsJson.RULE_FLAG = "S";
        generalFieldsJson.GROUP_TYPE = $("#groupType").val();
        if (matchType === "06") {
            generalFieldsJson.RULE_WEIGHT = $("#ruleWeight").val();
        }
        paraJson.key = keyFieldsJson;
        paraJson.general = generalFieldsJson;
        var params = {
            paraJson: JSON.stringify(paraJson)
        };
        sendPostRequest(url, params, callback_irlRuleMessageAdd, "json");
    }
}
function callback_irlRuleMessageAdd(json) {
    if (json.success) {
        parent.showMsgDuringTime("添加成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function callbak_element(json) {
    if (json.data.length > 0) {
        var tableName = json.data[0].TABLE_NAME;
        var fieldValue = json.data[0].IRL_FIELD_VALUE;
        var attr = json.data[0].ELEMENT_ATTR;
        $("#elementAttr").val(attr);
        if (tableName === "" || fieldValue === "" || fieldValue === null || tableName === null || tableName === undefined || fieldValue === undefined) {
            $("#DivinValue2").show();
            $("#DivinValue1").hide();
        } else {
            $("#DivinValue2").hide();
            $("#DivinValue1").show();
            if (tableName === "FM_REF_CODE") {
                var paraJson, keyFieldsJson;
                paraJson = {};
                keyFieldsJson = {};
                paraJson.tableName = "FM_REF_CODE";
                paraJson.tableCol = "FIELD_VALUE,MEANING";
                keyFieldsJson.DOMAIN = fieldValue;
                paraJson.key = keyFieldsJson;
                getPkList({
                    url: contextPath + "/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
                    id: "inValue",
                    async: false
                });
            } else {
                getPkList({
                    url: contextPath + "/baseCommon/pklistBase?tableName=" + tableName + "&tableCol=" + fieldValue,
                    id: "inValue",
                    async: false
                });
            }
        }
    }else if(json.data.length == 0){
        $("#DivinValue2").show();
        $("#DivinValue1").hide();
    }
}

function callbak_ruleGroup(json) {
    var paraJson, keyFieldsJson, groupType;
    paraJson = {};
    keyFieldsJson = {};
    groupType = $("#groupType").val();
    paraJson.tableName = "IRL_ELEMENT_GROUP";
    paraJson.tableCol = "ELEMENT_ID,ELEMENT_DESC";

    keyFieldsJson.GROUP_TYPE = groupType;
    paraJson.key = keyFieldsJson;

    getPkList({
        url: contextPath + "/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
        id: "factInfo1",
        async: false
    });

    if (json.data.length > 0) {
        var matchType = json.data[0].GROUP_MATCH_TYPE;
        $("#matchType").val(matchType);
        if (matchType === "06") {
            $("#ruleWeight0").show();
            showMsg('所选规则分组，内规则关系为权重，请输入权重值，谢谢！', 'warning');
        }
    }
}


function irlRuleMessageAddCancel() {
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}



  