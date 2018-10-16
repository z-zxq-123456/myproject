var sub_url;
var refreshRedis;
var refreshZkPar;
$(document).ready(function () {
    var opt = getDataTableOpt($("#zookeeeperList"));
    opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;
    opt.columns = [
        {
            "data": "serName",
            "defaultContent": ""
        },
        {
            "data": "serIp",
            "defaultContent": ""
        },
        {
            "data": "zkintClientPort",
            "defaultContent": ""
        },
        {
            "data": "zkintName",
            "defaultContent": ""
        },
        {
            "data": "midwareVerNo",
            "defaultContent": ""
        },
        {
            "data": "zkintStatusName",
            "defaultContent": ""
        },
        {
            "data": "currentZkIntantStatus",
            "defaultContent": ""
        },
        {
            "data": "healthMessage",
            "defaultContent": ""
        },
        {
            "data": "midwarePath",
            "defaultContent": ""
        }
    ];
    //渲染tables
    drawDataTable($("#zookeeeperList"), opt);
    //界面美化tables
    $("#zookeeeperList").beautyUi({
       tableId: "zookeeeperList",
       buttonName:["部署", "启动" , "停止","重启","卸载","刷新"],
       buttonId:["deploy",　"start" , "stop","restart","clear","refresh"]
    });
     $("#deploy").click(function () {
            sub_url = contextPath +"/saveZkIntant?midwareId="+$('#queryMidwareId').val();
            var allZks = $("#zookeeeperList").DataTable().data();
            if (allZks.length != 0) {
                for (var i = 0; i < allZks.length; i++) {
                   if(allZks[i].zkintStatus!="0020003"){
                      showMsg('zk实例'+allZks[i].zkintName+'已经部署!','warning');
                      return;
                   }
                }
                  openProgressBar();
            }else {
                 showMsg( '没有可操作实例!','warning');
            }
       });
            $("#start").click(function () {
                 sub_url = contextPath +"/startZkIntant?midwareId="+$('#queryMidwareId').val();
                 var allZks = $("#zookeeeperList").DataTable().data();
                 if (allZks.length != 0) {
                     for (var i = 0; i < allZks.length; i++) {
                            if (allZks[i].zkintStatusName == "尚未部署") {
                                showMsg('zk实例' + allZks[i].zkintName + '尚未部署,请先部署所有应用实例!', 'warning');
                                return;
                            }
                     }
                 openProgressBar();
                 }else {
                     showMsg( '没有可操作实例!','warning');
                 }
            });
            $("#stop").click(function () {
                  sub_url = contextPath + "/stopZkIntant?midwareId="+$('#queryMidwareId').val();
                  var allZks = $("#zookeeeperList").DataTable().data();
                  if (allZks.length != 0) {
                           for (var i = 0; i < allZks.length; i++) {
                                  if (allZks[i].zkintStatusName == "尚未部署") {
                                      showMsg('Zk实例' + allZks[i].zkintName + '尚未部署,请先部署所有应用实例!', 'warning');
                                      return;
                                  }
                           }
                  openProgressBar();
                  }else {
                           showMsg( '没有可操作实例!','warning');
                  }
            });
          $("#restart").click(function () {
           sub_url = contextPath + "/reStartZkIntant?midwareId=" +$("#queryMidwareId").val() ;
           var allZks = $("#zookeeeperList").DataTable().data();
           if (allZks.length != 0) {
              for (var i = 0; i < allZks.length; i++) {
                     if (allZks[i].zkintStatusName == "尚未部署") {
                         showMsg('Zk实例' + allZks[i].zkintName + '尚未部署,请先部署所有应用实例!', 'warning');
                         return;
                     }
              }
                 openProgressBar();
           }else {
                  showMsg( '没有可操作实例!','warning');
           }
            });
          $("#clear").click(function () {
                 sub_url = contextPath + "/cleanZkIntant?midwareId="+$('#queryMidwareId').val();
                 var allZks = $("#zookeeeperList").DataTable().data();
                        if (allZks.length != 0) {
                          for (var i = 0; i < allZks.length; i++) {
                                 if (allZks[i].zkintStatusName == "尚未部署") {
                                         showMsg('zk实例' + allZks[i].zkintName + '尚未部署,请先部署所有应用实例!', 'warning');
                                         return;
                                 }
                                 if (allZks[i].currentZkIntantStatus == "启动") {
                                         showMsg('请先停止所有实例', 'warning');
                                         return;
                                 }
                             }
                        openProgressBar();
                        }else {
                                     showMsg( '没有可操作实例!','warning');
                        }
          });
          $("#refresh").on("click", function () {
           refreshZk();
          });
    $('#zookeeeperList tbody').on('click', 'tr', function (e) {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $('#zookeeeperList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#zookeeeperList').on('page.dt', function (e) {
        $('#zookeeeperList').find("tr").removeClass('selected');
    });
    //获取roleName
        getPkList({
            url:contextPath+"/findMidwareDefaultCombox",
            id:"queryMidwareId",
            async:false,
            normalSelect:false,
            params: {
                     midwareType: '0006002'

                    }
        });
      $(".select2").select2();
      refreshZk();
});
function  refreshZk(){
            var midwareId = $("#queryMidwareId").val();
            var userTab = $("#zookeeeperList").dataTable();
            var api = userTab.api();
            api.ajax.url(contextPath + "/findZkIntant?midwareId=" + midwareId).load();
}
//打开进度条
function openProgressBar() {
    layer.open({
                     type: 2,
                     content: "middlewareProgress.jsp",
                     area: ['400px', '200px'],
                     title: '操作进度',
                     end: function () {
                                     refreshZk();
                                 }
    });
}