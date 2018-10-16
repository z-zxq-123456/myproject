<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>表格信息</title>
<script type="text/javascript" src="${ContextPath}/app/pm/js/paraSetting/paraFieldsColumnList.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span>参数流程设置<span >&gt;</span><span >元数据设置</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <div class="mr-20 ml-20 mt-10">
        <table id="paraFieldsColumnList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th  style="width:290px;">字段描述</th>
                <th  style="width:220px;">列名</th>
                <th  style="width:150px;">显示类型</th>
                <th  style="width:170px;">来自表</th>
                <th  style="width:300px;">来自列</th>
                <th  style="width:120px;">字段类型</th>
                <th  style="width:80px;">数据长度</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
