var statusFlag;
var rowData;
$(document).ready(function () {
    statusFlag = parent.$(".breadcrumb").data("statusFlag");
    $(".breadcrumb").data("needButton", "N");
    $("#backOrCheck").val("C");
    $("#backOrPublish").val("P");
    $("#backOrCheck").select2();
    $("#backOrPublish").select2();
    //信息区展示
    if (statusFlag == "3") {
        $("#checkAreaShow").show();
        rowData = parent.$('#checkInfoList').DataTable().rows(".selected").data()[0];
        $(".breadcrumb").data("reqNum",rowData.reqNo);
        $("#checkTableName").val(rowData.transactionDesc).prop("disabled", true);
        $("#checkComplete").on('click', function () {
            check();
        });
        $("#checkBack").on('click', function () {
            backStep();
        });
        //差异数据查看
        $('#checkDiffShow').on('click', function () {
            differentShowButton();
        });
    } else if (statusFlag == "4") {
        getPkList({
            url: contextPath + "/pklist/getEnvId",
            id: "envId",
            async: false
        });

        $("#envId").change(function () {
            if ($("#envId").val() == null || $("#envId").val() == "") {
                $("#envUrl").val("");
                $("#module").val("");
                $("#serviceCode").val("");
                $("#messageType").val("");
                $("#messageCode").val("");
                $("#legalentity").val("");
                return;
            }
            $.ajax({
                url: contextPath + "/paraEnv/getEnvUrlByEnvId",
                data: {
                    envId: $('#envId').val()
                },
                dataType: "json",
                type: "POST",
                async: false,
                success: function (json) {
                    if (json.envUrl) {
                        $("#envUrl").val(json.envUrl);
                        $("#module").val(json.module);
                        $("#serviceCode").val(json.serviceCode);
                        $("#messageType").val(json.messageType);
                        $("#messageCode").val(json.messageCode);
                        $("#legalentity").val(json.legalentity);
                    } else  {
                        show(json.retMsg);
                    }
                }
            });
        });

        $('#envId').select2();
        $("#publishAreaShow").show();
        rowData = parent.$('#publishInfoList').DataTable().rows(".selected").data()[0];
        if(rowData.transactionId==='CMC_CMC_CARD_NO_SPECIAL'){
            $("#promptWord").css('display','block');
        }
        $(".breadcrumb").data("reqNum",rowData.reqNo);
        $("#publishTableName").val(rowData.transactionDesc).prop("disabled", true);
        $("#publishComplete").on('click', function () {
            publish();
        });
        $("#publishBack").on('click', function () {
            backStep();
        });
        $('#publishDiffShow').on('click', function () {
            differentShowButton();
        });
    }
    //历史信息展示
    tableShowHistory();
    $("#backOrCheck").change(function () {
        var checkType = $("#backOrCheck").val();
        if (checkType == "C") {
            $("#checkBack").hide();
            $("#checkComplete").show();
        } else if (checkType == "B") {
            $("#checkBack").show();
            $("#checkComplete").hide();
        } else if (checkType == "") {
            return;
        }
    });
    $("#backOrPublish").change(function () {
        var doType = $("#backOrPublish").val();
        if (doType == "P") {
            $("#publishBack").hide();
            $("#publishComplete").show();
        } else if (doType == "B") {
            $("#publishBack").show();
            $("#publishComplete").hide();
        } else if (doType == "") {
            return;
        }
    });
});

function check() {
    if (statusFlag == "3") {
        var $ct = $("#inspectText");
        if ($ct.val() == "") {
            showMsg($ct.attr("nullmsg"));
            console.log($ct.attr("nullmsg"));
            return;
        }
    } else if (statusFlag == "4") {
        var $pt = $("#publishText"), $ei = $('#envId');
        if ($pt.val() == "") {
            showMsg($pt.attr("nullmsg"));
            return;
        } else if ($ei.val() == "") {
            showMsg($ei.attr("nullmsg"));
            return;
        }
    }
    $.ajax({
        url: contextPath + "/baseCommon/checkSuccess",
        data: {
            reqNo: rowData.reqNo,
            operatorType: "C",
            checkText: "交易名：" + $("#checkTableName").val() + "，复核意见:" + $("#inspectText").val(),
            approve: "Y"
        },
        dataType: "json",
        type: "POST",
        async: false,
        success: function (json) {
            if (json.success) {
                if (json.success == "success") {
                    parent.showMsgDuringTime("复核操作成功！");
                }
                else {
                    parent.showMsgDuringTime(json.success);
                }
            } else if (json.errorMsg) {
                showMsg(json.errorMsg);
            }
        }
    });
}

