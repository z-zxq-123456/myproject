<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>MB_PROD_TYPE交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/prod/js/MbProdType.js"></script>
</head>
<body>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="mbProdType" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>产品类型</th>
                        <th>产品分类</th>
                        <th>产品类型描述</th>
                        <th>状态</th>
                        <th>基础产品类型</th>
                        <th>是否产品组</th>
                        <th>产品作用范围</th>
                        <th>所属法人</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>