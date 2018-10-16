<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表MB_COMMISSION_PARAMETER修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/mbCommissionParameterMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="mbCommissionParameterMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>客户分类：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="clientType" name="clientType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>交易编号：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="programId" name="programId" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>状态：</label>
                    <div class="formControls span6">
                        <select id="status" name="status"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="A" >A-有效</option>
                                <option value="C" >C-关闭</option>
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
