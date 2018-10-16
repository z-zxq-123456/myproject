<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>Redis主键修改</title>
	<script type="text/javascript" src="${ContextPath}/app/oms/js/middleware/redisKeyEdit.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-redisKey-edit">
		<div class="row cl">
			<label class="form-label span4"><span class="c-red">*</span>Redis服务器的名称：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" name="masterName" id="masterName" disabled=true>
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row cl">
			<label class="form-label span4"><span class="c-red">*</span>Redis地址：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" name="redisUrl" id="redisUrl" disabled=true>
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row cl">
			<label class="form-label span4"><span class="c-red">*</span>Redis主键名：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" name="seqKey" id="seqKey" class="input-text size-MINI" datatype="*1-100" placeholder="请输入1-100位字符！" nullmsg="请输入！" tipmsg="格式错误!" >
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row cl">
			<label class="form-label span4">Redis主键值：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid"  name="seqValue" id="seqValue"  datatype="*1-40" placeholder="请输入1-40位字符！" nullmsg="请输入！" tipmsg="格式错误!">
			</div>
			<div class="span2"> </div>
		</div>

			<div class="row">
    			<div class="span-offset-1 span10 span-offset-1 mt-10">
    				<input type="submit" class="button-select L smartButton"  value="修改">
    			</div>
    		</div>
	</form>
</div>
</body>
</html>