<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>应用实例部署</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/appInstantAdd.js"></script>
</head>
<body>
<div class="pd-20">

    <form action="" method="post" class="form form-horizontal" id="form-data-add" name="fileForm">
        <div class="row">
            <label class="form-label span4">实例IP:</label>
            <div class="formControls span6">
                <input type="text" name="serIp" id="serIp" class="input-text grid" disabled="true">
            </div>
        </div>
        <div class="row">
            <label class="form-label span4">安装路径:</label>
            <div class="formControls span6">
                <input name="appPath" id="appPath" class="input-text grid" disabled="true">
            </div>
        </div>
        <div class="row">
            <label class="form-label span4">工作目录:</label>
            <div class="formControls span6">
                <input name="appWork" id="appWork" disabled="true" class="input-text grid">
            </div>
        </div>
        <div class="row">
            <label class="form-label span4">当前版本号:</label>
            <div class="formControls span6">
                <input type="text" name="appIntantVer" id="appIntantVer" class="input-text grid" disabled="true">
            </div>
        </div>
        <div class="row">
            <label class="form-label span4">部署版本号:</label>
            <div class="formControls span6">
                <select type="text" class="select2" placeholder="部署版本号" name="demoAppIntantVer" id="demoAppIntantVer"
                        datatype="*1-10"
                        nullmsg="请输入">
                </select>
            </div>
        </div>
        <div class="row">
            <label class="form-label span4">配置文件选择:</label>
            <div class="formControls span6">
                <select type="text" class="select2" placeholder="配置文件选择" name="isRemainConfig" id="isRemainConfig"
                        datatype="*1-10"
                        nullmsg="请输入"></select>
            </div>
        </div>
        <div class="row" style="display:none;">
            <label class="form-label span4">应用版本ID:</label>
            <div class="formControls span6">
                <input name="appVerId" id="appVerId" class="input-text grid">
            </div>
        </div>
        <div class="row" style="display:none;">
            <label class="form-label span4">应用版本路径::</label>
            <div class="formControls span6">
                <input name="appVerPath" id="appVerPath" class="input-text grid">
            </div>
        </div>
        <div class="row" style="display:none;">
            <label class="form-label span4">应用版本类型::</label>
            <div class="formControls span6">
                <input name="appVerType" id="appVerType" class="input-text grid">
            </div>
        </div>
        <div class="row">
            <div class="span4"></div>
            <div class="span4">
                <input type="submit" class="button-select M" title="部署" value="部署">&nbsp;
                <a onclick="dataAddCancel()" class="button-cancle M" title="关闭">关闭</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>
