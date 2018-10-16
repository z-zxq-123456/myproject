<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/js/paraSetting/edit/paraSystemEdit.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-system-edit">
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>目标系统ID：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" id="systemId" placeholder="请输入1-10位字符"  name="systemId" datatype="*1-10" nullmsg="请输入！" tipmsg="格式错误!" disabled>
			</div>
			<div class="span2"></div>
		</div>
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>目标系统名称：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" id="systemName" placeholder="请输入1-10位字符"  name="systemName" datatype="*1-10" nullmsg="请输入！" tipmsg="格式错误!">
			</div>
			<div class="span2"></div>
		</div>
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>目标系统描述：</label>
			<div class="formControls span6">
				<textarea name="" cols="" rows=""  id="systemDesc" name="systemDesc"   style="width:265px" placeholder="请输入0-100位字符" dragonfly="true" datatype="*0-100" nullmsg="请输入!"  tipmsg="格式错误!" onKeyUp="textarealength(this,100)" ></textarea>
                                                <p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
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
