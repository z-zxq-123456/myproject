<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>角色修改</title>
<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/js/sys/roleEdit.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-role-edit">
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>角色ID：</label>
            <div class="formControls span6">
                <input type="text" class="input-text  disabled" id="roleId" placeholder="请输入1-5的数字"  name="roleId" datatype="n1-5" nullmsg="请输入！" tipmsg="格式错误!" disabled=true;>

            </div>
            <!--validform提示信息空间位置-->
       			<div class="span2"> </div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>角色名：</label>
            <div class="formControls span6">
                <input type="text" class="input-text" id="roleName"  name="roleName" datatype="*1-20"  placeholder="请输入1-20位字符！" nullmsg="请输入！" tipmsg="格式错误!">
            </div>
    			<div class="span2"> </div>
        </div>
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>角色描述：</label>
			<div class="formControls span6">
				<textarea name="" cols="" rows=""	 style="width:100%;"  id="roleDesc" name="roleDesc"   dragonfly="true" datatype="*1-50"  placeholder="请输入1-50位字符！" nullmsg="请输入！" tipmsg="格式错误!" onKeyUp="textarealength(this,50)" ></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/50</p>
			</div>
			<div class="span2"> </div>
		</div>
			<div class="row formBtnArea">
                			<div class="span-offset-1 span10 span-offset-1 mt-10">
                				<input type="submit" class="button-select L smartButton" title="修改"  value="修改">
                			</div>
                		</div>
	</form>
</div>
</body>
</html>
