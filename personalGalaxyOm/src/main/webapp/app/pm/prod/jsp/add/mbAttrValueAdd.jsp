<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_ATTR_VALUE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/add/mbAttrValueAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbAttrValueAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>属性KEY：</label>
						<div class="formControls span6">
											<select id="attrKey" name="attrKey" data-placeholder="attrKey" class="select2" datatype="*1-40" nullmsg="参数代码不能为空">
                                                                   <option value="" selected="selected">请选择</option>
                                                                </select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>属性值：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="attrValue" id="attrValue" name="MB_ATTR_VALUE"  nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>参数值描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="valueDesc" id="valueDesc" name="MB_ATTR_VALUE"  nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>引用列名：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="refColumns"  id="refColumns" name="refColumns" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>引用条件：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="refCondition"  id="refCondition" name="refCondition" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>引用表名：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="refTable"  id="refTable" name="refTable" tipmsg="格式错误!" >
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
