<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>TB_VOUCHER_DEF交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/TbVoucherDef.js"></script>
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
            <table id="tbVoucherDef" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>凭证类型</th>
                        <th>是否支票标记</th>
                        <th>前缀标志</th>
                        <th>状态</th>
                        <th>总行入库标志</th>
                        <th>是否有号</th>
                        <th>类型描述</th>
                        <th>凭证种类</th>
                        <th>他行票据标记</th>
                        <th>允许调拨标志</th>
                        <th>是否限制机构使用</th>
                        <th>凭证票据标识</th>
                        <th>是否按顺序使用</th>
                        <th>凭证号长度</th>
                        <th>有价单证固定面额组</th>
                        <th>生效日期</th>
                        <th>利润中心</th>
                        <th>柜员ID</th>
                        <th>上次修改柜员</th>
                        <th>复核柜员</th>
                        <th>代办人口挂天数</th>
                        <th>法人</th>
                        <th>存款类型</th>
                        <th>失效日期</th>
                        <th>是否现金支票标记</th>
                        <th>有价单证是否固定面额标志</th>
                        <th>上次修改日期</th>
                        <th>口挂天数</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
