<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_CCY_HOLIDAY添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmCcyHolidayAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmCcyHolidayAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>节假日日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="holidayDate" id="holidayDate" name="FM_CCY_HOLIDAY" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>节假日描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="holidayDesc" id="holidayDesc" name="FM_CCY_HOLIDAY" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>适用范围：</label>
						<div class="formControls span6">
								<select id="applyInd" name="applyInd" data-placeholder="适用范围" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="I" >I-个人</option>
										<option value="E" >E-公司</option>
										<option value="B" >B-个人和公司</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>节假日类型：</label>
						<div class="formControls span6">
								<select id="holidayType" name="holidayType" data-placeholder="节假日类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="S" >S-标准假日</option>
										<option value="N" >N-非标准假日</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>币种：</label>
						<div class="formControls span6">
											<select id="ccy" name="ccy" datatype="*" class="select2" tabindex="4" tipmsg="格式错误!">
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
