<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_ANALYSIS1添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/mbAnalysis1Add.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbAnalysis1Add">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>分类代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="analysis1" id="analysis1" name="MB_ANALYSIS1" datatype="*1-6" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="analysis1Desc" id="analysis1Desc" name="MB_ANALYSIS1" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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