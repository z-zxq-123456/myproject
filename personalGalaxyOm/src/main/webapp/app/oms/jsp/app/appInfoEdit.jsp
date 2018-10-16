<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>用户修改</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/appInfoEdit.js"></script>
</head>
<body>
<hidden id="appId" value=""></hidden>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-appInfo-edit">
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>应用名称：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" placeholder="应用名称" name="appName" id="appName"
                       datatype="*1-40" nullmsg="请输入应用名称">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>应用英文简称：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" placeholder="应用英文简称" name="appSimpenNm" id="appSimpenNm"
                       datatype="*1-20" nullmsg="请输入应用英文简称">
            </div>
            <div class="span2"></div>
        </div>

        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>应用分类：</label>
            <div class="formControls span6">
                <select type="text" class="select2" placeholder="应用分类" name="appType" id="appType" datatype="*1-10"
                        nullmsg="应用类型"></select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>Redis集群：</label>
            <div class="formControls span6">
                <select type="text" class="select2" name="redisMidwareName" id="redisMidwareName"
                        datatype="*1-60"></select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>ZK集群：</label>
            <div class="formControls span6">
                <select type="text" class="select2" name="zkMidwareName" id="zkMidwareName" datatype="*1-60"></select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>DB集群：</label>
            <div class="formControls span6">
                <select type="text" class="select2" name="dbMidwareName" id="dbMidwareName" datatype="*1-60"></select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>应用安装路径：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" placeholder="应用安装路径" name="appPath" id="appPath"
                       datatype="*1-200" nullmsg="应用安装路径">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4">应用描述：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" placeholder="应用描述" name="appDesc" id="appDesc"
                       datatype="*1-60" nullmsg="应用描述">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <div class="span4"></div>
            <input type="submit" class="button-select M" title="提交" value="提交">&nbsp;
            <a onclick="dataEditCancel()" class="button-cancle M" title="关闭">关闭</a>
        </div>
    </form>
</div>
</body>
</html>