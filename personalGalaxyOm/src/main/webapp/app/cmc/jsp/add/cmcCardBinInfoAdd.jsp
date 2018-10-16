<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易CMC_CARD_BIN_INFO添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/cmc/js/add/cmcCardBinInfoAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="cmcCardBinInfoAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>卡BIN序号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="binOrder" id="binOrder" name="CMC_CARD_BIN_INFO"  datatype="*1-10" nullmsg=" 请输入！" tipmsg="格式错误!" ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>卡BIN：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="bin" id="bin" name="CMC_CARD_BIN_INFO" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>卡BIN的长度：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="binLength" id="binLength" name="CMC_CARD_BIN_INFO" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>延期标志：</label>
						<div class="formControls span6">
								<select id="delayFlag" name="delayFlag" data-placeholder="延期标志" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="0" >0-正常</option>
										<option value="1" >1-已延期</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>卡BIN有效期：</label>
						<div class="formControls span6">
                            <input type="text" class="input-text grid" value="" placeholder="lastTime" id="lastTime" name="CMC_CARD_BIN_INFO" datatype="*1-8" nullmsg=" 请输入！" tipmsg="格式错误!" >
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
