<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表MB_SETTLE_ACCT_MAPPING修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/mbSettleAcctMappingMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="mbSettleAcctMappingMod">

		<div class="row cl">
			<label class="form-label span4"><span class="c-red">*</span>结算账户分类：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" value=""  id="settleAcctClass" name="settleAcctClass" >
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
                    <label class="form-label span4"><span class="c-red">*</span>收付款方向：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="payRecInd" id="payRecInd" name="payRecInd" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>金额类型集合：</label>
                    <div class="formControls span6">
                                <select id="amtTypeList" name="amtTypeList" data-placeholder="金额类型集合" class="select2"  tabindex="4" datatype="*" multiple nullmsg="请输入！" tipmsg="格式错误!">
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>法人代码：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="company" datatype="*0-20" id="company" name="company" tipmsg="格式错误!" >
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
