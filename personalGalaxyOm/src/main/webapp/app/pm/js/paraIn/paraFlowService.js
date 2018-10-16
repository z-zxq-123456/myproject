var jspUrl,jspViewUrl;
//用于流程控制所用参数，改成全局变量便于控制和复用
var indexForEdit = null;
var indexForImport = null;
var step,enterOrCheckFlag,deleteAllowed,sequenceNumber,statusFlag;
var transactionId = null;
var systemId = null;
var transactionDesc=null;
var maxStep;
$(document).ready(function() {
    $.ajax({
        url: contextPath + "/paraNamespace/userStep",
        type: "POST",
        dataType: "json",
        async: false,
        success: function (json) {
            if (json.userStep) {
                if (json.userStep === "4") {
                    maxStep = 4;
                }
                if (json.userStep === "5") {
                    maxStep = 5;
                }
            }
        }
    });

    step = $("#handleTransactionStep").step({
        animate: true,
        initStep: 1,
        maxStepSize: maxStep,
        speed: 1000
    });

    getPkList({
        url: contextPath + "/pklist/getSystemIgnoreProd",
        id: "systemId",
        async: false
    });

    $("#systemId").change(function () {
        systemId = $("#systemId").val();
        if (systemId !== "") {
            $("#selectSystem").removeClass("disabled");
            $("#selectSystem").show();
        } else {
            $("#selectSystem").addClass("disabled");
            $("#selectSystem").hide();
        }
    });

    $("#selectSystem").click(function (event) {
        systemId = $("#systemId").val();
        if (systemId === "") {
            showMsg("请先选择系统！");
            return;
        }
        step.nextStep();
        getModuleBySystem();
    });

    $("#moduleId").change(function () {
        if ($("#moduleId").val() !== "") {
            $("#selectModule").removeClass("disabled");
            $("#selectModule").show();
        } else {
            $("#selectModule").addClass("disabled");
            $("#selectModule").hide();
        }
    });

    $("#selectModule").click(function (event) {
        if ($("#moduleId").val() === "") {
            showMsg("请先选择模块！");
            return;
        }
        step.nextStep();
        getTableByNamespace();
    });

    $("#selSystemBack").click(function (event) {
        step.preStep();
    });

    function getModuleBySystem() {
        if (systemId === "") {
            $("#moduleId").empty();
            getPkList({
                url: contextPath + "/pklist/getModuleBySystem",
                params: {systemId: null},
                id: "moduleId",
                async: false
            });
        } else {
            $("#moduleId").empty();
            getPkList({
                url: contextPath + "/pklist/getModuleBySystem",
                params: {systemId: systemId},
                id: "moduleId",
                async: false
            });
        }
    }

    $("#transactionId").change(function () {
        transactionId = $("#transactionId").val();
        if (transactionId !== "") {
            $("#handleData").removeClass("disabled");
            $("#viewDataNext").removeClass("disabled");
            $("#viewData").removeClass("disabled");
            $("#handleData").show();
            $("#viewDataNext").show();
            $("#viewData").show();
            if (maxStep === 5) {
                $("#importData").removeClass("disabled");
                $("#importDataNext").removeClass("disabled");
                $("#editDataNext").removeClass("disabled");
                $("#editData").removeClass("disabled");
                $("#editDataNext").show();
                $("#editData").show();
                $("#importDataNext").show();
                $("#importData").show();
                afterSelectPara("view", "请查看或编辑"+ transactionId +"交易的数据");
            }else
            {
                $("#importData").addClass("disabled");
                $("#importDataNext").addClass("disabled");
                $("#nullifyDataNext").addClass("disabled");
                $("#nullifyData").addClass("disabled");
                $("#editDataNext").addClass("disabled");
                $("#editData").addClass("disabled");
                $("#nullifyData").hide();
                $("#nullifyDataNext").hide();
                $("#editData").hide();
                $("#editDataNext").hide();
                $("#importData").hide();
                $("#importDataNext").hide();
                showMsgDuringTime( "请查看"+ transactionId +"交易的数据");
            }
            getJspUrlByTableName();
            indexForEdit = null;
        }
        else
        {
            $("#handleData").addClass("disabled");
            $("#viewDataNext").addClass("disabled");
            $("#editDataNext").addClass("disabled");
            $("#editData").addClass("disabled");
            $("#nullifyDataNext").addClass("disabled");
            $("#nullifyData").addClass("disabled");
            $("#importData").addClass("disabled");
            $("#importDataNext").addClass("disabled");
            $("#handleData").hide();
            $("#viewDataNext").hide();
            $("#nullifyData").hide();
            $("#nullifyDataNext").hide();
            $("#editData").hide();
            $("#editDataNext").hide();
            $("#importData").hide();
            $("#importDataNext").hide();
        }
        transactionId = $("#transactionId").val();
    });

    $("#handleData").click(function(event){
        step.nextStep();
    });

    $("#selTableBack").click(function(event){
        step.preStep();
    });

    $("#selModuleBack").click(function(event){
        step.preStep();
    });

    $("#viewTableBack").click(function(event){
        step.preStep();
    });

    $("#nullifyData").click(function(event) {
        nullifyAppNo();
    });

    $("#nullifyDataNext").click(function(event) {
        nullifyAppNo();
    });

    $("#editDataNext").click(function(event){
        step.nextStep();
        fillData();
    });

    $("#viewDataNext").click(function(event){
        step.nextStep();
        $("#viewData").removeClass("disabled");
        $("#viewData").show();
        viewData();
    });

    $("#viewData").click(function(event){
        viewData();
    });

    $("#editData").click(function(event){
        fillData();
    });

    $("#importDataNext").click(function(event){
        importData();
    });

    $("#importData").click(function(event){
        importData();
    });

    $(".select2").select2();
});

