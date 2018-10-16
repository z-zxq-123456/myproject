 <%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<%
String appId=request.getParameter("id"); 
String appName = request.getParameter("name");
try {  
	  if(appName!=null){
	  appName=java.net.URLDecoder.decode(appName,"utf-8"); 
	  }
	  }catch (Exception e) {  
	  e.printStackTrace();  
	  }
%>
<style scoped="scoped">
		.tt-inner{
			display:inline-block;
			line-height:12px;
			padding-top:5px;			
		}
		.tt-inner img{
			border:0;
			vertical-align:middle;
		}
</style>
<script type="text/javascript" >
var appId=<%=appId%>;
var appName="<%=appName%>";
</script>
<html>
	<head>
		<title>不间断升级管理</title>
		<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/uninterruptedUpdate.js"></script>
		
	</head>
	<body class="easyui-layout"> 
	<div id="dlg" class="easyui-panel" style="width:100%;height:90%;padding:10px" closed="false" buttons="#addBtn">
          <div   id = "addTab"  fit="true"  data-options="tabWidth:190,tabHeight:35"></div>
    </div>
    <div id="addBtn" class="easyui-panel" style="width:100%;height:10%;padding:10px"
          closed="false"></div>
</body>	
</html>
