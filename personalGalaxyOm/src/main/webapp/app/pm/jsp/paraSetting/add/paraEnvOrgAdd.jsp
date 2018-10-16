<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/js/paraSetting/add/paraEnvOrgAdd.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-env-add">
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>环境ID：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" id="envId" placeholder="请输入1-30位字符"  name="envId" datatype="*1-30" nullmsg="请输入！" tipmsg="格式错误!" ajaxurl="${ContextPath}/paraEnv/vaildEnv">
			</div>
			<div class="span2"></div>
		</div>
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>系统ID：</label>
			<div class="formControls span6">
				<select class="select2" size="1" id="systemId" name="systemId" datatype="*1-10" placeholder="不能为空！" nullmsg="请选择！" tipmsg="格式错误!">
                 </select>
			</div>
			<div class="span2"></div>
		</div>
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>环境描述：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" placeholder="请输入1-60位字符！" value="" id="envDesc" name="envDesc" datatype="*1-60" nullmsg="请输入" tipmsg="格式错误!">
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row">
			<label class="form-label span4">HTTP接入URL：</label>
			<div class="formControls span6">
				<input type="text"  class="input-text grid" placeholder="http://192.168.165.220:9001/galaxy" name="url" id="url" datatype=/^(http://)(([0-9]{1,3}[.]){3})[0-9]{1,3}[:]([0-9]{1,5})[/].*$/  nullmsg="请输入" tipmsg="格式错误!">
			</div>
			<div class="span2"> </div>
		</div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>模块：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" id="module" name="module" datatype="*"  placeholder="不能为空！" nullmsg="请选择！" tipmsg="格式错误!"></select>
            </div>
            <div class="span2"> </div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>服务代码：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" id="serviceCode" name="serviceCode" datatype="*"  placeholder="不能为空！" nullmsg="请选择！" tipmsg="格式错误!"></select>
            </div>
            <div class="span2"> </div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>报文类型：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value=""  id="messageType" name="messageType" datatype="*4-4" placeholder="请输入长度为4的字符！" nullmsg="请输入！" tipmsg="格式错误!" >
            </div>
            <div class="span2"> </div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>报文代码：</label>
            <div class="formControls span6">
                <input type="text"  value="" id="messageCode" name="messageCode" class="input-text grid" datatype="*4-4" placeholder="请输入长度为4的字符！" nullmsg="请输入！" tipmsg="格式错误!">
            </div>
            <div class="span2"> </div>
        </div>
        <div class="row formBtnArea">
            <div class="span-offset-1 span10 span-offset-1 mt-10">
                <input type="submit" class="button-select L smartButton" title="添加"  value="添加">
            </div>
        </div>
    </form>
</div>
</body>
</html>