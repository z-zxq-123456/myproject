<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_COMMISSION_PARAMETER添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/mbCommissionParameterAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbCommissionParameterAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>客户分类：</label>
						<div class="formControls span6">
											<select id="clientType" name="clientType" data-placeholder="客户分类" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>交易编号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="programId" id="programId" name="MB_COMMISSION_PARAMETER" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>状态：</label>
						<div class="formControls span6">
								<select id="status" name="status" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="A" >A-有效</option>
										<option value="C" >C-关闭</option>
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
