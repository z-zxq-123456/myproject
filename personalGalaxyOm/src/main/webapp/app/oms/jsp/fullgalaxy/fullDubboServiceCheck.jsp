<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>
<html>
	<head>
		<title>服务检查</title>
		 <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/fullgalaxy/fullDubboServiceCheck.js"></script>
	</head>
	<body>
	<div class="mb-10">
	     <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a href="#">服务管理</a><span >&gt;</span><span >服务查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
	</div>
	<form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
    <div class="row">
       <label class="form-label span2"><span class="c-red">*</span>ZK集群名称：</label>
	   <div class="span3">
			  <select name="midwareId" id="midwareId" size="1" class="select2" style="margin-top:5px">
			  </select>
	   </div>
	</div>
	<div class="row">
	    <label class="form-label span2">zookeeper地址是:</label> <label class="form-label" id="zkName"></label>
	</div>
	</form>
    <div class="mr-20 ml-20 mt-10">
 			<table id="serviceList" class="table table-border table-bordered table-hover table-bg table-sort">
 				<thead>
 				<tr>
 					<th>服务方</th>
 					<th>IP地址</th>
 					<th>服务名称</th>
 				</tr>
 				</thead>
 			</table>
 	  </div>
	</body>		  
</html>
<script>
function loadTable(midwareValue){
       $.post('${ContextPath}/readZkUrl',{midwareId:midwareValue},function(result){
		var result = eval('('+result+')');
		if (result.errorMsg){
		      showMsg(result.errorMsg,'error');
		  } else{
			  $('#zkName').html(result);
			   var  serviceTab = $("#serviceList").dataTable();
			   var  serviceApi = serviceTab.api();
			   serviceApi.ajax.url( contextPath + "/findDubboCheck?midwareId="+$("#midwareId").val()).load();
		  }
	   });
}
</script>
 

  