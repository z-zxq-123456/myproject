$(document).ready(function () {
    var opt = getDataTableOpt($("#midwareDeployViewList"));
    opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;

    opt.ajax = {
        "url": contextPath + "/findMidwareDeployView",
        "type": "POST"
    };
    opt.columns = [
        {
            "data": "midwareDeployDate",
            "defaultContent": ""
        },
        {
            "data": "midwareName",
            "defaultContent": ""
        },
        {
            "data": "midwareTypeName",
            "defaultContent": ""
        },
        {
            "data": "instantName",
            "defaultContent": ""
        },
        {
            "data": "instantTypeName",
            "defaultContent": ""
        },
        {
            "data": "serIp",
            "defaultContent": ""
        },
        {
            "data": "midwareVerNo",
            "defaultContent": ""
        },
         {
            "data": "midwarePath",
            "defaultContent": ""
        },
        {
            "data": "userName",
            "defaultContent": ""
        }
    ];
    //渲染tables
    drawDataTable($("#midwareDeployViewList"), opt);
    //界面美化tables
    $("#midwareDeployViewList").beautyUi({
        tableId: "midwareDeployViewList",
        needBtn: false
    });
    $('#midwareDeployViewList tbody').on('click', 'tr', function (e) {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $('#midwareDeployViewList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#midwareDeployViewList').on('page.dt', function (e) {
        $('#midwareDeployViewList').find("tr").removeClass('selected');
    });
    getPkList({
        url: contextPath + "/pklist/getUserNameToUpdate",
        id: "queryUserId",
        async: false
    });
    getPkList({
        url: contextPath + "/findParaCombox",
        id: "queryMiderParaCode",
        async: false,
        params: {
            paraParentId: '0006',
            isDefault: false
        }
    });
    $(".select2").select2();
        $("#select").click(function () {
            var startDate = $("#startTime").val();
            var endDate = $("#endTime").val();
            var userId = $("#queryUserId").val();
            var midwareType = $("#queryMiderParaCode").val();
            var userTab = $("#midwareDeployViewList").dataTable();
            var api = userTab.api();
            if (startDate != "" && endDate != "") {
                api.ajax.url(contextPath + "/findMidwareDeployView?startTime=" + startDate + "&endTime=" + endDate + "&userId=" + userId +"&midwareType"+midwareType).load();
            } else {
                if (startDate != "") {
                    alert('请输入结束日期！');
                } else {
                    if (endDate != "") {
                        alert('请输入开始日期！');
                    } else {
                        api.ajax.url(contextPath + "/findMidwareDeployView?userId=" + userId + "&midwareType=" + midwareType).load();
                    }
                }
            }
        });
});


