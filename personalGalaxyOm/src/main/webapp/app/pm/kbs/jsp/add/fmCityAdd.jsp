<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_CITY添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmCityAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmCityAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>城市代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="city" id="city" name="FM_CITY" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>省市：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="state" id="state" name="FM_CITY" datatype="*1-2" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>国家：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="country" id="country" name="FM_CITY" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
						<label class="form-label span4"><span class="c-red"></span>描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="cityDesc" datatype="*0-100" id="cityDesc" name="cityDesc" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>电话区号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="cityTel" datatype="*0-4" id="cityTel" name="cityTel" tipmsg="格式错误!" >
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
