<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表TB_CASH_SIGN_RESON修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/tbCashSignResonMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="tbCashSignResonMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>原因代号/资金流向代号：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="shtgId" name="shtgId" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>处理类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="shtgType" name="shtgType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>法人：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="company" datatype="*0-20" id="company" name="company" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>长款交易类型：</label>
                    <div class="formControls span6">
                                <select id="overTranType" name="overTranType" datatype="*"  class="select2" tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>短款交易类型：</label>
                    <div class="formControls span6">
                                <select id="shortTranType" name="shortTranType" datatype="*"  class="select2" tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>原因描述/资金流向描述：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="shtgDesc" datatype="*0-50" id="shtgDesc" name="shtgDesc" tipmsg="格式错误!" >
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
