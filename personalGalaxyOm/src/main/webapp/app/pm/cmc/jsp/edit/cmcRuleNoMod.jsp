<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表CMC_RULE_NO修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/cmc/js/edit/cmcRuleNoMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form class="form form-horizontal" method="post" id="cmcRuleNoMod">
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>产品系列编号：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="ruleCode" id="ruleCode" name="CMC_PRODUCT_INFO" datatype="/^\d{3}$/" nullmsg=" 请输入！"  tipmsg="格式错误,只能为三位数字!" >
            </div>
            <div class="span2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>产品系列名称：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="ruleDesc" id="ruleDesc" name="CMC_RULE_NO" datatype="*1-50" tipmsg="格式错误!">
            </div>
            <div class="span2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>预制卡规则编号：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="ruleNo" id="ruleNo" name="CMC_RULE_NO" datatype="/^\d{4}$/" nullmsg=" 请输入！"  tipmsg="格式错误,只能为四位数字!!" >
            </div>
            <div class="span2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>制卡渠道编号：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="ruleEx" id="ruleEx" name="CMC_RULE_NO" datatype="/^\d{2}$/" tipmsg="格式错误,只能为两位数字!">
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
