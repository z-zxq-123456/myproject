<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_LANGUAGE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmLanguageAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmLanguageAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>用户语言：</label>
						<div class="formControls span6">
								<select id="languageCode" name="languageCode" data-placeholder="用户语言" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="E" >E-英文</option>
										<option value="C" >C-中文</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>字符值：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="charValue" id="charValue" name="FM_LANGUAGE" datatype="*1-1" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>用户语言描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="languageDesc" id="languageDesc" name="FM_LANGUAGE" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
