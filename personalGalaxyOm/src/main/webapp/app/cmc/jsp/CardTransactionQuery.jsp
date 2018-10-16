<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>卡交易历史查询</title>
    <script type="text/javascript" src="${ContextPath}/app/cmc/js/CardTransactionQuery.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>已发卡管理</a><span >&gt;</span><span >卡交易查询</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <form action="" method="get" class="form form-horizontal" id="queryPrimaryKey">
        <div class="row cl">
            <label class="form-label mt-5">卡号：</label>
            <input type="text" class="input-text" value="" placeholder="卡号" id="cardNo" name="cardNo"
                   style="width:11%;">&nbsp;&nbsp;
            <td style="text-align:right;padding-right:5px;padding-left:5px;">
                开始日期
            </td>
            <td>
                <input type="text" onfocus="WdatePicker()" class="input-text Wdate" style="width:140px;margin-bottom:0px;" id="beginDate"/>
            </td>
            <td style="text-align:right;padding-right:5px;padding-left:5px;">
                结束日期
            </td>
            <td>
                <input type="text"  onfocus="WdatePicker()" class="input-text Wdate" style="width:140px;margin-bottom:0px;" id="endDate"/>
            </td>
            <a id="selectByCardNo" href="#" class="button-sure M" style="margin-bottom:10px">
                <i class="iconfont">&#xe624;</i>  查询</a>
        </div>
    </form>

    <div class="mr-20 ml-20 mt-10">
        <table id="cmcTranInfo" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th>卡号</th>
                <th>账号</th>
                <th>客户号</th>
                <th>对手账号</th>
                <th>对手客户号</th>
                <th>全局流水号</th>
                <th>子交易序号</th>
                <th>交易日期</th>
                <th>对账日期</th>
                <th>渠道类型</th>
                <th>交易流水状态</th>
                <th>冲正标志</th>
                <th>撤销标志</th>
                <th>币种</th>
                <th>系统ID</th>
                <th>交易类型</th>
                <th>借贷方向</th>
                <th>交易金额</th>
                <th>原交易全局流水号</th>
                <th>原交易子交易序号</th>
                <th>结算标志</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
