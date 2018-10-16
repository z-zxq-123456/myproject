<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
	request.getSession().setAttribute("ContextPath", basePath);
    String userIp = request.getRemoteHost();
    request.getSession().setAttribute("UserIp", userIp);
%>
<script type="text/javascript">
	var contextPath = "${ContextPath}";
</script>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="${ContextPath}/images/galaxyd.ico" >
<link rel="Shortcut Icon" href="${ContextPath}/images/galaxyd.ico" />

<script type="text/javascript" src="${ContextPath}/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${ContextPath}/lib/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="${ContextPath}/lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="${ContextPath}/lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="${ContextPath}/lib/datatables/1.10.7/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ContextPath}/js/tools/ui-dataTable.js"></script>
<script type="text/javascript" src="${ContextPath}/js/galaxy.js"></script>
<script type="text/javascript" src="${ContextPath}/js/galaxy.admin.js"></script>
<script type="text/javascript" src="${ContextPath}/js/application.js"></script>
<script type="text/javascript" src="${ContextPath}/lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ContextPath}/lib/select2-4.0.2/dist/js/select2.full.min.js"></script>
<script type="text/javascript" src="${ContextPath}/lib/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ContextPath}/lib/lobipanel/lobipanel.min.js"></script>
<script type="text/javascript" src="${ContextPath}/lib/bootstrap/js/jquery.nestable.js"></script>
