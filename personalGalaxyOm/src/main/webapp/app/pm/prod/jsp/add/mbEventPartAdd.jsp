<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_EVENT_PART添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/add/mbEventPartAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbEventPartAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>组件ID：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="assembleId" id="assembleId" name="MB_EVENT_PART" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>属性KEY：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="attrKey" id="attrKey" name="MB_EVENT_PART" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>事件类型：</label>
						<div class="formControls span6">
											<select id="eventType" name="eventType" data-placeholder="事件类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>属性值：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="attrValue" datatype="*0-30" id="attrValue" name="attrValue" tipmsg="格式错误!" >
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
