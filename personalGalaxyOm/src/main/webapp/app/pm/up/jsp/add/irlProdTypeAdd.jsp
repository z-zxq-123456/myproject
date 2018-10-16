<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易IRL_PROD_TYPE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/add/irlProdTypeAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="irlProdTypeAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="prodType" id="prodType" name="IRL_PROD_TYPE"  datatype="*1-20" nullmsg=" 请输入！" tipmsg="格式错误!" ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
				<div class="row cl">
                						<label class="form-label span4"><span class="c-red">*</span>产品类型描述：</label>
                						<div class="formControls span6">
                											<input type="text" class="input-text grid" value="" placeholder="prodTypeDesc" id="prodTypeDesc" name="IRL_PROD_TYPE" datatype="*1-60" nullmsg=" 请输入！"  tipmsg="格式错误!" >
                						</div>
                						<div class="span2"> </div>
                					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>产品组：</label>
						<div class="formControls span6">
											<select id="prodGrp" name="prodGrp" data-placeholder="产品组" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>


					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>定期账户细类：</label>
						<div class="formControls span6">
									<select id="fixedCall" name="fixedCall"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="A" >A-协议存款</option>
										<option value="B" >B-定期一本通</option>
										<option value="C" >C-通知存款</option>
										<option value="D" >D-定活两便</option>
										<option value="E" >E-教育储蓄</option>
										<option value="F" >F-整存整取</option>
										<option value="L" >L-零存整取</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>法人代码：</label>
						<div class="formControls span6">
											<select id="company" name="company" datatype="*0-20" class="select2" tabindex="4" tipmsg="格式错误!">
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

