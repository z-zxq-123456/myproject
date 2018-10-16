<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>FM_USER交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/FmUser.js"></script>
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
            <table id="fmUser" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>柜员ID</th>
                        <th>是否批处理用户</th>
                        <th>柜员状态</th>
                        <th>柜员类别</th>
                        <th>复核日期</th>
                        <th>法人代码</th>
                        <th>部门代码</th>
                        <th>证件号码</th>
                        <th>证件类型</th>
                        <th>所属机构</th>
                        <th>创建柜员</th>
                        <th>创建日期</th>
                        <th>利润中心</th>
                        <th>账薄</th>
                        <th>柜员描述信息</th>
                        <th>柜员语言</th>
                        <th>柜员级别</th>
                        <th>柜员名称</th>
                        <th>复核柜员</th>
                        <th>机构代码</th>
                        <th>授权级别</th>
                        <th>是否应用柜员</th>
                        <th>客户经理</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
