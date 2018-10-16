<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易TB_VOUCHER_CHANGE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/tbVoucherChangeAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="tbVoucherChangeAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>描述信息：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="statusDesc" id="statusDesc" name="TB_VOUCHER_CHANGE" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>凭证出售后登记簿更新标志：</label>
						<div class="formControls span6">
								<select id="updateAfter" name="updateAfter" data-placeholder="凭证出售后登记簿更新标志" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="I" >I-新增</option>
										<option value="D" >D-删除</option>
										<option value="U" >U-修改</option>
										<option value="N" >N-不变</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>凭证出售前登记簿更新标志：</label>
						<div class="formControls span6">
								<select id="updateBefore" name="updateBefore" data-placeholder="凭证出售前登记簿更新标志" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="I" >I-新增</option>
										<option value="D" >D-删除</option>
										<option value="U" >U-修改</option>
										<option value="N" >N-不变</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>法人代码：</label>
						<div class="formControls span6">
											<select id="company" name="company"  class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>凭证新状态：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="newStatus" datatype="*0-3" id="newStatus" name="newStatus" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>凭证原状态：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="oldStatus" datatype="*0-3" id="oldStatus" name="oldStatus" tipmsg="格式错误!" >
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
