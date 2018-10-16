<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>db监控</title>
    <script type="text/javascript" charset="UTF-8"
            src="${ContextPath}/app/oms/js/middleware/dbInstant.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>中间件管理</a><span>&gt;</span>Database</a><span>&gt;</span><span>db监控</span><a
                       href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
    <div class="row">
        <label class="form-label span2">DB集群选择：</label>
        <div class="formControls span3">
            <select name="midwareId" id="queryMidwareId" size="1" class="select2" style="margin-top:5px">
            </select>
        </div>
    </div>
</form>
<div class="mr-20 ml-20 mt-10">
    <table id="dbInfoList" class="table table-border table-bordered table-hover table-bg table-sort">
        <thead>
        <tr>
            <th>集群名称</th>
            <th>服务器IP</th>
            <th>数据库类型</th>
            <th>db实例名称</th>
            <th>用户名</th>
            <th>DB实例状态</th>
            <th>健康信息</th>
            <th>DB实例标志</th>
            <th>实例客户端口</th>
            <th>DB服务名</th>
        </tr>
        </thead>
    </table>
</div>
<script type="text/javascript">
  $("#queryMidwareId").change(function(){
	    exection();
});
   $("#queryMidwareId").ready(function(){
 	 exection();
 });
</script>
</body>
</html>
  