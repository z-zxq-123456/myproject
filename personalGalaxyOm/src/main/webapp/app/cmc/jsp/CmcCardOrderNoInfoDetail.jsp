<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>CMC_CARD_ORDER_NO_INFO交易</title>
    <script type="text/javascript" src="${ContextPath}/app/cmc/js/CmcCardOrderNoInfoDetail.js"></script>
</head>
<body>
    <div class="padding-10">
        <div class="mr-20 ml-20 mt-10">
            <table id="cmcCardOrderNoInfo" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>卡号规则</th>
                        <th>卡顺序号起始值</th>
                        <th>卡顺序号终止值</th>
                        <th>卡顺序号当前值</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
