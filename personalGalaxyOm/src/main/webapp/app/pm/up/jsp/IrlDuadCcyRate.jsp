<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>IRL_DUAD_CCY_RATE交易</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/IrlDuadCcyRate.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">目标币种：</label>
                        <div class="formControls  span2">
                                        <select id="TARGET_CCY" class="select2" name="targetCcy" tabindex="4" size="1"  style="width:100%">
                                        </select>
                        </div>
                        <label class="form-label">源币种：</label>
                        <div class="formControls  span2">
                                        <select id="SOURCE_CCY" class="select2" name="sourceCcy" tabindex="4" size="1"  style="width:100%">
                                        </select>
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="irlDuadCcyRate" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                                    <th style="width:160px;">源币种</th>
                                    <th style="width:160px;">目标币种</th>
                                    <th style="width:160px;">生效日期</th>
                                    <th style="width:160px;">生效时间</th>
                                    <th style="width:160px;">汇率类型</th>
                                    <th style="width:160px;">机构代码</th>
                                    <th style="width:160px;">牌价类型</th>
                                    <th style="width:160px;">中间价</th>
                                    <th style="width:160px;">汇买价</th>
                                    <th style="width:160px;">汇卖价</th>
                                    <th style="width:160px;">参考汇率</th>
                                    <th style="width:160px;">钞买价</th>
                                    <th style="width:160px;">钞卖价</th>
                                    <th style="width:160px;">最大浮动点</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
