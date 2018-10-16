<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易CIF_CONTACT_TYPE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/cifContactTypeAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="cifContactTypeAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>联系类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="contactType" id="contactType" name="CIF_CONTACT_TYPE" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>联系类型描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="contactTypeDesc" id="contactTypeDesc" name="CIF_CONTACT_TYPE" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>联系方式类型：</label>
						<div class="formControls span6">
								<select id="route" name="route" data-placeholder="联系方式类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="SWIFT" >SWIFT-SWIFT电文</option>
										<option value="POSTAL" >POSTAL-邮寄</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>SWIFT ID：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="swiftId" datatype="*0-1" id="swiftId" name="swiftId" tipmsg="格式错误!" >
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