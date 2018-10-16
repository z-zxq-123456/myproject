$(document).ready(function () {
    var opt = getDataTableOpt($("#midwareOperInfoList"));
    opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;

    opt.ajax = {
        "url": contextPath + "/findMidwareOperInfo",
        "type": "POST"
    };
    opt.columns = [
         {
            "data": "midwareOperTime",
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
            "data": "midwareOperTypeName",
            "defaultContent": ""
        },
        {
            "data": "userName",
            "defaultContent": ""
        }
    ];
    //渲染tables
    drawDataTable($("#midwareOperInfoList"), opt);
    //界面美化tables
    $("#midwareOperInfoList").beautyUi({
        tableId: "midwareOperInfoList",
        needBtn: false
    });
    $('#midwareOperInfoList tbody').on('click', 'tr', function (e) {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $('#midwareOperInfoList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#midwareOperInfoList').on('page.dt', function (e) {
        $('#midwareOperInfoList').find("tr").removeClass('selected');
    });
    getPkList({
        url: contextPath + "/pklist/getUserNameToUpdate",
        id: "queryUserId",
        async: false
    });
    getPkList({
        url: contextPath + "/findAppCombox",
        id: "queryAppId",
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
    getPkList({
            url: contextPath + "/findParaCombox",
            id: "queryParaCode",
            async: false,
            params: {
                paraParentId: '0020',
                isDefault: false
            }
        });
    $(".select2").select2();
        $("#select").click(function () {
            var startDate = $("#startTime").val();
            var endDate = $("#endTime").val();
            var userId = $("#queryUserId").val();
            var midwareType = $("#queryMiderParaCode").val();
            var midwareOperType = $("#queryParaCode").val();
            var userTab = $("#midwareOperInfoList").dataTable();
            var api = userTab.api();
            if (startDate != "" && endDate != "") {
                api.ajax.url(contextPath + "/findMidwareOperInfo?startTime=" + startDate + "&endTime=" + endDate + "&userId=" + userId + "&midwareType=" + midwareType + "&midwareOperType=" + midwareOperType).load();
            } else {
                if (startDate != "") {
                    alert('请输入结束日期！');
                } else {
                    if (endDate != "") {
                        alert('请输入开始日期！');
                    } else {
                        api.ajax.url(contextPath + "/findMidwareOperInfo?userId=" + userId + "&midwareType=" + midwareType + "&midwareOperType=" + midwareOperType).load();
                    }
                }
            }
        });
});


