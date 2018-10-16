<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>MB_EVENT_LINK交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/prod/js/MbEventLink.js"></script>
</head>
<body>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="mbEventLink" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>链接事件类型</th>
                        <th>关联产品类型</th>
                        <th>源事件类型</th>
                        <th>源产品类型</th>
                        <th>产品类型</th>
                        <th>关联条件</th>
                        <th>状态</th>
                        <th>所属法人</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
