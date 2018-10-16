<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>
<html>
	<head>
		<title>Zookeeper实例管理</title>
		<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/middleware/zkManager.js"></script>
	</head>
	<body>
		<div class="mb-10">
					<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>系统规划</a><span>&gt;</span>中间件规划</a><span>&gt;</span><span>Zookeeper实例管理</span><a
                                                                                href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
				</div>
				<form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
				<div class="row">
						 <label class="form-label span2"><span class="c-red">*</span>集群名称：</label>
						 <div class="span3">
							  <select name="queryMidwareId" id="queryMidwareId" size="1" class="select2" style="margin-top:5px">
							  </select>
						 </div>
						 <div class="span2"></div>
						 <div class="span4">
							  <a  onclick="searchRec()" class="button-select M ml-20 mr-20"><i class="iconfont">&#xe614;</i>查询</a>
						 </div>
				</div>
				</form>
				 <div class="mr-20 ml-20 mt-10">
						<table id="midwareZkList" class="table table-border table-bordered table-hover table-bg table-sort">
							<thead>
							<tr>
								<th>集群名称</th>
								<th>服务器名称</th>
								<th>服务器IP</th>
								<th>ZK实例名称</th>
								<th>ZK实例节点数</th>
								<th>ZK实例客户端口</th>
								<th>ZK实例通信端口</th>
								<th>ZK实例选举端口</th>
								<th>ZK实例描述</th>
							</tr>
							</thead>
						</table>
				 </div>
	</body>
</html>
  