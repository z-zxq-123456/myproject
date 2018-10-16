<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>应用实例管理</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/appIntantConfigManager.js"></script>
</head>
<body class="easyui-layout">
<div class="mr-10 ml-10">
     <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>系统规划</a><span>&gt;</span>应用规划</a><span>&gt;</span><span>应用实例管理</span><a
          href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>

<form method="post" class="form form-horizontal" id="selectForm" name="selectForm">
    <div class="row">
        <label class="form-label span2" >应用名称：</label>
        <div class="formControls span3"  >
            <select type="text" class="select2"  name="queryAppId"
                    id="queryAppId"></select>
        </div>
        <label class="form-label span2 " >服务器名称：</label>
        <div class="formControls span3" >
            <select type="text" class="select2" name="serName" 
                    id="serName"></select>
        </div>
        <a class="button-select S ml-20"  onclick="searchRec()"><i class="iconfont icon-4"></i>搜索</a>
    </div>
</form>

<div class="padding-10">
    <div class="mr-20 ml-20 mt-10">
        <table id="dataList" fit="false" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>实例ID</th>
                <th>应用名称</th>
                <th>服务器名称</th>
                <th>服务器IP</th>
                <th>实例名称</th>
                <th>应用实例描述</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
  