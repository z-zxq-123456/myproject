<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_FAKE_COIN_DEF添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmFakeCoinDefAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmFakeCoinDefAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>面额：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="bondnotes" id="bondnotes" name="FM_FAKE_COIN_DEF" datatype="*1-17" nullmsg=" 请输入！"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>套数：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="bondnumber" id="bondnumber" name="FM_FAKE_COIN_DEF" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>券别代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="bondtypeid" id="bondtypeid" name="FM_FAKE_COIN_DEF" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>版别：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="bondversionnum" id="bondversionnum" name="FM_FAKE_COIN_DEF" datatype="*1-15" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
						<label class="form-label span4"><span class="c-red"></span>券别名称：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="bondname" datatype="*0-15" id="bondname" name="bondname" tipmsg="格式错误!" >
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
