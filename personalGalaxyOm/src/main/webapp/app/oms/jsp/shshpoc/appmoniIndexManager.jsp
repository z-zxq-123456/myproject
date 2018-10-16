<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>应用指标监控配置</title>
     <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/shshpoc/appmoniIndexManager.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>公共配置</a>
        <span>&gt;</span><span>应用指标监控配置</span><a href="javascript:location.replace(location.href);" title="刷新"><i
                class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <div class="mr-20 ml-20 mt-10">
        <table id="indexList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>
                    指标名称
                </th>
                <th>
                    指标序号
                </th>
                <th>
                    是否显示
                </th>
                <th>
                    指表字段
                </th>
                <th>
                    指标算法
                </th>
            </tr>
            </thead>
        </table>

    </div>
</div>
</body>
</html>

