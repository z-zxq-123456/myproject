<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表TB_TRAILBOX_OVERDRAW修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/tbTrailboxOverdrawMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="tbTrailboxOverdrawMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>交易编号：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="programId" name="programId" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>允许透支金额：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="overdrawAmt" id="overdrawAmt" name="overdrawAmt" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!">
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
