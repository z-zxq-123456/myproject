<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表CD_CARD_BIN修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/cdCardBinMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="cdCardBinMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>卡BIN：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="cardBin" name="cardBin" >
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
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>时间：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="validTime" datatype="*0-20" id="validTime" name="validTime" tipmsg="格式错误!" >
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
