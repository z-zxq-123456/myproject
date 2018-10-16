<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>一件维护</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/fullgalaxy/fullAppInstantManager.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a
            href="#">一件部署</a><span>&gt;</span><span>一件维护</span><a href="javascript:location.replace(location.href);"
                                                                      title="刷新"><i class="iconfont">&#xe61e;</i></a>
    </nav>
</div>
<div class="mr-20 ml-20 mt-10">
    <table id="appInstantList" class="table table-border table-bordered table-hover   table-bg ">
        <thead>
        <tr>
            <th>应用名称</th>
            <th>实例名称</th>
            <th>实例IP</th>
            <th>版本号</th>
            <th>最新操作</th>
            <th>当前状态</th>
            <th>健康信息</th>
            <th>安装路径</th>
            <th>工作目录</th>
        </tr>
        </thead>
    </table>

    <table id="table_data_redis"  class="table table-border table-bordered table-hover dataTable-nofooter table-bg ">
        <thead>
        <tr>redis集群:</tr>
        <tr>
            <th>集群名称</th>
            <th>集群节点</th>
            <th>实例名称</th>
            <th>实例类型</th>
            <th>IP端口</th>
            <th>最新操作</th>
            <th>当前状态</th>
            <th>健康信息</th>
            <th>分配内存</th>
            <th>版本号</th>
            <th>Redis实例安装路径</th>
        </tr>
        </thead>
    </table>

    <table id="table_data_zookeeper"  class="table table-border table-bordered table-hover dataTable-nofooter table-bg  ">
        <thead>
        <tr>zookeeper集群:</tr>
        <tr>
            <th>集群名称</th>
            <th>服务器IP</th>
            <th>ZK实例名称</th>
            <th>ZK实例节点数</th>
            <th>最新操作</th>
            <th>当前状态</th>
            <th>健康信息</th>
            <th>ZK实例客户端口</th>
            <th>版本号</th>
            <th>ZK实例安装路径</th>
        </tr>
        </thead>
    </table>



    <table id="table_data_database"  class="table table-border table-bordered table-hover dataTable-nofooter table-bg  ">
        <thead>
        <tr>database集群:</tr>
        <tr>
            <th>集群名称</th>
            <th>服务器名称</th>
            <th>服务器IP</th>
            <th>实例名称</th>
            <th>数据库类型</th>
            <th>用户名</th>
            <th>数据库端口</th>
            <th>数据库服务名</th>
            <th>实例标志</th>
            <th>DB实例状态</th>
            <th>健康信息</th>
        </tr>
        </thead>
    </table>
</div>

</body>
</html>