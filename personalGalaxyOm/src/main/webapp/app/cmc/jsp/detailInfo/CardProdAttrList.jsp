<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
  <script type="text/javascript" src="${ContextPath}/app/cmc/js/detailInfo/CardProdAttrList.js"></script>
    <link href="${ContextPath}/lib/bootstrap/css/font-awesome.min.css" rel="stylesheet"
        type="text/css" />
    <title>产品参数一览表</title>
</head>
<body>
<div class="mr-10 ml-10">
<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i> 系统首页<span class="c-gray en">&gt;</span>产品工厂<span class="c-gray en">&gt;</span>产品参数一览表<a class="btn btn-success radius r mr-20 size-MINI" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <div class="mr-20 ml-20 mt-10">
        <table id="prodList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
                <tr class="text-c">
                    <th>产品编号</th>
                    <th>产品描述</th>
                    <th>产品类型</th>
                    <th>发行渠道</th>
                    <th>产品状态</th>
                    <th>发布状态</th>
                </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>