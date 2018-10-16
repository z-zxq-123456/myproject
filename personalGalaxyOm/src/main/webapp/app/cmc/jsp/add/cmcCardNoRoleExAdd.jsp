<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
	<title>交易CMC_CARD_NO_ROLE_EX添加</title>
	<script type="text/javascript" src="${ContextPath}/app/cmc/js/add/cmcCardNoRoleExAdd.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="cmcCardNoRoleExAdd">
		<div class="row cl">
			<label class="form-label span4"><span class="c-red">*</span>卡产品编号：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" value="" placeholder="卡产品编号" id="paramName" name="CMC_CARD_NO_ROLE_EX" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!">
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row cl">
			<label class="form-label span4"><span class="c-red">*</span>制卡渠道编号：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" value="" placeholder="卡产品编号" id="paramValue" name="CMC_CARD_NO_ROLE_EX">
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row">
			<div class="span-offset-1 span10 span-offset-1 mt-10">
				<input type="submit" class="button-select L smartButton" title="添加"  value="添加">
			</div>
		</div>
	</form>
</div>
</body>
</html>
