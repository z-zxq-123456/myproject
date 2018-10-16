<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>RC_LIST_CHECK_RULE交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/RcListCheckRule.js"></script>
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
            <table id="rcListCheckRule" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>名单类型代码</th>
                        <th>事件禁止标识</th>
                        <th>禁止渠道集合</th>
                        <th>是否核实后禁止</th>
                        <th>渠道禁止标识</th>
                        <th>禁止事件集合</th>
                        <th>禁止期限</th>
                        <th>禁止期限单位</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
