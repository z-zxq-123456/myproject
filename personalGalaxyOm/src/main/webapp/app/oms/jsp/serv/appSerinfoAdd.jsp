<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户添加</title>
<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/serv/appSerinfoAdd.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-appSerinfo-add">
	    <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>应用服务名称：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" id="appSerName" placeholder="请输入3-20位字符"  name="appSerName" datatype="*3-20" nullmsg="请输入！" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
               <label class="form-label span4"><span class="c-red">*</span>应用服务类名：</label>
               <div class="formControls span6">
                   <input type="text" class="input-text grid" id="appSerClsnm" placeholder="请输入3-100位字符"  name="appSerClsnm" datatype="*3-100" nullmsg="请输入！" tipmsg="格式错误!">
               </div>
               <div class="span2"></div>
        </div>
		<div class="row">
			 <label class="form-label span4"><span class="c-red">*</span>应用服务类型：</label>
			 <div class="formControls span6">
				  <select name="appSerType" id="appSerType" size="1" class="select2" style="margin-top:5px">
				  </select>
			 </div>
			 <div class="span2"></div>
		</div>
	    <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>应用服务描述：</label>
            <div class="formControls span6">
                 <textarea name="" cols="" rows=""  id="appSerDesc" name="appSerDesc"   style="width:265px" placeholder="请输入0-50位字符" dragonfly="true" datatype="*1-50" nullmsg="请输入!"  tipmsg="格式错误!" onKeyUp="textarealength(this,50)" ></textarea>
                        <p class="textarea-numberbar"><em class="textarea-length">0</em>/50</p>
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