<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
    <title>首页</title>
    ${includeJspPath}
</head>
<body style="background: #d4d4d4; overflow:auto;">
<div class="pt-20">
    <div id="mainPanle" region="center" >
        <div class="all fastDisplay">
        <#if (firstLineForPage)?has_content>
            ${firstLineForPage}
        </#if>
        <#if (secondLineForPage)?has_content>
            ${secondLineForPage}
        </#if>
        <#if (thirdLineForPage)?has_content>
            ${thirdLineForPage}
        </#if>
        <#if (fourthLineForPage)?has_content>
            ${fourthLineForPage}
        </#if>
        </div>
    </div>
</div>
</body>
</html>