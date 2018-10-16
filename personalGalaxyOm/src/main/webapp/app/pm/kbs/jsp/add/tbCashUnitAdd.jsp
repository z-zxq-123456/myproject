<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易TB_CASH_UNIT添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/tbCashUnitAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="tbCashUnitAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>券别代号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="parValueId" id="parValueId" name="TB_CASH_UNIT" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>每把张数：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="unitSumB" id="unitSumB" name="TB_CASH_UNIT" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>每捆张数：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="unitSumK" id="unitSumK" name="TB_CASH_UNIT" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>法人：</label>
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
