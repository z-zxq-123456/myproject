<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>服务器管理</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/server/serverInfoManager.js"></script>
</head>
<body>
<div class="mr-10 ml-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>系统规划</a><span>&gt;</span>服务器规划</a><span>&gt;</span><span>服务器管理</span><a
                              href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">

    <div class="mr-20 ml-20 mt-10">
        <table id="EcmServerList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>
                    服务器ID
                </th>
                <th>
                    服务器名称
                </th>
                <th>
                    服务器IP
                </th>
                <th>
                    服务器用户名
                </th>
                <th>
                    服务器操作系统
                </th>
            </tr>
            </thead>
        </table>
    </div>
</div>

</body>
</html>
  