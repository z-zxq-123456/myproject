<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表FM_CURRENCY_ATTACH修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/fmCurrencyAttachMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="fmCurrencyAttachMod">
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>币种：</label>
                    <div class="formControls span6">
                                <select id="ccy" name="ccy" datatype="*"  class="select2" tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>国家：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="country" datatype="*0-3" id="country" name="country" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>浮动类型：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="spreadPointType" datatype="*0-1" id="spreadPointType" name="spreadPointType" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>基准汇率：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="rateTypeRelated" datatype="*0-3" id="rateTypeRelated" name="rateTypeRelated" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>浮动值：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="spreadPoint" datatype="*0-10" id="spreadPoint" name="spreadPoint" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>汇率类型：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="rateType" datatype="*0-3" id="rateType" name="rateType" tipmsg="格式错误!" >
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
