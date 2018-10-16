<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>GL_PROD_ACCOUNTING交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/glr/js/GlProdAccounting.js"></script>
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
            <table id="glProdAccounting" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>核算状态</th>
                        <th>产品类型</th>
                        <th>应收罚息科目代码</th>
                        <th>罚息收入科目代码</th>
                        <th>应计罚息科目代码</th>
                        <th>应收复利科目代码</th>
                        <th>复利收入科目代码</th>
                        <th>应计复利科目代码</th>
                        <th>负债科目代码</th>
                        <th>应收利息科目代码</th>
                        <th>应付利息科目代码</th>
                        <th>利息收入科目代码</th>
                        <th>利息支出科目代码</th>
                        <th>应计利息科目代码</th>
                        <th>资产损失科目</th>
                        <th>科目调整</th>
                        <th>资产科目代码</th>
                        <th>账套</th>
                        <th>利润中心</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
