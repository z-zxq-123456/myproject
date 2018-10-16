<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表TB_VOUCHER_UNIT修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/tbVoucherUnitMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="tbVoucherUnitMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>凭证类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="docType" name="docType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>凭证单位代码：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="unitCode" name="unitCode" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>单位基数：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="unitBase" id="unitBase" name="unitBase" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>凭证单位描述：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="unitDesc" id="unitDesc" name="unitDesc" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!">
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
