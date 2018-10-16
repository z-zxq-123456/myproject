var sub_url;
var isFinish = false;
var row;
//部署设置
$(document).ready(function () {
        // 获取默认opt配置
        var opt = getDataTableOpt($("#redisList"));
        opt.stateSave = true;
        opt.processing = true;
        opt.autoWidth = false;
        opt.columns = [
            {
                "data": "redisintNodeNum",
                "defaultContent": ""
            },
            {
                "data": "redisintName",
                "defaultContent": ""
            },
            {
                "data": "redisintTypeName",
                "defaultContent": ""
            },
            {
                "data": "hostAndPort",
                "defaultContent": ""
            },
            {
                "data": "midwareVerNo",
                "defaultContent": ""
            },
            {
                "data": "redisintStatusName",
                "defaultContent": ""
            },
            {
                "data": "currentRedisintStatus",
                "defaultContent": ""
            },
            {
                "data": "healthMessage",
                "defaultContent": ""
            },
            {
                "data": "redisintMemoryUnit",
                "defaultContent": ""
            },
            {
                "data": "midwarePath",
                "defaultContent": ""
            }
        ];
        //渲染tables
        drawDataTable($("#redisList"), opt);
        //界面美化tables
        $("#redisList").beautyUi({
            tableId: "redisList",
            buttonName: ["部署", "启动", "停止", "重启", "卸载", "刷新"],
            buttonId: ["deploy", "start", "stop", "restart", "clear", "refresh"]
        });
         $("#deploy").on("click", function () {
                    deployRedis();
                });
         $("#start").on("click", function () {
                    startRedis();
                });
         $("#stop").on("click", function () {
                    stopRedis();
                });
         $("#restart").on("click", function () {
                    restartRedis();
                });
         $("#clear").on("click", function () {
                    clearRedis();
                });
         $("#refresh").on("click", function () {
                    refreshRedis();
                });

//        $('#redisList tbody').on('click', 'tr', function (e) {
//            if ($(this).hasClass('selected')) {
//                $(this).removeClass('selected');
//            } else {
//                $('#redisList').find("tr").removeClass('selected');
//                $(this).addClass('selected');
//            }
//        });
        $('#redisList').on('page.dt', function (e) {
            $('#redisList').find("tr").removeClass('selected');
        });
        getPkList({
                url: contextPath + "/findMidwareDefaultCombox",
                id: "queryMidwareId",
                async: false,
                params: {midwareType: '0006001'},
                normalSelect:false
            });
        $(".select2").select2();
        refreshRedis();
        $("#queryMidwareId").change(function(){
             var redisInstant = $("#redisList").dataTable();
             var redisApi = redisInstant.api();
             redisApi.ajax.url(contextPath + "/findRedisint?midwareId="+$('#queryMidwareId').val()).load();
        });
        /*页面按钮根据权限实现隐藏*/
        pagePerm();
    });

function deployRedis() {
     sub_url = contextPath +"/saveRedis?midwareId="+$('#queryMidwareId').val();
    var allRedis = $("#redisList").DataTable().data();
    if (allRedis.length != 0) {
        for (var i = 0; i < allRedis.length; i++) {
           if(allRedis[i].redisintStatus!="0020003"){
              showMsg('redis实例'+allRedis[i].redisintName+'已经部署!','warning');
              return;
           }
        }
          openProgressBar();
    }else {
         showMsg( '没有可操作实例!','warning');
    }
}
//启动redis集群
function startRedis() {
     sub_url = contextPath +"/startRedisints?midwareId="+$('#queryMidwareId').val();
     var allRedis = $("#redisList").DataTable().data();
     if (allRedis.length != 0) {
         for (var i = 0; i < allRedis.length; i++) {
                        if (allRedis[i].redisintStatusName == "尚未部署") {
                            showMsg('redis实例' + allRedis[i].redisintStatusName + '尚未部署,请先部署所有应用实例!', 'warning');
                            return;
                        }
         }
     openProgressBar();
     }else {
         showMsg( '没有可操作实例!','warning');
     }
}

function stopRedis() {
      sub_url = contextPath + "/stopRedisints?midwareId="+$('#queryMidwareId').val();
      var allRedis = $("#redisList").DataTable().data();
      if (allRedis.length != 0) {
               for (var i = 0; i < allRedis.length; i++) {
                              if (allRedis[i].redisintStatusName == "尚未部署") {
                                  showMsg('redis实例' + allRedis[i].redisintStatusName + '尚未部署,请先部署所有应用实例!', 'warning');
                                  return;
                              }
               }
      openProgressBar();
      }else {
               showMsg( '没有可操作实例!','warning');
      }
}

function restartRedis() {
       sub_url = contextPath + "/restartRedisints?midwareId="+$('#queryMidwareId').val();
       var allRedis = $("#redisList").DataTable().data();
       if (allRedis.length != 0) {
                      for (var i = 0; i < allRedis.length; i++) {
                                     if (allRedis[i].redisintStatusName == "尚未部署") {
                                         showMsg('redis实例' + allRedis[i].redisintStatusName + '尚未部署,请先部署所有应用实例!', 'warning');
                                         return;
                                     }
                      }
             openProgressBar();
       }else {
              showMsg( '没有可操作实例!','warning');
       }
}

function clearRedis() {
        sub_url = contextPath + "/clearRedisints?midwareId="+$('#queryMidwareId').val();
        var allRedis = $("#redisList").DataTable().data();
        if (allRedis.length != 0) {
             for(var i;i<allRedis.length;i++) {
//              if (allRedis[i].currentRedisintStatus == "尚未部署") {
//                                  showMsg('redis实例' + allRedis[i].redisintStatusName + '尚未部署,请先部署所有应用实例!', 'warning');
//                                  return;
//              }
//              if (allRedis[i].redisintStatusName == "启动") {
//                      showMsg('请先停止所有实例', 'warning');
//                      return;
//              }
                if(allRedis[i].currentRedisintStatus !="停止")
                {
                   showMsg('redis实例清理失败', 'warning');
                                                    return;
                }
        }
        openProgressBar();
        }else {
                     showMsg( '没有可操作实例!','warning');
        }
}

function refreshRedis () {
       var redisApi = $("#redisList").dataTable().api();
       redisApi.ajax.url(contextPath + "/findRedisint?midwareId="+$('#queryMidwareId').val()).load();
}

//打开进度条
function openProgressBar() {
    layer.open({
                                 type: 2,
                                 content: "middlewareProgress.jsp",
                                 area: ['400px', '200px'],
                                 title: '操作进度'
    });
}
