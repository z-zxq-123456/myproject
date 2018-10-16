var sub_url;
var isFinish = false;
var row;
//部署设置
$(document).ready(function () {
        // 获取默认opt配置
        var opt = getDataTableOpt($("#kafkaList"));
        opt.stateSave = true;
        opt.processing = true;
        opt.autoWidth = false;
        opt.columns = [
            {
                "data": "kafkaintId",
                "defaultContent": ""
            },
            {
                "data": "kafkaintName",
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
                "data": "kafkaintStatusName",
                "defaultContent": ""
            },
            {
                "data": "currentKafkaintStatus",
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
        drawDataTable($("#kafkaList"), opt);
        //界面美化tables
        $("#kafkaList").beautyUi({
            tableId: "kafkaList",
            buttonName: ["部署", "启动", "停止",  "卸载", "刷新"],
            buttonId: ["deploy", "start", "stop",  "clear", "refresh"]
        });
         $("#deploy").on("click", function () {
                    deployKafka();
                });
         $("#start").on("click", function () {
                    startKafka();
                });
         $("#stop").on("click", function () {
                    stopKafka();
                });

         $("#clear").on("click", function () {
                    clearKafka();
                });
         $("#refresh").on("click", function () {
                    refreshRedis();
        });

//        $('#kafkaList tbody').on('click', 'tr', function (e) {
//            if ($(this).hasClass('selected')) {
//                $(this).removeClass('selected');
//            } else {
//                $('#kafkaList').find("tr").removeClass('selected');
//                $(this).addClass('selected');
//            }
//        });
        $('#kafkaList').on('page.dt', function (e) {
            $('#kafkaList').find("tr").removeClass('selected');
        });
        getPkList({
                url: contextPath + "/findMidwareDefaultCombox",
                id: "queryMidwareId",
                async: false,
                params: {midwareType: '0006004'},
                normalSelect:false
            });
        $(".select2").select2();
        refreshRedis();
        $("#queryMidwareId").change(function(){
             var kafkaInstant = $("#kafkaList").dataTable();
             var kafkaApi = kafkaInstant.api();
             kafkaApi.ajax.url(contextPath + "/findKafkaint?midwareId="+$('#queryMidwareId').val()).load();
        });
        /*页面按钮根据权限实现隐藏*/
        pagePerm();
    });

function deployKafka() {
    sub_url = contextPath +"/saveKafkaints?midwareId="+$('#queryMidwareId').val();
    var allKafka = $("#kafkaList").DataTable().data();
    if (allKafka.length != 0) {
        for (var i = 0; i < allKafka.length; i++) {
           if(allKafka[i].kafkaintStatus!="0020003"){
              showMsg('kafka实例'+allKafka[i].kafkaintName+'已经部署!','warning');
              return;
           }
        }
          openProgressBar();
    }else {
         showMsg( '没有可操作实例!','warning');
    }
}
//启动kafka集群
function startKafka() {
     sub_url = contextPath +"/startKafkaints?midwareId="+$('#queryMidwareId').val();
     var allKafka = $("#kafkaList").DataTable().data();
     if (allKafka.length != 0) {
         for (var i = 0; i < allKafka.length; i++) {
                        if (allKafka[i].kafkaintStatusName == "尚未部署") {
                            showMsg('kafka实例' + allKafka[i].kafkaintStatusName + ',请先部署所有kafka实例!', 'warning');
                            return;
                        }
         }
     openProgressBar();
     }else {
         showMsg( '没有可操作实例!','warning');
     }
}

function stopKafka() {
      sub_url = contextPath + "/stopKafkaints?midwareId="+$('#queryMidwareId').val();
      var allKafka = $("#kafkaList").DataTable().data();
      if (allKafka.length != 0) {
               for (var i = 0; i < allKafka.length; i++) {
                              if (allKafka[i].kafkaintStatusName == "尚未部署") {
                                  showMsg('kafka实例' + allKafka[i].kafkaintStatusName + ',请先部署所有kafka实例!', 'warning');
                                  return;
                              }
               }
      openProgressBar();
      }else {
               showMsg( '没有可操作实例!','warning');
      }
}



function clearKafka() {
        sub_url = contextPath + "/clearKafkaints?midwareId="+$('#queryMidwareId').val();
        var allKafka = $("#kafkaList").DataTable().data();
        if (allKafka.length != 0) {
             for(var i = 0;i<allKafka.length;i++) {
//              if (allKafka[i].kafkaintStatusName == "尚未部署") {
//                                  showMsg('kafka实例' + allKafka[i].kafkaintStatusName + '尚未部署,请先部署所有应用实例!', 'warning');
//                                  return;
//              }
//              if (allKafka[i].kafkaintStatusName == "启动") {
//                      showMsg('请先停止所有实例', 'warning');
//                      return;
//              }
                if(allKafka[i].currentKafkaintStatus != "停止") {
                      showMsg('请先停止所有实例', 'warning')
                      return;
                }
        }
        openProgressBar();
        }else {
                     showMsg( '没有可操作实例!','warning');
        }
}

function refreshRedis () {
       var kafkaApi = $("#kafkaList").dataTable().api();
       kafkaApi.ajax.url(contextPath + "/findKafkaint?midwareId="+$('#queryMidwareId').val()).load();
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
