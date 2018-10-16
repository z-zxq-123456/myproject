<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户列表</title>
<script type="text/javascript" src="${ContextPath}/app/pm/js/paraSetting/paraSystemList.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span>参数流程设置<span >&gt;</span><span >系统设置</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <div class="mr-20 ml-20 mt-10">
        <table id="paraSystemList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>目标系统ID</th>
                <th>目标系统名称</th>
                <th>目标系统描述</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
