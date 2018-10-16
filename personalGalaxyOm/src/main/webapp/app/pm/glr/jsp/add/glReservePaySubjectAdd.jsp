<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易GL_RESERVE_PAY_SUBJECT添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/add/glReservePaySubjectAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="glReservePaySubjectAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>缴存科目：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="payGlCode" id="payGlCode" name="GL_RESERVE_PAY_SUBJECT" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>准备金类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="payType" id="payType" name="GL_RESERVE_PAY_SUBJECT" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>缴存比例：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="countRate" id="countRate" name="countRate" datatype="/^([0-9][0-9]*)+(.[0-9]{1,4})?$/"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>余额汇总方式：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="sumBalFlag" datatype="*0-1" id="sumBalFlag" name="sumBalFlag" tipmsg="格式错误!" >
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
