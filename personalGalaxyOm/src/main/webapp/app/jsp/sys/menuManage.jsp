<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
	<title>菜单管理</title>
    <link rel="stylesheet" type="text/css" href="${ContextPath}/lib/bootstrap/css/jquery.nestable.css" />
	<script src="${ContextPath}/lib/bootstrap/js/jquery.nestable.js"></script>
    <link href="${ContextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${ContextPath}/lib/lobipanel/lobipanel.min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/js/sys/menuManage.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a href="#">权限管理</a><span >&gt;</span><span >菜单管理</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <div class="cl padding-10 bg-gray-e">
	 	<span class="l">
			<a onclick="menuManageAdd()" class="button-add S "><i class="iconfont">&#xe767;</i>添加菜单</a>
	 		<a onclick="menuManageEdit()" class="button-edit S "><i class="iconfont">&#xe760;</i>修改菜单</a>
            <a onclick="buttonManageAdd()" class="button-sure S "><i class="iconfont">&#xe767;</i>屏蔽按钮</a>
			<a href="#" onclick="refresh()" class="button-edit S "><i class="iconfont">&#xe644;</i>刷新</a>
	 	</span>
    </div>
	<div class="mt-20">
        <div class="panel panel-danger">
           <div class="panel-heading">
               <div class="panel-title">
                   <span>菜单列表</span>
               </div>
           </div>
           <div class="panel-body" id="nestableMenuList">


           </div>
        </div>
   </div>
</div>
</body>
</html>


