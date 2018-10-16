<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>AC_SUBJECT交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/glr/js/AcSubject.js"></script>
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
            <table id="acSubject" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>科目代码</th>
                        <th>转账标志</th>
                        <th>余额方向</th>
                        <th>BSPL类型</th>
                        <th>科目类型</th>
                        <th>科目状态</th>
                        <th>科目描述</th>
                        <th>是否允许手工批量记账</th>
                        <th>账户类型</th>
                        <th>是否计提营业税</th>
                        <th>账套</th>
                        <th>转账指示</th>
                        <th>科目英文描述</th>
                        <th>是否允许手工记账</th>
                        <th>是否内部科目标志</th>
                        <th>科目级别</th>
                        <th>上级科目</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
