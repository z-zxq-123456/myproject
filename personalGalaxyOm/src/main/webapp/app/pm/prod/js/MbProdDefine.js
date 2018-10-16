var tableNames = "MB_PROD_TYPE,MB_EVENT_ATTR,MB_PROD_DEFINE,MB_EVENT_PART,IRL_FEE_MAPPING,IRL_PROD_TYPE,IRL_PROD_INT,GL_PROD_ACCOUNTING,GL_PROD_RULE,GL_PROD_MAPPING,MB_EVENT_TYPE";
var transActionId = "MB_SINGLE_PROD_DEFINE";
var irlProdIntData = false;
var irlFeeMappingData = false;
var irlProdTypeData = false;
var glProdAccountingData = false;
var glProdMappingData = false;
var glProdRuleData = false;
$(function () {
    $("#newProdDiv").hide();
    $("#attrList").hide();
    $.ajax({
        url: contextPath + "/ProdToJson/Start",
        data: {
            reqNum: parent.$(".breadcrumb").data("reqNum"),
            tableNames: tableNames,
            tableName: "PROD_MB_SINGLE_PROD_DEFINE",
            tableDesc: "单一产品定义"
        },
        success: function (json) {
            if (json.appNo !== null) {
                $(".breadcrumb").data("reqNum", json.appNo);
                if (json.currentStatus === "2" || json.currentStatus === "3") {
                    $("#save").hide();
                    $("#nullifyAppNo").hide();
                } else if (json.currentStatus === "1") {
                    $("#nullifyAppNo").hide();
                }
            }
        },
        dataType: "json",
        type: "POST"
    });
    //步骤函数初始化
    var step = $("#myStep").step({
        animate: true,
        initStep: 1,
        maxStepSize: 5,
        speed: 1000
    });
    $("#nullifyAppNo").click(function () {
        nullifyAppNo();
    });
    $("#preOne").click(function () {
        step.preStep();
    });
    $("#preTwo").click(function () {
        step.preStep();
    });
    $("#preThree").click(function () {
        step.preStep();
    });
    $("#preFour").click(function () {
        step.preStep();
    });
    $("#ome").click(function () {
        step.nextStep();
    });
    $("#two").click(function () {
        step.nextStep();
    });
    $("#three").click(function () {
        step.nextStep();
    });
    $("#four").click(function () {
        step.nextStep();
    });
    $("#attrList").click(function () {
        $("#attrList").data("contrastFlag", true);
        var indexForEdit = layer.open({
            type: 2,
            content: contextPath + "/app/pm/prod/jsp/ProdAttrList.jsp",
            end: function () {
                $("#attrList").data("contrastFlag", false);
            }
        });
        layer.full(indexForEdit);
    });
    $("#IrlProdInt").click(function () {
        if ($("#operateType").val() === "") {
            alert("请先选择操作类型！");
        } else if ($("#operateType").val() === "add" || $("#operateType").val() === "copy") {
            if ($("#newProdType").val() === "" || $("#newProdDesc").val() === "") {
                alert("请先输入新定义产品类型/描述！");
            } else {
                $("#index").attr("src", "IrlProdInFormT.jsp");
            }
        } else {
            $("#index").attr("src", "IrlProdInFormT.jsp");
        }
    });
    $("#IrlProdType").click(function () {
        if ($("#operateType").val() === "") {
            alert("请先选择操作类型！");
        } else if ($("#operateType").val() === "add" || $("#operateType").val() === "copy") {
            if ($("#newProdType").val() === "" || $("#newProdDesc").val() === "") {
                alert("请先输入新定义产品类型/描述！");
            } else {
                var url = contextPath + "/irlProdType/getProdTypeKey";
                $.ajax({
                    url: url,
                    data: "prodType=" + $("#newProdType").val(),
                    success: function (json) {
                        if (json.retStatus === 'F') {
                            showMsg(json.retMsg, 'info');
                        } else if (json.retStatus === 'S') {
                            $("#index").attr("src", "IrlProdTypeInFormT.jsp");
                            return;
                        }
                    },
                    dataType: "json"
                });
            }
        } else {
            $("#index").attr("src", "IrlProdTypeInFormT.jsp");
        }
    });
    $("#IRL_FEE_MAPPING").click(function () {
        if ($("#operateType").val() === "") {
            alert("请先选择操作类型！");
        } else if ($("#operateType").val() === "add" || $("#operateType").val() === "copy") {
            if ($("#newProdType").val() === "" || $("#newProdDesc").val() === "") {
                alert("请先输入新定义产品类型/描述！");
            } else {
                $("#index1").attr("src", "IrlFeeMappingPf.jsp");
            }
        } else {
            $("#index1").attr("src", "IrlFeeMappingPf.jsp");
        }
    });
    $("#GlProdAccount").click(function () {
        if ($("#operateType").val() === "") {
            alert("请先选择操作类型！");
        } else if ($("#operateType").val() === "add" || $("#operateType").val() === "copy") {
            if ($("#newProdType").val() === "" || $("#newProdDesc").val() === "") {
                alert("请先输入新定义产品类型/描述！");
            } else {
                $("#index2").attr("src", "GlProdAccountingPf.jsp");
            }
        } else {
            $("#index2").attr("src", "GlProdAccountingPf.jsp");
        }
    });
    $("#GlProdRule").click(function () {
        if ($("#operateType").val() === "") {
            alert("请先选择操作类型！");
        } else if ($("#operateType").val() === "add" || $("#operateType").val() === "copy") {
            if ($("#newProdType").val() === "" || $("#newProdDesc").val() === "") {
                alert("请先输入新定义产品类型/描述！");
            } else {
                $("#index2").attr("src", "GlProdRulePf.jsp");
            }
        } else {
            $("#index2").attr("src", "GlProdRulePf.jsp");
        }
    });
    $("#GlProdMapping").click(function () {
        if ($("#operateType").val() === "") {
            alert("请先选择操作类型！");
        } else if ($("#operateType").val() === "add" || $("#operateType").val() === "copy") {
            if ($("#newProdType").val() === "" || $("#newProdDesc").val() === "") {
                alert("请先输入新定义产品类型/描述！");
            } else {
                $("#index2").attr("src", "GlProdMappingPf.jsp");
            }
        } else {
            $("#index2").attr("src", "GlProdMappingPf.jsp");
        }
    });
});
$(function () {
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });
    $.galaxytab("#tab-system .tabBar span", "#tab-system .tabCon", "current", "click", "0");
});
$(document).ready(function () {
    attrClassInFor();
});

