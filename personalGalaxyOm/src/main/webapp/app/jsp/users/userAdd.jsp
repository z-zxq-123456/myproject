<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>用户添加</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/js/users/userAdd.js"></script>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-user-add">
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>用户ID：</label>
            <div class="formControls span6">
                <input type="text" class="input-text" id="userId" placeholder="请输入1-20位字符" name="userId"
                       datatype="*1-20" nullmsg="请输入！" tipmsg="格式错误!" ajaxurl="${ContextPath}/user/vaildUser">
            </div>
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
                       name="password" datatype="*6-20" nullmsg="请输入" tipmsg="格式错误!">
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
                          placeholder="请输入2-50位字符" dragonfly="true" datatype="*2-50" nullmsg="请输入!" tipmsg="格式错误!"
                          onKeyUp="textarealength(this,50)"></textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/50</p>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row formBtnArea">
            <div class="span-offset-1 span10 span-offset-1 mt-10">
                <input type="submit" class="button-select L smartButton" title="添加" value="添加">
            </div>
        </div>
    </form>
</div>
</body>
</html>
