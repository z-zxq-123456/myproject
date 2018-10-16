<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>FM_CURRENCY交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/FmCurrency.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
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
            <table id="fmCurrency" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>币种</th>
                        <th>名称</th>
                        <th>舍入</th>
                        <th>整数描述</th>
                        <th>小数位</th>
                        <th>利率</th>
                        <th>法人代码</th>
                        <th>基准天数</th>
                        <th>小数描述</th>
                        <th>周末1</th>
                        <th>固定日期</th>
                        <th>周末2</th>
                        <th>付/收款通知日</th>
                        <th>净头寸限额</th>
                        <th>牌价类型</th>
                        <th>内部码</th>
                        <th>货币组代码</th>
                        <th>是否货币组</th>
                        <th>即期日期</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
