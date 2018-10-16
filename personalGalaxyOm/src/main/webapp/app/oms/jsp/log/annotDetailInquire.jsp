<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>
<%
    String cirId = request.getParameter("cirId");
%>
<html>
	<head>
		<title>注解查看</title>

	</head>
	<body>
        <div class="mr-20 ml-20 mt-10">
            <table id="table_annot_data" class="table table-border table-bordered table-hover table-bg table-sort dataTable-nofooter dataTable-noheader">
                <thead>
                    <tr>
                        <th>时间</th>
                        <th>级别</th>
                        <th>类型</th>
                        <th>类名</th>
                        <th>方法名</th>
                        <th>行数</th>
                        <th>内容</th>
                        <th>线程号</th>
                    </tr>
                </thead>
            </table>
        </div>
	</body>
</html>
<script>
$(document).ready(function () {

        // 获取默认opt配置
        var _annotopt = getDataTableOpt($("#table_annot_data"));

        _annotopt.ajax = {
            "url": contextPath + "/findEcmAnnotDetailInfo?cirId=<%=cirId%>",
            "type": "POST"
        };
        _annotopt.columns = [
            {
                "data": "annotTime",
                "defaultContent": ""
            },
            {
                "data": "annotLev",
                "defaultContent": ""
            },
            {
                "data": "annotType",
                "defaultContent": ""
            },
            {
                "data": "annotCls",
                "defaultContent": ""
            },
            {
                "data": "annotMtd",
                "defaultContent": ""
            },
            {
                "data": "annotRownm",
                "defaultContent": ""
            },
            {
                "data": "annotText",
                "defaultContent": ""
            },
            {
                "data": "annotThreadNum",
                "defaultContent": ""
            }
        ];
        //渲染tables
        setDataTableOpt($("#table_annot_data"), _annotopt);
        //界面美化tables
        $("#table_annot_data").beautyUi({
            tableId: "table_annot_data",
            needBtn: false
        });
        });
</script>