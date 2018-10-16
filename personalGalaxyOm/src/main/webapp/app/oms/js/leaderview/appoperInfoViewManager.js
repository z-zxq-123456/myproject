$(document).ready(function () {
    var opt = getDataTableOpt($("#operInfoList"));
    opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;

    opt.ajax = {
        "url": contextPath + "/findEcmAppoperInfo",
        "type": "POST"
    };
    opt.columns = [
        {
            "data": "ecmAppoperTime",
            "defaultContent": ""
        },
        {
            "data": "appName",
            "defaultContent": ""
        },
        {
            "data": "appIntantName",
            "defaultContent": ""
        },
        {
            "data": "serIp",
            "defaultContent": ""
        },
        {
            "data": "appIntantVer",
            "defaultContent": ""
        },
        {
            "data": "appPath",
            "defaultContent": ""
        },
        {
            "data": "ecmAppoperTypeName",
            "defaultContent": ""
        },
        {
            "data": "userName",
            "defaultContent": ""
        }
    ];
    //渲染tables
    drawDataTable($("#operInfoList"), opt);
    //界面美化tables
    $("#operInfoList").beautyUi({
        tableId: "operInfoList",
        needBtn: false
    });
    $('#operInfoList tbody').on('click', 'tr', function (e) {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $('#operInfoList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#operInfoList').on('page.dt', function (e) {
        $('#operInfoList').find("tr").removeClass('selected');
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
            var appId = $("#queryAppId").val();
            var ecmAppoperType = $("#queryParaCode").val();
            var userTab = $("#operInfoList").dataTable();
            var api = userTab.api();
            if (startDate != "" && endDate != "") {
                api.ajax.url(contextPath + "/findEcmAppoperInfo?startTime=" + startDate + "&endTime=" + endDate + "&userId=" + userId + "&appId=" + appId + "&ecmAppoperType=" + ecmAppoperType).load();
            } else {
                if (startDate != "") {
                    alert('请输入结束日期！');
                } else {
                    if (endDate != "") {
                        alert('请输入开始日期！');
                    } else {
                        api.ajax.url(contextPath + "/findEcmAppoperInfo?userId=" + userId + "&appId=" + appId + "&ecmAppoperType=" + ecmAppoperType).load();
                    }
                }
            }
        });
});


