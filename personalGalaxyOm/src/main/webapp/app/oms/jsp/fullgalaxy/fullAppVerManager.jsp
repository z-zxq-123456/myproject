<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>

<html>
	<head>
		<title>全量版本管理</title>
		<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/fullgalaxy/fullAppVerManager.js"></script>
	</head>
	<body>
	<div class="mb-10">
		 <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a href="#">一键部署</a><span >&gt;</span><span >全量应用版本</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
	</div>
	<form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
	<div class="row">
			 <label class="form-label span2"><span class="c-red">*</span>应用名称：</label>
			 <div class="span3">
				  <select name="queryAppId" id="queryAppId" size="1" class="select2" style="margin-top:5px">
				  </select>
			 </div>
			 <div class="span2"></div>
			 <div class="span4">
				  <a  onclick="searchRec()" class="button-select M ml-20 mr-20"><i class="iconfont">&#xe614;</i>查询</a>
			 </div>
	</div>
	</form>
	<div class="mr-20 ml-20 mt-10">
			<table id="appVerList" class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
				<tr>
                    <th>应用版本Id</th>
                    <th>应用名称</th>
					<th>应用简称</th>
					<th>版本号</th>
					<th>创建日期</th>
					<th>创建人</th>
					<th>保存路径</th>
					<th>版本描述</th>
					<th>版本类型</th>
				</tr>
				</thead>
			</table>
	 </div>
	</body>
</html>
  