function attrClassInFor() {
    var url = contextPath + "/attr/getAll";
    sendPostRequest(url, null,
        function (json) {
            for (var i = 0; i < json.data.length; i++) {
                var li;
                var div;
                if (i === 0) {
                    li = '<li class="active"><a href="#' + json.data[i].attrClass + '" data-toggle="tab">' + json.data[i].attrClassDesc + '</a></li>';
                    div = '<div class="tab-pane active" id="' + json.data[i].attrClass + '"><div class="span3"><ul id="' + json.data[i].attrClass + '1" class="ztree showIcon"></ul></div><div class="span9"><div class="padding-20" style="width:100%"><div class="portlet" id="sortable_portlets"><div class="row" id="' + json.data[i].attrClass + 'D"></div></div></div></div></div>';
                } else {
                    li = '<li><a href="#' + json.data[i].attrClass + '" data-toggle="tab">' + json.data[i].attrClassDesc + '</a></li>';
                    div = '<div class="tab-pane" id="' + json.data[i].attrClass + '"><div class="span3"><ul id="' + json.data[i].attrClass + '1" class="ztree showIcon"></ul></div><div class="span9"><div class="padding-20" style="width:100%"><div class="portlet" id="sortable_portlets"><div class="row" id="' + json.data[i].attrClass + 'D"></div></div></div></div></div>';
                }
                $("#list").append(li);
                $("#tabDiv").append(div);
                var str11 = "#" + json.data[i].attrClass + "1";
                var str12 = "#" + json.data[i].attrClass + "D";
                $.ajax({
                    url: contextPath + "/partCon/getzTreeList?attrClass=" + json.data[i].attrClass + "&busiCategory=" + $("#Bmodule").val(),
                    async: false,
                    success: function (zNodes) {
                        var treeObj = $.fn.zTree.init($(str11), getSetting(str12, "attrTree", "attr"), zNodes);
                        treeObj.expandAll(true);
                    },
                    dataType: "json"
                });
            }
        }, "json");
}

$(document).ready(function () {
    var url = contextPath + "/partCon/getPartzTreeList";
    $.get(url, null,
        function (zNodes) {
            $.fn.zTree.init($("#partTree"), getSetting("#compontentP", "partTree", "part"), zNodes);
        },
        "json");
});
$(document).ready(function () {
    var url = contextPath + "/eventAttr/getzTreeList";
    $.get(url, null,
        function (zNodes) {
            $.fn.zTree.init($("#eventTree"), getSetting("#compontentE", "eventTree", "event"), zNodes);
        },
        "json");

    getPkList({
        url: contextPath + "/pklist/getBmodule",
        id: "Bmodule",
        async: false
    });
    $('#operateType').change(function () {
        $("#prodType").val("");
        $("#prodDesc").val("");
        $("#newProdType").val("");
        $("#newProdDesc").val("");
        $("#prodClass").select2().val("").trigger("change");
        $("#baseProdType").select2().val("").trigger("change");
        $("#prodRange").select2().val("").trigger("change");
        $("#status").select2().val("A").trigger("change");
        $("#Bmodule").val("");
        $("#tabDiv").html("");
        $("#list").html("");
        $("#compontentE").html("");
        attrClassInFor();
        if ($("#operateType").val() === "add") {
            $("#newProdDiv").show();
            $("#prodType").attr("disabled", true);
            $("#prodDesc").attr("disabled", true);
            $("#Bmodule").attr("disabled", false);
        } else if ($("#operateType").val() === "copy") {
            $("#newProdDiv").show();
            $("#prodType").attr("disabled", false);
            $("#prodDesc").attr("disabled", false);
            $("#Bmodule").attr("disabled", false);
        } else {
            $("#newProdDiv").hide();
            $("#prodType").attr("disabled", false);
            $("#prodDesc").attr("disabled", false);
            $("#Bmodule").attr("disabled", false);
        }
    });
    $('#Bmodule').change(function () {
        $("#prodRange").val("");
        $("#status").val("A");
        $("#prodType").val("");
        $("#prodDesc").val("");
        $("#newProdType").val("");
        $("#newProdDesc").val("");
        $("#tabDiv").html("");
        $("#list").html("");
        $("#compontentE").html("");
        attrClassInFor();
        $("#newProdType").attr("disabled", false);
        $("#newProdDesc").attr("disabled", false);
        $("#prodClass").attr("disabled", false);
        $("#prodRange").attr("disabled", false);
        $("#baseProdType").attr("disabled", false);
        $("#status").attr("disabled", false);
        getPkList({
            url: contextPath + "/pklist/getprodClass?Bmodule=" + $("#Bmodule").val(),
            id: "prodClass",
            async: false
        });

        getPkList({
            url: contextPath + "/pklist/getbaseProd?Bmodule=" + $("#Bmodule").val(),
            id: "baseProdType",
            async: false
        });

        $.get(contextPath + "/eventAttr/getzTreeList?Bmodule=" + $("#Bmodule").val(),
            function (zNodes) {
                $.fn.zTree.init($("#eventTree"), getSetting("#compontentE", "eventTree", "event"), zNodes);
            },
            "json");
    });
    $(".select2").select2();
});
$(document).ready(function () {
    jQuery('#query').on('click', '',
        function () {
            if ($("#prodType").val() === "") {
                alert("请输入产品类型");
                return;
            }
            clearAll();
            getDesc();
            getAttr();
            showProdType();
        });
});

