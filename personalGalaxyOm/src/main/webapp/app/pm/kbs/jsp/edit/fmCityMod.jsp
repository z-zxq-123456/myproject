<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表FM_CITY修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/fmCityMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="fmCityMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>城市代码：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="city" name="city" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>省市：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="state" name="state" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>国家：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="country" id="country" name="country" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!">
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
                    <label class="form-label span4"><span class="c-red"></span>描述：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="cityDesc" datatype="*0-100" id="cityDesc" name="cityDesc" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>电话区号：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="cityTel" datatype="*0-4" id="cityTel" name="cityTel" tipmsg="格式错误!" >
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