function publish() {
    if (statusFlag == "3") {
        var $ct = $("#inspectText");
        if ($ct.val() == "") {
            showMsg($ct.attr("nullmsg"));
            console.log($ct.attr("nullmsg"));
            return;
        }
    } else if (statusFlag == "4") {
        var $pt = $("#publishText"), $ei = $('#envId');
        if ($pt.val() == "") {
            showMsg($pt.attr("nullmsg"));
            return;
        } else if ($ei.val() == "") {
            showMsg($ei.attr("nullmsg"));
            return;
        }
    }

    $.ajax({
        url: contextPath + "/baseCommon/publishSuccess",
        data: {
            transactionId: rowData.transactionId,
            reqNo: rowData.reqNo,
            operatorType: "P",
            checkText: "交易名：" + $("#publishTableName").val() + "，发布意见:" + $("#publishText").val(),
            approve: "Y",
            envUrl: $("#envUrl").val(),
            module: $("#module").val(),
            serviceCode: $("#serviceCode").val(),
            messageType: $("#messageType").val(),
            messageCode: $("#messageCode").val(),
            legalentity: $("#legalentity").val()
        },
        dataType: "json",
        type: "POST",
        async: false,
        success: function (json) {
            if (json.success) {
                if (json.success == "success") {
                    parent.showMsgDuringTime("发布操作成功！");
                }
                else {
                    parent.showMsgDuringTime(json.success);
                }
            } else if (json.errorMsg) {
                showMsg(json.errorMsg);
            }
        }
    });
}

function backStep() {
    if (statusFlag == "3") {
        var $ct = $("#inspectText");
        if ($ct.val() == "") {
            showMsg($ct.attr("nullmsg"));
            console.log($ct.attr("nullmsg"));
            return;
        }
    } else if (statusFlag == "4") {
        var $pt = $("#publishText"), $ei = $('#envId');
        if ($pt.val() == "") {
            showMsg($pt.attr("nullmsg"));
            return;
        }
    }
    var operaType, text;
    if (statusFlag == "3") {
        operaType = "R";
        text = $("#checkTableName").val() + ":" + $("#inspectText").val();
    } else if (statusFlag == "4") {
        operaType = "J";
        text = $("#publishTableName").val() + ":" + $("#publishText").val();
    }
    $.ajax({
        url: contextPath + "/baseCommon/rejectFromCheckOrPublish",
        data: {
            reqNo: rowData.reqNo,
            operatorType: operaType,
            checkText: text,
            approve: "N"
        },
        dataType: "json",
        type: "POST",
        async: false,
        success: function (json) {
            if (json.success) {
                if (json.success == "success") {
                    parent.showMsgDuringTime("驳回操作成功！");
                }
                else {
                    parent.showMsgDuringTime(json.success);
                }
            } else if (json.errorMsg) {
                showMsg(json.errorMsg);
            }

        }
    });
}

//差异数据查看
function differentShowButton() {
    var transactionId = rowData.transactionId, jspUrl;
    $.ajax({
        url: contextPath + "/paraFlowService/getFullInfoForTableOrg",
        type: "POST",
        dataType: "json",
        async: false,
        data: {
            transactionId: transactionId
        },
        success: function (json) {
            jspUrl = json.jspUrl;
            if(json.multiDB=="Y")
            {
                jspUrl = json.jspViewUrl;
            }
        }
    });
    var index = layer.open({
        type: 2,
        title: '查看差异数据',
        content: contextPath + "/" + jspUrl
    });
    layer.full(index);
}

function tableShowHistory() {
    var opt = getDataTableOpt($("#tableList"));
    opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;
    opt.deferRender = true;
    opt.ajax = {
        "url": contextPath + "/paraAcp/findHistory",
        "data": {reqNo: rowData.reqNo},
        "type": "POST"
    };
    opt.columns = [
        {
            "data": "operatorType",
            "defaultContent": ""
        },
        {
            "data": "checkText",
            "defaultContent": ""
        },
        {
            "data": "operatorId",
            "defaultContent": ""
        },
        {
            "data": "currentsystemTime",
            "defaultContent": ""
        },
        {
            "data": "clientIp",
            "defaultContent": ""
        }

    ];
    opt.columnDefs=[
        {
            "render": function (data, type, row) {
                if (row.operatorType == "A")
                    return '完成申请';
                else if (row.operatorType == "C")
                    return '完成复核';
                else if (row.operatorType == "P")
                    return '完成发布';
                else if (row.operatorType == "R")
                    return '完成复核驳回';
                else if (row.operatorType == "J")
                    return '完成发布驳回';
                else if (row.operatorType == "E")
                    return '完成录入提交';
                else if (row.operatorType == "F")
                    return '完成作废';
                else if (row.operatorType == "S")
                    return '同主交易状态';
                else
                    return '不适用';
            },
            "targets": 0
        }
    ];
    //渲染tables
    drawDataTable($("#tableList"), opt);
    //界面美化tables
    $("#tableList").beautyUi({
        tableId: "tableList",
        needBtn: false
    });
}

function disBtn() {
    if (statusFlag == "3") {
        $("#backOrCheck").val("").select2().prop("disabled", true);
        $("#checkBack").hide();
        $("#checkComplete").hide();
    } else if (statusFlag == "4") {
        $("#backOrPublish").val("").select2().prop("disabled", true);
        $("#publishBack").hide();
        $("#publishComplete").hide();
    }
}