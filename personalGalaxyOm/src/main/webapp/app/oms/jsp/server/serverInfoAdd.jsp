<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>服务器信息管理</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/server/serverInfoAdd.js"></script>
</head>
<body>
<div class="pd-20">

    <form action="" method="post" class="form form-horizontal" id="form-patternMetadata-add" name="fileForm">
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>服务器名称：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" placeholder="服务器名称" name="serName" id="serName"
                       datatype="*1-600" nullmsg="请输入服务器名称">
            </div>
            <div class="span4"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>服务器IP：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" placeholder="服务器IP" name="serIp" id="serIp" datatype="*1-60"
                       nullmsg="请输入服务器IP">
            </div>
            <div class="span4"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>服务器用户名：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" placeholder="服务器用户名" name="serUser" id="serUser"
                       datatype="*1-60" nullmsg="请输入服务器用户名">
            </div>
            <div class="span4"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>服务器密码：</label>
            <div class="formControls span4">
                <input type="password" class="input-text grid" placeholder="服务器密码" name="serPwd" id="serPwd"
                       datatype="*2-32" nullmsg="服务器密码">
            </div>
            <div class="span4"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>确认服务器密码：</label>
            <div class="formControls span4">
                <input type="password" class="input-text grid" placeholder="服务器密码" name="serPwdConfirm" id="serPwdConfirm"
                        nullmsg="服务器密码" errormsg="输入密码要一致" recheck="serPwd">
            </div>
            <div class="span4"></div>
        </div>

        <div class="span4"></div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>服务器操作系统：</label>
            <div class="formControls span4">
                <select class="select2" placeholder="服务器操作系统" name="serOs" id="serOs" datatype="*1-60"
                        nullmsg="服务器操作系统">

                </select>
            </div>
            <div class="span4"></div>

        </div>
        <div class="row">
            <div class="span4"></div>
            <div class="span4">
                <input type="submit" class="button-select M" title="提交" value="提交">&nbsp;
                <a onclick="patternMetadataAddCancel()" class="button-cancle M" title="关闭">关闭</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>
