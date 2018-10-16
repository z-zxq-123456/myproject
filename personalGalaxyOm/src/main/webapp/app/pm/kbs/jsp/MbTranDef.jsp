<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>MB_TRAN_DEF交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/MbTranDef.js"></script>
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
            <table id="mbTranDef" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>交易类型</th>
                        <th>现金交易</th>
                        <th>余额类型次序</th>
                        <th>借贷标志</th>
                        <th>更正交易</th>
                        <th>多种冲正方式标志</th>
                        <th>对方交易类型</th>
                        <th>凭证打印交易描述</th>
                        <th>交易类型与交易界面对应关系</th>
                        <th>重新计算余额止付标志</th>
                        <th>重新计算限制金额标志</th>
                        <th>冻结级别(限制级别)</th>
                        <th>冲正交易标志</th>
                        <th>冲正交易类型</th>
                        <th>渠道类型</th>
                        <th>交易类别</th>
                        <th>交易时间</th>
                        <th>交易时间戳</th>
                        <th>交易类型描述</th>
                        <th>是否更新尾箱</th>
                        <th>收付款标志</th>
                        <th>余额标志</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
