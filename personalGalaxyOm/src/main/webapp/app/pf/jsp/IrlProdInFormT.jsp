<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
<script type="text/javascript" src="${ContextPath}/app/pf/js/IrlProdInFormT.js"></script>
    <title>参数表IRL_PROD_INT</title>
</head>
<body>
<div class="padding-10" style="font-size:12px;text-align:left;">
    <form action="" method="post" class="form form-horizontal" id="irlProdIntPrimaryKey">
        <div class="row cl" style="display:none">
            <label class="form-label">事件类型：</label>
            <div class="formControls  span2">
                <input type="text" value="" class="input-text grid" placeholder="eventType" id="EVENT_TYPE"
                       name="eventType">
            </div>
            <label class="form-label">计息类型：</label>
            <div class="formControls  span2">
                <input type="text" value="" class="input-text grid" placeholder="intClass" id="INT_CLASS" name="intClass">
            </div>
            <label class="form-label">产品类型：</label>
            <div class="formControls  span2">
                <input type="text" value="" class="input-text grid" placeholder="prodType" id="PROD_TYPE"  name="prodType">
            </div>
            <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                <i class="iconfont">&#xe624;</i> 查询</a>
        </div>
    </form>
    <div class="mr-20 ml-20 mt-10">
        <table id="irlProdInt" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th>产品类型</th>
                <th>事件类型</th>
                <th>计息类型</th>
                <th>利息计算金额ID</th>
                <th>利息计算方法</th>
                <th>靠档天数计算方式</th>
                <th>计息起始日期取值方法</th>
                <th>利率类型</th>
                <th>利率计算金额ID</th>
                <th>重算利息方法</th>
                <th>税率类型代码</th>
                <th>法人代表</th>
                <th>利率启用方式</th>
                <th>利率变更周期</th>
                <th>变更日期</th>
                <th>最大利率</th>
                <th>最小利率</th>
                <th>月基准天数类型</th>
                <th>利率靠档标志</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>