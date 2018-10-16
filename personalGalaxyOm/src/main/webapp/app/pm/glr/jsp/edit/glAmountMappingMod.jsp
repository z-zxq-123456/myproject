<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表GL_AMOUNT_MAPPING修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/edit/glAmountMappingMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="glAmountMappingMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>映射金额类型：</label>
			    <div class="formControls span6">
                											<select id="mappingType" name="mappingType" data-placeholder="映射金额类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                											</select>
                						</div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>金额类型：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="amountType" id="amountType" name="amountType" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>映射名称：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="mappingName" datatype="*0-100" id="mappingName" name="mappingName" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>金额类型描述：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="amountTypeDesc" datatype="*0-100" id="amountTypeDesc" name="amountTypeDesc" tipmsg="格式错误!" >
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
