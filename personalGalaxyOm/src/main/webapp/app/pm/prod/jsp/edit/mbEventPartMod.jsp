<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表MB_EVENT_PART修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/edit/mbEventPartMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="mbEventPartMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>组件ID：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="assembleId" name="assembleId" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>属性KEY：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="attrKey" name="attrKey" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>事件类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="eventType" name="eventType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>属性值：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="attrValue" datatype="*0-30" id="attrValue" name="attrValue" tipmsg="格式错误!" >
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
