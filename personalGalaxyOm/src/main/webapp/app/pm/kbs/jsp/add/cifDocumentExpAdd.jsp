<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易CIF_DOCUMENT_EXP添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/cifDocumentExpAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="cifDocumentExpAdd">
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>证件类型：</label>
				<div class="formControls span6">
					<select id="documentType" name="documentType" class="select2" tabindex="4" datatype="*" tipmsg="格式错误!" nullmsg=" 请输入！" >
					</select>
				</div>
				<div class="span2"> </div>
			</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>交易处理方式取值：</label>
						<div class="formControls span6">
								<select id="dealFlow" name="dealFlow" data-placeholder="交易处理方式取值" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="A" >A-授权处理</option>
										<option value="B" >B-拒绝处理</option>
										<option value="D" >D-提醒处理；</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>是否止付：</label>
						<div class="formControls span6">
									<select id="stopFlag" name="stopFlag" data-placeholder="是否止付" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>法人代码：</label>
						<div class="formControls span6">
											<select id="company" name="company" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>提醒天数：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="days" datatype="*0-5" id="days" name="days" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>限制类型：</label>
						<div class="formControls span6">
											<select id="restraintType" name="restraintType" class="select2" tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>渠道类型规则：</label>
				<div class="formControls span6">
					<select id="sourceTypeRule" name="sourceTypeRule" class="select2" tabindex="4" datatype="*" tipmsg="格式错误!" nullmsg=" 请输入！" >
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
