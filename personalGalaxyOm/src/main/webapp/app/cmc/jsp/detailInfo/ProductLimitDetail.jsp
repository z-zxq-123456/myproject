<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>CMC_PRODUCT_LIMIT交易</title>
    <script type="text/javascript" src="${ContextPath}/app/cmc/js/detailInfo/ProductLimitDetail.js"></script>
</head>
<body>
    <div class="padding-10">
        <div class="mr-20 ml-20 mt-10">
            <table id="cmcProductLimit" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>卡产品编号</th>
                        <th>渠道类型</th>
                        <th>主帐户的币种</th>
                        <th>限制周期</th>
                        <th>本周期消费限额</th>
                        <th>本周期转入限额</th>
                        <th>本周期转出限额</th>
                        <th>本周期消费次数</th>
                        <th>本周期转入次数</th>
                        <th>本周期转出次数</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
