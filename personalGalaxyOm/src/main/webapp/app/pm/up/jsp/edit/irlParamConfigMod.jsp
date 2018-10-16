<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表IRL_PARAM_CONFIG修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/edit/irlParamConfigMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="irlParamConfigMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>参数名称：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="paramName" name="paramName" >
			    </div>
			    <div class="span2"> </div>
			</div>
			   <div class="row cl">
                                <label class="form-label span4"><span class="c-red">*</span>参数值：</label>
                                <div class="formControls span6">
                                                <input type="text" class="input-text grid" value="" placeholder="paramValue" id="paramValue" name="paramValue" datatype="*1-64" nullmsg=" 请输入！"  tipmsg="格式错误!">
            			    </div>
            			    <div class="span2"> </div>
            			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>参数描述：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="paramDesc" id="paramDesc" name="paramDesc" datatype="*1-64" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>

                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>备注：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="remark" datatype="*0-100" id="remark" name="remark" tipmsg="格式错误!" >
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
