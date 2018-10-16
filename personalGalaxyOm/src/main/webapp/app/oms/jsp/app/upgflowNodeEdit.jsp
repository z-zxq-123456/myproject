<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
	<title>用户修改</title>
<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/upgflowNodeEdit.js"></script>
</head>
<body>
<hidden id="upgflowNodeId" value=""></hidden>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-data-edit">
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>流程节点名：</label>
			<div class="formControls span6">
				<input type="text" class="input-text" id="upgflowNodeName"  name="upgflowNodeName" datatype="*1-20"  placeholder="请输入1-20位字符！" nullmsg="请输入！" tipmsg="格式错误!">
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>流程节点序号：</label>
			<div class="formControls span6">
				<input type="text" class="input-text" id="upgflowNodeSeq"  name="upgflowNodeSeq" datatype="*1-20"  placeholder="请输入1-20位字符！" nullmsg="请输入！" tipmsg="格式错误!">
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>流程节点url：</label>
			<div class="formControls span6">
				<input type="text" class="input-text" id="upgflowNodeUrl"  name="upgflowNodeUrl" datatype="*1-100"  placeholder="请输入1-100位字符！" nullmsg="请输入！" tipmsg="格式错误!">
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row">
			<div class="span4"> </div>
			<input type="submit" class="button-select M" title="提交"  value="提交">&nbsp;
			<a onclick="dataEditCancel()" class="button-cancle M" title="关闭" >关闭</a>
		</div>
	</form>
</div>
</body>
</html>