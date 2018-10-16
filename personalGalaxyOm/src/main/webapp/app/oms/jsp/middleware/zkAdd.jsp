<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户添加</title>
<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/middleware/zkAdd.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-zkInfo-add">
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
			<label class="form-label span4"><span class="c-red">*</span>ZK实例名称：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" id="zkintName" placeholder="请输入3-20位字符"  name="zkintName" datatype="*3-20" nullmsg="请输入！" tipmsg="格式错误!">
			</div>
			<div class="span2"></div>
		</div>
	    <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>ZK实例客户端口：</label>
            <div class="formControls span6">
              	<input   onblur="checkClientPortProt()"   type="text" class="input-text grid" id="zkintClientPort" placeholder="请输入4-5位数字"  name="zkintClientPort" datatype="n4-5" nullmsg="请输入！" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>ZK实例通信端口：</label>
			<div class="formControls span6">
				<input  onblur="checkCommPort()"  type="text" class="input-text grid" id="zkintCommPort" placeholder="请输入4-5位数字"  name="zkintCommPort" datatype="n4-5" nullmsg="请输入！" tipmsg="格式错误!">
			</div>
			<div class="span2"></div>
		</div>
		 <div class="row">
            <label class="form-label span4">ZK实例选举端口：</label>
            <div class="formControls span6">
                <input onblur="checkElectPort()" type="text" class="input-text grid" placeholder="请输入4-5位数字"  id="zkintElectPort" name="zkintElectPort"  datatype="n4-5" nullmsg="请输入" tipmsg="格式错误!">
            </div>
            <div class="span2"> </div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>ZK实例节点数：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" id="zkintNodeNum" placeholder="请输入1-5位数字"  name="zkintNodeNum" datatype="*1-5" nullmsg="请输入！" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>ZK实例描述：</label>
            <div class="formControls span6">
                 <textarea name="" cols="" rows=""  id="zkintDesc" name="zkintDesc"   style="width:315px" placeholder="请输入0-50位字符" dragonfly="true" datatype="*1-50" nullmsg="请输入!"  tipmsg="格式错误!" onKeyUp="textarealength(this,50)" ></textarea>
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
</div>s
</body>
</html>