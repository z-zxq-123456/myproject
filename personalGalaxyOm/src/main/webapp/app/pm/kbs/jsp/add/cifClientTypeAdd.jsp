<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易CIF_CLIENT_TYPE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/cifClientTypeAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="cifClientTypeAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>客户分类：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="clientType" id="clientType" name="CIF_CLIENT_TYPE"  datatype="*1-3" nullmsg=" 请输入！" tipmsg="格式错误!" ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>是否个体客户：</label>
						<div class="formControls span6">
									<select id="isIndividual" name="isIndividual" data-placeholder="是否个体客户" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>类型描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="clientTypeDesc" id="clientTypeDesc" name="CIF_CLIENT_TYPE" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
			<div class="row">
				<div class="span-offset-1 span10 span-offset-1 mt-10">
					<input type="submit" class="button-select L smartButton" title="添加"  value="添加">
				</div>
			</div>
    	</form>
	</div>
</body>
</html>
