<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
	<title>角色添加</title>
	<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/redis/redisServAdd.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-redisServ-add">
		<div class="row cl">
			<div class="row">
				<label class="form-label span4"><span class="c-red">*</span>服务器IP：</label>
				<div class="formControls span6">
					<input type="text" class="input-text" id="serverIp"  name="serverIp" datatype="*1-20"  placeholder="请输入1-20位字符！" nullmsg="请输入IP信息！" tipmsg="格式错误!">
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row">
				<label class="form-label span4"><span class="c-red">*</span>服务器端口</label>
				<div class="formControls span6">
					<input type="text" class="input-text" id="serverPort"  name="serverPort" datatype="n4-5"  placeholder="请输入4-5位数字！" nullmsg="请输入数字！" tipmsg="格式错误!">
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row ">
				<div class="span4 "> </div>
				<input type="submit" class="button-select M" title="提交"  value="提交">&nbsp;
				<a onclick="appInfoAddCancel()" class="button-cancle M" title="关闭" >关闭</a>
			</div>
		</div>
	</form>
</div>
</body>
</html>
