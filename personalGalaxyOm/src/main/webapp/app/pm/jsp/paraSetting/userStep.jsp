<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户流程权限管理</title>
<script type="text/javascript" src="${ContextPath}/app/pm/js/paraSetting/userStep.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>参数流程管理</a><span >&gt;</span><span >用户流程权限</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <div class="mr-20 ml-20 mt-10">
        <table id="userStep" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>用户ID</th>
                <th>申请权限</th>
                <th>录入权限</th>
                <th>复核权限</th>
                <th>发布权限</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
