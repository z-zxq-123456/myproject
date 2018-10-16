<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>MB_ANNUAL_SURVEY交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/MbAnnualSurvey.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="mbAnnualSurvey" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>账户属性</th>
                        <th>期限频率</th>
                        <th>截止日期</th>
                        <th>截止日期</th>
                        <th>是否止付</th>
                        <th>限制类型</th>
                        <th>天数</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
