<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>MB_STAGE_DEFINE交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/MbStageDefine.js"></script>
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
            <table id="mbStageDefine" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>期次代码</th>
                        <th>发行年度</th>
                        <th>发行起始日期</th>
                        <th>产品类型</th>
                        <th>发行终止日期</th>
                        <th>发行额度</th>
                        <th>币种</th>
                        <th>期次描述</th>
                        <th>期限</th>
                        <th>期限类型</th>
                        <th>转让标识</th>
                        <th>交易支行</th>
                        <th>交易日期</th>
                        <th>销售方式</th>
                        <th>利率重置频率</th>
                        <th>是否允许提前支取</th>
                        <th>付息方式</th>
                        <th>部提次数</th>
                        <th>计息方式</th>
                        <th>柜员ID</th>
                        <th>取息频率</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
