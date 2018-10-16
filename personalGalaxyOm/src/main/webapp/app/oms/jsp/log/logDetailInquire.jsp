<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>
<%
    String traceId = request.getParameter("traceId");
%>
<html>
	<head>
		<title>交易日志查看</title>
<style type="text/css" class="init">
                td.details-control {
                    background: url('${ContextPath}/images/icon-jia.jpg') no-repeat center center;
                    cursor: pointer;
                }
                 td.details-control1 {
                    background: url('${ContextPath}/images/dataTable/icon-search.png') no-repeat center center;
                    cursor: pointer;
                }
                  td.details-control2 {
                     background: url('${ContextPath}/images/dataTable/icon-search.png') no-repeat center center;
                     cursor: pointer;
                }
                tr.shown td.details-control {
                    background: url('${ContextPath}/images/icon-jian.jpg') no-repeat center center;
                }

                tableCss { width: 100%; empty-cells: show; background-color: transparent; border-collapse: separate; border-spacing: 0; font-family: Microsoft Yahei; }
                tableCss th { text-align: left; font-weight: 400; }
                tableCss tr {border-left:2px solid #c3ced9;border-right: 2px solid #c3ced9;}
                .tableCss th { padding: 8px 19px 8px 8px; line-height: 20px; font-size: 14px; color: #000000; }
                .tableCss td { font-weight: 400; text-align: left; line-height: 30px; padding-left: 8px; font-size: 13px; color: #4d4d4d; }
                .tableCss tbody tr.success > td { background-color: #dff0d8; }
                .tableCss tbody tr.error > td { background-color: #f2dede; }
                .tableCss tbody tr.warning > td { background-color: #fcf8e3; }
                .tableCss tbody tr.info > td { background-color: #d9edf7; }
                .tableCss tbody + tbody { border-top: 2px solid #ddd; }
                .tableCss .table { background-color: #fff; }
            </style>
	</head>
	<body>
        <div class="mr-20 ml-20 mt-10">
            <table id="table_annot_data" style="background-color: #FDF7E6" class="table table-border table-bordered table-hover table-bg table-sort dataTable-nofooter dataTable-noheader">
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
<script  type="text/javaScript">
$(document).ready(function () {
var statusMark="0015002";//失败参数
var colorFail="#FF8888";//失败背景色
        // 获取默认opt配置
        var _annotopt = getDataTableOpt($("#table_annot_data"));

        _annotopt.ajax = {
            "url": contextPath + "/findTransactionLogInfo?traceId=<%=traceId%>",
            "type": "POST"
        };
        _annotopt.rowCallback = function(row,data,index){
                   row.style.backgroundColor=colorFail;
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
                "data": "annotTypeName",
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

   $("#table_annot_data  td.details-control").each(function(){//循环table的每行（tr）
           var tr = $(this).closest('tr');
           var row = table.row( tr );
    });
  });

</script>