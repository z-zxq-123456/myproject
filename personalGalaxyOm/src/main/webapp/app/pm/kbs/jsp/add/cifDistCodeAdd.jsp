<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易CIF_DIST_CODE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/cifDistCodeAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="cifDistCodeAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>城市代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="city" id="city" name="CIF_DIST_CODE" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>省：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="province" id="province" name="CIF_DIST_CODE" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>地区代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="distCode" id="distCode" name="CIF_DIST_CODE" datatype="*1-6" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>名称：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="distName" id="distName" name="CIF_DIST_CODE" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>级别：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="distGrade" id="distGrade" name="CIF_DIST_CODE" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
						<label class="form-label span4"><span class="c-red"></span>省市：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="state" datatype="*0-2" id="state" name="state" tipmsg="格式错误!" >
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
