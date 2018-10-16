<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表MB_SETTLE_TRAN_MAPPING修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/mbSettleTranMappingMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="mbSettleTranMappingMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>账户类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="acctType" name="acctType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>收付款方向：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="payRecInd" name="payRecInd" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>结算类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="settleGlType" name="settleGlType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>交易类型集合：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="tranTypeList" id="tranTypeList" name="tranTypeList" datatype="*1-200" nullmsg=" 请输入！"  tipmsg="格式错误!">
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
