<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_ATTR_CLASS添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/add/mbAttrClassAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbAttrClassAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>属性分类：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="attrClass" id="attrClass" name="MB_ATTR_CLASS" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>属性分类描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="attrClassDesc" id="attrClassDesc" name="MB_ATTR_CLASS" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>属性分类层级：</label>
						<div class="formControls span6">
								<select id="attrClassLevel" name="attrClassLevel" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="1" >1</option>
										<option value="2" >2</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>上级属性分类：</label>
						<div class="formControls span6">
						   <select id="parentAttrClass" name="parentAttrClass" data-placeholder="上级参数分类" class="select2" tabindex="4" tipmsg="格式错误!">
								 <option value="" selected="selected">请选择</option>
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
