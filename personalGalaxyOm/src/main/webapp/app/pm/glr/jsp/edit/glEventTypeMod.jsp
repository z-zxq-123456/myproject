<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表GL_EVENT_TYPE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/edit/glEventTypeMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="glEventTypeMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>事件类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="eventType" name="eventType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>事件类型描述：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="eventTypeDesc" id="eventTypeDesc" name="eventTypeDesc" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>借贷方向：</label>
                    <div class="formControls span6">
                        <select id="crDr" name="crDr" data-placeholder="借贷方向" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="C" >C-贷方</option>
                                <option value="D" >D-借方</option>
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