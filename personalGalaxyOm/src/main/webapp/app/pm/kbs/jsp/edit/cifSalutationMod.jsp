<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表CIF_SALUTATION修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/cifSalutationMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="cifSalutationMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>称呼代码：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="salutation" name="salutation" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>称呼代码说明：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="salutationDesc" datatype="*0-10" id="salutationDesc" name="salutationDesc" tipmsg="格式错误!" >
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
                    <label class="form-label span4"><span class="c-red"></span>适用性别：</label>
                    <div class="formControls span6">
                        <select id="genderFlag" name="genderFlag"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="M" >M-男</option>
                                <option value="F" >F-女</option>
                                <option value="U" >U-未知</option>
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
