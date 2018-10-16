<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易GL_ACCT_TYPE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/add/glAcctTypeAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="glAcctTypeAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>账户类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="acctType" id="acctType" name="GL_ACCT_TYPE" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>账户类型描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="acctTypeDesc" id="acctTypeDesc" name="GL_ACCT_TYPE" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>存款类型：</label>
						<div class="formControls span6">
								<select id="depositType" name="depositType" data-placeholder="存款类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="S" >S-储蓄</option>
										<option value="C" >C-往来</option>
										<option value="T" >T-定期</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>资产负债共同类科目：</label>
						<div class="formControls span6">
											<select id="glCodeAl" name="glCodeAl" datatype="*" class="select2" tabindex="4" tipmsg="格式错误!">
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
