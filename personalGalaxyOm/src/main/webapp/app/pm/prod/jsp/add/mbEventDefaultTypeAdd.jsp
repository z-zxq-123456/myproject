<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_EVENT_DEFAULT_TYPE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/add/mbEventDefaultTypeAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbEventDefaultTypeAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>事件类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="eventDefaultType" id="eventDefaultType" name="MB_EVENT_DEFAULT_TYPE" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>默认事件类型描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="eventDefaultDesc" id="eventDefaultDesc" name="MB_EVENT_DEFAULT_TYPE" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
