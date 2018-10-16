<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表GL_CUSTOM_RULE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/edit/glCustomRuleMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="glCustomRuleMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>规则编号：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="ruleNo" name="ruleNo" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>科目：</label>
                    <div class="formControls span6">
                                <select id="glCode" name="glCode" data-placeholder="科目" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>规则描述：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="ruleDesc" id="ruleDesc" name="ruleDesc" datatype="*1-256" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>规则表达式：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="ruleExpression" id="ruleExpression" name="ruleExpression" datatype="*1-1,000" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>帐套：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="bussinessUnit" datatype="*0-20" id="bussinessUnit" name="bussinessUnit" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>利润中心：</label>
                    <div class="formControls span6">
                                <select id="profitCentre" name="profitCentre" datatype="*"  class="select2" tipmsg="格式错误!"  >
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
