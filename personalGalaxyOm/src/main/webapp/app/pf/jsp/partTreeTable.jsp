<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>指标数表格页面</title>
    <script type="text/javascript" src="${ContextPath}/app/pf/js/partTreeTable.js"></script>
    <script type="text/javascript" src="${ContextPath}/lib/treeTable/jquery.treetable.js"></script>
    <link href="${ContextPath}/lib/treeTable/jquery.treetable.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${ContextPath}/lib/treeTable/jquery.treetable.theme.default.css">
</head>
<body>
    <table id="part">
        <thead>
          <tr>
            <th>指标分类</th>
            <th>指标描述</th>
          </tr>
        </thead>
        <tbody id="partTreeTable">

        </tbody>
    </table>
</body>
</html>