$(document).ready(function () {
    var rowData = parent.$('#prodList').DataTable().rows(".selected").data()[0];
    $("#newCardProductCode").val(rowData.CARD_PRODUCT_CODE);
    $("#cardProductName").val(rowData.CARD_PRODUCT_NAME);

    $.session.remove("infoBackUp_code");
    $.session.set("infoBackUp_code",$("#newCardProductCode").val());

    dataInit(rowData);
    stepInit();
    $(".flowStep").data("flag","lookup");
});

function dataInit(rowData) {
    var type = rowData.CARD_PRODUCT_TYPE;
    if (type != null && type === "0"){
        $("#cardProductType").val("虚户类");
    }else if (type != null && type === "1"){
        $("#cardProductType").val("实户类");
    }else {
        $("#cardProductType").val("未定义");
    }

    var channel = rowData.PUBLISH_CHANNEL;
    if (channel != null && channel === "1"){
        $("#publishChannel").val("本行发卡");
    }else if (channel != null && channel === "2"){
        $("#publishChannel").val("合作发卡");
    }else {
        $("#publishChannel").val("未定义");
    }
}

function stepInit() {
    //步骤函数初始化
    var step = $("#myStep").step({
        animate: true,
        initStep: 1,
        maxStepSize: 4,
        speed: 500
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
}