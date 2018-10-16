<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易IRL_FEE_ITEM添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/add/irlFeeItemAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="irlFeeItemAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>费用项目代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="feeItem" id="feeItem" name="IRL_FEE_ITEM"  datatype="*1-10" nullmsg=" 请输入！" tipmsg="格式错误!" ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>费用项目描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="feeItemDesc" id="feeItemDesc" name="IRL_FEE_ITEM" datatype="*1-150" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
