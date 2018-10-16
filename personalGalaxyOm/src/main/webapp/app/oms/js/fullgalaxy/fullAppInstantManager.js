    var sub_url;
    var isFinish = false;
    var isRemainConfig;
    $(document).ready(function () {
        var opt_app = getDataTableOpt($("#appInstantList"));
        var opt_redis = getDataTableOpt($("#table_data_redis"));
        var opt_zookeeper = getDataTableOpt($("#table_data_zookeeper"));
        var opt_db = getDataTableOpt($("#table_data_database"));


        opt_app.ajax = {
            "url": contextPath + "/findFullAppIntant",
            "type": "POST"
        };
        opt_redis.ajax = {
                    "url": contextPath + "/findFullRedisGroup",
                    "type": "POST"
                };
        opt_zookeeper.ajax = {
                    "url": contextPath + "/findFullZookeeperGroup",
                    "type": "POST"
                };
        opt_db.ajax = {
                    "url": contextPath + "/findFullDatabaseGroup",
                    "type": "POST"
                };
        opt_app.columns = [
         {
                                                          'bSortable': false,
                                                             "data": "aa",
                                                             "defaultContent": ""
                                                         },
            {
                 "orderable":      false,
                "data": "appName",
                "defaultContent": ""
            },
            {
             "orderable":      false,
                "data": "appIntantName",
                "defaultContent": ""
            },
            {
             "orderable":      false,
                "data": "serIp",
                "defaultContent": ""
            },
            {
             "orderable":      false,
                "data": "appIntantVer",
                "defaultContent": ""
            },
            {
             "orderable":      false,
                "data": "appIntantStatusName",
                "defaultContent": ""
            },
            {
             "orderable":      false,
                "data": "currentAppIntantStatus",
                "defaultContent": ""
            },
            {
             "orderable":      false,
                "data": "healthMessage",
                "defaultContent": ""
            },
            {
             "orderable":      false,
                "data": "appPath",
                "defaultContent": ""
            },
            {
             "orderable":      false,
                "data": "appWork",
                "defaultContent": ""
            }
        ];
        opt_redis.columns = [

                    {
                        "orderable":      false,
                        "data": "midwareName",
                        "defaultContent": ""
                    },
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
                        "data": "redisintMemory",
                        "defaultContent": ""
                    },
                    {
                        "data": "midwareVerNo",
                        "defaultContent": ""
                    },
                    {
                     "orderable":      false,
                        "data": "midwarePath",
                        "defaultContent": ""
                    },
                ];
        opt_zookeeper.columns = [
                            {
                             "orderable":      false,
                                "data": "midwareName",
                                "defaultContent": ""
                            },
                            {
                             "orderable":      false,
                                "data": "serIp",
                                "defaultContent": ""
                            },
                            {
                             "orderable":      false,
                                "data": "zkintName",
                                "defaultContent": ""
                            },
                            {
                             "orderable":      false,
                                "data": "zkintNodeNum",
                                "defaultContent": ""
                            },
                            {
                             "orderable":      false,
                                "data": "zkintStatusName",
                                "defaultContent": ""
                            },
                            {
                             "orderable":      false,
                                "data": "currentZkIntantStatus",
                                "defaultContent": ""
                            },
                            {
                             "orderable":      false,
                                "data": "healthMessage",
                                "defaultContent": ""
                            },
                            {
                             "orderable":      false,
                                "data": "zkintClientPort",
                                "defaultContent": ""
                            },
                            {
                             "orderable":      false,
                                "data": "midwareVerNo",
                                "defaultContent": ""
                            },
                            {
                             "orderable":      false,
                                "data": "midwarePath",
                                "defaultContent": ""
                            }
        ];
        opt_db.columns = [
                            {
                             "orderable":      false,
                                "data": "midwareName",
                                "defaultContent": ""
                            },
                            {
                                "data": "serName",
                                "defaultContent": ""
                            },
                            {
                                "data": "serIp",
                                "defaultContent": ""
                            },
                            {
                                "data": "dbintName",
                                "defaultContent": ""
                            },
                            {
                                "data": "dbTypeName",
                                "defaultContent": ""
                            },
                            {
                                "data": "dbintUserName",
                                "defaultContent": ""
                            },
                            {
                                "data": "dbintPort",
                                "defaultContent": ""
                            },
                            {
                                "data": "dbintServiceName",
                                "defaultContent": ""
                            },
                            {
                                "data": "dbintNodeNum",
                                "defaultContent": ""
                            },
                            {
                                "data": "dbintCurrentStatusName",
                                "defaultContent": ""
                            },
                            {
                                "data": "healthMessage",
                                "defaultContent": ""
                            }
        ];
       
     //   opt.bFilter = false;
//        opt_app.bInfo = true;
//        opt_redis.bInfo = true;
//        opt_zookeeper.bInfo = true;
//        opt_db.bInfo = true;
        opt_app.bFilter = false;
        opt_redis.bFilter = false;
        opt_zookeeper.bFilter = false;
        opt_db.bFilter = false;
        setDataTableOpt($("#appInstantList"), opt_app);
        setDataTableOpt($("#table_data_redis"), opt_redis);
        setDataTableOpt($("#table_data_zookeeper"), opt_zookeeper);
        setDataTableOpt($("#table_data_database"), opt_db);
        //界面美化tables
        $("#appInstantList").beautyUi({
            tableId: "appInstantList",
            buttonName: ["全量部署", "全量启动", "全量停止", "全量重启", "全量清理", "刷新"],
            buttonId: ["deploy", "start", "stop", "reStart", "clear", "refresh"]
        });
        $("#table_data_redis").beautyUi({
        tableId: "table_data_redis",
        needBtn: false,
        });
        $("#table_data_zookeeper").beautyUi({
        tableId: "table_data_zookeeper",
        needBtn: false,
        });
        $("#table_data_database").beautyUi({
        tableId: "table_data_zookeeper",
        needBtn: false,
        });

        $("#deploy").on("click", function () {
            deploy();
        });
        $("#start").on("click", function () {
            start();
        });
        $("#stop").on("click", function () {
            stop();
        });
        $("#reStart").on("click", function () {
            reStart();
        });
        $("#clear").on("click", function () {
            clear();
        });
        $("#refresh").on("click", function () {
            refresh();
        });


    });
    function checkIntant(){
       //应用效验
          	  var appInstant = $("#appInstantList").DataTable();
              var rows_app = appInstant.data();
          	  for(var i=0; i<rows_app.length; i++){
      		     if(rows_app[i].appIntantStatusName=="尚未部署"){
        	    	  showMsg('应用实例'+rows_app[i].appIntantName+'尚未部署,请先部署所有应用实例!','warning');
       		         return false;
        	     }
      	      }
          	  //redis效验
      	      var redis =  $("#table_data_redis").DataTable();
              var rows_redis = redis.data();
       	      for(var i=0; i<rows_redis.length; i++){
       	    	if(rows_redis[i].redisintStatusName=="尚未部署"){
      	    	 showMsg('Redis实例'+rows_redis[i].redisintName+'尚未部署,请先部署所有Redis实例!','warning');
      		         return false;
      	        }
       	      }

       	      //zookeeper效验
       	      var zookeeper =  $("#table_data_zookeeper").DataTable();
              var rows_zookeeper = zookeeper.data();
      	      for(var a=0; a<rows_zookeeper.length; a++){
      	    	if(rows_zookeeper[a].zkintStatusName=="尚未部署"){
      	    	 showMsg('提示','zookeeper实例'+rows_zookeeper[a].zkintName+'尚未部署,请先部署所有zookeeper实例!','warning');
      		        return false;
      	        }
      	      }
              return true;
    }
    //全量部署所有应用和中间件实例
    function deploy() {
        sub_url = contextPath + "/deployFullIntant";
        var appInstant = $("#appInstantList").DataTable();
        var appData = appInstant.data();
        for (var i = 0; i < appData.length; i++) {
            if (appData[i].appName != null && (appData[i].appIntantVer == null || appData[i].appIntantVer == "")) {
                showMsg('应用' + appData[i].appName + '尚未有安装版本,请先维护该应用版本!', 'warning')
                return;
            }
            if (appData[i].currentAppIntantStatus == "启动") {
                showMsg('应用实例' + appData[i].appIntantName + '未停止,请先停止所有应用实例!', 'warning');
                return;
            }
        }

        //redis部署效验
           	      var redis =  $("#table_data_redis").DataTable();
                  var rows_redis = redis.data();
                  for(var i=0; i<rows_redis.length; i++){
                      if(rows_redis[i].midwareName!=null&&(rows_redis[i].midwareVerNo==null||rows_redis[i].midwareVerNo=="")){
                         showMsg('Redis集群'+rows_redis[i].midwareName+'尚未有安装版本,请先维护该Redis集群版本!','warning');
                         return;
                      }
                      if(rows_redis[i].currentRedisintStatus=="启动"){
                        showMsg('Redis实例'+rows_redis[i].redisintName+'未停止,请先停止所有Redis实例!','warning');
                        return;
                      }
           	      }
         //zookeeper部署效验
         var zookeeper =  $("#table_data_zookeeper").DataTable();
         var rows_zookeeper = zookeeper.data();
         for(var i=0; i<rows_zookeeper.length; i++){
         if(rows_zookeeper[i].midwareName!=null&&(rows_zookeeper[i].midwareVerNo==null||
         rows_zookeeper[i].midwareVerNo=="")){
         showMsg('zookeeper实例'+rows_zookeeper[i].midwareName+'尚未有安装版本,请先维护该Redis集群版本!','warning');
            return;
         }
         if(rows_zookeeper[i].currentZkIntantStatus=="启动"){
             showMsg('zookeeper实例'+rows_zookeeper[i].zkintName+'未停止,请先停止所有zookeeper实例!','warning');
            	return;
             }
         }

        layer.confirm('您确定要保留配置文件？', function (index) {
            isRemainConfig = "0023001";
            layer.open({
                type: 2,
                content: "fullInstantProgress.jsp",
                area: ['400px', '200px'],
                title: '全量实例部署进度'
            });
            layer.close(index);
        }, function (index) {
            isRemainConfig = "0023002";

            layer.open({
                type: 2,
                content: "fullInstantProgress.jsp",
                area: ['400px', '200px'],
                title: '全量实例部署进度'
            });
            layer.close(index);
        });

    }
    function start() {
         sub_url = contextPath + '/startFullIntant';
        		 if(!checkIntant()){
        			return;
        		 }
        layer.open({
            type: 2,
            content: "fullInstantProgress.jsp",
            area: ['400px', '200px'],
            title: '全量实例启动进度'
        });
    }
    function stop() {
       sub_url = contextPath + '/stopFullIntant';
           	if(!checkIntant()){
       			return;
       		}
        layer.open({
            type: 2,
            content: "fullInstantProgress.jsp",
            area: ['400px', '200px'],
            title: '全量实例停止进度'
        });
    }
    function reStart() {
         sub_url = contextPath + '/reStartFullIntant';
               		if(!checkIntant()){
               			return;
               		}
        layer.open({
            type: 2,
            content: "fullInstantProgress.jsp",
            area: ['400px', '200px'],
            title: '全量实例重启进度'
        });
    }
    function clear() {
        sub_url = contextPath + '/clearFullIntant';
        var appInstant = $("#appInstantList").DataTable();
        var appData = appInstant.data();
        if (appData.length > 0) {
            for (var i = 0; i < appData.length; i++) {
                if (appData[i].appIntantStatusName == "启动") {
                    showMsg('应用实例' + appData[i].appIntantName + '未停止,请先停止所有应用实例!', 'warning');
                    return;
                }
            }
        }
         var redis =  $("#table_data_redis").DataTable();
              var rows_redis = redis.data();
              for(var i=0; i<rows_redis.length; i++){
              if(rows_redis[i].currentRedisintStatus=="启动"){
              showMsg('redis实例' + rows_redis[i].redisintName + '未停止,请先停止所有Redis实例!', 'warning');
              return;
              }
              }
         var zookeeper =  $("#table_data_zookeeper").DataTable();
               var rows_zookeeper = zookeeper.data();
               for(var i=0; i<rows_zookeeper.length; i++){
               if(rows_zookeeper[i].currentZkIntantStatus=="启动"){
               showMsg('zookeeper实例' + rows_zookeeper[i].zkintName + '未停止,请先停止所有Redis实例!', 'warning');
               return;
               }
               }
         isFinish=false;
         layer.confirm(
                "确定进行全量清理？",
                function (index) {
                    layer.open({
                        type: 2,
                        content: "fullInstantProgress.jsp",
                        area: ['400px', '200px'],
                        title: '实例清理进度'
                    });
                    layer.close(index);
                    refresh();
                }
         )
    }
    function refresh() {
        location.replace(location.href);
    }