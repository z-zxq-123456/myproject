<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表GL_PROD_RULE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/edit/glProdRuleMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="glProdRuleMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>交易类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="tranType" name="tranType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>核算状态：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="accountingStatus" name="accountingStatus" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>币种：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="ccy" name="ccy" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>客户分类：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="clientType" name="clientType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>事件类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="tranEventType" name="tranEventType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="prodType" name="prodType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>渠道类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="sourceType" name="sourceType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>系统名称：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="sysName" name="sysName" >
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
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>自定义规则编号：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="customRule" datatype="*0-30" id="customRule" name="customRule" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>分录描述：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="accountingDesc" datatype="*0-100" id="accountingDesc" name="accountingDesc" tipmsg="格式错误!" >
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
