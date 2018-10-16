<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/formServerSide.jsp"%>
<html>
<head>
    <title>服务器管理</title>
</head>
<script type="text/javascript">
$(document).ready(function () {
    // 获取默认opt配置
    var opt = getDataTableOpt($("#serverAlarmList"));
    opt.ajax = {
                "url": contextPath + "/findServerAlarmInfo",
                "type": "POST"
    };
     opt.columns = [
         {
            "data": "serIp",
            "defaultContent": ""
         },
         {
            "data": "alarmTime",
            "defaultContent": ""
         },
         {
            "data":"alarmTypeName",
            "defaultContent":""
         },
         {
            "data":"alarmInfo",
            "defaultContent":""
         },
         {
            "data":"handleInfo",
            "defaultContent":""
         }
     ];
     //渲染tables
     drawDataTableServerSide($("#serverAlarmList"), opt,10);
     $("#serverAlarmList").beautyUi({
             tableId: "serverAlarmList",
             needBtn: false,
             serverSide : true
         });
});

</script>
<body>
<div class="mr-10 ml-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>管理视图</a><span>&gt;</span>服务器性能报警</a><span>&gt;</span><a
                              href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">

    <div class="mr-20 ml-20 mt-10">
        <table id="serverAlarmList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>
                    服务器IP
                </th>
                <th>
                    时间
                </th>
                <th>
                    报警类型
                </th>
                <th>
                    报警信息
                </th>
                <th>
                    处理记录
                </th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
  