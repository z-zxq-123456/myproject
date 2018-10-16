<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_GAP_PERIOD添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmGapPeriodAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmGapPeriodAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>期限：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="periodNo" id="periodNo" name="FM_GAP_PERIOD" datatype="*1-5" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>敞口类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="gapType" id="gapType" name="FM_GAP_PERIOD" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>期限类型：</label>
						<div class="formControls span6">
								<select id="periodType" name="periodType" data-placeholder="期限类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="D" >D-Days(天)</option>
										<option value="M" >M-Months(月)</option>
										<option value="Y" >Y-Years(年)</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>法人代码：</label>
						<div class="formControls span6">
											<select id="company" name="company" data-placeholder="法人代码" class="select2"  tabindex="4"  nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>期限描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="periodDesc" id="periodDesc" name="FM_GAP_PERIOD" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
