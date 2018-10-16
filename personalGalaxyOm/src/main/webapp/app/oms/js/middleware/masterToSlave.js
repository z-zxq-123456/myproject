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
                "data": "midwarePath",
                "defaultContent": ""
            },
        ];
        //渲染tables
        drawDataTable($("#redisList"), opt);
        //界面美化tables
        $("#redisList").beautyUi({
            tableId: "redisList",
            buttonName: ["随机主备切换",  "刷新"],
            buttonId: ["exchangeMS", "refresh"]
        });

        $('#redisList tbody').on('click', 'tr', function (e) {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            } else {
                $('#redisList').find("tr").removeClass('selected');
                $(this).addClass('selected');
            }
        });
        $('#redisList').on('page.dt', function (e) {
            $('#redisList').find("tr").removeClass('selected');
        });
        $("#exchangeMS").on("click", function () {
            exchangeMS();
        });
        $("#refresh").on("click", function () {
            refresh();
        });
        getPkList({
                url: contextPath + "/findMidwareCombox",
                id: "queryMidwareId",
                async: false,
                params: {midwareType: '0006001'}
            });
            $(".select2").select2();

        $("#queryMidwareId").change(function(){
                      getPkList({
                                     url: contextPath + "/findRedisNodes",
                                     id: "queryRedisintNodeNum",
                                     async: false,
                                     params: {midwareId: $('#queryMidwareId').val()}
                      });
                      $("#queryRedisintNodeNum").change(function(){
                                  var dataTab = $("#redisList").dataTable();
                                  var api = dataTab.api();
                                  api.ajax.url(contextPath + "/findRedisByNodes?midwareId="+$('#queryMidwareId').val()+"&redisintNodeNum="+$('#queryRedisintNodeNum option:selected').text()).load();
                      });
        });
        /*页面按钮根据权限实现隐藏*/
        pagePerm();
    });

    function exchangeMS() {
        sub_url = contextPath + "/setMasterToSlave?midwareId="+$('#queryMidwareId').val()+"&redisintNodeNum="+$('#queryRedisintNodeNum option:selected').text();
        var allRedis = $("#redisList").DataTable().data();
        if (allRedis.length >=2) {
        openProgressBar();
        }else {
             showMsg( '无法进行主备切换操作!','warning');
        }
    }

    function refresh() {
        var dataTab = $("#redisList").dataTable();
        var api = dataTab.api();
        api.ajax.url(contextPath + "/findRedisByNodes?midwareId="+$('#queryMidwareId').val()+"&redisintNodeNum="+$('#queryRedisintNodeNum option:selected').text()).load();
    }
    function openProgressBar() {
        layer.open({
                                     type: 2,
                                     content: "middlewareProgress.jsp",
                                     area: ['400px', '200px'],
                                     title: '操作进度'
        });
    }