<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>FM_GAP_TYPE交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/FmGapType.js"></script>
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
            <table id="fmGapType" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>敞口类型</th>
                        <th>敞口开始日期</th>
                        <th>是否工作日</th>
                        <th>敞口类型描述</th>
                        <th>法人代码</th>
                        <th>所属模块</th>
                        <th>币种</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
