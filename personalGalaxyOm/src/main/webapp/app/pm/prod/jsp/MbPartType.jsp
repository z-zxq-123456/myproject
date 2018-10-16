<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>MB_PART_TYPE交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/prod/js/MbPartType.js"></script>
</head>
<body>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="mbPartType" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>指标类型</th>
                        <th>是否标准模板</th>
                        <th>指标分类</th>
                        <th>组件描述</th>
                        <th>所属分类</th>
                        <th>默认指标</th>
                        <th>处理类型</th>
                        <th>状态</th>
                        <th>所属法人</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
