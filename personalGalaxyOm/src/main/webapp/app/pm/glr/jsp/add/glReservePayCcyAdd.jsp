<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易GL_RESERVE_PAY_CCY添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/add/glReservePayCcyAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="glReservePayCcyAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>机构：</label>
						<div class="formControls span6">
											<select id="branch" name="branch" data-placeholder="机构" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>缴存币种：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="payCcy" id="payCcy" name="GL_RESERVE_PAY_CCY" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>准备金类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="payType" id="payType" name="GL_RESERVE_PAY_CCY" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>是否汇总上缴：</label>
						<div class="formControls span6">
									<select id="collInd" name="collInd" data-placeholder="是否汇总上缴" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
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
