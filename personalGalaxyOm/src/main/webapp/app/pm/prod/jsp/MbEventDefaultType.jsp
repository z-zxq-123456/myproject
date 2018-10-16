<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>MB_EVENT_DEFAULT_TYPE交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/prod/js/MbEventDefaultType.js"></script>
</head>
<body>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="mbEventDefaultType" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>事件类型</th>
                        <th>默认事件类型描述</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
