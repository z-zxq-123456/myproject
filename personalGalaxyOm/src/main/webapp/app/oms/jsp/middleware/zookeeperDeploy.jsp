<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>zookeeper实例部署</title>
    <script type="text/javascript" charset="UTF-8"
            src="${ContextPath}/app/oms/js/middleware/zookeeperDeploy.js"></script>
</head>
<body>
<div class="mb-10">
     <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>中间件管理</a><span>&gt;</span>zookeeper</a><span>&gt;</span><span>zookeeper实例部署</span><a
                href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">

    <div class="row">
        <label class="form-label span2">zk集群名称：</label>
        <div class="formControls span3">
            <select name="midwareId" id="queryMidwareId" size="1" class="select2" style="margin-top:5px">
            </select>
        </div>
        <div class="span1"></div>
    </div>
</form>
<div class="mr-20 ml-20 mt-10">
    <table id="zookeeeperList" class="table table-border table-bordered table-hover table-bg table-sort">
        <thead>
        <tr>
            <th>服务器名称</th>
            <th>服务器IP</th>
            <th>zk客户端口</th>
            <th>ZK实例名称</th>
            <th>实例版本号</th>
            <th>最新操作</th>
            <th>当前状态</th>
            <th>健康信息</th>
            <th>ZK安装路径</th>
        </tr>
        </thead>
    </table>
</div>
<script type="text/javascript">
 $("#queryMidwareId").change(function(){
	  refreshZk();
});
$("#queryMidwareId").ready(function(){
	  refreshZk();
});
</script>
</body>
</html>
