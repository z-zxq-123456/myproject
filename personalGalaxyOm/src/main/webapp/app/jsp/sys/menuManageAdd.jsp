<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>菜单添加</title>
<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/js/sys/menuManageAdd.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-menuManage-add">
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>父级菜单：</label>
            <div class="formControls span6">
                <select class="select2" size="1" id="menuParentId" name="menuParentId" datatype="*" placeholder="不能为空！" nullmsg="请选择！" tipmsg="格式错误!" disabled=true;>
                </select>
            </div>
            <div class="span2"> </div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>菜单名：</label>
            <div class="formControls span6">
                <input type="text" class="input-text  grid" id="menuName"   name="menuName" datatype="*3-20" placeholder="请输入3-20位字符！" nullmsg="请输入！" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>菜单URL：</label>
            <div class="formControls span6">
                <input type="text" class="input-text  grid" id="menuUrl" placeholder="xxx.jsp(1-100位字符)"  name="menuUrl" datatype="*1-100"  nullmsg="请输入！" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>菜单状态:</label>
            <div class="formControls span6">
                  <select class="select2" size="1" id="menuStatus" name="menuStatus" datatype="*" disabled=true;>
                      <option value="">请选择</option>
                      <option value="Y">有效</option>
                  </select>
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
