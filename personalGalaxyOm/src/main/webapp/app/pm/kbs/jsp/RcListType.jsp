<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>RC_LIST_TYPE交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/RcListType.js"></script>
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
            <table id="rcListType" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>名单类型代码</th>
                        <th>名单种类</th>
                        <th>核实期限</th>
                        <th>是否需要核实本身</th>
                        <th>是否需要核实关联账户</th>
                        <th>状态</th>
                        <th>是否关联同一客户下的其他账户标识</th>
                        <th>名单类型描述</th>
                        <th>发送机构</th>
                        <th>核实期限类型</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
