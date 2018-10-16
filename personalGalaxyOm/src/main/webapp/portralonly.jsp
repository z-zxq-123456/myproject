<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath();
    request.getSession().setAttribute("ContextPath", basePath);
%>
<script type="text/javascript">
    var contextPath = "${ContextPath}";
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<base href=".">
<link rel="Bookmark" href="${ContextPath}/images/galaxyr.ico" >
<link rel="Shortcut Icon" href="${ContextPath}/images/galaxyr.ico" />
<link rel="stylesheet" type="text/css" href="${ContextPath}/css/app/galaxy.login.css">
<script type="text/javascript" src="${ContextPath}/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${ContextPath}/js/login/login.js"></script>
