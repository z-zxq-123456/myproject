<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>角色添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/js/paraSetting/add/userStepAdd.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-add">
		<div class="row cl">
			 <div class="row">
				<label class="form-label span4"><span class="c-red">*</span>选择用户：</label>
				<div class="formControls span6">
					<select class="select2" size="1" id="userId" name="userId" datatype="*" placeholder="不能为空！" nullmsg="请选择！" tipmsg="格式错误!">
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row">
				<label class="form-label span4"><span class="c-red">*</span>申请权限：</label>
				<div class="formControls span6">
					<select class="select2" size="1" id="authApplication" name="authApplication" datatype="*" disabled>
						<option value="Y">有</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row">
				<label class="form-label span4">录入权限：</label>
				<div class="formControls span6">
					<select class="select2" size="1" id="authEntering" name="authEntering" >
				 		<option value="">请选择</option>
				 		<option value="Y">有</option>
                    	<option value="N">无</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row">
				<label class="form-label span4">复核权限：</label>
				<div class="formControls span6">
					<select class="select2" size="1" id="authCheck" name="authCheck" >
						<option value="">请选择</option>
						<option value="Y">有</option>
						<option value="N">无</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row">
				<label class="form-label span4">发布权限：</label>
				<div class="formControls span6">
					<select class="select2" size="1" id="authPublish" name="authPublish" >
						<option value="">请选择</option>
						<option value="Y">有</option>
						<option value="N">无</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row formBtnArea">
				<div class="span-offset-1 span10 span-offset-1 mt-10">
					<input type="submit" class="button-select L smartButton"  value="添加">
				</div>
			</div>
	</form>
</div>
</body>
</html>
