<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>复核信息列表</title>
<script type="text/javascript" src="${ContextPath}/app/pm/js/paraIn/checkInfo.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span>参数管理入口<span >&gt;</span><span >复核信息列表</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <div class="mr-20 ml-20 mt-10">
        <table id="checkInfoList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>交易</th>
                <th>流水编号</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
