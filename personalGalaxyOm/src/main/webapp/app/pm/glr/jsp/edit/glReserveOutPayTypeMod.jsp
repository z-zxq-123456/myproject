<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表GL_RESERVE_OUT_PAY_TYPE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/edit/glReserveOutPayTypeMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="glReserveOutPayTypeMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>准备金类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="payType" name="payType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>对方客户号：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="adjustAcctClientNo" datatype="*0-20" id="adjustAcctClientNo" name="adjustAcctClientNo" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>对方账户类型：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="adjustAcctType" datatype="*0-10" id="adjustAcctType" name="adjustAcctType" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>准备金类型描述信息：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="payTypeDesc" datatype="*0-50" id="payTypeDesc" name="payTypeDesc" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>缴存客户号：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="payAcctClientNo" datatype="*0-20" id="payAcctClientNo" name="payAcctClientNo" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>缴存账户类型：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="payAcctType" datatype="*0-10" id="payAcctType" name="payAcctType" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>客户名称：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="adjustAcctClientName" datatype="*0-200" id="adjustAcctClientName" name="adjustAcctClientName" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>缴存客户名称：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="payAcctClientName" datatype="*0-200" id="payAcctClientName" name="payAcctClientName" tipmsg="格式错误!" >
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
