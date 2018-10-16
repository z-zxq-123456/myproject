<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易TB_VOUCHER_UNIT添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/tbVoucherUnitAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="tbVoucherUnitAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>凭证类型：</label>
						<div class="formControls span6">
											<select id="docType" name="docType" data-placeholder="凭证类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>凭证单位代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="unitCode" id="unitCode" name="TB_VOUCHER_UNIT" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>单位基数：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="unitBase" id="unitBase" name="TB_VOUCHER_UNIT" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>凭证单位描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="unitDesc" id="unitDesc" name="TB_VOUCHER_UNIT" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
