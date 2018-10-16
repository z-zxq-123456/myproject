<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表IRL_FEE_ITEM修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/edit/irlFeeItemMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="irlFeeItemMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>费用项目代码：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="feeItem" name="feeItem" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>费用项目描述：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="feeItemDesc" id="feeItemDesc" name="feeItemDesc" datatype="*1-150" nullmsg=" 请输入！"  tipmsg="格式错误!">
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
