<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户添加</title>
<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/middleware/midwareVerEdit.js"></script>
</head>
<body>
<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="form-midwareVer-edit">
    		<div class="row">
    			 <label class="form-label span4"><span class="c-red">*</span>中间件类型：</label>
    			 <div class="formControls span6">
    				  <select name="queryMidwareType" id="queryMidwareType" size="1" class="select2" style="margin-top:5px" disabled>
    				  </select>
    			 </div>
    			 <div class="span2"></div>
    		</div>
    		<div class="row">
    			<label class="form-label span4">版本号：</label>
    			<div class="formControls span6">
    				<input type="text" class="input-text grid" id="midwareVerNo" placeholder="请输入3-20位字符"  name="midwareVerNo" datatype="*1-20" nullmsg="请输入！" tipmsg="格式错误!">
    			</div>
    			<div class="span2"></div>
    		</div>
            <div class="row">
                <label class="form-label span4"><span class="c-red">*</span>版本保存路径：</label>
                <div class="formControls span6">
                    <input type="text" class="input-text grid" id="midwareVerPath" placeholder="请输入3-20位字符"  name="midwareVerPath" datatype="*3-20" nullmsg="请输入！" tipmsg="格式错误!" disabled>
                </div>
                <div class="span2"></div>
            </div>
            <div class="row">
                <label class="form-label span4"><span class="c-red">*</span>版本创建日期：</label>
                <div class="formControls span6">
                    <input type="text" class="input-text grid" id="midwareVerDate" placeholder="请输入3-20位字符"  name="midwareVerDate" datatype="*3-20" nullmsg="请输入！" tipmsg="格式错误!" disabled>
                </div>
                <div class="span2"></div>
            </div>
    		<div class="row">
    			<label class="form-label span4">版本描述：</label>
    			<div class="formControls span6">
    				 <textarea name="" cols="" rows=""  id="midwareVerDesc" name="midwareVerDesc"   style="width:265px" placeholder="请输入2-50位字符" dragonfly="true" datatype="*2-50" nullmsg="请输入!"  tipmsg="格式错误!" onKeyUp="textarealength(this,50)" ></textarea>
    						<p class="textarea-numberbar"><em class="textarea-length">0</em>/50</p>
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