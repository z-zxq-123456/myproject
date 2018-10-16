<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_PROD_CHARGE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/add/mbProdChargeAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbProdChargeAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>费率类型：</label>
						<div class="formControls span6">
											<select id="feeType" name="feeType" data-placeholder="费率类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="prodType" id="prodType" name="MB_PROD_CHARGE" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>下一收取日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="nextChargeDate" id="nextChargeDate" name="MB_PROD_CHARGE" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>收取频率：</label>
						<div class="formControls span6">
											<select id="chargePeriodFreq" name="chargePeriodFreq" data-placeholder="收取频率" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>收取日：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="chargeDay" id="chargeDay" name="chargeDay" datatype="n0-2"  tipmsg="格式错误!">
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