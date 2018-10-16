<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>Sm@rtLi-Market-利率市场化</title>
</head>
<body>
    <div class="mr-20 ml-20 mt-10" >
        <table id="transferInfoList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
                <tr>
                    <th>流程节点名</th>
                    <th>服务类名</th>
                    <th>操作时间</th>
                    <th>操作人</th>
                </tr>
            </thead>
        </table>
    </div>
</body>
</html>
<script type="text/javascript">
$(document).ready(function() {
        var opt = getDataTableOpt($("#transferInfoList"));
        var row = parent.$('#upgViewList').DataTable().rows(".selected").data()[0];
        opt.stateSave=true;
        opt.processing=true;
        opt.autoWidth=false;
        opt.ajax= {
             "url": contextPath + "/findAppUpgfor?appUpgId="+row.appUpgId,
             "type": "POST"
         };
        opt.columns=[
            { "data": "upgflowNodeName"},
            { "data": "upgflowNodeUrl"},
            { "data": "appUpgforTime"},
            { "data": "userName"}
        ];
        //渲染tables
        drawDataTable($("#transferInfoList"),opt);
        //界面美化ables
        $("#transferInfoList").beautyUi({
                   tableId:"transferInfoList"
        });
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
});
</script>
