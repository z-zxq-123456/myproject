<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易IRL_FEE_PACKAGE_MAP添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/add/irlFeePackageMapAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="irlFeePackageMapAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>套餐代码：</label>
						<div class="formControls span6">
											<select id="packageId" name="packageId" data-placeholder="套餐代码" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>费率类型：</label>
						<div class="formControls span6">
											<select id="feeType" name="feeType" data-placeholder="费率类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
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
