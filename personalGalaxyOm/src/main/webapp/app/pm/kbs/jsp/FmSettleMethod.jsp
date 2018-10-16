<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>FM_SETTLE_METHOD交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/FmSettleMethod.js"></script>
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
            <table id="fmSettleMethod" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>结算方法</th>
                        <th>收付标记</th>
                        <th>目标客户类型</th>
                        <th>报表格式</th>
                        <th>结算方法描述</th>
                        <th>结算帐户类型</th>
                        <th>联系方式类型</th>
                        <th>安全释放</th>
                        <th>安全复合</th>
                        <th>电位类型</th>
                        <th>联系类型</th>
                        <th>打印模式</th>
                        <th>法人代码</th>
                        <th>是否现金</th>
                        <th>目标ID</th>
                        <th>是否为DP清算</th>
                        <th>证件类型</th>
                        <th>目标类型</th>
                        <th>发报方联系类型</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
