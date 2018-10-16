<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_COMPANY添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmCompanyAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmCompanyAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>公司代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="company" id="company" name="FM_COMPANY" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>公司名称：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="companyName" id="companyName" name="FM_COMPANY" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
