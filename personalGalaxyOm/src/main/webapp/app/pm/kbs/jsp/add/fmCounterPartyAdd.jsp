<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_COUNTER_PARTY添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmCounterPartyAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmCounterPartyAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>对手：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="counterParty" id="counterParty" name="FM_COUNTER_PARTY" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>对手描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="counterPartyDesc" id="counterPartyDesc" name="FM_COUNTER_PARTY" datatype="*1-60" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
