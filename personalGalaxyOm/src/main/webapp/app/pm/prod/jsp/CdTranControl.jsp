<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>CD_TRAN_CONTROL交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/prod/js/CdTranControl.js"></script>
</head>
<body>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="cdTranControl" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>区域代码</th>
                        <th>产品类型</th>
                        <th>渠道</th>
                        <th>卡客户等级</th>
                        <th>商户</th>
                        <th>密码控制</th>
                        <th>日累计限额</th>
                        <th>单次交易限额</th>
                        <th>终端号</th>
                        <th>交易笔数</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
