<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易IRL_FEE_PROFIT_DISTRIBUTION添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/add/irlFeeProfitDistributionAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="irlFeeProfitDistributionAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>费率类型：</label>
						<div class="formControls span6">
											<select id="feeType" name="feeType" data-placeholder="费率类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>机构代码：</label>
						<div class="formControls span6">
											<select id="branch" name="branch" data-placeholder="机构代码" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>百分比：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="percent" id="percent" name="IRL_FEE_PROFIT_DISTRIBUTION" datatype="*1-21" nullmsg=" 请输入！"  tipmsg="格式错误!">
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


