$(document).ready(function () {
    var opt = getDataTableOpt($("#upgViewList"));
    opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;

    opt.ajax = {
        "url": contextPath + "/findEcmAppUpgView",
        "type": "POST"
    };
    opt.columns = [
        {
            "data": "appUpgOpertime",
            "defaultContent": ""
        },
        {
            "data": "appName",
            "defaultContent": ""
        },
        {
            "data": "appUpgStatusName",
            "defaultContent": ""
        },
        {
            "data": "appUpgOldverno",
            "defaultContent": ""
        },
        {
            "data": "appUpgNewverno",
            "defaultContent": ""
        }
    ];
    //渲染tables
    drawDataTable($("#upgViewList"), opt);
    //界面美化tables
    $("#upgViewList").beautyUi({
        tableId: "upgViewList",
        needBtn: false
    });
    $('#upgViewList tbody').on('click', 'tr', function (e) {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $('#upgViewList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#upgViewList').on('page.dt', function (e) {
        $('#upgViewList').find("tr").removeClass('selected');
    });
    getPkList({
        url: contextPath + "/findAppCombox",
        id: "queryAppId",
        async: false,
    });
    $(".select2").select2();
    $("#select").click(function(){
            var startDate = $("#startTime").val();
            var endDate  = $("#endTime").val();
            var appId = $("#queryAppId").val();
            var userTab = $("#upgViewList").dataTable();
            var api = userTab.api();
            if(startDate!=""&&endDate!=""){
               api.ajax.url(contextPath+"/findEcmAppUpgView?startTime="+startDate+"&endTime="+endDate+"&appId="+appId).load();
            }else{
               if(startDate!=""){
                  alert('请输入结束日期！');
               }else{
                  if(endDate!=""){
                    alert('请输入开始日期！');
                  }else{
                     api.ajax.url(contextPath+"/findEcmAppUpgView?appId="+appId).load();
                  }
               }
            }
    });
    $("#selectVaild").click(function(){
          if ($("#upgViewList").find(".selected").length!=1){
                     showMsg('请选择一行记录！','warning');
                     return;
          }
           layer.open({
                  type: 2,
                  title:"升级验证规则信息",
                  content: "updateVaildInfo.jsp",
                  area: [650+'px', 400+'px']
                  });

    });
    $("#selectTransfer").click(function(){
          if ($("#upgViewList").find(".selected").length!=1){
                     showMsg('请选择一行记录！','warning');
                     return;
          }
           layer.open({
                  type: 2,
                  title:"升级流转信息",
                  content: "updateTransferInfo.jsp",
                  area: [650+'px', 400+'px']
                  });

    });
});


