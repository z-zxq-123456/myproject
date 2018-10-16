<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>kafka实例添加</title>
<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/middleware/midwareKafkaAdd.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-kafkaInfo-add">
	    <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>集群名称：</label>
            <div class="formControls span6">
                <select name="midwareId" id="midwareId" size="1"datatype="*1-50" class="select2" style="margin-top:5px">
                				  </select>
            </div>
            <div class="span2"></div>
        </div>
		<div class="row">
			 <label class="form-label span4"><span class="c-red">*</span>服务器名称：</label>
			 <div class="formControls span6">
				  <select name="serId" id="serId" size="1" datatype="*1-50"class="select2" style="margin-top:5px">
				  </select>
			 </div>
			 <div class="span2"></div>
		</div>
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>Kafka实例名称：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" id="kafkaintName" placeholder="请输入1-200位字符"  name="kafkaintName" datatype="s1-200" nullmsg="请输入！" tipmsg="格式错误!">
			</div>
			<div class="span2"></div>
		</div>
		<div class="row">
			<label class="form-label span4"><span class="c-red">*</span>Kafka实例端口：</label>
			<div class="formControls span6">
				<input   onblur = "checkPort()" type="text" class="input-text grid" id="kafkaintPort" placeholder="请输入4-5位数字"  name="kafkaintPort" datatype="n4-5" nullmsg="请输入！" tipmsg="格式错误!">
			</div>
			<div class="span2"></div>
		</div>
        <div class="row">
            <label class="form-label span4">Kafka实例描述：</label>
            <div class="formControls span6">
                 <textarea name="" cols="" rows=""  id="kafkaintDesc" name="kafkaintDesc"   style="width:315px" placeholder="请输入0-50位字符" dragonfly="true" datatype="*0-50" nullmsg="请输入!"  tipmsg="格式错误!" onKeyUp="textarealength(this,50)" ></textarea>
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