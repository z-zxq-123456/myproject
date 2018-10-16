<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>IRL_FEE_PACKAGE交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/up/js/IrlFeePackage.js"></script>
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
            <table id="irlFeePackage" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>套餐代码</th>
                        <th>生效日期</th>
                        <th>套餐描述</th>
                        <th>套餐类型</th>
                        <th>套餐状态</th>
                        <th>套餐币种</th>
                        <th>失效日期</th>
                        <th>套餐模式</th>
                        <th>抵扣顺序</th>
                        <th>结算金额</th>
                        <th>剩余费用处理方式</th>
                        <th>套餐频率</th>
                        <th>可抵扣笔数</th>
                        <th>套餐费费用类型</th>
                        <th>可抵扣金额</th>
                        <th>下一处理日</th>
                        <th>结算币种</th>
                        <th>客户分类</th>
                        <th>处理日</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
