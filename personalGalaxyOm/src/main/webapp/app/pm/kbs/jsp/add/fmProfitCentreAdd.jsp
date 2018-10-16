<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_PROFIT_CENTRE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmProfitCentreAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmProfitCentreAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>利润中心：</label>
						<div class="formControls span6">
							<input type="text" id="profitCentre" name="profitCentre" data-placeholder="利润中心" class="input-text grid"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">

						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>中文说明：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="profitCentreDesc" id="profitCentreDesc" name="FM_PROFIT_CENTRE" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>利润中心类型：</label>
						<div class="formControls span6">
								<select id="profitCentreType" name="profitCentreType" data-placeholder="利润中心类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="S" >S-Sub(记账类)</option>
										<option value="C" >C-Control(控制类)</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>英文说明：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="profitCentreDescEn" datatype="*0-50" id="profitCentreDescEn" name="profitCentreDescEn" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>利润中心级别：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="profitCentreLevel" datatype="*0-12" id="profitCentreLevel" name="profitCentreLevel" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>法人代码：</label>
						<div class="formControls span6">
											<select id="company" name="company" class="select2" tabindex="4" tipmsg="格式错误!">
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
