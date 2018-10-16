<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>CMC_PROD_CHANNEL交易</title>
    <script type="text/javascript" src="${ContextPath}/app/cmc/js/CmcProductChannel.js"></script>
</head>
<body>
<div class="padding-10">
    <div class="mr-20 ml-20 mt-10">
        <table id="cmcProductChannel" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th>操作类型</th>
                <th>卡产品编号</th>
                <th>禁用的渠道</th>
                <th>禁用的交易类型</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
