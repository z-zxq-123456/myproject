<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_EVENT_PART_RELATION添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/add/mbEventPartRelationAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbEventPartRelationAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>组件ID：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="assembleId" id="assembleId" name="MB_EVENT_PART_RELATION" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>组件类型：</label>
						<div class="formControls span6">
								<select id="assembleType" name="assembleType" data-placeholder="组件类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="EVENT" >EVENT-事件</option>
										<option value="PART" >PART-部件</option>
										<option value="ATTR" >ATTR-属性</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>事件类型：</label>
						<div class="formControls span6">
											<select id="eventType" name="eventType" data-placeholder="事件类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>组件描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="partDesc" datatype="*0-100" id="partDesc" name="partDesc" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>状态：</label>
						<div class="formControls span6">
								<select id="status" name="status" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="A" >A-有效</option>
										<option value="C" >C-关闭</option>
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