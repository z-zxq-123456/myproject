<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_EXCHANGE_TRAN_TYPE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/mbExchangeTranTypeAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbExchangeTranTypeAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>交易类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="tranType" id="tranType" name="MB_EXCHANGE_TRAN_TYPE" datatype="*1-4" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>结售汇类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="exType" datatype="*0-1" id="exType" name="exType" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>操作类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="opType" datatype="*0-2" id="opType" name="opType" tipmsg="格式错误!" >
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
