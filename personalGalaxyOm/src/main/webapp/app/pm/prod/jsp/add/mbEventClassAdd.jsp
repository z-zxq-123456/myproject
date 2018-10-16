<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_EVENT_CLASS添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/add/mbEventClassAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbEventClassAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>事件分类：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="eventClass" id="eventClass" name="MB_EVENT_CLASS"  datatype="*1-20" nullmsg=" 请输入！" tipmsg="格式错误!" ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>事件分类描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="eventClassDesc" id="eventClassDesc" name="MB_EVENT_CLASS" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>事件分类层级：</label>
						<div class="formControls span6">
								<select id="eventClassLevel" name="eventClassLevel" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="1" >1</option>
										<option value="2" >2</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
			 			<label class="form-label span4"><span class="c-red"></span>上级事件分类：</label>
						<div class="formControls span6">
								<select id="parentEventClass" name="parentEventClass" data-placeholder="上级事件分类"  multiple="multiple" class="select2" tabindex="4" tipmsg="格式错误!">
								 </select>
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
