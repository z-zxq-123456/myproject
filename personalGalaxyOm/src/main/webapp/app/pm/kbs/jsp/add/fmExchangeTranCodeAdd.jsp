<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_EXCHANGE_TRAN_CODE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmExchangeTranCodeAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmExchangeTranCodeAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>升降标志：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="incExpInd" id="incExpInd" name="FM_EXCHANGE_TRAN_CODE" datatype="*1-2" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>结售汇项目编码描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="tranCodeDesc" id="tranCodeDesc" name="FM_EXCHANGE_TRAN_CODE" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>结售汇项目编码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="tranCode" id="tranCode" name="FM_EXCHANGE_TRAN_CODE" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
