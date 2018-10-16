<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_PART_TYPE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/add/mbPartTypeAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbPartTypeAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>指标类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="partType" id="partType" name="MB_PART_TYPE" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>是否标准模板：</label>
						<div class="formControls span6">
									<select id="isStandard" name="isStandard" data-placeholder="是否标准模板" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>指标分类：</label>
						<div class="formControls span6">
											<select id="partClass" name="partClass" data-placeholder="指标分类" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>组件描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="partDesc" id="partDesc" name="MB_PART_TYPE" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>所属分类：</label>
						<div class="formControls span6">
											<select id="busiCategory" name="busiCategory" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>默认指标：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="defaultPart" datatype="*0-20" id="defaultPart" name="defaultPart" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>处理类型：</label>
						<div class="formControls span6">
								<select id="processMethod" name="processMethod" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="A" >A-检查</option>
										<option value="C" >C-提交</option>
								</select>
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
