<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>IRL_RISK_SETING交易</title>
            <script type="text/javascript" src="${ContextPath}/app/pm/up/js/IrlRiskSeting.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">序号：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="irlSeqNo" id="IRL_SEQ_NO" name="irlSeqNo"  >
                        </div>
                        <label class="form-label">产品类型：</label>
                        <div class="formControls  span2">
                                        <select id="PROD_TYPE" class="select2" name="prodType" tabindex="4" size="1"  style="width:100%">
                                        </select>
                        </div>
                        <label class="form-label">币种：</label>
                        <div class="formControls  span2">
                                        <select id="CCY" class="select2" name="ccy" tabindex="4" size="1"  style="width:100%">
                                        </select>
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="irlRiskSeting" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                                <th style="width:160px;">序号</th>
                                <th style="width:160px;">产品类型</th>
                                <th style="width:160px;">币种</th>
                                <th style="width:160px;">余额</th>
                                <th style="width:160px;">频率类型</th>
                                 <th style="width:160px;">每期天数</th>
                                 <th style="width:160px;">浮动点数下限</th>
                                 <th style="width:160px;">浮动点数上限</th>
                                <th style="width:160px;">执行利率上限</th>
                                <th style="width:160px;">执行利率下限</th>
                                <th style="width:160px;">浮动百分比上限</th>
                                <th style="width:160px;">浮动百分比下限</th>
                                <th style="width:160px;">行内利率下限</th>
                                <th style="width:160px;">行内利率上限</th>
                                <th style="width:160px;">法人代码</th>

                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>

