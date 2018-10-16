<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>
<html>
	<head>
		<title>Redis实例</title>
		<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/middleware/midwareRedisint.js"></script>
	</head>
	<body>
		<div class="mb-10">
                     <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a href="#">系统管理</a><span >&gt;</span><span >中间件配置</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
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
                        <table id="midwareRedisList" class="table table-border table-bordered table-hover table-bg table-sort">
                            <thead>
                            <tr>
                                <th>集群名称</th>
                                <th>Redis实例名称</th>
                                <th>服务器IP</th>
                                <th>Redis实例类型</th>
                                <th>Redis实例端口</th>
								<th>Redis实例节点数</th>
								<th>Redis实例使用内存</th>
								<th>Redis实例描述</th>
                            </tr>
                            </thead>
                        </table>
                 </div>
	</body>
</html>
