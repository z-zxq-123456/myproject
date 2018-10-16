<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户列表</title>
<script type="text/javascript" src="${ContextPath}/app/pm/js/paraSetting/paraEnvOrgList.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span>参数流程设置<span >&gt;</span><span >环境设置</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <div class="mr-20 ml-20 mt-10">
        <table id="paraEnvList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>环境ID</th>
                <th>系统ID</th>
                <th>环境描述</th>
                <th>HTTP接入URL</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
