<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表GL_CCY_CTRL_ACCT_TBL修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/edit/glCcyCtrlAcctTblMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="glCcyCtrlAcctTblMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>科目：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="glCode" name="glCode" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>利润科目：</label>
                    <div class="formControls span6">
                                <select id="glCodeProfit" name="glCodeProfit" data-placeholder="利润科目" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>损益科目：</label>
                    <div class="formControls span6">
                                <select id="glCodeLoss" name="glCodeLoss" data-placeholder="损益科目" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
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
