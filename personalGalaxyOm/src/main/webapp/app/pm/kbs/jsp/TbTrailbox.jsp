<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>TB_TRAILBOX交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/TbTrailbox.js"></script>
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
            <table id="tbTrailbox" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>尾箱代号</th>
                        <th>机构代码</th>
                        <th>尾箱类型</th>
                        <th>尾箱状态</th>
                        <th>凭证碰库时间</th>
                        <th>柜员ID</th>
                        <th>更新日期</th>
                        <th>尾箱属性</th>
                        <th>分配柜员</th>
                        <th>上一使用柜员</th>
                        <th>创建日期</th>
                        <th>法人代码</th>
                        <th>现金碰库会计日期</th>
                        <th>现金碰库时间</th>
                        <th>凭证碰库会计日期</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