function getTableByNamespace() {
    $("#transactionId").empty();
    getPkList({
        url: contextPath + "/pklist/getTableForModifingDataBySystemModule",
        params: {
            systemId: systemId,
            moduleId: $("#moduleId").val()
        },
        id: "transactionId",
        async: false
    });
}

function getJspUrlByTableName() {
    $.ajax({
        url: contextPath + "/paraFlowService/getFullInfoForTableOrg",
        type: "POST",
        dataType: "json",
        async: false,
        data: {
            transactionId: transactionId,
            systemId: systemId
        },
        success: function (json) {
            jspUrl = json.jspUrl;
            jspViewUrl = jspUrl;
            if(json.multiDB==="Y")
            {
                jspViewUrl = json.jspViewUrl;
                $("#importData").addClass("disabled");
                $("#importDataNext").addClass("disabled");
                $("#importData").hide();
                $("#importDataNext").hide();
            }
        }
    });
}

function nullifyAppNo() {
    layer.confirm('确认要作废吗？', function () {
        var url = contextPath + "/baseCommon/nullifyParaData";
        sendPostRequest(url, {
            transactionId: transactionId,
            reqNum:$("#appSeriesNumber").val()
        }, callback_fmCompanyNullify, "json");
    });
}

function  callback_fmCompanyNullify(json) {
    if (json.success) {
        afterSelectPara("nullify", "作废成功");
        $("#nullifyDataNext").addClass("disabled");
        $("#nullifyData").addClass("disabled");

        $("#nullifyDataNext").hide();
        $("#nullifyData").hide();
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function viewData() {
    if (transactionId === "") {
        showMsg("请先选择参数表！");
        return;
    }
    needButton("NoEdit", "N", "无");
    var index = layer.open({
        type: 2,
        title:  "交易：" + transactionDesc + '   流水编号：' + sequenceNumber + '   当前状态：' + statusFlag,
        content: contextPath + "/" + jspViewUrl

    });
    layer.full(index);
}


function importData() {
    if (transactionId === "") {
        showMsg("请先选择参数表！");
        return;
    }
    afterSelectPara("apply", "请导入参数数据");
}

function fillData() {
    if (transactionId === "") {
        showMsg("请先选择参数表！");
        return;
    }
    afterSelectPara("apply", "请录入交易参数");
}

function showMsgDuringTime(msg)
{
    layer.msg(msg);
    setTimeout(function(){
        layer.closeAll('dialog');
    }, 1000);
    if(msg==="提交成功")
    {
        if (indexForEdit !== null) {
            layer.close(indexForEdit);
        }
    }
    if(msg==="导入成功")
    {
        if (indexForImport !== null) {
            layer.close(indexForImport);
        }
    }

}

/*从参数表设置中读取数据来控制录入复核是否为同一人，从而控制用户流程权限*/
function afterSelectPara(apply,msg){
    var importData = false;
	if(transactionId === ""){
        showMsg("请先选择参数表！");
	}else{
        if(msg!=="") {
            showMsgDuringTime(msg);
        }
        if(msg==="请导入参数数据")
        {
            importData = true;
        }else if(apply !== "nullify"){
            $.ajax({
                url: contextPath + "/paraFlowService/getFullInfoForTableOrg",
                type: "POST",
                dataType: "json",
                async: false,
                data: {
                    transactionId: transactionId,
                    systemId: systemId
                },
                success: function (data) {
                    //控制录入复核是否为同一人
                    if (data.enterOrCheck === "Y") {
                        enterOrCheckFlag = "Y";
                        $("#editData").html("录入复核");
                        $("#editDataNext").html("录入复核");
                    } else {
                        enterOrCheckFlag = "N";
                        $("#editDataNext").html("录入数据");
                        $("#editData").html("录入数据");
                    }
                    transactionDesc = data.transactionDesc;
                    $("#tableNameDisplay").show().val(data.transactionDesc);
                    deleteAllowed = "N";
                    if (data.deleteAllowed === "Y") {
                        deleteAllowed = "Y";
                    }
                }
            });
        }
		getApplicationNo(apply,importData);
	}
}

function getApplicationNo(apply,importData) {
    var index1;
    $.ajax({
        url:contextPath+"/paraFlowService/getApplicationNo",
        type:"post",
        dataType:"json",
        data:{
            transactionId : systemId + "_" + transactionId,
            apply: apply,
            transactionDesc: $("#tableNameDisplay").val()
        },
        beforeSend:function(){
            index1 = layer.load(4, {
                shade: [0.4,'#777777'] //0.5透明度的白色背景
            });
        },
        success:function(json){
            if(importData)
            {
                apply = "import";
            }
            getApplicationNo_callBack(json,apply);
        },
        complete:function(){
            layer.close(index1);
        }
    });
}

function getApplicationNo_callBack(json,apply) {
    if (json.retStatus === 'F') {
        showMsg(json.retMsg, 'info');
    } else if (json.retStatus === 'S') {
        sequenceNumber = json.appNo;
        statusFlag = json.tableStatus;
        $("#appSeriesNumber").show().val(json.appNo);
        $("#tableStatus").show().val( json.tableStatus);
        if (apply === "refresh" || apply === "apply") {
            getJspUrlByTableName();
            needButton(statusFlag, deleteAllowed, json.appNo);
            if (statusFlag === "已复核") {
                needButton("N", deleteAllowed, json.appNo);
            }
            if (indexForEdit !== null) {
                layer.close(indexForEdit);
            }
            indexForEdit = layer.open({
                type: 2,
                title: "交易：" + transactionDesc + "   流水编号：" + json.appNo + " 流水单状态：" + json.tableStatus ,
                content: contextPath + "/" + jspUrl
            });
            layer.full(indexForEdit);

            $("#nullifyData").removeClass("disabled");
            $("#nullifyDataNext").removeClass("disabled");

            $("#nullifyData").show();
            $("#nullifyDataNext").show();
        }
        else if (apply === "view")
        {
            if(json.tableStatus === "已申请" || json.tableStatus === "已驳回")
            {
                $("#editData").removeClass("disabled");
                $("#editDataNext").removeClass("disabled");
                $("#nullifyData").removeClass("disabled");
                $("#nullifyDataNext").removeClass("disabled");

                $("#editData").show();
                $("#editDataNext").show();
                $("#nullifyData").show();
                $("#nullifyDataNext").show();
            }
            else {
                $("#editDataNext").addClass("disabled");
                $("#editData").addClass("disabled");
                $("#importData").addClass("disabled");
                $("#importDataNext").addClass("disabled");
                $("#nullifyDataNext").addClass("disabled");
                $("#nullifyData").addClass("disabled");

                $("#editData").hide();
                $("#editDataNext").hide();
                $("#importData").hide();
                $("#importDataNext").hide();
                $("#nullifyData").hide();
                $("#nullifyDataNext").hide();
            }
            if(json.tableStatus === "未申请" )
            {
                $("#editData").removeClass("disabled");
                $("#editDataNext").removeClass("disabled");
                $("#importData").removeClass("disabled");
                $("#importDataNext").removeClass("disabled");

                $("#editData").show();
                $("#editDataNext").show();
                $("#importData").show();
                $("#importDataNext").show();
            }
        }
        else if(apply === "import")
        {
            indexForImport = layer.open({
                type: 2,
                title: "交易：" + transactionDesc + '   流水编号：' + sequenceNumber + '   当前状态：' + statusFlag,
                content: contextPath + "/app/pm/jsp/paraIn/paraImport.jsp"

            });
            layer.full(indexForImport);

            $("#nullifyData").removeClass("disabled");
            $("#nullifyDataNext").removeClass("disabled");

            $("#nullifyData").show();
            $("#nullifyDataNext").show();
        }
    }
}

function needButton(isCheck,isDelete,reqNum){
	$(".breadcrumb").data("needButton",isCheck);
    $(".breadcrumb").data("deleteButton",isDelete);
    $(".breadcrumb").data("reqNum",reqNum);
}

