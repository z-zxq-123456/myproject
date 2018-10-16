<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>CMC_CARD_ORDER_NO_INFO交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/cmc/js/CmcCardOrderNoInfo.js"></script>
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
            <table id="cmcCardOrderNoInfo" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>卡号规则</th>
                        <th>卡顺序号起始值</th>
                        <th>卡顺序号终止值</th>
                        <th>卡顺序号当前值</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
