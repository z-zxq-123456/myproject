<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>应用信息管理</title>
<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/upgflowNodeManager.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>基础配置</a><span>&gt;</span><span>应用升级流程配置</span><span>&gt;</span><span>流程节点定义</span><a
            href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<hidden id="upgflowNodeId" value=""></hidden>
<div class="padding-10">
    <div class="mr-20 ml-20 mt-10">
        <table id="table_data" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>
                    流程节点ID
                </th>
                <th>
                    流程节点名
                </th>
                <th>
                    流程节点序号
                </th>
                <th>
                    流程节点url
                </th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
