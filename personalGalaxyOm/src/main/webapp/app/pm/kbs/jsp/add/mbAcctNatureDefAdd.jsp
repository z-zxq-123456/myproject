<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_ACCT_NATURE_DEF添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/mbAcctNatureDefAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbAcctNatureDefAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>账户属性：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="acctNature" id="acctNature" name="MB_ACCT_NATURE_DEF"  datatype="*1-4" nullmsg=" 请输入！" tipmsg="格式错误!" ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>属性描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="description" id="description" name="MB_ACCT_NATURE_DEF" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>法人代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="company" datatype="*0-20" id="company" name="company" tipmsg="格式错误!" >
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
