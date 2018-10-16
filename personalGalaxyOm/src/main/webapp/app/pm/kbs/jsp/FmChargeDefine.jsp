<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>FM_CHARGE_DEFINE交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/FmChargeDefine.js"></script>
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
            <table id="fmChargeDefine" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>服务费类型</th>
                        <th>转账收费交易类型</th>
                        <th>是否按平均余额计费</th>
                        <th>现金收费交易类型</th>
                        <th>币种</th>
                        <th>服务费计费方式</th>
                        <th>费用收取方式</th>
                        <th>金额折算标志</th>
                        <th>费用描述</th>
                        <th>固定金额/单价</th>
                        <th>固定费率</th>
                        <th>上限</th>
                        <th>下限</th>
                        <th>矩阵计算方式</th>
                        <th>缺口类型</th>
                        <th>说明</th>
                        <th>计算公式名称</th>
                        <th>联机/日终收取</th>
                        <th>是否全额计费</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
