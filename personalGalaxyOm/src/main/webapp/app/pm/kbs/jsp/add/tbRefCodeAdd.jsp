<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易TB_REF_CODE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/TbRefCodeAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="tbRefCodeAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>表字段：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="domain" id="domain" name="TB_REF_CODE" datatype="*1-70" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>分组：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="refGroup" id="refGroup" name="TB_REF_CODE" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>语言：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="refLang" id="refLang" name="TB_REF_CODE" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>取值：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="fieldValue" id="fieldValue" name="TB_REF_CODE" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>取值的含义说明：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="meaning" id="meaning" name="TB_REF_CODE" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>缩写：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="abbreviation" datatype="*0-8" id="abbreviation" name="abbreviation" tipmsg="格式错误!" >
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
