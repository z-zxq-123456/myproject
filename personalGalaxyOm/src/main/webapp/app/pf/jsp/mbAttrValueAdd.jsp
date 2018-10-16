<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<script type="text/javascript" src="${ContextPath}/app/pf/js/mbAttrValueAdd.js"></script>
<title>参数值添加</title>
</head>
<body>
<div class="padding-20">
	<form action="" method="post" class="form form-horizontal" id="form-attr-add">
		<div class="row cl">
			<label class="form-label span4"><span class="c-red">*</span>参数代码：</label>
			<div class="formControls span6">
        			<select id="attrKey" name="attrKey" data-placeholder="参数代码" class="select2" datatype="*1-40" nullmsg="参数代码不能为空">
                       <option value="" selected="selected">请选择</option>
                    </select>
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row cl">
			<label class="form-label span4"><span class="c-red">*</span>参数描述：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" value="" placeholder="attrDesc" id="attrDesc" name="attrDesc" disabled="true">
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row cl">
        	<label class="form-label span4"><span class="c-red">*</span>取值方式：</label>
        		<div class="formControls span6">
						<select class="select2" id="valueMethod" name="valueMethod"  disabled="true">
						    <option value="" selected="selected">请选择</option>
							<option value="FD">FD-取自固定值</option>
							<option value="YN">YN-取值Y或N</option>
							<option value="VL">VL-取自列表值</option>
							<option value="RF">RF-取自数据表</option>
						</select>
        		</div>
        		<div class="span2"> </div>
        </div>
		<div class="row cl">
			<label class="form-label span4">参数值：</label>
			<div class="formControls span6">
				<input type="text" placeholder="attrValue"  value="" id="attrValue" name="attrValue" class="input-text grid"  nullmsg="参数值不能为空"	>
			</div>
		<div class="span2"> </div>
		</div>
		<div class="row cl">
			<label class="form-label span4">参数值描述：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" value=""  id="valueDesc" name="valueDesc" placeholder="描述"  nullmsg="参数值描述不能为空">
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row cl">
			<label class="form-label span4">参照表：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" value=""  id="refTable" name="refTable"  nullmsg="参照表不能为空">
			</div>
			<div class="span2"> </div>
		</div>

		<div class="row cl">
			<label class="form-label span4">参照条件：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" value=""  id="refCondition" name="refCondition"  nullmsg="参照条件不能为空" >
			</div>
			<div class="span2"> </div>
		</div>

		<div class="row cl">
			<label class="form-label span4">参照列：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" value=""  id="refColumns" name="refColumns"  nullmsg="参照列不能为空" >
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