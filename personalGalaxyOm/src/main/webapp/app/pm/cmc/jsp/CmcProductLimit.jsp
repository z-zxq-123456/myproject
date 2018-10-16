<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>CMC_PRODUCT_LIMIT交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/cmc/js/CmcProductLimit.js"></script>
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
            <table id="cmcProductLimit" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>卡产品编号</th>
                        <th>渠道类型</th>
                        <th>主帐户的币种</th>
                        <th>限制周期</th>
                        <th>本周期消费限额</th>
                        <th>本周期转入限额</th>
                        <th>本周期转出限额</th>
                        <th>本周期消费次数</th>
                        <th>本周期转入次数</th>
                        <th>本周期转出次数</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
