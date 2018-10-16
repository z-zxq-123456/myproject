<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>MB_ATTR_TYPE交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/prod/js/MbAttrType.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>产品工厂</a><span >&gt;</span><span >参数录入</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="mbAttrType" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>属性KEY</th>
                        <th>属性分类</th>
                        <th>属性描述</th>
                        <th>状态</th>
                        <th>取值方式</th>
                        <th>参数类型</th>
                        <th>所属分类</th>
                        <th>赋值标记</th>
                        <th>使用方式</th>
                        <th>所属法人</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
