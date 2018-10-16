<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>

<html>
<head>
    <title>服务器信息修改</title>
        <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/server/serverInfoEdit.js"></script>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-patternMetadata-edit" name="fileForm">
        <div class="row" style="display:none;">
            <label class="form-label span4"><span class="c-red">*</span>服务器ID：</label>
            <div class="formControls span4">
                <input type="text" disabled="true" class="input-text grid" placeholder="服务器ID" name="serId" id="serId"
                       datatype="*1-30" nullmsg="请输入服务器ID">
            </div>
            <div class="span4"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>服务器名称：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" placeholder="服务器名称" name="serName" id="serName"
                       datatype="*1-60" nullmsg="请输入服务器名称">
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
                <input class="input-text grid" type="password" placeholder="服务器密码" name="serPwd" id="serPwd"
                       datatype="*2-32" nullmsg="请输入服务器密码" onkeypress="displayTestPwd()">
            </div>
            <div class="span4"></div>
        </div>
        <div class="row" id="pwdConfDiv" style="display:none">
            <label class="form-label span4"><span class="c-red">*</span>确认服务器密码：</label>
            <div class="formControls span4">
                <input type="password" class="input-text grid" placeholder="服务器密码" name="serPwdConfirm" id="serPwdConfirm"
                        nullmsg="服务器密码" errormsg="输入密码要一致" recheck="serPwd" >
            </div>
            <div class="span4"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>服务器操作系统：</label>
            <div class="formControls span4">
                <select class="select2" name="serOs" id="serOs" datatype="*1-60" size="1">

                </select>
            </div>
            <div class="span4"></div>
        </div>
        <div class="row">
            <div class="span4"></div>
            <div class="span4">
                <input type="submit" onclick="patternMetadataEdit()" class="button-select M" title="提交" value="提交">&nbsp;
                <a onclick="patternMetadataEditCancel()" class="button-cancle M" title="关闭">关闭</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>