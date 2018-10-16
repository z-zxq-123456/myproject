<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易IRL_FEE_RATE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/add/irlFeeRateAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="irlFeeRateAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>序号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="irlSeqNo" datatype="*1-30" id="irlSeqNo" name="irlSeqNo" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
									<label class="form-label span4"><span class="c-red">*</span>费率类型：</label>
									<div class="formControls span6">
										<select id="feeType" name="feeType" datatype="*1-8"  class="select2" tabindex="4" tipmsg="格式错误!">
										</select>
									</div>
									<div class="span2"> </div>
								</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>生效日期：</label>
						<div class="formControls span6">
						<input type="text" onfocus="WdatePicker({dateFmt:'yyyyMMdd'})" id="effectDate" name="effectDate" datatype="*1-8" class="input-text Wdate" style="width:265px;">
												</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>机构代码：</label>
						<div class="formControls span6">
											<select id="branch" name="branch" datatype="*" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>币种：</label>
						<div class="formControls span6">
											<select id="ccy" name="ccy" datatype="*" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>



					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>上限：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="highLimit" id="highLimit" name="highLimit" datatype="n1-17"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>下限：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="lowLimit" id="lowLimit" name="lowLimit" datatype="n1-17"  tipmsg="格式错误!">
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


