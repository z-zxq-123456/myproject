<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>应用信息管理</title>
     <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/appInfoManager.js"></script>
</head>
<body>
<hidden id="appId" value=""></hidden>
<div class="mb-10">
     <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>系统规划</a><span>&gt;</span>应用规划</a><span>&gt;</span><span>应用信息管理</span><a
      href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <div class="mr-20 ml-20 mt-10">
        <table id="appList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>
                    应用名称
                </th>
                <th>
                    应用英文简称
                </th>
                <th>
                    Redis集群
                </th>
                <th>
                    ZK集群
                </th>
                <th>
                    DB集群
                </th>
                <th>
                    应用分类
                </th>
                <th>
                    应用安装路径
                </th>
                <th>
                    应用描述
                </th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>

