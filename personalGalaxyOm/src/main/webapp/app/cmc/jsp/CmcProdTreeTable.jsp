<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>产品树表页面</title>
    <script type="text/javascript" src="${ContextPath}/app/cmc/js/CmcProdTreeTable.js"></script>
    <script type="text/javascript" src="${ContextPath}/lib/treeTable/jquery.treetable.js"></script>
    <link href="${ContextPath}/lib/treeTable/jquery.treetable.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${ContextPath}/lib/treeTable/jquery.treetable.theme.default.css">
</head>
<body>
    <table id="prod">
        <thead>
          <tr>
            <th>产品编号</th>
            <th>产品类型</th>
            <th>产品描述</th>
          </tr>
        </thead>
        <tbody id="prodTreeTable">

        </tbody>
    </table>
</body>
</html>