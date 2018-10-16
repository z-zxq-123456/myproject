<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表FM_DIST_CODE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/fmDistCodeMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="fmDistCodeMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>所在城市：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="city" name="city" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>省：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="province" name="province" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>地区代码：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="distCode" name="distCode" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>名称：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="distName" name="distName" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>级别：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="distGrade" id="distGrade" name="distGrade" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>法人代码：</label>
                    <div class="formControls span6">
                        <select id="company" name="company"  class="select2" tabindex="4" tipmsg="格式错误!"></select>
			        </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>所在地区：</label>
                    <div class="formControls span6">
                                <select id="state" name="state" class="select2" tipmsg="格式错误!"  >
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
