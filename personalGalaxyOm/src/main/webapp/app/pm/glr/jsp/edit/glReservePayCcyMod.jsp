<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表GL_RESERVE_PAY_CCY修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/edit/glReservePayCcyMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="glReservePayCcyMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>机构：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="branch" name="branch" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>缴存币种：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="payCcy" name="payCcy" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>准备金类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="payType" name="payType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>是否汇总上缴：</label>
                    <div class="formControls span6">
                            <select id="collInd" name="collInd" data-placeholder="是否汇总上缴" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
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
