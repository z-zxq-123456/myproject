<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>角色菜单授权</title>
    <link rel="stylesheet" type="text/css" href="${ContextPath}/lib/bootstrap/css/jquery.nestable.css" />
    <link href="${ContextPath}/lib/jquery/jquery-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="${ContextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${ContextPath}/lib/lobipanel/lobipanel.min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/js/sys/roleMenuAccredit.js"></script>
</head>
<body>
	<div class="mb-10">
        <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a href="#">权限管理 </a><span >&gt;</span><span >角色菜单授权</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
	</div>
	<div class="row cl span-offset-1 span12 span-setoff-1 mb-10" >
        <select  id="roleId" name="roleId" class="select2" style="width:20%;" size="1" ></select>
        <a href="javascript:;" onclick="save()" class="button-del S "><i class="iconfont">&#xe629;</i> 保存</a>
    </div>
    <div class="row cl">
        <div class="span-offset-1 span5">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title">
                        <span>角色菜单列表</span>
                    </div>
                </div>
                <div class="panel-body" id="nestableOrginal">

                </div>
            </div>
        </div>
        <div class="span5 span-setoff-1">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <div class="panel-title">
                        <span>网站菜单列表</span>
                    </div>
                </div>
                <div class="panel-body" id="nestableOverplus">

                </div>
            </div>
        </div>
    </div>
</body>
</html>

