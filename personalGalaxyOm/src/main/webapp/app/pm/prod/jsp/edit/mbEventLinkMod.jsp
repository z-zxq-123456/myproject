<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表MB_EVENT_LINK修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/edit/mbEventLinkMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="mbEventLinkMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>链接事件类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="linkEventType" name="linkEventType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>关联产品类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="linkProdType" name="linkProdType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>源事件类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="origEventType" name="origEventType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>源产品类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="origProdType" name="origProdType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="prodType" name="prodType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>关联条件：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="linkCondition" id="linkCondition" name="linkCondition" datatype="*1-1,000" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>状态：</label>
                    <div class="formControls span6">
                        <select id="status" name="status" data-placeholder="状态" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="A" >A-有效</option>
                                <option value="C" >C-关闭</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
				<div class="row cl">
					<label class="form-label span4"><span class="c-red"></span>法人代码：</label>
					<div class="formControls span6">
											<select id="company" name="company"  class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
					</div>
					<div class="span2"> </div>
				</div>
        <div class="row">
            <div class="span-offset-1 span10 span-offset-1 mt-10">
                <input type="submit" class="button-select L smartButton" title="修改"  value="修改">
            </div>
        </div>
	</form>
</div>
</body>
</html>