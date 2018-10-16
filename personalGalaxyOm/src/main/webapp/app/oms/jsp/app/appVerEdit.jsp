<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
	<title>用户修改</title>
<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/appVerEdit.js"></script>
</head>
<body>
<hidden id="appId" value=""></hidden>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-user-edit">
		<div class="fitem" style="display:none">
			<label>应用版本ID:</label>
			<input name="appVerId" id="appVerId">
		</div>
		<div class="row" >
			<label class="form-label span4">版本保存路径：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" name="appVerPath" id="appVerPath" disabled="disabled">
			</div>
		</div>
		<div class="row">
			<label class="form-label span4">版本创建日期：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" name="appVerDate" id="appVerDate" disabled="disabled">
			</div>
		</div>
		<div class="row">
			<label class="form-label span4">版本描述：</label>
			<div class="formControls span6">
				<input type="text"  class="input-text grid" placeholder="版本描述" name="appVerDesc" id="appVerDesc" datatype="*1-60" nullmsg="版本描述">
			</div>
		</div>
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>版本类型：</label>
			<div class="formControls span6">
				<select type="text" class="select2" placeholder="版本类型" name="appVerType" id="appVerType" datatype="*1-10" nullmsg="应用类型"></select>
			</div>
		</div>
		<div class="row">
			<div class="span5"> </div>
			<input type="submit" class="button-select M" title="提交"  value="提交">&nbsp;
			<a onclick="dataEditCancel()" class="button-cancle M" title="关闭" >关闭</a>
		</div>
	</form>
</div>
</body>
</html>
