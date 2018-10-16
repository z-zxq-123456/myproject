<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表FM_GAP_PERIOD修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/fmGapPeriodMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="fmGapPeriodMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>期限：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="periodNo" name="periodNo" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>敞口类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="gapType" name="gapType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>期限类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="periodType" name="periodType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>法人代码：</label>
                    <div class="formControls span6">
                                <select id="company" name="company" data-placeholder="法人代码" class="select2"  tabindex="4"   tipmsg="格式错误!">
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>期限描述：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="periodDesc" id="periodDesc" name="periodDesc" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!">
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
