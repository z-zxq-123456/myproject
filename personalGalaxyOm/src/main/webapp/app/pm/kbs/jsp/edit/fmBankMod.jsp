<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表FM_BANK修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/fmBankMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="fmBankMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>银行代码：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="bankCode" name="bankCode" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>银行名称：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="bankName" id="bankName" name="bankName" datatype="*1-35" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>银行类别：</label>
                    <div class="formControls span6">
                        <select id="bankType" name="bankType" data-placeholder="银行类别" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="O" >O-我行</option>
                                <option value="B" >B-他行</option>
                                <option value="S" >S-建房互助协会</option>
                                <option value="T" >T-第三方银行</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>状态：</label>
                    <div class="formControls span6">
                        <select id="status" name="status" data-placeholder="状态" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="A" >A-有效</option>
                                <option value="C" >C-关闭</option>
                        </select>
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
                    <label class="form-label span4"><span class="c-red"></span>银行ID：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="bankId" datatype="*0-8" id="bankId" name="bankId" tipmsg="格式错误!" >
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
