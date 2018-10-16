<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>角色列表</title>
<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/js/sys/roleList.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a href="#">权限管理</a><span >&gt;</span><span >角色管理</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">

    <div class="mr-20 ml-20 mt-10">
        <table id="roleList" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                    <tr class="text-c">
                        <th>角色ID</th>
                        <th>角色名称</th>
                        <th>角色描述</th>
                    </tr>
                </thead>
            </table>
    </div>
</div>
</body>
</html>

