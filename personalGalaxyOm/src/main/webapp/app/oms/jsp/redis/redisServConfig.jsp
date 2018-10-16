<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/form.jsp" %>

<html>
<head>
    <title>Redis服务器配置</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/redis/redisServConfig.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>Redis配置</a><span>&gt;</span><span>Redis服务器配置</span><a
            href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="mr-20 ml-20 mt-10">
    <table id="redisServList" class="table table-border table-bordered table-hover table-bg table-sort">
        <thead>
        <tr>
            <th>
                服务器IP
            </th>
            <th>
                服务器端口
            </th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>