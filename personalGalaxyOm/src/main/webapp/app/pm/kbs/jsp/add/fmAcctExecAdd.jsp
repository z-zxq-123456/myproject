<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_ACCT_EXEC添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmAcctExecAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmAcctExecAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>客户经理：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="acctExec" id="acctExec" name="FM_ACCT_EXEC" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>客户经理姓名：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="acctExecName" id="acctExecName" name="FM_ACCT_EXEC" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>是否担保经理：</label>
						<div class="formControls span6">
									<select id="collatMgrInd" name="collatMgrInd" data-placeholder="是否担保经理" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>利润中心：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="profitSegment" datatype="*0-12" id="profitSegment" name="profitSegment" tipmsg="格式错误!" >
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
						<label class="form-label span4"><span class="c-red"></span>主管经理：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="manager" datatype="*0-30" id="manager" name="manager" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>机构代码：</label>
						<div class="formControls span6">
											<select id="branch" name="branch" datatype="*" class="select2" tabindex="4" tipmsg="格式错误!">
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
