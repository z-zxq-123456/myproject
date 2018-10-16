<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>
<html>
	<head>
		<title>galaxy全量部署</title>
		<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/fullgalaxy/fullAppInstantView.js"></script>
	</head>
	<body>
	<div class="mb-10">
		 <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a href="#">全量galaxy</a><span >&gt;</span><span >全量查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
	</div>
	  <div class="mr-20 ml-20 mt-10">
				<table id="instantViewList" class="table table-border table-bordered table-hover table-bg table-sort">
					<thead>
					<tr>
						<th>应用名称</th>
						<th>实例名称</th>
						<th>实例IP</th>
						<th>实例版本号</th>
						<th>最新操作</th>
						<th>当前状态</th>
					    <th>健康信息</th>
						<th>安装路径</th>
						<th>工作目录</th>
					</tr>
					</thead>
				</table>
		 </div>
	</body>		  
</html>
 

  