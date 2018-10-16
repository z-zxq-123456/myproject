<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>

<%
    String traceId = request.getParameter("traceId");
%>
<html>
	<head>
	    <title>调用链跟踪</title>
	    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/log/chainDetailsInquire.js"></script>
	    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/log/logCircleView.js"></script>
	    <style type="text/css">
        .OrgBox{
        	font-size:12px;
        	padding:5px 5px 5px 5px;
        	clear:left;
        	float:left;
        	text-align:center;
        	position:absolute;
        	background-image:url(http://www.on-cn.cn/tempimg/org.jpg);
        	width:70px;
        	height:106px;
        }
        .OrgBox img{
        	width:60px;
        	height:70px;
        }
        .OrgBox div{
        	color:#FFA500;
        	font-weight:800;
        }
        .div-height{border:1px ; min-height:250px}

        </style>
	</head>
	<body>
        <div  class="div-height">
        <tr scope="col" colspan="7">调用链视图</tr>

        </div>
	</body>
</html>
<script>
var traceId =  "<%=traceId%>";

</script>
