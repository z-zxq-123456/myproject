<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_REASON_CODE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/mbReasonCodeAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbReasonCodeAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>原因代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="reasonCode" id="reasonCode" name="MB_REASON_CODE" datatype="*1-6" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>原因代码描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="reasonCodeDesc" id="reasonCodeDesc" name="MB_REASON_CODE" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>模块：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="sourceMode" datatype="*0-10" id="sourceMode" name="sourceMode" tipmsg="格式错误!" >
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
