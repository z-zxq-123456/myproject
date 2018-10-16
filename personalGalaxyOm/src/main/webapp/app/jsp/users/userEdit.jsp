<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>用户修改</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/js/users/userEdit.js"></script>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-user-edit">
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>用户ID：</label>
            <div class="formControls span6">
                <input type="text" class="input-text disabled" id="userId" placeholder="不能为空！" nullmsg="请输入！"
                       tipmsg="格式错误!" name="userId" disabled=true;>

            </div>
            <!--validform提示信息空间位置-->
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>用户名：</label>
            <div class="formControls span6">
                <input type="text" class="input-text" id="userName" placeholder="请输入1-20位字符" name="userName"
                       datatype="*1-20" nullmsg="请输入！" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>密码：</label>
            <div class="formControls span6">
                <input type="password" class="input-text" placeholder="请输入6-20位字符！" value="" id="password"
                       name="password" datatype="*6-20" nullmsg="请输入！" tipmsg="格式错误!">

            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>确认密码：</label>
            <div class="formControls span6">
                <input type="password" class="input-text" placeholder="请输入6-20位字符！" value="" id="passwordConfirm"
                       name="password" datatype="*6-20" nullmsg="请输入" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4">法人：</label>
            <div class="formControls span6">
                <input type="text" class="input-text" placeholder="请输入0-100位字符！" value="" id="legalentity"
                       name="legalentity">
            </div>
            <div class="span2"></div>
        </div>

        <div class="row">
            <label class="form-label span4">机构：</label>
            <div class="formControls span6">
                <input type="text" class="input-text" placeholder="请输入0-100位字符！" value="" id="organization"
                       name="organization">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>信息描述：</label>
            <div class="formControls span6">
                <textarea name="" cols="" rows="" id="message" name="message" style="width:195px"
                          placeholder="请输入2-50位字符" dragonfly="true" datatype="*2-50" nullmsg="请输入!"
                          onKeyUp="textarealength(this,50)" tipmsg="格式错误!"></textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/50</p>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row formBtnArea">
            <div class="span-offset-1 span10 span-offset-1 mt-10">
                <input type="submit" class="button-select L smartButton" title="修改" value="修改">
            </div>
        </div>
    </form>
</div>
</body>
</html>
