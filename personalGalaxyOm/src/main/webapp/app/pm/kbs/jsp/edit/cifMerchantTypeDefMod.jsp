<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表CIF_MERCHANT_TYPE_DEF修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/cifMerchantTypeDefMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="cifMerchantTypeDefMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>商户类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="ccSubType" name="ccSubType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>商户大类：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="ccType" id="ccType" name="ccType" datatype="*1-19" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>法人代码：</label>
                    <div class="formControls span6">
                                <select id="company" name="company"  class="select2" tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>商户大类说明：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="ccTypeDesc" datatype="*0-300" id="ccTypeDesc" name="ccTypeDesc" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>商户类型说明：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="ccSubTypeDesc" datatype="*0-300" id="ccSubTypeDesc" name="ccSubTypeDesc" tipmsg="格式错误!" >
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
