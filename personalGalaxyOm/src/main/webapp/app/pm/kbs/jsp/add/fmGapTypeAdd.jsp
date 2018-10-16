<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_GAP_TYPE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmGapTypeAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmGapTypeAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>敞口类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="gapType" id="gapType" name="FM_GAP_TYPE" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>敞口开始日期：</label>
						<div class="formControls span6">
								<select id="gapStart" name="gapStart" data-placeholder="敞口开始日期" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="R" >R-代表运行日期</option>
										<option value="S" >S-代表即期(两天后)</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>是否工作日：</label>
						<div class="formControls span6">
									<select id="workingDays" name="workingDays" data-placeholder="是否工作日" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>敞口类型描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="gapTypeDesc" id="gapTypeDesc" name="FM_GAP_TYPE" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>所属模块：</label>
						<div class="formControls span6">
											<select id="prodGrp" name="prodGrp" datatype="*" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>币种：</label>
						<div class="formControls span6">
											<select id="ccy" name="ccy" datatype="*" class="select2" tabindex="4" tipmsg="格式错误!">
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
