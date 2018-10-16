<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表FM_FAKE_COIN_DEF修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/fmFakeCoinDefMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="fmFakeCoinDefMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>面额：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="bondnotes" name="bondnotes" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>套数：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="bondnumber" name="bondnumber" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>券别代码：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="bondtypeid" name="bondtypeid" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>版别：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="bondversionnum" name="bondversionnum" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>币种：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="ccy" name="ccy" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>券别名称：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="bondname" datatype="*0-15" id="bondname" name="bondname" tipmsg="格式错误!" >
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
