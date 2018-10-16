<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户添加</title>
<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/serv/routerCondAdd.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-routerCond-add">
	    <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>条件名称：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" id="routerCondName" placeholder="请输入1-100位字符"  name="routerCondName" datatype="*1-100" nullmsg="请输入！" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
		<div class="row">
			 <label class="form-label span4"><span class="c-red">*</span>操作符：</label>
			 <div class="formControls span6">
				  <select name="routerCondOper" id="routerCondOper" size="1" class="select2" style="margin-top:5px">
				  </select>
			 </div>
			 <div class="span2"></div>
		</div>
		<div class="row">
             <label class="form-label span4"><span class="c-red">*</span>字段名称：</label>
             <div class="formControls span6">
                  <select name="routerColId" id="routerColId" size="1" class="select2" style="margin-top:5px">
                  </select>
             </div>
             <div class="span2"></div>
        </div>
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>条件值：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" id="routerCondVal" placeholder="请输入1-100位字符"  name="routerCondVal" datatype="*1-100" nullmsg="请输入！" tipmsg="格式错误!">
			</div>
			<div class="span2"></div>
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