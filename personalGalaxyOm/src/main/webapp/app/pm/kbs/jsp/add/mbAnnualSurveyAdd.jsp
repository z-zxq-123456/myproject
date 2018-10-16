<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_ANNUAL_SURVEY添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/mbAnnualSurveyAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbAnnualSurveyAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>账户属性：</label>
						<div class="formControls span6">
											<select id="acctNature" name="acctNature" data-placeholder="账户属性" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>期限频率：</label>
						<div class="formControls span6">
											<select id="periodFreq" name="periodFreq" data-placeholder="期限频率" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>重置日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="resetDate" id="resetDate" name="MB_ANNUAL_SURVEY" datatype="*1-4" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>截止日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="stopDate" id="stopDate" name="MB_ANNUAL_SURVEY" datatype="*1-4" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否止付：</label>
						<div class="formControls span6">
									<select id="stopFlag" name="stopFlag"  class="select2"  tipmsg="格式错误!">
										<option value="Y" selected="selected">是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>限制类型：</label>
						<div class="formControls span6">
											<select id="restraintType" name="restraintType" class="select2" tabindex="4" datatype="*"
											 tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>天数：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="days" datatype="*0-5" id="days" name="days" tipmsg="格式错误!" >
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
