var tableNames = "CMC_PRODUCT_INFO,CMC_CARD_NO_ROLE_EX,CMC_CARD_PRODUCT_CHANNEL,CMC_PRODUCT_LIMIT";
var transActionId = "CMC_PRODUCT_TYPE";
$(function () {
    $.session.clear();
    $("#newProdDiv").hide();
    $("#attrList").hide();
    $.ajax({
        url: contextPath + "/CardProdToJson/Start",
        data: {
            reqNum: parent.$(".breadcrumb").data("reqNum"),
            tableNames: tableNames,
            tableName: "PROD_CMC_PRODUCT_TYPE",
            tableDesc: "卡产品定义"
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
        maxStepSize: 4,
        speed: 500
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
    $("#one").click(function () {
        step.nextStep();
    });
    $("#two").click(function () {
        step.nextStep();
    });
    $("#three").click(function () {
        step.nextStep();
    });

    $("#reset").click(function () {
       reset();
       resetElement();
    });

    $("#cardProductInfo").click(function () {
        if ($("#operateType").val() === "") {
            alert("请先选择操作类型！");
        } else if ($("#operateType").val() === "add") {
            if ($("#newCardProductCode").val() === "" || $("#newCardProductName").val() === "") {
                alert("请先输入新定义产品类型/描述！");
            } else {
                $("#index0").attr("src", "CmcProdInfoDetail.jsp");
            }
        } else {
            $("#index0").attr("src", "CmcProdInfoDetail.jsp");
        }
    });
    $("#cardRoleEx").click(function () {
        if ($("#operateType").val() === "") {
            alert("请先选择操作类型！");
        } else if ($("#operateType").val() === "add") {
            if ($("#newCardProductCode").val() === "" || $("#newCardProductName").val() === "") {
                alert("请先输入新定义产品类型/描述！");
            } else {
                $("#index1").attr("src", "CmcCardNoRoleExDetail.jsp");
            }
        } else {
            $("#index1").attr("src", "CmcCardNoRoleExDetail.jsp");
        }
    });

    $("#cardProdChannel").click(function () {
        if ($("#operateType").val() === "") {
            alert("请先选择操作类型！");
        } else if ($("#operateType").val() === "add") {
            if ($("#newCardProductCode").val() === "" || $("#newCardProductName").val() === "") {
                alert("请先输入新定义产品类型/描述！");
            } else {
                $("#index5").attr("src", "CmcProductChannelDetail.jsp");
            }
        } else {
            $("#index5").attr("src", "CmcProductChannelDetail.jsp");
        }
    });
    $("#cardProductLimit").click(function () {
        if ($("#operateType").val() === "") {
            alert("请先选择操作类型！");
        } else if ($("#operateType").val() === "add") {
            if ($("#newCardProductCode").val() === "" || $("#newCardProductName").val() === "") {
                alert("请先输入新定义产品类型/描述！");
            } else {
                $("#index4").attr("src", "CmcProductLimitDetail.jsp");
            }
        } else {
            $("#index4").attr("src", "CmcProductLimitDetail.jsp");
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

function resetElement() {
    if ($('#operateType').val() === "add" ){
        $("#newCardProductCode").val("");
        $("#newCardProductName").val("");
        $("#status").val("");
        $("#cardProductType").val("");
    }
    document.location.reload();
}

$(document).ready(function () {

    $('#operateType').change(function () {
        $("#prodType").val("");
        $("#prodDesc").val("");
        $("#newCardProductCode").val("");
        $("#newCardProductName").val("");
        $("#status").select2().val("A").trigger("change");
        $("#tabDiv").html("");
        $("#list").html("");

        if ($("#operateType").val() === "add") {
            $("#newProdDiv").show();
            $("#prodType").attr("disabled", true);
            $("#prodDesc").attr("disabled", true);
            $("#newCardProductCode").attr("disabled", false);
            $("#newCardProductName").attr("disabled", false);
            $("#cardProductType").attr("disabled", false);
            $("#status").attr("disabled", false);
            $("#one").hide();
            $("#two").hide();
        } else {
            $("#newProdDiv").show();
            $("#newCardProductCode").attr("disabled", true);
            $("#newCardProductName").attr("disabled", true);
            $("#cardProductType").attr("disabled", true);
            $("#status").attr("disabled", false);
            $("#prodType").attr("disabled", false);
            $("#prodDesc").attr("disabled", false);
            $("#one").show();

        }
    });

    $(".select2").select2();
});

$(document).ready(function () {
    $("#prodType").focus(function () {
        if ($("#publishChannel").val() === "") {
            alert("请输入发行渠道！");
            return;
        } else {
            layer.open({
                type: 2,
                title: "请选择产品",
                content: contextPath + "/app/cmc/jsp/CmcProdTreeTable.jsp",
                area: [500 + 'px', 400 + 'px'],
                end: function () {
                    $("#query").click();
                }
            });
        }
    });
});

$(document).ready(function () {
    jQuery('#query').on('click', '',
        function () {
            if ($("#prodType").val() === "") {
                alert("请输入产品类型");
                return;
            }
            getAttr();
        }
    );
});

function getAttr() {
    var url = contextPath + "/cardProdType/getAll?cardProductCode="+ $("#prodType").val();
    $.ajax({
        url: url,
        type : "POST",
        dataType : "json",
        success:showProdType
    });
}

function showProdType(json) {
    var data = JSON.parse(json.data);
    if (data.length > 1){
        showMsg("查询记录不唯一！","error");
    }else if (data.length === 1){
        $.session.clear();
        $("#newCardProductCode").val(data[0].cardProductCode);
        $("#newCardProductName").val(data[0].cardProductName);
        $.session.set("cardProductCode",data[0].cardProductCode);
        $("#cardProductType").val(data[0].cardProductType).select2();
        $("#status").val(data[0].status).select2();
    }else {
        showMsg("查询记录为空！","error");
    }
}

$(document).ready(function () {
    var elReg = /^[A-Z0-9]+$/;
    $("#newCardProductCode").blur(function () {
        if ($("#newCardProductCode").val() === "") {
            alert("请输入新产品编号！");
            return;
        } else if (elReg.test($("#newCardProductCode").val()) !== true) {
            alert("产品编号应为大写英文/数字！");
            $("#newCardProductCode").val("");
            return;
        }else if ($("#newCardProductCode").val().length > 50){
            alert("产品编号最大长度为50！");
            $("#newCardProductCode").val("");
            return;
        } else {
            $.session.clear();
            $.session.set("cardProductCode",$("#newCardProductCode").val());
            var url = contextPath + "/cardProdType/getProdTypeKey";
            $.ajax({
                url: url,
                data: "prodType=" + $("#newCardProductCode").val(),
                success: function (json) {
                    if (json.retStatus === 'F') {
                        showMsg(json.retMsg, 'info');
                    } else if (json.retStatus === 'S') {
                        if (json.data.length > 0){
                            $("#newCardProductName").val(json.data[0].CARD_PRODUCT_NAME);
                            $("#cardProductType").val(json.data[0].CARD_PRODUCT_TYPE);
                        }
                        return;
                    }
                },
                dataType: "json"
            });
        }
    });
});

function submitToAddInfo(urlAdd,jsonData) {
    $.ajax({
        url: urlAdd,
        data:jsonData,
        async: false,
        success:function (json) {
            if (json.retStatus === "F"){
                showMsg(json.retMsg,"error");
            }else{
                dbSubmitToSave();
            }
        },
        error:function () {
            showMsg("执行异常","error");
        },
        dataType:"json"
    });
}

function dbSubmitToSave() {
    var url = contextPath + "/CardProdToJson/End";
    var jsonData1 ={
        reqNum:$(".breadcrumb").data("reqNum"),
        tableNames: tableNames,
        transActionId: transActionId
    };
    $.ajax({
        url: url,
        async: false,
        data: jsonData1 ,
        success: function (json) {
            if (json.errorMsg) {
                showMsg(json.errorMsg, error);
            } else if (json.retStatus === 'S') {
                var url2 = contextPath + "/cardProdType/insert";
                var jsonData2 = JSON.stringify({
                    cardProductCode: $("#newCardProductCode").val(),
                    cardProductName: $("#newCardProductName").val(),
                    status: $("#status").val(),
                    publishChannel: $("#publishChannel").val(),
                    cardProductType: $("#cardProductType").val(),
                    reqNum: $(".breadcrumb").data("reqNum")
                });
                $.ajax({
                    url:url2,
                    data:jsonData2,
                    success: function (json) {
                        if (json.retStatus === 'F') {
                            showMsg(json.retMsg, 'error');
                        } else if (json.retStatus === 'S') {
                            $("#save").hide();
                            $("#nullifyAppNo").show();
                            reset();
                        }
                    },
                    dataType: "json",
                    type: "POST",
                    contentType: "application/json"
                });
            }
        },
        dataType: "json",
        type: "POST"
    });
}

function reset() {
    var url = contextPath+"/cardProdType/reset";
    $.ajax({
        url:url,
        data:{
            reqNum:$(".breadcrumb").data("reqNum")
        },
        dataType:"json",
        success:function () {
            showMsg("操作成功","info");
        },
        error:function (json) {
            showMsg(json.retMsg,"error");
        }
    });
}

function submitToUpdateInfo(urlUp,jsonDataUp) {
    $.ajax({
        url: urlUp,
        async: false,
        data: jsonDataUp ,
        success: function (json) {
            if (json.errorMsg) {
                showMsg(json.errorMsg, error);
            } else if (json.retStatus === 'S') {
                $.ajax({
                    url: contextPath + "/CardProdToJson/End",
                    data: {
                        reqNum: $(".breadcrumb").data("reqNum"),
                        tableNames: tableNames,
                        transActionId: transActionId
                    },
                    dataType: "json",
                    type: "POST",
                    success: function (json) {
                        if (json.retStatus === 'F') {
                            showMsg(json.retMsg, 'error');
                        } else if (json.retStatus === 'S') {
                            $("#save").hide();
                            $("#nullifyAppNo").show();
                            reset();
                        }
                    }
                });
            }
        },
        dataType: "json",
        type: "POST",
        contentType: "application/json"
    });
}
$(document).ready(function () {
    $("#save").click(function () {
        if ($("#operateType").val() === "add") {
            if ($("#newCardProductCode").val() === "" || $("#newCardProductName").val() === "") {
                alert("请输入新产品类型和描述！");
                return;
            } else {
                var urlAdd = contextPath +"/cardProdType/validate";
                var jsonData = {reqNum:$(".breadcrumb").data("reqNum")};
                submitToAddInfo(urlAdd,jsonData);
            }
        } else if ($("#operateType").val() === "update") {
            var urlUp = contextPath + "/cardProdType/updateReqNum";
            var jsonDataUp = JSON.stringify({
                cardProductCode: $("#newCardProductCode").val(),
                cardProductName: $("#newCardProductName").val(),
                status: $("#status").val(),
                publishChannel: $("#publishChannel").val(),
                cardProductType: $("#cardProductType").val(),
                reqNum: $(".breadcrumb").data("reqNum")
            });
            submitToUpdateInfo(urlUp,jsonDataUp);
        }
    });
    handlePortletTools();
    handlePortletTools3();
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
        $("#nullifyAppNo").hide();
        $("#save").hide();
        insertNullify();
        showMsg("作废成功", 'success');
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function insertNullify() {
    if ($("#operateType").val() === "add"){
        var url = contextPath + "/cardProdType/delete";
        sendPostRequest(url, {reqNum: $(".breadcrumb").data("reqNum")},callback_fmCompanyNullify2,"json");
    }
}

function callback_fmCompanyNullify2(json) {
    if (json.retStatus ==="S") {
        showMsg("作废成功");
    } else {
        showMsg(json.errorMsg, 'errorMsg');
    }
}