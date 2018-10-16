<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>生成参数平台代码</title>
<script type="text/javascript" src="${ContextPath}/app/pm/js/tools/createParameterCode.js"></script>
</head>
<body>
<div class="mb-10">
<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i> 系统首页 <span>&gt;</span><a href="#"> 开发辅助 </a><span>&gt;</span> <span>生成参数平台代码</span><a   href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
	<form action="" method="post" class="form form-horizontal" id="createParameter">

		<div class="row cl">
    			<label class="form-label span3"><span class="c-red">*</span>选择表：</label>
    			<div class="formControls span5">
    				<select id="table" name="table" data-placeholder="选择表..." class="select2" multiple="multiple" tabindex="4" datatype="*" nullmsg="请选择表"></select>
    			</div>
    			<div class="span4"></div>
    		</div>
		<div class="row cl">
			<label class="form-label span3"><span class="c-red">*</span>JSP包路径：</label>
			<div class="formControls span5">
				<input type="text" class="input-text grid" style="width:428px" value="com.dcits.ensemble.om.pm.app.parameter"  id="jspPackage" name="jspPackage" disabled=false >
			</div>
		  	<div class="span4"></div>
		</div>

			<div class="row cl">
        		<label class="form-label span4"></label>
        			<div class="span4 span-setoff-1 show">
				<input class="button-check L active" type="submit"   value="生成代码">&nbsp;
				</div>

		</div>
	</form>
</div>
</body>
</html>

