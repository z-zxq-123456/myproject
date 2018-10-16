<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户角色修改</title>
<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/js/sys/userRoleEdit.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-userRole-edit">
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>用户名称：</label>
			<div class="formControls span6">
				<select class="select2" size="1" id="userName" name="userName" datatype="*" placeholder="不能为空！" nullmsg="请选择！" tipmsg="格式错误!" disabled=true;>
				</select>
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>角色名称：</label>
			<div class="formControls span6">
				<select class="select2" size="1" id="roleName" name="roleName" datatype="*" placeholder="不能为空！" nullmsg="请选择！" tipmsg="格式错误!">
				</select>
			</div>
			<div class="span2"> </div>
		</div>
		    	<div class="row formBtnArea">
            			<div class="span-offset-1 span10 span-offset-1 mt-10">
            				<input type="submit" class="button-select L smartButton" title="修改"  value="修改">
            			</div>
            		</div>
	</form>
</div>
</body>
</html>
