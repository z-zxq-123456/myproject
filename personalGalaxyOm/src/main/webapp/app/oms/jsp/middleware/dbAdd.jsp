<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户添加</title>
<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/middleware/dbAdd.js"></script>
</head>
<body>
<div class="pd-20" >
	<form action="" method="post" class="form form-horizontal" id="form-dbInfo-add">
	    <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>集群名称：</label>
            <div class="formControls span6">
               <select name="midwareId" id="midwareId" size="1" class="select2" style="margin-top:5px">
               </select>
            </div>
            <div class="span2"></div>
        </div>
		<div class="row">
			 <label class="form-label span4"><span class="c-red">*</span>服务器名称：</label>
			 <div class="formControls span6">
				  <select name="serId" id="serId" size="1" class="select2" style="margin-top:5px">
				  </select>
			 </div>
			 <div class="span2"></div>
		</div>
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>DB实例名称：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" id="dbintName" placeholder="请输入1-20位字符"  name="dbintName" datatype="s1-20" nullmsg="请输入！" tipmsg="格式错误!">
			</div>
			<div class="span2"></div>
		</div>
	    <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>数据库类型：</label>
            <div class="formControls span6">
                <select name="dbType" id="dbType" size="1" class="select2" style="margin-top:5px">
              	</select>
            </div>
            <div class="span2"></div>
        </div>
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>DB用户名：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" id="dbintUserName" placeholder="请输入1-20位字符"  name="dbintUserName" datatype="s1-20" nullmsg="请输入！" tipmsg="格式错误!">
			</div>
			<div class="span2"></div>
		</div>
		 <div class="row">
            <label class="form-label span4">DB密码：</label>
            <div class="formControls span6">
                <input type="password" class="input-text grid" placeholder="新密码" value="" id="dbintUserPwd" name="dbintUserPwd"  datatype="*1-20" nullmsg="请输入">
            </div>
            <div class="span2"> </div>
        </div>
        <div class="row">
            <label class="form-label span4">确认密码：</label>
            <div class="formControls span6">
                <input type="password" class="input-text grid" placeholder="确认新密码" value="" id="password" name="password" datatype="*1-20" nullmsg="请输入"  errormsg="输入密码要一致" recheck="dbintUserPwd">
            </div>
            <div class="span2"> </div>
        </div>
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>数据库端口：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" id="dbintPort" placeholder="请输入1-5位数字"  name="dbintPort" datatype="n1-5" nullmsg="请输入！" tipmsg="格式错误!">
			</div>
			<div class="span2"></div>
		</div>
		<div class="row">
            <label class="form-label span4"><span class="c-red">*</span>数据库服务名：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" id="dbintServiceName" placeholder="请输入1-5位字符"  name="dbintServiceName" datatype="s1-50" nullmsg="请输入！" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>DB实例标志：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" id="dbintNodeNum" placeholder="请输入1-10位字符"  name="dbintNodeNum" datatype="*1-10" nullmsg="请输入！" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>DB描述：</label>
            <div class="formControls span6">
                 <textarea name="" cols="" rows=""  id="dbintDesc" name="dbintDesc"   style="width:265px" placeholder="请输入0-50位字符" dragonfly="true" datatype="*1-50" nullmsg="请输入!"  tipmsg="格式错误!" onKeyUp="textarealength(this,50)" ></textarea>
                        <p class="textarea-numberbar"><em class="textarea-length">0</em>/50</p>
            </div>
            <div class="span2"> </div>
        </div>
		<div class="row ">
			<div class="span-offset-1 span10 span-offset-1 mt-10">
				<input type="submit" class="button-select L smartButton" title="添加"  value="添加">
			</div>
		</div>
    </form>s
</div>
</body>
</html>