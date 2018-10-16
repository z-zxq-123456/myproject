<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户列表</title>
<script type="text/javascript" src="${ContextPath}/app/pm/js/paraSetting/paraTableFieldsList.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span>参数流程管理<span >&gt;</span><span >参数内容设置</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <div class="mr-20 ml-20 mt-10">
        <table id="paraFieldsTableList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>交易名</th>
                <th>交易内容</th>
                <th>能否为空</th>
                <th>是否主键</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
