<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>
<html>
	<head>
		<title>路由字段</title>
		<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/serv/routerColManager.js"></script>
	</head>
	<body>
		 <div class="mb-10">
	 <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>基础配置</a><span>&gt;</span><span>应用升级路由规则</span><span>&gt;</span><span>路由字段管理</span><a
                href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
			</div>
		 <div class="mr-20 ml-20 mt-10">
				<table id="routerColList" class="table table-border table-bordered table-hover table-bg table-sort">
					<thead>
						<tr>
							<th>字段中文名</th>
							<th>字段代码名</th>
							<th>字段类型</th>
						</tr>
					</thead>
				</table>
		 </div>
	</body>
</html>
