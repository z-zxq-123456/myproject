<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>Sm@rtLi-Market-利率市场化</title>
</head>
<body>
    <div class="mr-20 ml-20 mt-10" >
        <table id="vaildInfoList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
                <tr>
                    <th>服务名称</th>
                    <th>服务类名</th>
                    <th>路由参数位置</th>
                    <th>字段中文名</th>
                    <th>条件值</th>
                    <th>条件名称</th>
                    <th>自定义规则</th>
                    <th>验证规则类型</th>
                </tr>
            </thead>
        </table>
    </div>
</body>
</html>
<script type="text/javascript">
$(document).ready(function() {
        var opt = getDataTableOpt($("#vaildInfoList"));
        var row = parent.$('#upgViewList').DataTable().rows(".selected").data()[0];
        opt.stateSave=true;
        opt.processing=true;
        opt.autoWidth=false;
        opt.ajax= {
             "url":  contextPath + "/findAllRule?appUpgId="+row.appUpgId,
             "type": "POST"
         };
        opt.columns=[
            { "data": "appSerName"},
            { "data": "appSerClsnm"},
            { "data": "routerArgsPos"},
            { "data": "routerColCn"},
            { "data": "routerCondVal"},
            { "data": "routerCondName"},
            { "data": "servRuleSelf"},
            { "data": "appValruleTypeName"},
        ];
        //渲染tables
        drawDataTable($("#vaildInfoList"),opt);
        $("#vaildInfoList").beautyUi({
             	    tableId:"vaildInfoList"
        });
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
});
</script>
