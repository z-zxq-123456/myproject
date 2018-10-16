<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>IRL_FEE_TYPE交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/up/js/IrlFeeType.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span>&gt;</span><span>参数录入查看</span><a
            href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
        <div class="row cl">
            <label class="form-label">费用类型：</label>
            <div class="formControls  span2">
                <select id="FEE_TYPE" class="select2" name="feeType" tabindex="4" size="1" style="width:100%">
                </select>
            </div>
            <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                <i class="iconfont">&#xe624;</i> 查询</a>
        </div>
    </form>
    <div class="mr-20 ml-20 mt-10">
        <table id="irlFeeType" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th>操作类型</th>
                <th >费用类型</th>
                <th style="width:90px;">费用类型描述</th>
                <th>产品组</th>
                <th>收费方式</th>
                <th style="width:36px;">缺口计算金额编号</th>
                <th style="width:36px;" >费用计算金额编号</th>
                <th>缺口描述</th>
                <th>收费币种标识</th>
                <th>目标收费币种</th>
                <th>折算标志</th>
                <th style="width:45px;" >联机收取/批量收取</th>
                <th>折扣方式</th>
                <th>法人代码</th>
                <th>增值税类型</th>
                <th>费用项目代码</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
