<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<%
String appId=request.getParameter("id");
String appName = request.getParameter("name");
%>
<script type="text/javascript" >
var appId=<%=appId%>;
</script>
<html>
<head>
	<title>后升级服务路由规则</title>
	<script type="text/javascript" charset="UTF-8" src="${ContextPath}/app/oms/js/app/appLateServRule.js"></script>
</head>
<body>

<div class="padding-10">
	<input type="hidden" id="appId" value="<%=appId%>">
	<div class="mr-20 ml-20 mt-10">
		<table id="lateServRuleList"
        					class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
			<tr>
				<th>
					服务名称
				</th>
				<th>
					服务类名
				</th>
				<th>
					路由参数位置
				</th>
				<th>
					字段中文名
				</th>
				<th>
					条件值
				</th>
				<th>
					条件名称
				</th>
				<th>
					自定义规则
				</th>
			</tr>
			</thead>
		</table>
	</div>
</div>
</body>
</html>
