<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>CMC_CARD_NO_PARAM交易</title>
    <script type="text/javascript" src="${ContextPath}/app/cmc/js/CmcCardNoParamDetail.js"></script>
</head>
<body>
    <div class="padding-10">
        <div class="mr-20 ml-20 mt-10">
            <table id="cmcCardNoParam" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>卡号规则</th>
                        <th>制卡数量</th>
                        <th>启用标识</th>
                        <th>制卡数量警戒值</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
