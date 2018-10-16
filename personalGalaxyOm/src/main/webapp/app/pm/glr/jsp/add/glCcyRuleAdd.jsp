<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易GL_CCY_RULE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/add/glCcyRuleAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="glCcyRuleAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>买入币种：</label>
						<div class="formControls span6">
								<select id="buyCcy" name="buyCcy" data-placeholder="买入币种" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="AUD" >AUD-澳大利亚元</option>
										<option value="CAD" >CAD-加拿大元</option>
										<option value="CHF" >CHF-瑞士法郎</option>
										<option value="CNY" >CNY-人民币</option>
										<option value="EUR" >EUR-欧元</option>
										<option value="GBP" >GBP-英镑</option>
										<option value="HKD" >HKD-港元</option>
										<option value="JPY" >JPY-日元</option>
										<option value="SGD" >SGD-新加坡元</option>
										<option value="USD" >USD-美元</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>卖出币种：</label>
						<div class="formControls span6">
								<select id="sellCcy" name="sellCcy" data-placeholder="卖出币种" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="AUD" >AUD-澳大利亚元</option>
										<option value="CAD" >CAD-加拿大元</option>
										<option value="CHF" >CHF-瑞士法郎</option>
										<option value="CNY" >CNY-人民币</option>
										<option value="EUR" >EUR-欧元</option>
										<option value="GBP" >GBP-英镑</option>
										<option value="HKD" >HKD-港元</option>
										<option value="JPY" >JPY-日元</option>
										<option value="SGD" >SGD-新加坡元</option>
										<option value="USD" >USD-美元</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>事件类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="eventType" id="eventType" name="GL_CCY_RULE" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>会计分录编号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="accountingNo" id="accountingNo" name="GL_CCY_RULE" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
