$(document).ready(function () {
    var opt = getDataTableOpt($("#deployViewList"));
    opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;

    opt.ajax = {
        "url": contextPath + "/findEcmAppDeployView",
        "type": "POST"
    };
    opt.columns = [
        {
            "data": "appDeployDate",
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
            "data": "userName",
            "defaultContent": ""
        }
    ];
    //渲染tables
    drawDataTable($("#deployViewList"), opt);
    //界面美化tables
    $("#deployViewList").beautyUi({
        tableId: "deployViewList",
        needBtn: false
    });
    $('#deployViewList tbody').on('click', 'tr', function (e) {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $('#deployViewList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#deployViewList').on('page.dt', function (e) {
        $('#deployViewList').find("tr").removeClass('selected');
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
    $(".select2").select2();
    $("#select").click(function(){
            var startDate = $("#startTime").val();
            var endDate  = $("#endTime").val();
            var userId = $("#queryUserId").val();
            var appId = $("#queryAppId").val();
            var userTab = $("#deployViewList").dataTable();
            var api = userTab.api();
            if(startDate!=""&&endDate!=""){
               api.ajax.url(contextPath+"/findEcmAppDeployView?startTime="+startDate+"&endTime="+endDate+"&userId="+userId+"&appId="+appId).load();
            }else{
               if(startDate!=""){
                  alert('请输入结束日期！');
               }else{
                  if(endDate!=""){
                    alert('请输入开始日期！');
                  }else{
                     api.ajax.url(contextPath+"/findEcmAppDeployView?userId="+userId+"&appId="+appId).load();
                  }
               }
            }
    });
});
  