<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>应用信息管理</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/upgndBtnManager.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>基础配置</a><span>&gt;</span><span>应用升级流程配置</span><span>&gt;</span><span>流程节点按钮定义</span><a
               href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<hidden id="upgndBtnId" value=""></hidden>
<div class="padding-10">
    <div class="mr-20 ml-20 mt-10">
        <table id="table_data" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>
                    流程节点Id
                </th>
                <th>
                    流程节点名
                </th>
                <th>
                    节点按钮名
                </th>
                <th>
                    节点按钮序号
                </th>
                <th>
                    节点按钮处理类
                </th>
                <th>
                    转向节点名
                </th>
                <th>
                    按钮触发函数
                </th>
                <th>
                    是否显示按钮
                </th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>

