<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <script type="text/javascript" src="${ContextPath}/app/pf/js/mbAttrValue.js"></script>
    <link href="${ContextPath}/lib/bootstrap/css/font-awesome.min.css" rel="stylesheet"
        type="text/css" />
    <title>参数值定义</title>
</head>
<body>
<div class="mr-10 ml-10">
<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i> 系统首页<span class="c-gray en">&gt;</span>产品工厂<span class="c-gray en">&gt;</span>参数值定义<a class="btn btn-success radius r mr-20 size-MINI" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">

       <div class="mr-20 ml-20 mt-10">
        <table id="attrList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
                <tr class="text-c">
                    <th>参数代码</th>
                    <th>参数描述</th>
                    <th>参数值</th>
                    <th>参数值描述</th>
                    <th>参照表</th>
                    <th>参照条件</th>
                    <th>参照列</th>
                </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>