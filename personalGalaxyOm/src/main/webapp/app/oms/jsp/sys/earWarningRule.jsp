<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>参数管理</title>
     <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/sys/earWarningRule.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>基础配置</a>
        <span>&gt;</span><span>公共配置</span><span>&gt;</span><span>预警规则</span><a href="javascript:location.replace(location.href);" title="刷新"><i
                class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">

    <form method="post" class="form form-horizontal" id="selectForm" name="selectForm">
        <div class="row">
            <label class="form-label span2">预警名称：</label>
            <div class="formControls span3">
                <input class="input-text grid" name="ruleName" id="queryRuleName" type="text">
            </div>

            <label class="form-label span2">预警级别：</label>
            <div class="formControls span3">
                <select type="text" class="select2" name="ruleRank" id="ruleRank"></select>
            </div>

            <a class="button-select S " onclick="searchRec()"><i class="iconfont icon-4" ></i>查询</a>
        </div>
    </form>

    <div class="mr-20 ml-20 mt-10">
        <table id="parameterList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>
                    预警名称
                </th>
                <th>
                    预警级别
                </th>
                <th>
                    预警描述
                </th>
            </tr>
            </thead>
        </table>

    </div>
</div>
</body>
</html>

