<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易CMC_CARD_NO_SPECIAL添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/cmc/js/add/cmcCardNoSpecialAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="cmcCardNoSpecialAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>顺序号：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="请填入八位顺序号" id="orderNo" name="CMC_CARD_NO_SPECIAL" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>卡号规则：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="请填入卡号规则代码" id="productRuleNo" name="CMC_CARD_NO_SPECIAL" datatype="*1-4" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>卡状态：</label>
						<div class="formControls span6">
								<select id="status" name="status" data-placeholder="卡状态" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
									<option value="0" >未使用</option>
									<option value="1" >已使用</option>
									<option value="2" >开卡中</option>
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
