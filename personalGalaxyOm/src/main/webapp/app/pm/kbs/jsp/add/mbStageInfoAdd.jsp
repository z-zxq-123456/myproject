<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_STAGE_INFO添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/mbStageInfoAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbStageInfoAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>期次代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="stageCode" id="stageCode" name="MB_STAGE_INFO" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>币种：</label>
						<div class="formControls span6">
											<select id="ccy" name="ccy" data-placeholder="币种" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>期次发行额度：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="issueAmt" id="issueAmt" name="MB_STAGE_INFO" datatype="*1-19" nullmsg=" 请输入！"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>发行年度：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="issueYear" id="issueYear" name="MB_STAGE_INFO" datatype="*1-4" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="prodType" id="prodType" name="MB_STAGE_INFO" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>上次修改日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="lastChangeDate" datatype="*0-8" id="lastChangeDate" name="lastChangeDate" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>上次已使用额度：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="prevUsedAmt" id="prevUsedAmt" name="prevUsedAmt" datatype="n0-19"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>期次已使用额度：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="usedAmt" id="usedAmt" name="usedAmt" datatype="n0-19"  tipmsg="格式错误!">
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