$(document).ready(function () {
    var elReg = /^[A-Z0-9]+$/;
    $("#newProdType").blur(function () {
        if ($("#newProdType").val() === "") {
            alert("请输入新产品类型！！！");
            return;
        } else if (elReg.test($("#newProdType").val()) !== true) {
            alert("产品类型应为大写英文/数字");
            $("#newProdType").val("");
            return;
        } else {
            var url = contextPath + "/prodType/getProdTypeKey";
            $.ajax({
                url: url,
                data: "prodType=" + $("#newProdType").val(),
                success: function (json) {
                    if (json.retStatus === 'F') {
                        showMsg(json.retMsg, 'info');
                    } else if (json.retStatus === 'S') {
                        return;
                    }
                },
                dataType: "json"
            });
        }
    });
});
function getDesc() {
    var url = contextPath + "/prodType/getOne";
    sendPostRequest(url, {
            prodType: $("#prodType").val()
        },
        function (json) {
            if (json.retStatus === 'F') {
                showMsg(json.retMsg, 'info');
            } else if (json.retStatus === 'S') {
                $("#prodDesc").val(json.data.prodDesc);
            }
        },
        "json");
}
function getAttr() {
    var url = contextPath + "/prodDefine/getDefine";
    sendPostRequest(url, {
            prodType: $("#prodType").val()
        },
        function (partRet) {
            var div;
            json = eval(partRet);
            for (var i = 0; i < partRet.length; i++) {
                if (partRet[i].assembleType === "EVENT") {
                    div = showEventDiv(partRet[i].assembleId, partRet[i].eventDesc, partRet[i].eventClass, partRet[i].seqNo, partRet[i].prodData, "O");
                    $("#compontentE").append(div);
                    $('#' + partRet[i].assembleId).find(".portlet-body.event").slideUp(200);
                    for (var j = 0; j < partRet[i].prodData.length; j++) {
                        if (partRet[i].prodData[j].assembleType === "ATTR") {
                            if (partRet[i].prodData[j].setValueFlag === "M") {
                                if (partRet[i].prodData[j].attrValue === "" || partRet[i].prodData[j].attrValue == null) {
                                    $('#q_' + partRet[i].prodData[j].eventType + partRet[i].prodData[j].assembleId).val(partRet[i].prodData[j].attrValue);
                                    $('#q_' + partRet[i].prodData[j].eventType + partRet[i].prodData[j].assembleId).select2();
                                } else {
                                    $('#q_' + partRet[i].prodData[j].eventType + partRet[i].prodData[j].assembleId).val(partRet[i].prodData[j].attrValue.split(","));
                                    $('#q_' + partRet[i].prodData[j].eventType + partRet[i].prodData[j].assembleId).select2();
                                }
                            } else {
                                $('#q_' + partRet[i].prodData[j].eventType + partRet[i].prodData[j].assembleId).val(partRet[i].prodData[j].attrValue);
                            }
                        } else if (partRet[i].prodData[j].assembleType === "PART") {
                            for (var k = 0; k < partRet[i].prodData[j].data.length; k++) {
                                if (partRet[i].prodData[j].data[k].setValueFlag === "M") {
                                    if (partRet[i].prodData[j].data[k].attrValue === "" || partRet[i].prodData[j].data[k].attrValue == null) {
                                        $('#p_' + partRet[i].prodData[j].eventType + partRet[i].prodData[j].data[k].attrKey).val(partRet[i].prodData[j].data[k].attrValue);
                                        $('#p_' + partRet[i].prodData[j].eventType + partRet[i].prodData[j].data[k].attrKey).select2();
                                    } else {
                                        $('#p_' + partRet[i].prodData[j].eventType + partRet[i].prodData[j].data[k].attrKey).val(partRet[i].prodData[j].data[k].attrValue.split(","));
                                        $('#p_' + partRet[i].prodData[j].eventType + partRet[i].prodData[j].data[k].attrKey).select2();
                                    }
                                } else {
                                    $('#p_' + partRet[i].prodData[j].eventType + partRet[i].prodData[j].data[k].attrKey).val(partRet[i].prodData[j].data[k].attrValue);
                                }
                            }
                        }
                    }
                } else if (json[i].assembleType === "PART") {
                    div = showPartDiv(partRet[i].assembleId, partRet[i].partDesc, "O");
                    $("#compontentP").append(div);
                } else if (json[i].assembleType === "ATTR") {
                    div = showAttrDiv(partRet[i].valueMethod, partRet[i].setValueFlag, partRet[i].attrDesc, partRet[i].seqNo, partRet[i].assembleId, "O");
                    $('#' + partRet[i].attrClass + 'D').append(div);
                    if (partRet[i].setValueFlag === "M") {
                        if (partRet[i].attrValue === "" || partRet[i].attrValue == null) {
                            $("#s_" + partRet[i].assembleId).val(partRet[i].attrValue);
                            $("#s_" + partRet[i].assembleId).select2();
                        } else {
                            $("#s_" + partRet[i].assembleId).val(partRet[i].attrValue.split(","));
                            $("#s_" + partRet[i].assembleId).select2();
                        }
                    } else {
                        $("#s_" + partRet[i].assembleId).val(partRet[i].attrValue);
                    }
                }
            }

        },
        "json");
}
$(document).ready(function () {
    $("#save").click(function () {
        if ($("#prodClass").val() === "" || $("#prodRange").val() === "" || $("#prodGroup").val() === "") {
            alert("请输入产品基础信息！");
            return;
        } else if ($("#operateType").val() === "add") {
            if ($("#newProdType").val() === "" || $("#newProdDesc").val() === "") {
                alert("请输入新产品类型！");
                return;
            } else {
                var url = contextPath + "/prodType/insert";
                $.ajax({
                    url: url,
                    async: false,
                    data: {
                        prodType: $("#newProdType").val(),
                        prodDesc: $("#newProdDesc").val(),
                        prodClass: $("#prodClass").val(),
                        prodGroup: $("#prodGroup").val(),
                        prodRange: $("#prodRange").val(),
                        baseProdType: $("#baseProdType").val(),
                        status: $("#status").val(),
                        reqNum: $(".breadcrumb").data("reqNum")
                    },
                    success: function (json) {
                        if (json.retStatus === 'F') {
                            showMsg(json.retMsg, 'info');
                        } else if (json.retStatus === 'S') {
                            var url = contextPath + "/prodDefine/save";
                            var data = [];
                            var index = 0;
                            $("#tabDiv").find(".attr").each(function () {
                                var abc = {};
                                abc["optionFlag"] = $(this).attr("optionFlag");
                                abc["prodType"] = $("#newProdType").val();
                                abc["prodDesc"] = $("#newProdDesc").val();
                                abc["seqNo"] = $(this).attr("seqNo");
                                abc["assembleType"] = "ATTR";
                                abc["assembleId"] = $(this).attr("id");
                                abc["attrKey"] = $(this).attr("id");
                                abc["reqNum"] = $(".breadcrumb").data("reqNum");
                                if ($(this).attr("setValueFlag") === "M") {
                                    if ($('#s_' + abc["assembleId"]).val() == null || $('#s_' + abc["assembleId"]).val() === "") {
                                        abc["attrValue"] = " ";
                                    } else {
                                        abc["attrValue"] = $('#s_' + abc["assembleId"]).val().join(",");
                                    }
                                } else {
                                    abc["attrValue"] = $('#s_' + abc["assembleId"]).val();
                                }
                                data[index] = abc;
                                index++;
                            });
                            $("#compontentE").children().each(function () {
                                var abc = {};
                                var attr = [];
                                var part = [];
                                var part1 = [];
                                var index1 = 0;
                                var index2 = 0;
                                var index3 = 0;
                                abc["optionFlag"] = $(this).attr("optionFlag");
                                abc["prodType"] = $("#newProdType").val();
                                abc["prodDesc"] = $("#newProdDesc").val();
                                abc["seqNo"] = $(this).attr("seqNo");
                                abc["assembleType"] = "EVENT";
                                abc["assembleId"] = $(this).attr("id");
                                abc["eventDefault"] = $(this).attr("eventClass");
                                abc["reqNum"] = $(".breadcrumb").data("reqNum");
                                $(this).find(".attr").each(function () {
                                    var eventAttr = {};
                                    eventAttr["optionFlag"] = $(this).attr("optionFlag");
                                    eventAttr["assembleRule"] = $(this).attr("assembleRule");
                                    eventAttr["eventType"] = abc["assembleId"];
                                    eventAttr["assembleId"] = $(this).attr("id");
                                    eventAttr["seqNo"] = $(this).attr("seqNo");
                                    if ($(this).attr("setValueFlag") === "M") {
                                        if ($('#q_' + eventAttr["eventType"] + eventAttr["assembleId"]).val() == null || $('#q_' + eventAttr["eventType"] + eventAttr["assembleId"]).val() === "") {
                                            eventAttr["attrValue"] = " ";
                                        } else {
                                            eventAttr["attrValue"] = $('#q_' + eventAttr["eventType"] + eventAttr["assembleId"]).val().join(",");
                                        }
                                    } else {
                                        eventAttr["attrValue"] = $('#q_' + eventAttr["eventType"] + eventAttr["assembleId"]).val();
                                    }
                                    attr[index1] = eventAttr;
                                    index1++;
                                });
                                $(this).find(".part").children().find(".attrs").each(function () {
                                    var partAttr = {};
                                    partAttr["optionFlag"] = $(this).parents(".part").attr("optionFlag");
                                    partAttr["eventType"] = abc["assembleId"];
                                    partAttr["assembleId"] = $(this).attr("partId");
                                    partAttr["attrKey"] = $(this).attr("name");
                                    if ($(this).attr("setValueFlag") === "M") {
                                        if ($("#p_" + partAttr["eventType"] + partAttr["attrKey"]).val() == null || $("#p_" + partAttr["eventType"] + partAttr["attrKey"]).val() === "") {
                                            partAttr["attrValue"] = " ";
                                        } else {
                                            partAttr["attrValue"] = $("#p_" + partAttr["eventType"] + partAttr["attrKey"]).val().join(",");
                                        }
                                    } else {
                                        partAttr["attrValue"] = $("#p_" + partAttr["eventType"] + partAttr["attrKey"]).val();
                                    }
                                    part[index2] = partAttr;
                                    index2++;
                                });
                                $(this).find(".part").each(function () {
                                    var eventPart = {};
                                    eventPart["optionFlag"] = $(this).attr("optionFlag");
                                    eventPart["assembleRule"] = $(this).attr("assembleRule");
                                    eventPart["eventType"] = abc["assembleId"];
                                    eventPart["seqNo"] = $(this).attr("seqNo");
                                    eventPart["assembleId"] = $(this).attr("id");
                                    part1[index3] = eventPart;
                                    index3++;
                                });
                                abc["attr"] = attr;
                                abc["part"] = part;
                                abc["part1"] = part1;
                                abc["reqNum"] = $(".breadcrumb").data("reqNum");
                                data[index] = abc;
                                index++;
                            });
                            $("#compontentP").children().each(function () {
                                var abc = {};
                                abc["optionFlag"] = $(this).attr("optionFlag");
                                abc["prodType"] = $("#newProdType").val();
                                abc["assembleType"] = "PART";
                                abc["assembleId"] = $(this).attr("id");
                                abc["reqNum"] = $(".breadcrumb").data("reqNum");
                                data[index] = abc;
                                index++;
                            });
                            console.log("abc  = " + JSON.stringify(data));
                            $.ajax({
                                url: url,
                                async: false,
                                data: JSON.stringify(data),
                                success: function (json) {
                                    if (json.retStatus === 'F') {
                                        showMsg(json.retMsg, 'info');
                                    } else if (json.retStatus === 'S') {
                                        $.ajax({
                                            url: contextPath + "/ProdToJson/End",
                                            data: {
                                                reqNum: $(".breadcrumb").data("reqNum"),
                                                tableNames: tableNames,
                                                transActionId: transActionId
                                            },
                                            dataType: "json",
                                            type: "POST",
                                            success: function (json) {
                                                if (json.retStatus === 'F') {
                                                    showMsg(json.retMsg, 'info');
                                                } else if (json.retStatus === 'S') {
                                                    showMsg(json.retMsg, 'info');
                                                    $("#save").hide();
                                                    $("#nullifyAppNo").hide();
                                                }
                                            }
                                        });
                                    }
                                },
                                dataType: "json",
                                contentType: "application/json",
                                type: "POST"
                            });
                        }
                    },
                    dataType: "json",
                    type: "POST"
                });
            }
        } else if ($("#operateType").val() === "update") {
            var url = contextPath + "/prodType/update";
            $.ajax({
                url: url,
                async: false,
                data: {
                    prodType: $("#prodType").val(),
                    prodDesc: $("#prodDesc").val(),
                    prodClass: $("#prodClass").val(),
                    prodGroup: $("#prodGroup").val(),
                    prodRange: $("#prodRange").val(),
                    baseProdType: $("#baseProdType").val(),
                    status: $("#status").val(),
                    reqNum: $(".breadcrumb").data("reqNum")
                },
                success: function (json) {
                    if (json.retStatus === 'F') {
                        showMsg(json.retMsg, 'info');
                    } else if (json.retStatus === 'S') {
                        var url = contextPath + "/prodDefine/save";
                        var data = [];
                        var index = 0;
                        $("#tabDiv").find(".attr").each(function () {
                            var abc = {};
                            abc["optionFlag"] = $(this).attr("optionFlag");
                            abc["prodType"] = $("#prodType").val();
                            abc["prodDesc"] = $("#prodDesc").val();
                            abc["seqNo"] = $(this).attr("seqNo");
                            abc["assembleType"] = "ATTR";
                            abc["assembleId"] = $(this).attr("id");
                            abc["attrKey"] = $(this).attr("id");
                            abc["reqNum"] = $(".breadcrumb").data("reqNum");
                            if ($(this).attr("setValueFlag") === "M") {
                                if ($('#s_' + abc["assembleId"]).val() == null || $('#s_' + abc["assembleId"]).val() === "") {
                                    abc["attrValue"] = " ";
                                } else {
                                    abc["attrValue"] = $('#s_' + abc["assembleId"]).val().join(",");
                                }
                            } else {
                                abc["attrValue"] = $('#s_' + abc["assembleId"]).val();
                            }
                            data[index] = abc;
                            index++;
                        });
                        $("#compontentE").children().each(function () {
                            var abc = {};
                            var attr = [];
                            var part = [];
                            var part1 = [];
                            var index1 = 0;
                            var index2 = 0;
                            var index3 = 0;
                            abc["optionFlag"] = $(this).attr("optionFlag");
                            abc["prodType"] = $("#prodType").val();
                            abc["prodDesc"] = $("#prodDesc").val();
                            abc["seqNo"] = $(this).attr("seqNo");
                            abc["assembleType"] = "EVENT";
                            abc["assembleId"] = $(this).attr("id");
                            abc["eventDefault"] = $(this).attr("eventClass");
                            abc["reqNum"] = $(".breadcrumb").data("reqNum");
                            $(this).find(".attr").each(function () {
                                var eventAttr = {};
                                eventAttr["optionFlag"] = $(this).attr("optionFlag");
                                eventAttr["assembleRule"] = $(this).attr("assembleRule");
                                eventAttr["seqNo"] = $(this).attr("seqNo");
                                eventAttr["eventType"] = abc["assembleId"];
                                eventAttr["assembleId"] = $(this).attr("id");
                                if ($(this).attr("setValueFlag") === "M") {
                                    if ($('#q_' + eventAttr["eventType"] + eventAttr["assembleId"]).val() == null || $('#q_' + eventAttr["eventType"] + eventAttr["assembleId"]).val() === "") {
                                        eventAttr["attrValue"] = " ";
                                    } else {
                                        eventAttr["attrValue"] = $('#q_' + eventAttr["eventType"] + eventAttr["assembleId"]).val().join(",");
                                    }
                                } else {
                                    eventAttr["attrValue"] = $('#q_' + eventAttr["eventType"] + eventAttr["assembleId"]).val();
                                }
                                attr[index1] = eventAttr;
                                index1++;
                            });
                            $(this).find(".part").children().find(".attrs").each(function () {
                                var partAttr = {};
                                partAttr["optionFlag"] = $(this).parents(".part").attr("optionFlag");
                                partAttr["eventType"] = abc["assembleId"];
                                partAttr["assembleId"] = $(this).attr("partId");
                                partAttr["attrKey"] = $(this).attr("name");
                                if ($(this).attr("setValueFlag") === "M") {
                                    if ($("#p_" + partAttr["eventType"] + partAttr["attrKey"]).val() == null || $("#p_" + partAttr["eventType"] + partAttr["attrKey"]).val() === "") {
                                        partAttr["attrValue"] = " ";
                                    } else {
                                        partAttr["attrValue"] = $("#p_" + partAttr["eventType"] + partAttr["attrKey"]).val().join(",");
                                    }
                                } else {
                                    partAttr["attrValue"] = $("#p_" + partAttr["eventType"] + partAttr["attrKey"]).val();
                                }
                                part[index2] = partAttr;
                                index2++;
                            });
                            $(this).find(".part").each(function () {
                                var eventPart = {};
                                eventPart["optionFlag"] = $(this).attr("optionFlag");
                                eventPart["assembleRule"] = $(this).attr("assembleRule");
                                eventPart["seqNo"] = $(this).attr("seqNo");
                                eventPart["eventType"] = abc["assembleId"];
                                eventPart["assembleId"] = $(this).attr("id");
                                part1[index3] = eventPart;
                                index3++;
                            });
                            abc["attr"] = attr;
                            abc["part"] = part;
                            abc["part1"] = part1;
                            abc["reqNum"] = $(".breadcrumb").data("reqNum");
                            data[index] = abc;
                            index++;
                        });
                        $("#compontentP").children().each(function () {
                            var abc = {};
                            abc["optionFlag"] = $(this).attr("optionFlag");
                            abc["prodType"] = $("#prodType").val();
                            abc["assembleType"] = "PART";
                            abc["assembleId"] = $(this).attr("id");
                            abc["reqNum"] = $(".breadcrumb").data("reqNum");
                            data[index] = abc;
                            index++;
                        });
                        console.log("abc  = " + JSON.stringify(data));
                        $.ajax({
                            url: url,
                            data: JSON.stringify(data),
                            success: function (json) {
                                if (json.retStatus === 'F') {
                                    showMsg(json.retMsg, 'info');
                                } else if (json.retStatus === 'S') {
                                    $.ajax({
                                        url: contextPath + "/ProdToJson/End",
                                        data: {
                                            tableNames: tableNames,
                                            reqNum: $(".breadcrumb").data("reqNum"),
                                            transActionId: transActionId
                                        },
                                        dataType: "json",
                                        type: "POST",
                                        success: function (json) {
                                            if (json.retStatus === 'F') {
                                                showMsg(json.retMsg, 'info');
                                            } else if (json.retStatus === 'S') {
                                                showMsg(json.retMsg, 'info');
                                                $("#nullifyAppNo").hide();
                                                $("#save").hide();
                                            }
                                        }
                                    });
                                }
                            },
                            dataType: "json",
                            contentType: "application/json",
                            type: "POST"
                        });
                    }
                },
                dataType: "json",
                type: "POST"
            });
        } else if ($("#operateType").val() === "copy") {
            if ($("#newProdType").val() == "" || $("#newProdDesc").val() === "") {
                alert("请输入新产品类型！");
                return;
            } else {
                var url = contextPath + "/prodType/insert";
                $.ajax({
                    url: url,
                    async: false,
                    data: {
                        prodType: $("#newProdType").val(),
                        prodDesc: $("#newProdDesc").val(),
                        prodClass: $("#prodClass").val(),
                        prodGroup: $("#prodGroup").val(),
                        prodRange: $("#prodRange").val(),
                        baseProdType: $("#baseProdType").val(),
                        status: $("#status").val(),
                        reqNum: $(".breadcrumb").data("reqNum")
                    },
                    success: function (json) {
                        if (json.retStatus === 'F') {
                            showMsg(json.retMsg, 'info');
                        } else if (json.retStatus === 'S') {
                            var url = contextPath + "/prodDefine/copy";
                            var data = [];
                            var index = 0;
                            $("#tabDiv").find(".attr").each(function () {
                                var abc = {};
                                abc["optionFlag"] = $(this).attr("optionFlag");
                                abc["prodType"] = $("#newProdType").val();
                                abc["oldProdType"] = $("#prodType").val();
                                abc["prodDesc"] = $("#newProdDesc").val();
                                abc["assembleType"] = "ATTR";
                                abc["seqNo"] = $(this).attr("seqNo");
                                abc["assembleId"] = $(this).attr("id");
                                abc["attrKey"] = $(this).attr("id");
                                abc["reqNum"] = $(".breadcrumb").data("reqNum");
                                if ($(this).attr("setValueFlag") === "M") {
                                    if ($('#s_' + abc["assembleId"]).val() == null || $('#s_' + abc["assembleId"]).val() === "") {
                                        abc["attrValue"] = " ";
                                    } else {
                                        abc["attrValue"] = $('#s_' + abc["assembleId"]).val().join(",");
                                    }
                                } else {
                                    abc["attrValue"] = $('#s_' + abc["assembleId"]).val();
                                }
                                data[index] = abc;
                                index++;
                            });
                            $("#compontentP").children().each(function () {
                                var abc = {};
                                abc["optionFlag"] = $(this).attr("optionFlag");
                                abc["prodType"] = $("#newProdType").val();
                                abc["assembleType"] = "PART";
                                abc["assembleId"] = $(this).attr("id");
                                abc["reqNum"] = $(".breadcrumb").data("reqNum");
                                data[index] = abc;
                                index++;
                            });

                            $("#compontentE").children().each(function () {
                                var abc = {};
                                var attr = [];
                                var part = [];
                                var part1 = [];
                                var index1 = 0;
                                var index2 = 0;
                                var index3 = 0;
                                abc["optionFlag"] = $(this).attr("optionFlag");
                                abc["prodType"] = $("#newProdType").val();
                                abc["prodDesc"] = $("#newProdDesc").val();
                                abc["assembleType"] = "EVENT";
                                abc["seqNo"] = $(this).attr("seqNo");
                                abc["assembleId"] = $(this).attr("id");
                                abc["eventDefault"] = $(this).attr("eventClass");
                                abc["reqNum"] = $(".breadcrumb").data("reqNum");
                                $(this).find(".attr").each(function () {
                                    var eventAttr = {};
                                    eventAttr["optionFlag"] = $(this).attr("optionFlag");
                                    eventAttr["assembleRule"] = $(this).attr("assembleRule");
                                    eventAttr["seqNo"] = $(this).attr("seqNo");
                                    eventAttr["eventType"] = abc["assembleId"];
                                    eventAttr["assembleId"] = $(this).attr("id");
                                    if ($(this).attr("setValueFlag") === "M") {
                                        if ($('#q_' + eventAttr["eventType"] + eventAttr["assembleId"]).val() == null || $('#q_' + eventAttr["eventType"] + eventAttr["assembleId"]).val() === "") {
                                            eventAttr["attrValue"] = " ";
                                        } else {
                                            eventAttr["attrValue"] = $('#q_' + eventAttr["eventType"] + eventAttr["assembleId"]).val().join(",");
                                        }
                                    } else {
                                        eventAttr["attrValue"] = $('#q_' + eventAttr["eventType"] + eventAttr["assembleId"]).val();
                                    }
                                    attr[index1] = eventAttr;
                                    index1++;
                                });
                                $(this).find(".part").children().find(".attrs").each(function () {
                                    var partAttr = {};
                                    partAttr["optionFlag"] = $(this).parents(".part").attr("optionFlag");
                                    partAttr["eventType"] = abc["assembleId"];
                                    partAttr["assembleId"] = $(this).attr("partId");
                                    partAttr["attrKey"] = $(this).attr("name");
                                    if ($(this).attr("setValueFlag") === "M") {
                                        if ($("#p_" + partAttr["eventType"] + partAttr["attrKey"]).val() == null || $("#p_" + partAttr["eventType"] + partAttr["attrKey"]).val() === "") {
                                            partAttr["attrValue"] = " ";
                                        } else {
                                            partAttr["attrValue"] = $("#p_" + partAttr["eventType"] + partAttr["attrKey"]).val().join(",");
                                        }
                                    } else {
                                        partAttr["attrValue"] = $("#p_" + partAttr["eventType"] + partAttr["attrKey"]).val();
                                    }
                                    part[index2] = partAttr;
                                    index2++;
                                });
                                $(this).find(".part").each(function () {
                                    var eventPart = {};
                                    eventPart["optionFlag"] = $(this).attr("optionFlag");
                                    eventPart["assembleRule"] = $(this).attr("assembleRule");
                                    eventPart["seqNo"] = $(this).attr("seqNo");
                                    eventPart["eventType"] = abc["assembleId"];
                                    eventPart["assembleId"] = $(this).attr("id");
                                    part1[index3] = eventPart;
                                    index3++;
                                });
                                abc["attr"] = attr;
                                abc["part"] = part;
                                abc["part1"] = part1;
                                data[index] = abc;
                                index++;
                            });

                            console.log("abc[i]  = " + JSON.stringify(data));
                            $.ajax({
                                url: url,
                                async: false,
                                data: JSON.stringify(data),
                                success: function (json) {
                                    if (json.retStatus === 'F') {
                                        showMsg(json.retMsg, 'info');
                                    } else if (json.retStatus === 'S') {
                                        $.ajax({
                                            url: contextPath + "/ProdToJson/End",
                                            data: {
                                                tableNames: tableNames,
                                                reqNum: $(".breadcrumb").data("reqNum"),
                                                transActionId: transActionId
                                            },
                                            dataType: "json",
                                            type: "POST",
                                            success: function (json) {
                                                if (json.retStatus === 'F') {
                                                    showMsg(json.retMsg, 'info');
                                                } else if (json.retStatus === 'S') {
                                                    showMsg(json.retMsg, 'info');
                                                    $("#save").hide();
                                                    $("#nullifyAppNo").hide();
                                                }
                                            }
                                        });
                                    }
                                },
                                dataType: "json",
                                contentType: "application/json",
                                type: "POST"
                            });
                        }
                    },
                    dataType: "json",
                    type: "POST"
                });
            }
        }

    });


    handlePortletTools();
    handlePortletTools3();
    //产品类型事件 prodType
    $("#prodType").focus(function () {
        if ($("#Bmodule").val() === "") {
            alert("请选择所属业务模块！");
            return;
        } else {
            layer.open({
                type: 2,
                title: "请选择产品",
                content: contextPath + "/app/pf/jsp/prodTreeTable.jsp",
                area: [500 + 'px', 400 + 'px'],
                end: function () {

                    $("#query").click();
                }
            });
        }
    });

});
function nullifyAppNo() {
    layer.confirm('确认要作废吗？', function () {
        var url = contextPath + "/baseCommon/nullifyParaData";
        sendPostRequest(url, {
            transactionId: transActionId,
            reqNum: $(".breadcrumb").data("reqNum")
        }, callback_fmCompanyNullify, "json");
    });
}
function callback_fmCompanyNullify(json) {
    if (json.success) {
        showMsg("作废成功");
        $("#nullifyAppNo").hide();
        $("#save").hide();
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}
function showProdType() {
    var url = contextPath + "/prodType/selectByPrimaryKey";
    $.ajax({
        url: url,
        data: "prodType=" + $("#prodType").val(),
        success: function (partRet) {
            var $prodClass = $("#prodClass").select2();
            $prodClass.val(partRet.data.prodClass).trigger("change");
            var $prodRange = $("#prodRange").select2();
            $prodRange.val(partRet.data.prodRange).trigger("change");
            var $prodGroup = $("#prodGroup").select2();
            $prodGroup.val(partRet.data.prodGroup).trigger("change");
            var $baseProdType = $("#baseProdType").select2();
            $baseProdType.val(partRet.data.baseProdType).trigger("change");
            var $status = $("#status").select2();
            $status.val(partRet.data.status).trigger("change");
        },
        dataType: "json"
    });
}

function showMsgDuringTime(msg) {
    layer.msg(msg);
    setTimeout(function () {
        layer.closeAll('dialog');
    }, 1000);
    if (msg === "添加成功") {
        layer.close(layer_add_index);
    }
    if (msg === "复制成功") {
        layer.close(layer_add_index);
    }
}