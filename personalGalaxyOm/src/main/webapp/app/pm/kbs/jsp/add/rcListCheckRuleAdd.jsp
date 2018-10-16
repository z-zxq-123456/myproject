<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易RC_LIST_CHECK_RULE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/rcListCheckRuleAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="rcListCheckRuleAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>名单类型代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="listType" id="listType" name="RC_LIST_CHECK_RULE" datatype="*1-12" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>事件禁止标识：</label>
						<div class="formControls span6">
								<select id="eventForbidInd" name="eventForbidInd" data-placeholder="事件禁止标识" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="I" >I-包含</option>
										<option value="E" >E-排除</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>禁止渠道集合：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="forbidChannels" id="forbidChannels" name="RC_LIST_CHECK_RULE" datatype="*1-200" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>是否核实后禁止：</label>
						<div class="formControls span6">
									<select id="verifyFlag" name="verifyFlag" data-placeholder="是否核实后禁止" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>渠道禁止标识：</label>
						<div class="formControls span6">
								<select id="channelForbidInd" name="channelForbidInd" data-placeholder="渠道禁止标识" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="I" >I-包含</option>
										<option value=" E" > E-排除</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>禁止事件集合：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="forbidEvents" id="forbidEvents" name="RC_LIST_CHECK_RULE" datatype="*1-200" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>禁止期限：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="forbidTerm" datatype="*0-5" id="forbidTerm" name="forbidTerm" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>禁止期限单位：</label>
						<div class="formControls span6">
								<select id="forbidTermType" name="forbidTermType" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="Y" >Y-年</option>
										<option value="M" >M-月</option>
										<option value="D" >D-日</option>
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
