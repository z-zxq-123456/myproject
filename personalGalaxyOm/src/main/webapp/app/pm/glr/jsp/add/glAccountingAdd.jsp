<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易GL_ACCOUNTING添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/add/glAccountingAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="glAccountingAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>会计分录编号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="accountingNo" id="accountingNo" name="GL_ACCOUNTING" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>金额类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="amountType" id="amountType" name="GL_ACCOUNTING" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>序号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="counter" id="counter" name="GL_ACCOUNTING" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>利润中心：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="profitCentreExp" id="profitCentreExp" name="GL_ACCOUNTING" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="prodTypeExp" id="prodTypeExp" name="GL_ACCOUNTING" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>科目表达式：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="glCodeExp" id="glCodeExp" name="GL_ACCOUNTING" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>借贷方向：</label>
						<div class="formControls span6">
								<select id="crDr" name="crDr" data-placeholder="借贷方向" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="C" >C-贷方</option>
										<option value="D" >D-借方</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>法人：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="companyExp" id="companyExp" name="GL_ACCOUNTING" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>客户号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="clientNoExp" id="clientNoExp" name="GL_ACCOUNTING" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>币种表达式：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="ccyExp" id="ccyExp" name="GL_ACCOUNTING" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>账套表达式：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="businessUnitExp" id="businessUnitExp" name="GL_ACCOUNTING" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>机构表达式：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="branchExp" id="branchExp" name="GL_ACCOUNTING" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>金额表达式：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="amountExp" id="amountExp" name="GL_ACCOUNTING" datatype="*1-500" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>序号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="seqNo" id="seqNo" name="GL_ACCOUNTING" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
