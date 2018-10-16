<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户添加</title>
<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/serv/serMtdinfoAdd.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-serMtdinfo-add">
	    <div class="row">
			 <label class="form-label span4"><span class="c-red">*</span>应用服务名称：</label>
			 <div class="formControls span6">
				  <select name="appSerId" id="appSerId" size="1" class="select2" style="margin-top:5px">
				  </select>
			 </div>
			 <div class="span2"></div>
		</div>
	    <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>应用服务方法中文名：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" id="serMtdCnm" placeholder="请输入1-100位字符"  name="serMtdCnm" datatype="*1-100" nullmsg="请输入！" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
               <label class="form-label span4"><span class="c-red">*</span>应用服务方法代码名：</label>
               <div class="formControls span6">
                   <input type="text" class="input-text grid" id="serMtdEnm" placeholder="请输入1-100位字符"  name="serMtdEnm" datatype="*1-100" nullmsg="请输入！" tipmsg="格式错误!">
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