<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易GL_AMOUNT_TYPE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/add/glAmountTypeAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="glAmountTypeAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>金额类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="amountType" id="amountType" name="GL_AMOUNT_TYPE" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>金额类型描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="amountTypeDesc" id="amountTypeDesc" name="GL_AMOUNT_TYPE" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
