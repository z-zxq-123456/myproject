<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>日志配置信息管理</title>
     <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/log/logconfInfoManager.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>日志平台规划</a><span>&gt;</span><span>日志配置信息管理</span><a
            href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <div class="mr-20 ml-20 mt-10">
        <table id="table_data" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>
                    配置信息ID
                </th>
                <th>
                    ZK集群
                </th>
                <th>
                    日志平台模式
                </th>
                <th>
                    日志输出模式
                </th>
                <th>
                    是否扫描
                </th>
                <th>
                    扫描周期
                </th>
                <th>
                    入库批量
                </th>
                <th>
                    最大等待时间
                </th>
                 <th>
                   kafka集群
                </th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>

