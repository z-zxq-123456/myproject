<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易IRL_RISK_SETING添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/add/irlRiskSetingAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="irlRiskSetingAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>序号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="irlSeqNo" id="irlSeqNo" name="IRL_RISK_SETING" datatype="*1-32" nullmsg=" 请输入！"  tipmsg="格式错误!" ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
						<div class="formControls span6">
											<select id="prodType" name="prodType" data-placeholder="产品类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>币种：</label>
						<div class="formControls span6">
											<select id="ccy" name="ccy" data-placeholder="币种" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>余额：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="balance" id="balance" name="IRL_RISK_SETING" datatype="/^([0-9][0-9]*)+(.[0-9]{1,4})?$/" nullmsg=" 请输入！"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>频率类型：</label>
						<div class="formControls span6">
											<select id="periodFreq" name="periodFreq" datatype="*0-21" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>每期天数：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="dayNum" datatype="*0-5" id="dayNum" name="dayNum" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>浮动点数下限：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="minSpreadRate" datatype="n0-1|/^([0-9][0-9]*)+(.[0-9]{1,4})?$/" id="minSpreadRate" name="minSpreadRate" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>浮动点数上限：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="maxSpreadRate" datatype="n0-1|/^([0-9][0-9]*)+(.[0-9]{1,4})?$/" id="maxSpreadRate" name="maxSpreadRate" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>浮动百分比下限：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="minSpreadPercent" datatype="n0-1|/^([0-9][0-9]*)+(.[0-9]{1,4})?$/" id="minSpreadPercent" name="minSpreadPercent" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>浮动百分比上限：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="maxSpreadPercent" datatype="n0-1|/^([0-9][0-9]*)+(.[0-9]{1,4})?$/" id="maxSpreadPercent" name="maxSpreadPercent" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>行内利率下限：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="minBranchRate" datatype="n0-1|/^([0-9][0-9]*)+(.[0-9]{1,4})?$/" id="minBranchRate" name="minBranchRate" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>

					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>行内利率上限：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="maxBranchRate" datatype="n0-1|/^([0-9][0-9]*)+(.[0-9]{1,4})?$/" id="maxBranchRate" name="maxBranchRate" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
					<label class="form-label span4"><span class="c-red"></span>执行利率下限：</label>
							<div class="formControls span6">
												<input type="text" class="input-text grid" value="" placeholder="minIntRate" datatype="n0-1|/^([0-9][0-9]*)+(.[0-9]{1,4})?$/" id="minIntRate" name="minIntRate" tipmsg="格式错误!" >
							</div>
							<div class="span2"> </div>
						</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>执行利率上限：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="maxIntRate" datatype="n0-1|/^([0-9][0-9]*)+(.[0-9]{1,4})?$/" id="maxIntRate" name="maxIntRate" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>

					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>法人代码：</label>
						<div class="formControls span6">
											<select id="company" name="company" datatype="*0-20" class="select2" tabindex="4" tipmsg="格式错误!">
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


