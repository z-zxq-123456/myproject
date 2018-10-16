<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<%
    String appId = request.getParameter("id");
    String appName = request.getParameter("name");
%>

<html>
<head>
    <title>应用维护</title>
    <script type="text/javascript" src="${ContextPath}/app/oms/js/app/appInstantManager.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>应用管理</a>
            <span>&gt;</span><span>应用维护</span><a
                    href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <input type="hidden" id="appid" value="<%=appId%>">

    <div class="mr-20 ml-20 mt-10">
        <table id="appInstantList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>
                    实例ID
                </th>
                <th>
                    实例IP
                </th>
                <th>
                    实例名称
                </th>
                <th>
                    实例版本号
                </th>
                <th>
                    最新操作
                </th>
                <th>
                    当前状态
                </th>
                <th>
                    健康信息
                </th>
                <th>
                    安装路径
                </th>
                <th>
                    工作目录
                </th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
<script type="text/javascript" >
  var appName="<%=appName%>";
  var appId = "<%=appId %>";
</script>
