<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>事件数表页面</title>
    <script type="text/javascript" src="${ContextPath}/app/pf/js/eventTreeTable.js"></script>
    <script type="text/javascript" src="${ContextPath}/lib/treeTable/jquery.treetable.js"></script>
    <link href="${ContextPath}/lib/treeTable/jquery.treetable.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${ContextPath}/lib/treeTable/jquery.treetable.theme.default.css">
</head>
<body>
    <table id="event">
        <thead>
          <tr>
            <th>事件分类</th>
            <th>事件描述</th>
          </tr>
        </thead>
        <tbody id="prodTreeTable">

        </tbody>
    </table>
</body>
</html>