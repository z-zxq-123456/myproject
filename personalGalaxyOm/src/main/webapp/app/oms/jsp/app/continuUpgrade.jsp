<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<%
    String appId = request.getParameter("id");
    String appName = request.getParameter("appName");
    String urlArgs = "";
    if (appId != null) {
        urlArgs = "?appId=" + appId;
    }
%>
<html>
<head>
    <title>用户列表</title>
    <link href="${ContextPath}/lib/jquery-step/css/jquery.step.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ContextPath}/lib/jquery-step/js/jquery.step.js"></script>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/continuUpgrade.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">
        &#xe61d;</i>系统首页<a><span>&gt;</span>应用管理</a><span>&gt;</span><span>不间断升级</span><a
            href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="step-body  span11  mt-10" id="myStep" style="margin-left:25px">
        <div class="step-header">
            <ul id="stepMenu"></ul>
        </div>
        <div  class="step-current"  id = "updateDivId"   style="width:100%;height:390px;margin-top:5%"></div>
        <div id="addBtn" style="margin-top:3px;"   align = "right"></div>
    </div>

</body>
</html>
<script type="text/javascript">
 var appId = "<%=appId %>";
 var appName="<%=appName%>";
</script>