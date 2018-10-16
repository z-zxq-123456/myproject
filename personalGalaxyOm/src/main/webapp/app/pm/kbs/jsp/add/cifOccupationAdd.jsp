<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易CIF_OCCUPATION添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/cifOccupationAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="cifOccupationAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>职业类别：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="occupationCode" id="occupationCode" name="CIF_OCCUPATION" datatype="*1-6" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>职业说明：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="occupationDesc" id="occupationDesc" name="CIF_OCCUPATION" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>风险等级：</label>
						<div class="formControls span6">
								<select id="riskLevel" name="riskLevel" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="H" >H-高</option>
										<option value="L" >L-低</option>
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
			<div class="row">
				<div class="span-offset-1 span10 span-offset-1 mt-10">
					<input type="submit" class="button-select L smartButton" title="添加"  value="添加">
				</div>
			</div>
    	</form>
	</div>
</body>
</html>
