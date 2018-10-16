<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/js/paraSetting/edit/paraFieldsColumnEdit.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-column-edit">
		<div class="row">
             <label class="form-label span4"><span class="c-red">*</span>列名：</label>
             <div class="formControls span6">
             	<input type="text" class="input-text grid" id="columnName" placeholder="请输入1-32位字符"  name="columnName" datatype="*1-32" nullmsg="请输入！" tipmsg="格式错误!" disabled=true;>
             </div>
             <div class="span2"> </div>
        </div>
		<div class="row">
             <label class="form-label span4"><span class="c-red">*</span>字段类型：</label>
             <div class="formControls span6">
             	<select class="select2" size="1" id="columnType" name="columnType" datatype="*1-16">
                   <option value="VARCHAR2">VARCHAR2 字符串型</option>
                   <option value="NUMBER">NUMBER 整数型</option>
                </select>
             </div>
        </div>
        <div class="row">
        	<label class="form-label span4"><span class="c-red">*</span>字段描述：</label>
        	<div class="formControls span6">
             	<input type="text" class="input-text grid" id="columnDesc" placeholder="请输入1-32位字符"  name="columnDesc" datatype="*1-32" nullmsg="请输入！" tipmsg="格式错误!">
            </div>
        	<div class="span2"> </div>
        </div>

        <div class="row">
            <label class="form-label span4">数据长度：</label>
            <div class="formControls span6">
             	<input type="text" class="input-text grid" id="dataLength" placeholder="请输入0-5位字符"  name="dataLength" datatype="n0-5" nullmsg="请输入！" tipmsg="格式错误!">
            </div>
            <div class="span2"> </div>
        </div>
        <div class="row">
             <label class="form-label span4">显示类型：</label>
             <div class="formControls span6">
             	<select class="select2" size="1" id="valueMethod" name="valueMethod" datatype="*0-2">
                	<option value="FD">FD 手动输入</option>
                    <option value="YN">YN 是/否</option>
                    <option value="VL">VL 固定备选数据</option>
                    <option value="RF">RF 数据来源他表</option>
                </select>
             </div>
             <div class="span2"> </div>
        </div>
		<div class="row" id="VL">
             <label class="form-label span4">固定备选：</label>
             <div class="formControls span6">
				<textarea name="" cols="" rows=""  id="valueScore" name="valueScore"   style="width:265px" placeholder="请输入0-500位字符" dragonfly="true" datatype="*0-500" nullmsg="请输入!"  tipmsg="格式错误!" onKeyUp="textarealength(this,500)" ></textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/500</p>
             </div>
             <div class="span2"> </div>
        </div>
		<div class="row" id="RF1">
             <label class="form-label span4">来自表：</label>
             <div class="formControls span6">
             	<input type="text" class="input-text grid" id="refTable" placeholder="请输入0-32位字符"  name="refTable" datatype="*0-32" nullmsg="请输入！" tipmsg="格式错误!">
             </div>
             <div class="span2"> </div>
        </div>
		<div class="row" id="RF2">
             <label class="form-label span4">来自列：</label>
             <div class="formControls span6">
             	<input type="text" class="input-text grid" id="refCol" placeholder="请输入0-100位字符"  name="refCol" datatype="*0-100" nullmsg="请输入！" tipmsg="格式错误!">
             </div>
             <div class="span2"> </div>
        </div>


		<div class="row formBtnArea">
        	<div class="span-offset-1 span10 span-offset-1 mt-10">
            	<input type="submit" class="button-select L smartButton" title="修改"  value="修改">
            </div>
        </div>

    </form>
</div>
</body>
</html>
