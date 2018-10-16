<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>参数管理</title>
     <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/sys/paraManager.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>基础配置</a>
        <span>&gt;</span><span>公共配置</span><span>&gt;</span><span>参数管理</span><a href="javascript:location.replace(location.href);" title="刷新"><i
                class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">

    <form method="post" class="form form-horizontal" id="selectForm" name="selectForm">
        <div class="row">
            <label class="form-label span2">参数代码：</label>
            <div class="formControls span3">
                <input class="input-text grid" name="paraCode" id="queryParaCode" type="text">
            </div>
            <label class="form-label span3">参数名称：</label>
            <div class="formControls span3">
                <input class="input-text grid" name="paraName" id="queryParaName" type="text">
            </div>
        </div>

        <div class="row">
            <label class="form-label span2">状态：</label>
            <div class="formControls span3">
                <select type="text" class="select2" name="statue" id="statue"></select>
            </div>
            <div class="span2"></div>
            <a class="button-select S " onclick="searchRec()"><i class="iconfont icon-4" ></i>查询</a>

        </div>
    </form>

    <div class="mr-20 ml-20 mt-10">
        <table id="parameterList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>
                    参数编码
                </th>
                <th>
                    参数名称
                </th>
                <th>
                    上级编码
                </th>
                <th>
                    编码状态
                </th>
                <th>
                    备注一
                </th>
                <th>
                    备注二
                </th>
                <th>
                    备注三
                </th>
            </tr>
            </thead>
        </table>

    </div>
</div>
</body>
</html>

