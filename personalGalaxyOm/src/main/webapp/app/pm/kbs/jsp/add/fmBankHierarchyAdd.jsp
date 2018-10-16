<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_BANK_HIERARCHY添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmBankHierarchyAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmBankHierarchyAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>层级代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="hierarchyCode" id="hierarchyCode" name="FM_BANK_HIERARCHY" datatype="*1-5" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>层级说明：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="hierarchyName" datatype="*0-15" id="hierarchyName" name="hierarchyName" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>级别：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="hierarchyLevel" datatype="*0-5" id="hierarchyLevel" name="hierarchyLevel" tipmsg="格式错误!" >
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
