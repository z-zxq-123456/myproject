<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易CMC_PRODUCT_LIMIT添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/cmc/js/add/cmcProductLimitAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="cmcProductLimitAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>卡产品编号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="cardProductCode" id="cardProductCode" name="CMC_PRODUCT_LIMIT" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>

					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>渠道类型：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="channelType" id="channelType" name="CMC_PRODUCT_LIMIT" datatype="*1-4" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>主帐户的币种：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="ccy" id="ccy" name="CMC_PRODUCT_LIMIT" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>限制周期：</label>
						<div class="formControls span6">
								<select id="period" name="period" data-placeholder="限制周期" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="S" >S-单笔交易的限制</option>
										<option value="D" >D-一日内</option>
										<option value="M" >M-月</option>
										<option value="Q" >Q-季</option>
										<option value=" H" > H-半年</option>
										<option value="Y" >Y-年</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>

					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>本周期消费限额：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="conLimitAmt" id="conLimitAmt" name="CMC_PRODUCT_LIMIT"  datatype="*1-18" nullmsg=" 请输入！"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>本周期转入限额：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="tranInLimitAmt" id="tranInLimitAmt" name="CMC_PRODUCT_LIMIT" datatype="*1-18" nullmsg=" 请输入！"  tipmsg="格式错误!">
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>本周期转出限额：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="tranOutLimitAmt" id="tranOutLimitAmt" name="CMC_PRODUCT_LIMIT" datatype="*1-18" nullmsg=" 请输入！"  tipmsg="格式错误!">
				</div>
				<div class="span2"> </div>
			</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>本周期消费次数：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="conLimitTime" id="conLimitTime" name="CMC_PRODUCT_LIMIT" datatype="*1-5" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>


					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>本周期转入次数：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="tranInLimitTime" id="tranInLimitTime" name="CMC_PRODUCT_LIMIT" datatype="*1-5" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>

					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>本周期转出次数：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="tranOutLimitTime" id="tranOutLimitTime" name="CMC_PRODUCT_LIMIT" datatype="*1-5" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
