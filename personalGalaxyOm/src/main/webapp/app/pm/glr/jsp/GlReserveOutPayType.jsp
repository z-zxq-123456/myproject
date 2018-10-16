<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>GL_RESERVE_OUT_PAY_TYPE交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/glr/js/GlReserveOutPayType.js"></script>
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
            <table id="glReserveOutPayType" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>准备金类型</th>
                        <th>对方客户号</th>
                        <th>对方账户类型</th>
                        <th>准备金类型描述信息</th>
                        <th>缴存客户号</th>
                        <th>缴存账户类型</th>
                        <th>客户名称</th>
                        <th>缴存客户名称</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
