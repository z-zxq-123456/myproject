<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>用户添加</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/appEarlyConfigFileEdit.js"></script>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-configFileInfo-edit">
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>配置文件名：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" id="fileName" placeholder="请输入3-20位字符" name="fileName"
                       datatype="*3-50" nullmsg="请输入！" tipmsg="格式错误!" disabled>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4">配置参数名：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" id="configKey" placeholder="请输入3-20位字符" name="configKey"
                       datatype="*3-50" nullmsg="请输入！" tipmsg="格式错误!" disabled>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>配置参数原值：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" id="configValue" placeholder="请输入1-200位字符" name="configValue"
                       datatype="*1-200" nullmsg="请输入！" tipmsg="格式错误!" disabled>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>配置参数新值：</label>
            <div class="formControls span6">
                <input onblur="checkPort()" type="text" class="input-text grid" id="updateConfigValue" placeholder="请输入1-200位数字"
                       name="updateConfigValue" datatype="*1-200" nullmsg="请输入！" tipmsg="格式错误!"   >
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4">配置修改方式：</label>
            <div class="formControls span6">
                <select name="updateType" id="updateType" size="1" class="select2" style="margin-top:5px">
                </select>
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