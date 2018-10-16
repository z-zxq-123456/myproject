<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>GL_PROD_RULE交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/glr/js/GlProdRule.js"></script>
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
            <table id="glProdRule" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>事件类型</th>
                        <th>客户分类</th>
                        <th>核算状态</th>
                        <th>币种</th>
                        <th>系统名称</th>
                        <th>交易类型</th>
                        <th>产品类型</th>
                        <th>渠道类型</th>
                        <th>会计分录编号</th>
                        <th>分录描述</th>
                        <th>自定义规则编号</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
