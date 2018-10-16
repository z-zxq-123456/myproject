<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易CMC_CARD_ORDER_NO_INFO添加</title>
<script type="text/javascript" src="${ContextPath}/app/cmc/js/add/cmcCardOrderNoInfoAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="cmcCardOrderNoInfoAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>卡号规则：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="productRuleNo" id="productRuleNo" name="CMC_CARD_ORDER_NO_INFO" datatype="*1-4" nullmsg=" 请输入！"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>卡顺序号起始值：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="cardNoBegin" datatype="*0-8" id="cardNoBegin" name="cardNoBegin" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>卡顺序号终止值：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="cardNoEnd" datatype="*0-8" id="cardNoEnd" name="cardNoEnd" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>卡顺序号当前值：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="cardNoNow" datatype="*0-8" id="cardNoNow" name="cardNoNow" tipmsg="格式错误!" >
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
