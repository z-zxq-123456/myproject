<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>MB_ACCOUNTING_STATUS交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/MbAccountingStatus.js"></script>
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
            <table id="mbAccountingStatus" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>核算状态</th>
                        <th>是否考虑宽限期</th>
                        <th>持续扣款标志</th>
                        <th>是否涉及利息</th>
                        <th>是否涉及本金</th>
                        <th>期间</th>
                        <th>期间类型</th>
                        <th>是否久悬</th>
                        <th>是否终止</th>
                        <th>法人代码</th>
                        <th>变化类型</th>
                        <th>是否可交易标识</th>
                        <th>核算状态描述</th>
                        <th>费用顺序</th>
                        <th>利息顺序</th>
                        <th>复利顺序</th>
                        <th>罚息顺序</th>
                        <th>本金顺序</th>
                        <th>自动还款类型</th>
                        <th>自动变化标志</th>
                        <th>是否核销</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
