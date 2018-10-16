var sub_url;
var isFinish = false;
var row;
var appInstantId;//应用实例ID
var layer_deploy_index, layer_edit_index;
$(document).ready(function () {
        // 获取默认opt配置
        var opt = getDataTableOpt($("#appInstantList"));
        opt.stateSave = true;
        opt.processing = true;
        opt.autoWidth = false;
        opt.ajax = {
            "url": contextPath + "/findAppIntant?appId="+appId,
            "type": "POST"
        };
        opt.columns = [
            {
                "data": "appIntantId",
                "defaultContent": ""
            },
            {
                "data": "serIp",
                "defaultContent": ""
            },
            {
                "data": "appIntantName",
                "defaultContent": ""
            },
            {
                "data": "appIntantVer",
                "defaultContent": ""
            },
            {
                "data": "appIntantStatusName",
                "defaultContent": ""
            },
            {
                "data": "currentAppIntantStatus",
                "defaultContent": ""
            },
            {
                "data": "healthMessage",
                "defaultContent": ""
            },
            {
                "data": "appPath",
                "defaultContent": ""
            },
            {
                "data": "appWork",
                "defaultContent": ""
            }
        ];
        //渲染tables
        drawDataTable($("#appInstantList"), opt);
        //界面美化tables
        $("#appInstantList").beautyUi({
            tableId: "appInstantList",
            buttonName: ["部署", "启动", "停止", "重启", "应用卸载", "刷新"],
            buttonId: ["deploy", "start", "stop", "restart", "clear", "refresh"]
        });

        $('#appInstantList tbody').on('click', 'tr', function (e) {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            } else {
                $('#appInstantList').find("tr").removeClass('selected');
                $(this).addClass('selected');
            }
        });
        $('#appInstantList').on('page.dt', function (e) {
            $('#appInstantList').find("tr").removeClass('selected');
        });
        $("#deploy").on("click", function () {
            deployApp();
        });
        $("#start").on("click", function () {
            startApp();
        });
        $("#stop").on("click", function () {
            stopApp();
        });
        $("#restart").on("click", function () {
            reStartApp();
        });
        $("#clear").on("click", function () {
            clearAppIntant();
        });
        $("#refresh").on("click", function () {
            refresh();
        });
    });
//部署设置
function deployApp() {
    row = $('#appInstantList').DataTable().rows(".selected").data()[0];
    appInstantId = row.appIntantId;
    if (row) {
        if (row.currentAppIntantStatus == "启动") {
            showMsg('该应用实例已经启动!', 'warning');
            return;
        }
        deploy_app('应用实例部署', 'appInstantAdd.jsp', '600', '400');
    } else {
        showMsg('请选择一条记录!', "info");
    }
}
//启动应用实例
function startApp() {
    row = $('#appInstantList').DataTable().rows(".selected").data()[0];
    if (row) {
        if (row.currentAppIntantStatus == "启动") {
            showMsg('应用实例已启动!', 'warning');
            return;
        }
        if (row.appIntantStatusName == "尚未部署") {
            showMsg('应用实例尚未部署!', 'warning');
            return;
        }
        sub_url = contextPath + '/startAppIntant';
        layer.open({
            type: 2,
            content: "appInstantProgress.jsp",
            area: ['750px', '450px'],
            title: '启动应用实例进度'
        });
    } else {
        showMsg('请选择一条记录!', 'info');
    }
}
//重启应用实例
function reStartApp() {
    row = $('#appInstantList').DataTable().rows(".selected").data()[0];
    if (row) {
        if (row.appIntantStatusName == "尚未部署") {
            showMsg('应用实例尚未部署!', 'warning');
            return;
        }
        sub_url = contextPath + '/reStartAppIntant';
        layer.open({
            type: 2,
            content: "appInstantProgress.jsp",
             area: ['750px', '450px'],
            title: '重启应用实例进度'
        });
    } else {
        showMsg('请选择一条记录!', 'info');
    }
}
//停止应用实例
function stopApp() {
    row = $('#appInstantList').DataTable().rows(".selected").data()[0];
    if (row) {
        if (row.currentAppIntantStatus == "停止") {
            showMsg('应用实例已停止!', 'warning');
            return;
        }
        if (row.appIntantStatusName == "尚未部署") {
            showMsg('应用实例尚未部署!', 'warning');
            return;
        }
        sub_url = contextPath + '/stopAppIntant';
        layer.open({
            type: 2,
            content: "appInstantProgress.jsp",
             area: ['750px', '450px'],
            title: '停止应用实例进度'
        });
    } else {
        showMsg('请选择一条记录!', 'info');
    }
}
//清理应用实例
function clearAppIntant() {
    row = $('#appInstantList').DataTable().rows(".selected").data()[0];
    if (row) {
        if (row.currentAppIntantStatus == "启动") {
            showMsg('请先停止该应用实例!', 'warning');
            return;
        }
        if (row.appIntantStatusName == "尚未部署") {
            showMsg('应用实例尚未部署!', 'warning');
            return;
        }
        layer.confirm('该操作将删除服务器该实例,您确认要清理吗?', function (r) {
            sub_url = contextPath + '/cleanAppIntant';
            layer.open({
                type: 2,
                content: "appInstantProgress.jsp",
                  area: ['750px', '450px'],
                title: '清理应用实例进度'
            });
            layer.close(r);
        });
    } else {
        showMsg('请选择一条记录!', 'info');
    }
}
//通过子页面传送过来的值判断是否执行成功
 function showMsgDuringTime(msg) {
        layer.msg(msg);
        setTimeout(function () {
            layer.closeAll('dialog');
        }, 1000);
        if (msg == "部署成功") {
            layer.close(layer_deploy_index);
        }
}
 /*部署appIntstant*/
    function deploy_app(title, url, w, h) {
        layer_deploy_index = layer.open({
            type: 2,
            content: url,
            title: title,
            area: [w + 'px', h + 'px'],
            end: function () {
                selectdataList();
            }
        });
    }
//获取规则查询信息  并且判断该实例的端口是否占用
function selectdataList() {
    var dataTab = $("#appInstantList").dataTable();
    var api = dataTab.api();
    api.ajax.reload(function (){
                     var rowAll = $('#appInstantList').DataTable().rows().data();
                                    for (var i = 0; i < rowAll.length; i++) {
                                         if(rowAll[i].appIntantId==appInstantId&&rowAll[i].currentAppIntantStatus=="启动"){
                                                showMsg("该实例端口已被占用，请您到配置调整菜单下修改端口",'warning');
                                         }
                                    }
                    },false);
}
//刷新表格数据。从数据库再查了一次
function refresh() {
    var dataTab = $("#appInstantList").dataTable();
    var api = dataTab.api();
    api.ajax.url(contextPath + "/findAppIntant?appId="+appId).load();
}
//该函数为子页面调用，把url传到子页面
function getSubUrl() {
    return sub_url;
}
//该函数为子页面调用，把选中的一行的数据传到子页面
function getRow() {
    return row;
}
//该函数为子页面调用，改变变量isFinish的值
function getIsFinish() {
    return isFinish;
}
	
     
      
    
      
   
      
 
   
      
