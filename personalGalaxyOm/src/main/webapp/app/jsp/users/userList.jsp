<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/formServerSide.jsp" %>

<html>
<head>
    <title>用户列表</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/js/users/userList.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>管理员管理</a><span>&gt;</span><span>用户管理</span><a
            href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="row" id="userNameDiv">
    <input type="text" class="input-text grid" id="userName" placeholder="姓名" name="userName" disabled=true;>
</div>
<div class="padding-10">
    <div class="mr-20 ml-20 mt-10">
        <table id="userList" class="table table-border table-bordered table-hover table-bg">
            <thead>
                <tr>
                    <th>用户ID</th>
                    <th>用户名</th>
                    <th>信息描述</th>
                    <th>法人代表</th>
                    <th>机构</th>
                </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    var form;
    $(document).ready(function() {
        $("#userNameDiv").hide();
        $("#userName").val("${UserName}");
    });
</script>
