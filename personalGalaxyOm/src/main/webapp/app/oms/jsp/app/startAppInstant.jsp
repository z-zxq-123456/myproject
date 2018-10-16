<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<%
  String appId=request.getParameter("id"); 
  String appName = request.getParameter("name");
%>
<script type="text/javascript" >
 var appId=<%=appId%>;
</script>

<html>
	<head>
		<title>应用实例启动</title>
		<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/startAppInstant.js"></script>
	</head>
	<body class="easyui-layout"> 	
	<div region="center" style="padding: 5px;" border="false">
	 <div id="toolbar" style="padding:5px;height:auto">	
	   <div>		
		   <table class="tableForm datagrid-toolbar" style="height:30px;width:100%">
             <tr>
			   <td >应用名称</td><td> <label><%=appName %></label></td>
			   <td ></td><td></td>
			   <td ></td><td>  </td>
			   <td ></td>
			   <td ></td>
			   <td ></td>
		      </tr>
		    </table>
		</div>		
		<div style="margin-bottom:5px">
		     <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="startApp()">启动</a> 
			 <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="reStartApp()">重启</a> 
			 <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="javascript:$('#table_data').datagrid('reload');">刷新</a>
		</div>
	 </div>
			<table id="table_data" fit="false" fitColumns="true"  url="${ContextPath}/findAppIntant?appId=<%=appId %>" pagination="false" idField="appIntantId"  toolbar="#toolbar" singleSelect="true" 	striped="false" nowrap="false" rownumbers="true">
				<thead>
					<tr>
						<th field="ck" width="10" checkbox="true"></th>
						<th field="serIp" width="10%">
							实例IP
						</th>
						<th field="appIntantName" width="10%">
							实例名称
						</th>
						<th field="appIntantVer" width="7%">
							实例版本号
						</th>
						<th field="appIntantStatusName" width="8%">
							最新操作
						</th>
						<th field="currentAppIntantStatus" width="8%">
							当前状态
						</th>
						<th field="appPath" width="20%">
							安装路径
						</th>
						<th field="appPort" width="5%">
							端口号
						</th>	
						<th field="appWork" width="15%">
							工作目录
						</th>						
					</tr>
				</thead>
			</table>
		</div>	

	   <div id="dlg" class="easyui-dialog" style="width:600px;height:200px;padding:10px 20px"       closed="true" >            
               <div id="progressText"  style="width:400px;"></div>
               <div id="progressDiv"  class="easyui-progressbar" style="width:400px;"></div> 
       </div>      	  
	</body>		  
</html>
<script type="text/javascript">
 $('#table_data').datagrid({
	 onLoadSuccess:errorInfo
 });
 </script>
  