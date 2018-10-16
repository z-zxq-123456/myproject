<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_SETTLE_TRAN_MAPPING添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/mbSettleTranMappingAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbSettleTranMappingAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>账户类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="acctType" id="acctType" name="MB_SETTLE_TRAN_MAPPING" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>收付款方向：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="payRecInd" id="payRecInd" name="MB_SETTLE_TRAN_MAPPING" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>结算类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="settleGlType" id="settleGlType" name="MB_SETTLE_TRAN_MAPPING" datatype="*1-2" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>交易类型集合：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="tranTypeList" id="tranTypeList" name="MB_SETTLE_TRAN_MAPPING" datatype="*1-200" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>法人代码：</label>
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
