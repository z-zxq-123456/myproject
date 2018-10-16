<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易CIF_ECON_DIST添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/cifEconDistAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="cifEconDistAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>经济特区：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="econDist" id="econDist" name="CIF_ECON_DIST" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="econDistDesc" id="econDistDesc" name="CIF_ECON_DIST" datatype="*1-60" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>法人代码：</label>
						<div class="formControls span6">
											<select id="company" name="company"  class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
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
