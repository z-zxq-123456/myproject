<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户添加</title>
<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/serv/servRuleEdit.js"></script>
</head>
<body>
<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="form-servRule-edit">
        	   <div class="row">
				   <label class="form-label span4"><span class="c-red">*</span>路由规则类型：</label>
				   <div class="formControls span6">
						<select name="servRuleTypeName" id="servRuleTypeName" size="1" class="select2" style="margin-top:5px" disabled="true">
						</select>
				   </div>
				   <div class="span2"></div>
			   </div>
			   <div class="row">
					  <label class="form-label span4"><span class="c-red">*</span>方法名称：</label>
					  <div class="formControls span6">
						  <select name="serMtdId" id="serMtdId" size="1" class="select2" style="margin-top:5px">
						  </select>
					  </div>
					  <div class="span2"></div>
			   </div>
			  <div class="row">
					 <label class="form-label span4"><span class="c-red">*</span>规则状态：</label>
					 <div class="formControls span6">
						  <select name="ruleStatus" id="ruleStatus" size="1" class="select2" style="margin-top:5px">
						  </select>
					 </div>
					 <div class="span2"></div>
				</div>
			<div class="row" id="condDiv">
				   <label class="form-label span4" id="condLabel"><span class="c-red">*</span>条件名称：</label>
				   <div class="formControls span6">
					   <select name="routerCondId" id="routerCondId" size="1" class="select2" style="margin-top:5px">
					   </select>
				   </div>
				   <div class="span2"></div>
			</div>
			<div class="row" id="posDiv">
				<label class="form-label span4" id="argsPosLabel"><span class="c-red">*</span>路由参数位置:</label>
				<div class="formControls span6">
					 <input type="text" class="input-text grid" id="routerArgsPos" placeholder="请输入0-20位字符"  name="routerArgsPos" datatype="*0-20" nullmsg="请输入！" tipmsg="格式错误!">
				</div>
				<div class="span2"></div>
			</div>
			<div class="row" id="typeDiv">
				 <label class="form-label span4" id="argsTypeLabel"><span class="c-red">*</span>路由参数类型：</label>
				 <div class="formControls span6">
					  <select name="routerArgsType" id="routerArgsType" size="1" class="select2" style="margin-top:5px">
					  </select>
				 </div>
				 <div class="span2"></div>
			</div>
			<div class="row" id="selfDiv">
				 <label class="form-label span4" id="ruleSelfLabel"><span class="c-red">*</span>自定义规则：</label>
				 <div class="formControls span6">
					 <textarea name="" cols="" rows="" style="width:265px" id="servRuleSelf" name="servRuleSelf" dragonfly="true" datatype="*0-50"  placeholder="请输入不多于50位字符！" nullmsg="请输入！" tipmsg="格式错误!" onKeyUp="textarealength(this,50)" ></textarea>
					 <p class="textarea-numberbar"><em class="textarea-length">0</em>/50</p>
				 </div>
				 <div class="span2"></div>
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