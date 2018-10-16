<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户列表</title>
<script type="text/javascript" src="${ContextPath}/app/pm/js/paraSetting/paraSelectFieldsList.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span>参数流程管理<span >&gt;</span><span >交易查询条件设置</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <div class="mr-20 ml-20 mt-10">
        <table id="paraSelectFieldsList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>交易名</th>
                <th>交易查询条件一</th>
                <th>交易查询条件二</th>
                <th>交易查询条件三</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
