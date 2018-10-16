<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表MB_ATTR_VALUE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/edit/mbAttrValueMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="mbAttrValueMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>属性KEY：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="attrKey" name="attrKey" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>属性值：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="attrValue" name="attrValue" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>参数值描述：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="valueDesc" id="valueDesc" name="valueDesc"  nullmsg=" 请输入！"  tipmsg="格式错误!">
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
                <input type="submit" class="button-select L smartButton" title="修改"  value="修改">
            </div>
        </div>
	</form>
</div>
</body>
</html>
