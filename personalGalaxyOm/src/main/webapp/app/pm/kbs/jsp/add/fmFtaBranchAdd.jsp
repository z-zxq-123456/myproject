<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_FTA_BRANCH添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmFtaBranchAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmFtaBranchAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>自贸区代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="ftaCode" id="ftaCode" name="FM_FTA_BRANCH" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>自贸区描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="ftaDesc" id="ftaDesc" name="FM_FTA_BRANCH" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>自贸区性质：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="ftaNature" id="ftaNature" name="FM_FTA_BRANCH" datatype="*1-1" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>自贸区利率类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="ftaRateType" id="ftaRateType" name="FM_FTA_BRANCH" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>自贸区类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="ftaType" id="ftaType" name="FM_FTA_BRANCH" datatype="*1-1" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
