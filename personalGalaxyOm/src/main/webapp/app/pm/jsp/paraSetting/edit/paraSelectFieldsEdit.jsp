<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/js/paraSetting/edit/paraSelectFieldsEdit.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-select-edit">
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>交易名：</label>
			<div class="formControls span6">
				<select class="select2" size="1" id="tableName" name="tableName" datatype="*1-32" placeholder="不能为空！" nullmsg="请选择！" tipmsg="格式错误!" disabled="true";>
				</select>
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>查询条件一：</label>
			<div class="formControls span6">
				<select class="select2" size="1" id="select1" name="select1" datatype="*1-32" placeholder="不能为空！" nullmsg="请选择！" tipmsg="格式错误!">
				</select>
			</div>
			<div class="span2"></div>
		</div>
		<div class="row">
			<label class="form-label span4">查询条件二：</label>
			<div class="formControls span6">
				<select class="select2" size="1" id="select2" name="select2" datatype="*0-32" nullmsg="请选择！" tipmsg="格式错误!">
				</select>
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row">
			<label class="form-label span4">查询条件三：</label>
			<div class="formControls span6">
				<select class="select2" size="1" id="select3" name="select3" datatype="*0-32" nullmsg="请选择！" tipmsg="格式错误!">
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
