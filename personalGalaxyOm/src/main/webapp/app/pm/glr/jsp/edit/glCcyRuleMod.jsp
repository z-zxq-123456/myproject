<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表GL_CCY_RULE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/edit/glCcyRuleMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="glCcyRuleMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>买入币种：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="buyCcy" name="buyCcy" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>卖出币种：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="sellCcy" name="sellCcy" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>事件类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="eventType" name="eventType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>会计分录编号：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="accountingNo" id="accountingNo" name="accountingNo" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!">
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
