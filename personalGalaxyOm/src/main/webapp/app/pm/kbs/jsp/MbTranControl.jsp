<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>MB_TRAN_CONTROL交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/MbTranControl.js"></script>
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
            <table id="mbTranControl" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>卡客户等级</th>
                        <th>产品类型</th>
                        <th>渠道</th>
                        <th>区域代码</th>
                        <th>单次交易限额</th>
                        <th>交易笔数</th>
                        <th>日累计限额</th>
                        <th>法人代码</th>
                        <th>密码控制</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
