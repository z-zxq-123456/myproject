<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_CASH_ITEM添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/mbCashItemAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbCashItemAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>现金项目：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="cashItem" id="cashItem" name="MB_CASH_ITEM" datatype="*1-4" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>现金项目描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="cashItemDesc" datatype="*0-30" id="cashItemDesc" name="cashItemDesc" tipmsg="格式错误!" >
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
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>收入支出标志：</label>
						<div class="formControls span6">
								<select id="crDrInd" name="crDrInd" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="D" >D-借</option>
										<option value="C" >C-贷</option>
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
