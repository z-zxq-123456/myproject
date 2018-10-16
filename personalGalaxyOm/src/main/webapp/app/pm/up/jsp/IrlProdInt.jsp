<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>IRL_PROD_INT交易</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/IrlProdInt.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">利息分类：</label>
                        <div class="formControls  span2">
                                        <select id="INT_CLASS" class="select2" name="intClass" tabindex="4" size="1"  style="width:100%">
                                        </select>
                        </div>
                        <label class="form-label">产品类型：</label>
                        <div class="formControls  span2">
                                        <select id="PROD_TYPE" class="select2" name="prodType" tabindex="4" size="1"  style="width:100%">
                                        </select>
                        </div>
                        <label class="form-label">事件类型：</label>
                        <div class="formControls  span2">
                                        <select id="EVENT_TYPE" class="select2" name="eventType" tabindex="4" size="1"  style="width:100%">
                                        </select>
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="irlProdInt" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                                <th style="width:160px;">产品类型</th>
                                <th style="width:160px;">事件类型</th>
                                <th style="width:160px;">利率类型</th>
                                <th style="width:160px;">利息分类</th>
                                <th style="width:160px;">税率类型代码</th>
                                 <th style="width:160px;">利率计算金额编码</th>
                                <th style="width:160px;">利息计算金额编码</th>
                                <th style="width:160px;">重算利息方法</th>
                                <th style="width:160px;">计息起始日期取值方法</th>
                                <th style="width:160px;">靠档天数计算方式</th>
                                <th style="width:160px;">利息计算方法</th>
                                <th style="width:160px;">利率启用方式</th>
                                <th style="width:160px;">利率变更周期</th>
                                <th style="width:160px;">利率变更日</th>
                                <th style="width:160px;">最小利率</th>
                                <th style="width:160px;">最大利率</th>
                                <th style="width:160px;">利率靠档标志</th>
                                <th style="width:160px;">月基准天数类型</th>
                                <th style="width:160px;">分组规则关系</th>
                         <th style="width:160px;">分段ID</th>
                         <th style="width:160px;">分段类型</th>
                         <th style="width:160px;">规则ID</th>








                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
