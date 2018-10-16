<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>

<html>
<head>
    <title>服务器信息修改</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/appIntantConfigEdit.js"></script>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-data-edit" name="fileForm">
        <input type="hidden" id="appIntantId">
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>应用名称：</label>
            <div class="formControls span4">
                <select class="select2" name="appId" id="appId" datatype="*1-30" nullmsg="请应用名称"></select>
            </div>
            <div class="span4"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>服务器名称：</label>
            <div class="formControls span4">
                <select class="select2" name="serId" id="serId" datatype="*1-50" nullmsg="请服务器名称"></select>
            </div>
            <div class="span4"></div>
        </div>

        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>应用实例名称：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" placeholder="应用实例名称" name="appIntantName" id="appIntantName"
                       datatype="*1-60" nullmsg="请输入应用实例描述">
            </div>
            <div class="span4"></div>
        </div>

        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>应用实例描述：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" placeholder="应用实例描述" name="appIntantDesc" id="appIntantDesc"
                       datatype="*1-60" nullmsg="请输入应用实例描述">
            </div>
            <div class="span4"></div>
        </div>

        <div class="row">
            <div class="span4"></div>
            <div class="span4">
                <input type="submit" class="button-select M" title="提交" value="提交">&nbsp;
                <a onclick="dataEditCancel()" class="button-cancle M" title="关闭">关闭</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>