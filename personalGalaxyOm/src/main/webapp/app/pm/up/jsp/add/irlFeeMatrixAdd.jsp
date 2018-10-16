<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易IRL_FEE_MATRIX添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/add/irlFeeMatrixAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="irlFeeMatrixAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>矩阵序号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="matrixNo" id="matrixNo" name="IRL_FEE_MATRIX" datatype="*1-12" nullmsg=" 请输入！"  tipmsg="格式错误!" ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>费用代码：</label>
						<div class="formControls span6">
						<select id="irlSeqNo" name="irlSeqNo" data-placeholder="费用代码" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
						</select>
						</div>
						<div class="span2"> </div>
					</div>
						<div class="row cl">
                    						<label class="form-label span4"><span class="c-red">*</span>缺口值：</label>
                    						<div class="formControls span6">
                    											<input type="text" class="input-text grid" value="" placeholder="boundary" id="boundary" name="boundary" datatype="n0-1|/^([0-9][0-15]*)+(.[0-9]{1,4})?$/"  tipmsg="格式错误!">
                    						</div>
                    						<div class="span2"> </div>
                    					</div>
				<div class="row cl">
                						<label class="form-label span4"><span class="c-red">*</span>费用金额：</label>
                						<div class="formControls span6">
                											<input type="text" class="input-text grid" value="" placeholder="feeAmt" id="feeAmt" name="feeAmt" datatype="n0-1|/^([0-9][0-15]*)+(.[0-9]{1,2})?$/"  tipmsg="格式错误!">
                						</div>
                						<div class="span2"> </div>
                					</div>



					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>费率：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="feeRate" id="feeRate" name="feeRate" datatype="/^([0-9][0-9]*)+(.[0-9]{1,4})?$/"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>

								<div class="row cl">
                    						<label class="form-label span4"><span class="c-red">*</span>利率类型：</label>
                    						<div class="formControls span6">
                    											<select id="intType" name="intType" datatype="*1-3" class="select2" tabindex="4" tipmsg="格式错误!">
                    											</select>
                    						</div>
                    						<div class="span2"> </div>
                    					</div>
<div class="row cl">
                						<label class="form-label span4"><span class="c-red">*</span>浮动利率：</label>
                						<div class="formControls span6">
                											<input type="text" class="input-text grid" value="" placeholder="floatRate" id="floatRate" name="floatRate" datatype="n1-21"  tipmsg="格式错误!">
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


