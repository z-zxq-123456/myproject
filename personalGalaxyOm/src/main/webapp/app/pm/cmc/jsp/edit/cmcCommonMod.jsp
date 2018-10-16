<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表CMC_COMMON修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/cmc/js/edit/cmcCommonMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="cmcCommonMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>参数名：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="paramName" name="paramName" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>参数描述：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="paramDesc" datatype="*0-50" id="paramDesc" name="paramDesc" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>参数序号：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="paramOrder" datatype="*0-3" id="paramOrder" name="paramOrder" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>参数值：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="paramValue" datatype="*1-100" id="paramValue" name="paramValue"  nullmsg=" 请输入！" tipmsg="格式错误!" >
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
