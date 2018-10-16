<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表FM_REF_CODE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/fmRefCodeMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="fmRefCodeMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>取值范围：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="fieldValue" name="fieldValue" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>语言：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="refLang" name="refLang" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>表字段：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="domain" name="domain" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>取值的含义说明：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="meaning" id="meaning" name="meaning" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>参数行数：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="paraRowNum" id="paraRowNum" name="paraRowNum" datatype="n0-20"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>参数标志：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="paraFlag" id="paraFlag" name="paraFlag" datatype="n0-1"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>法人代码：</label>
                    <div class="formControls span6">
                                <select id="company" name="company"   class="select2" tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>缩写：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="abbreviation" datatype="*0-8" id="abbreviation" name="abbreviation" tipmsg="格式错误!" >
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
