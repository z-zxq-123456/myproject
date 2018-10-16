<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_ATTR_TYPE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/add/mbAttrTypeAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbAttrTypeAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>属性KEY：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="attrKey" id="attrKey" name="MB_ATTR_TYPE" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>属性分类：</label>
						<div class="formControls span6">
						<select class="select2" id="attrClass" name="attrClass" datatype="*1-40" nullmsg="参数分类不能为空"></select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>属性描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="attrDesc" id="attrDesc" name="MB_ATTR_TYPE" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>状态：</label>
						<div class="formControls span6">
								<select id="status" name="status" data-placeholder="状态" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="A" >A-有效</option>
										<option value="F" >F-无效</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>取值方式：</label>
						<div class="formControls span6">
								<select id="valueMethod" name="valueMethod" data-placeholder="取值方式" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="FD" >FD-固定</option>
										<option value="VL" >VL-取值自列表值</option>
										<option value="RF" >RF-取值自其它参数表</option>
										<option value="YN" >YN-取值Y或N</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>参数类型：</label>
						<div class="formControls span6">
								<select id="attrType" name="attrType" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="STRING" >STRING-字符型</option>
										<option value="DOUBLE" >DOUBLE-数值型</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>所属分类：</label>
						<div class="formControls span6">
								<select class="select2" size="1" name="demo2" id ="busiCategory"  name="busiCategory" placeholder="请选择" multiple nullmsg="业务分类不能为空">
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>赋值标记：</label>
						<div class="formControls span6">
								<select id="setValueFlag" name="setValueFlag" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="M" >M-多值</option>
										<option value="S" >S-单值</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>使用方式：</label>
						<div class="formControls span6">
								<select id="useMethod" name="useMethod" class="select2" tipmsg="格式错误!"  datatype="*" nullmsg=" 请输入！">
									<option value="" selected="selected">空</option>
										<option value="EVAL" >EVAL-赋值类</option>
										<option value="CTR" >CTR-控制类</option>
										<option value="IND" >IND-独立逻辑</option>
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
